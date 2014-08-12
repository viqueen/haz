/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.util.stream.Stream;

import org.apache.log4j.Logger;

/**
 * @author hasnaer
 *
 */
abstract class AbstractCodec<T> implements Codec<T> {

  protected final Logger LOG = Logger.getLogger(getClass());

  protected final String uri;
  protected final Class<T> type;

  public AbstractCodec(String pUri, Class<T> pType) {
    uri = pUri;
    type = pType;
  }

  @Override
  public Stream<String> uris() {
    return Stream.of(uri);
  }

}
