/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author hasnaer
 *
 */
public class Factory {

  @SuppressWarnings("serial")
  private static final Set<Codec<?>> primitiveCodecs = new HashSet<Codec<?>>() {
    {
      add(new IntegerCodec());
      add(new DoubleCodec());
      add(new UnsignedShortCodec());
      add(new ByteCodec());
      add(new UTFCodec());
    }
  };

  @SuppressWarnings("serial")
  public static final Map<String, Codec<?>> codecs = new HashMap<String, Codec<?>>() {
    {
      primitiveCodecs.forEach(codec -> codec.uris().forEach(
          uri -> put(uri, codec)));
    }
  };

  @SuppressWarnings("unchecked")
  public static <T> Codec<T> create(Class<T> pType) {
    // check if not already created
    String codecUri = pType.getCanonicalName();
    if (codecs.containsKey(codecUri)) {
      return (Codec<T>) codecs.get(codecUri);
    }    
    Codec<T> codec = pType.isArray() ? new ArrayCodec<T>(codecUri, pType) : new DefaultCodec<T>(codecUri, pType);    
    codecs.put(codecUri, codec);
    return codec;
  }

  public static Optional<Codec<?>> get(String pURI) {
    return codecs.containsKey(pURI) ? Optional.of(codecs.get(pURI)) : Optional
        .empty();
  }

  private static class IntegerCodec implements Codec<Integer> {
    @Override
    public Optional<Integer> decode(DataInputStream pInput, Context pContext)
        throws IOException {
      return Optional.of(pInput.readInt());
    }

    @Override
    public void encode(Integer pData, DataOutputStream pOutput)
        throws IOException {
      pOutput.writeInt(pData);
    }

    @Override
    public Stream<String> uris() {
      return Stream.of("int", "java.lang.Integer");
    }
  }

  private static class DoubleCodec implements Codec<Double> {
    @Override
    public Optional<Double> decode(DataInputStream pInput, Context pContext)
        throws IOException {
      return Optional.of(pInput.readDouble());
    }

    @Override
    public void encode(Double pData, DataOutputStream pOutput)
        throws IOException {
      pOutput.writeDouble(pData);
    }

    @Override
    public Stream<String> uris() {
      return Stream.of("double", "java.lang.Double");
    }
  }

  private static class UnsignedShortCodec implements Codec<Integer> {
    @Override
    public Optional<Integer> decode(DataInputStream pInput, Context pContext)
        throws IOException {
      return Optional.of(pInput.readUnsignedShort());
    }

    @Override
    public void encode(Integer pData, DataOutputStream pOutput)
        throws IOException {
      pOutput.writeShort(pData);
    }

    @Override
    public Stream<String> uris() {
      return Stream.of("unsignedshort");
    }
  }

  private static class ByteCodec implements Codec<Byte> {
    @Override
    public Optional<Byte> decode(DataInputStream pInput, Context pContext)
        throws IOException {
      return Optional.of(pInput.readByte());
    }

    @Override
    public void encode(Byte pData, DataOutputStream pOutput) throws IOException {
      pOutput.writeByte(pData);
    }

    @Override
    public Stream<String> uris() {
      return Stream.of("byte", "java.lang.Byte");
    }
  }

  private static class UTFCodec implements Codec<String> {
    public Optional<String> decode(DataInputStream pInput, Context pContext)
        throws IOException {
      return Optional.of(pInput.readUTF());
    }

    @Override
    public void encode(String pData, DataOutputStream pOutput)
        throws IOException {
      pOutput.writeUTF(pData);
    }

    @Override
    public Stream<String> uris() {
      return Stream.of("java.lang.String");
    }
  }
}