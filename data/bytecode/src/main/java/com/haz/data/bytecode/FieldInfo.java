/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.bytecode;

import com.haz.data.bytecode.att.Attribute;
import com.haz.data.codec.annotation.Bind;

/**
 * @author hasnaer
 *
 */
public class FieldInfo {

  @Bind(codec = "unsignedshort")
  private int         accessFlags;
  @Bind(codec = "unsignedshort")
  private int         nameIndex;
  @Bind(codec = "unsignedshort")
  private int         descriptorIndex;
  @Bind(codec = "unsignedshort")
  private int         attributesCount;
  @Bind(count = "$attributesCount")
  private Attribute[] attributes;

}