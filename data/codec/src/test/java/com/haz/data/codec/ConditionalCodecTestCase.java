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
import com.haz.data.codec.annotation.BindSelect;
import com.haz.data.codec.annotation.BindType;

import static org.junit.Assert.*;

/**
 * @author hasnaer
 *
 */
public class ConditionalCodecTestCase {

  private Codec<Info> infoCodec;
  private DataInputStream infoStream;
  
  @Before
  public void setUp() throws IOException {
    infoCodec = Factory.create(Info.class);
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(bout);
    out.writeInt(1);
    out.writeUTF("hasnae");
    out.writeUTF("rehioui");
    
    infoStream = new DataInputStream(new ByteArrayInputStream(bout.toByteArray()));
  }
  
  @Test
  public void testConditionalCodec () throws IOException {
    Optional<Info> info = infoCodec.decode(infoStream);
    assertTrue(info.isPresent());
    assertEquals(1, info.get().tag);
    assertEquals("hasnae", ((Contact)info.get().data).firstName);
    assertEquals("rehioui", ((Contact)info.get().data).lastName);
  }
  
  
  public static class Info {
    @Bind
    int tag;
    @BindSelect(keyExpr="$tag", types={Contact.class})
    Data data;
  }
  
  public static interface Data {}
  
  @BindType(key="1")
  public static class Contact implements Data {
    @Bind 
    String firstName;
    @Bind
    String lastName;
  }
}