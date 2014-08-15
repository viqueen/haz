/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

/**
 * @author hasnaer
 *
 */
interface Token {

  Type type();

  static enum Type {
    VALUE,OPERATOR,GROUPING;
  }
}