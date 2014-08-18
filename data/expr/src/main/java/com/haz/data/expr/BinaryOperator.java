/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

/**
 * @author hasnaer
 *
 */
public interface BinaryOperator<T> extends Operator {
  public T apply(T pLeft, T pRight);
}
