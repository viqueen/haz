/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec.binding;

import java.lang.reflect.Field;

/**
 * @author hasnaer
 *
 */
public interface Binding {

  Type type();
  
  Field field ();
  
  enum Type {
    SIMPLE, SELECTION;
  }
}