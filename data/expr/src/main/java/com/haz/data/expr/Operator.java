/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

/**
 * @author hasnaer
 *
 */
public interface Operator extends Token {
  
  default Type type() {
    return Type.OPERATOR;
  }

  int precedence();

}