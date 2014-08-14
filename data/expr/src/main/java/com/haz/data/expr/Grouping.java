/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

/**
 * @author hasnaer
 *
 */
public interface Grouping extends Token {
  @Override
  default boolean isOperator() {
    return false;
  }

  @Override
  default boolean isGrouping() {
    return true;
  }

  boolean opening();
}