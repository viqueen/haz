/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

/**
 * @author hasnaer
 *
 */
public interface Grouping extends Operator {

  @Override
  default Type type() {
    return Type.GROUPING;
  }

  boolean opening();
  
}