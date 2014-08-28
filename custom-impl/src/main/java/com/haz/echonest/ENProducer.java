/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.echonest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.echonest.api.v4.Artist;
import com.echonest.api.v4.Biography;
import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.haz.data.pc.Channel;
import com.haz.data.pc.impl.DataProducer;

/**
 * @author hasnaer
 *
 */
public class ENProducer extends DataProducer {

  private final EchoNestAPI api;
  private final Set<String> processedArtists;
  private final List<Artist> artists;

  /**
   * @param pOutputChannel
   */
  public ENProducer(Channel pOutputChannel, String pApiKey) {
    super(pOutputChannel);
    api = new EchoNestAPI(pApiKey);
    processedArtists = new HashSet<>();
    artists = new ArrayList<>();
  }

  @Override
  public Stream<Optional<?>> offer() {
    List<Optional<?>> data = new ArrayList<>();
    Artist artist;
    if ((artist = artists.isEmpty() ? null : artists.remove(0)) != null
        && !processedArtists.contains(artist.getID())) {
      try {
        String name = artist.getName();
        extract(artist.getImages(), data);
        extract(artist.getReviews(), data);
        extract(artist.getNews(), data);
        extract(
            artist.getBiographies().stream().map(bio -> new Bio(name, bio)),
            data);
        extract(artist.getVideos(), data);
        extract(artist.getBlogs(), data);
        extract(artist.getSongs(), data);
        artists.addAll(artist.getSimilar(100));
      } catch (Exception e) {
        log.error(e.getMessage(), e);
      } finally {
        processedArtists.add(artist.getID());
      }
    }
    return data.stream();
  }

  private <T> void extract(List<T> pList, List<Optional<?>> pData) {
    pList.forEach(item -> pData.add(Optional.of(item)));
  }

  private <T> void extract(Stream<T> pStream, List<Optional<?>> pData) {
    pStream.forEach(item -> pData.add(Optional.of(item)));
  }

  @Override
  public boolean canProduce() {
    return !artists.isEmpty();
  }

  @Override
  public boolean eoc() {
    return true;
  }

  @Override
  public void setUp() {
    try {
      artists.addAll(api.topHotArtists(1000));
    } catch (EchoNestException e) {
      log.error(e.getMessage(), e);
    }
  }

  @Override
  public void wrapUp() {

  }

  public class Bio {
    private final String artistName;
    private final Biography bio;

    public Bio(String pArtistName, Biography pBio) {
      artistName = pArtistName;
      bio = pBio;
    }

    public String getName() {
      return artistName;
    }

    public Biography getBio() {
      return bio;
    }
  }
}