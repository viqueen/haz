/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

/**
 * @author hasnaer
 *
 */
public interface Operator<T> extends Token {
  
  default Type type() {
    return Type.OPERATOR;
  }

  int precedence();

  public T apply(T pLeft, T pRight);
}