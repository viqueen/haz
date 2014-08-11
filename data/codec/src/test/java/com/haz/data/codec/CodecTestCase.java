/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
  private DataInputStream inputStream;

  @Before
  public void setUp() throws Exception {
    dimensionCodec = Factory.create(Dimension.class);
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
    Optional<Dimension> dim = dimensionCodec.decode(inputStream);
    
    Assert.assertTrue(dim.isPresent());
    Assert.assertEquals(20, dim.get().width);
    Assert.assertEquals(30, dim.get().height);
    Assert.assertEquals("square", dim.get().name);
  }

  public static class Dimension {
    @Bind
    private int width;
    @Bind
    private int height;
    @Bind
    private String name;
    
    public Dimension () {
      
    }
  }
}