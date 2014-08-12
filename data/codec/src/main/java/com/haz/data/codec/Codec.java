/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author hasnaer
 *
 */
public interface Codec<T> {

  default Optional<T> decode(DataInputStream pInput) throws IOException {
    return decode(pInput, new Context());
  }

  public Optional<T> decode(DataInputStream pInput, Context pContext)
      throws IOException;

  public void encode(T pData, DataOutputStream pOutput) throws IOException;

  public Stream<String> uris();
  
}