/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.codec;

import java.lang.reflect.Field;


/**
 * @author hasnaer
 *
 */
public class Binding {
  // public final, just to avoid get/set , guaranteed not to be reset
  public final Field field;
  public final String codecURI;
  public final String count;
  public Binding(Field pField, String pCodecURI, String pCount) {
    field = pField;
    codecURI = pCodecURI;
    count = pCount;
  }

}