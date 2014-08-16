/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec.binding;

import java.lang.reflect.Field;

/**
 * @author hasnaer
 *
 */
abstract class AbstractBinding implements Binding {

  private final Field field;
  
  public AbstractBinding (Field pField) {
    field = pField;
  }
  
  public Field field () {
    return field;
  }
  
  @Override
  public String toString () {
    return String.format("{name:%s ; type: %s}", field.getName(), type());
  }
}