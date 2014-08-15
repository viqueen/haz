/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

/**
 * @author hasnaer
 *
 */
public class Value<T> implements Token {

  protected final T value;

  public Value(T pValue) {
    value = pValue;
  }

  @Override
  public final Type type() {
    return Type.VALUE;
  }
}
