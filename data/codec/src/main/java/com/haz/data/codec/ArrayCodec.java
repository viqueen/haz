/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Optional;

/**
 * @author hasnaer
 *
 */
public class ArrayCodec<T> extends AbstractCodec<T> {

  protected final String ARRAY_LENGTH_KEY = String.format("%s.length",
                                              type.getCanonicalName());
  protected final String SUBCODEC_KEY = String.format("%s.subCodec", type.getCanonicalName());

  public ArrayCodec(String pUri, Class<T> pType) {
    super(pUri, pType);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Optional<T> decode(DataInputStream pInput, Context pContext)
      throws IOException {
    int length = getLength(pContext);
    T result = (T) Array.newInstance(type.getComponentType(), length);
    Optional<?> subCodec = pContext.get(SUBCODEC_KEY);
    Codec<?> componentCodec;
    if (subCodec.isPresent()) {
      componentCodec = (Codec<?>) subCodec.get();
    }
    else {
      componentCodec = Factory.create(type.getComponentType());
    }
    for (int i = 0; i < length; i++) {
      Array.set(result, i, componentCodec.decode(pInput, pContext).get());
    }
    return Optional.of(result);
  }

  @Override
  public void encode(T pData, DataOutputStream pOutput) throws IOException {
    // TODO Auto-generated method stub
  }

  private int getLength(Context pContext) throws IOException {
    Optional<?> length = pContext.get(ARRAY_LENGTH_KEY);
    if (length.isPresent()) {
      return Factory.exprEvaluator
          .eval(expandExpression(length.get().toString(), pContext)).get()
          .intValue();
    }
    length.orElseThrow(IOException::new);
    return -1;
  }

}