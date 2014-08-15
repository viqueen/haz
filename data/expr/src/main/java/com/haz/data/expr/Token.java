/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.expr;

/**
 * @author hasnaer
 *
 */
interface Token {

  boolean isValue();
  
  boolean isOperator();

  boolean isGrouping();

}
