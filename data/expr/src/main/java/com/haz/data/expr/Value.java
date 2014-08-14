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
  
  public Value (T pValue) {
    value = pValue;
  }
  
  @Override
  public final boolean isValue() {
    return true;
  }

  @Override
  public final boolean isOperator() {
    return false;
  }

  @Override
  public final boolean isGrouping() {
    return false;
  }

}
