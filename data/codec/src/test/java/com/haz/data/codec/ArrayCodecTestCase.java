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

import org.junit.Before;
import org.junit.Test;

import com.haz.data.codec.annotation.Bind;

/**
 * @author hasnaer
 *
 */
public class ArrayCodecTestCase {

  private Codec<Data> dataCodec;
  private DataInputStream dataStream;

  @Before
  public void setUp() throws IOException {
    dataCodec = Factory.create(Data.class);

    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    DataOutputStream dout = new DataOutputStream(bout);

    dout.writeShort(10);
    dataStream = new DataInputStream(new ByteArrayInputStream(
        bout.toByteArray()));

  }

  @Test
  public void testArrayWithSubCodec() throws IOException {
    Optional<Data> data = dataCodec.decode(dataStream);
  }

  public static class Data {
    @Bind(subCodec = "unsignedshort", count = "1")
    private int[] arrayOfUnsignedShorts;
  }
}