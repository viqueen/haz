/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

/**
 * @author hasnaer
 *
 */
public interface Operator<T> extends Token {
  @Override
  default boolean isValue() {
    return false;
  }
  @Override
  default boolean isOperator() {
    return true;
  }

  @Override
  default boolean isGrouping() {
    return false;
  }

  int precedence();

  public T apply (T pLeft, T pRight);
}