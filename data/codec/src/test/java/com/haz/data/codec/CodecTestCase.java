/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.haz.data.codec.annotation.Bind;

/**
 * @author hasnaer
 *
 */
public class CodecTestCase {

  private Codec<Dimension> dimensionCodec;
  private Codec<Space> spaceCodec;
  private Codec<Universe> universeCodec;
  private DataInputStream inputStream;

  @Before
  public void setUp() throws Exception {

    dimensionCodec = Factory.create(Dimension.class);
    spaceCodec = Factory.create(Space.class);
    universeCodec = Factory.create(Universe.class);
    
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(bout);
    out.writeInt(20);
    out.writeInt(30);
    out.writeUTF("square");
    inputStream = new DataInputStream(new ByteArrayInputStream(
        bout.toByteArray()));

  }

  @Test
  public void testDecode() throws IOException {
    Optional<Dimension> dimension = dimensionCodec.decode(inputStream);

    Assert.assertTrue(dimension.isPresent());
    Assert.assertEquals(20, dimension.get().width);
    Assert.assertEquals(30, dimension.get().height);
    Assert.assertEquals("square", dimension.get().name);
  }

  @Test
  public void testDecodeArray() throws IOException {
    Optional<Space> space = spaceCodec.decode(inputStream);
    Assert.assertTrue(space.isPresent());
    Assert.assertEquals(1, space.get().dimensions.length);
    Dimension[] dimensions = space.get().dimensions;
    Assert.assertEquals(20, dimensions[0].width);
    Assert.assertEquals(30, dimensions[0].height);
    Assert.assertEquals("square", dimensions[0].name);
  }

  @Test
  public void testDecodeList() throws IOException {
    Optional<Universe> universe = universeCodec.decode(inputStream);
    Assert.assertTrue(universe.isPresent());
    Assert.assertEquals(1, universe.get().dimensions.size());
    List<Dimension> dimensions = universe.get().dimensions;
    Assert.assertEquals(20, dimensions.get(0).width);
    Assert.assertEquals(30, dimensions.get(0).height);
    Assert.assertEquals("square", dimensions.get(0).name);
  }

  public static class Dimension {
    @Bind
    private int width;
    @Bind
    private int height;
    @Bind
    private String name;

    public Dimension() {

    }
  }

  public static class Space {
    @Bind(count = "1")
    private Dimension[] dimensions;
  }

  public static class Universe {
    @Bind(count = "1")
    private List<Dimension> dimensions;
  }
}