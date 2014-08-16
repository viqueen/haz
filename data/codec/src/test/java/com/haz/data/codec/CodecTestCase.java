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

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.haz.data.codec.annotation.Bind;

/**
 * @author hasnaer
 *
 */
public class CodecTestCase {

  private Codec<Dimension>    dimensionCodec;
  private Codec<Space>        spaceCodec;
  private Codec<Data>         dataCodec;
  private Codec<Child>        childCodec;
  private Codec<IntegerValue> integerValueCodec;

  private DataInputStream     inputStream;
  private DataInputStream     dataStream;

  @Before
  public void setUp() throws Exception {

    dimensionCodec = Factory.create(Dimension.class);
    spaceCodec = Factory.create(Space.class);
    dataCodec = Factory.create(Data.class);
    childCodec = Factory.create(Child.class);
    integerValueCodec = Factory.create(IntegerValue.class);

    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(bout);
    out.writeInt(20);
    out.writeInt(30);
    out.writeUTF("square");

    inputStream = new DataInputStream(new ByteArrayInputStream(
        bout.toByteArray()));

    bout = new ByteArrayOutputStream();
    out = new DataOutputStream(bout);
    out.writeInt(1);
    out.writeInt(1);
    out.writeInt(1);
    out.writeInt(1);
    out.writeInt(1);

    dataStream = new DataInputStream(new ByteArrayInputStream(
        bout.toByteArray()));
  }
  
  @Test
  public void testDecode() throws IOException {
    Optional<Dimension> dimension = dimensionCodec.decode(inputStream);
    assertTrue(dimension.isPresent());
    assertEquals(20, dimension.get().width);
    assertEquals(30, dimension.get().height);
    assertEquals("square", dimension.get().name);
  }

  @Test
  public void testDecodeArray() throws IOException {
    Optional<Space> space = spaceCodec.decode(inputStream);
    assertTrue(space.isPresent());
    assertEquals(1, space.get().dimensions.length);
    Dimension[] dimensions = space.get().dimensions;
    assertEquals(20, dimensions[0].width);
    assertEquals(30, dimensions[0].height);
    assertEquals("square", dimensions[0].name);
  }

  @Test
  public void testDecodeArrayWithExpr() throws IOException {
    Optional<Data> data = dataCodec.decode(dataStream);
    assertTrue(data.isPresent());
    assertEquals(4, data.get().entries.length);
    assertArrayEquals(new int[] { 1, 1, 1, 1 }, data.get().entries);
  }

  @Test
  public void testDecodeWithInheritence() throws IOException {
    Optional<Child> child = childCodec.decode(inputStream);
    assertTrue(child.isPresent());
    assertEquals(20, child.get().twenty);
    assertEquals(30, child.get().thirty);
  }

  @Test
  public void testDecodeWithInheritenceAndGenerics() throws IOException {
    Optional<IntegerValue> integerValue = integerValueCodec.decode(inputStream);
    assertTrue(integerValue.isPresent());
    assertEquals(20, integerValue.get().value.intValue());
    assertEquals(30, integerValue.get().anotherValue);
  }

  public static class Dimension {
    @Bind
    private int    width;
    @Bind
    private int    height;
    @Bind
    private String name;

    public Dimension() {

    }
  }

  public static class Space {
    @Bind(count = "1")
    private Dimension[] dimensions;
  }

  public static class Data {
    @Bind
    private int   count;

    @Bind(count = "($count + 1) * 2")
    private int[] entries;
  }

  public static class Baby {
    @Bind
    int twenty;
  }

  public static class Child extends Baby {
    @Bind
    int thirty;
  }

  public static class NumberValue<T> {
    @Bind
    T value;
  }

  public static class IntegerValue extends NumberValue<Integer> {
    @Bind
    int anotherValue;
  }
}