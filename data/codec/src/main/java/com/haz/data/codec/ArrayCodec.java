/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hasnaer
 *
 */
public class ArrayCodec<T> extends AbstractCodec<T> {

  protected final String ARRAY_LENGTH_KEY = String.format("%s.length",
                                              type.getCanonicalName());
  private final Codec<?> componentCodec;

  public ArrayCodec(String pUri, Class<T> pType) {
    super(pUri, pType);
    componentCodec = Factory.create(pType.getComponentType());
  }

  @SuppressWarnings("unchecked")
  @Override
  public Optional<T> decode(DataInputStream pInput, Context pContext)
      throws IOException {
    int length = getLength(pContext);
    T result = (T) Array.newInstance(type.getComponentType(), length);
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

  // yuk ugly, must extract this into its own resolver
  private String expandExpression(String pExpr, Context pContext) {
    Pattern variable = Pattern.compile("(?<VAR>\\$(?<NAME>[a-zA-Z]+))");
    Matcher matcher = variable.matcher(pExpr);
    StringBuffer buffer = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(buffer, pContext.get(matcher.group("NAME"))
          .get().toString());
    }
    matcher.appendTail(buffer);
    return buffer.toString();
  }

}