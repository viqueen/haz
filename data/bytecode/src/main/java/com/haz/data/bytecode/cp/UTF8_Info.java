/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.bytecode.cp;

import com.haz.data.codec.annotation.Bind;

/**
 * @author hasnaer
 *
 */
public class UTF8_Info {
  @Bind(codec = "unsignedshort")
  private int    length;
  @Bind
  private String value;
}
