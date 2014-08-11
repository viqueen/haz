/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author hasnaer
 *
 */
public interface Codec<T> {
  public Optional<T> decode(DataInputStream pInput) throws IOException;

  public void encode(T pData, DataOutputStream pOutput) throws IOException;

  public Stream<URI> uris();
}