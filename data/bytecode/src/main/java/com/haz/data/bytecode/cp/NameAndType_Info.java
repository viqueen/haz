/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.bytecode.cp;

import com.haz.data.bytecode.cp.CP_Info.Info;
import com.haz.data.codec.annotation.Bind;
import com.haz.data.codec.annotation.BindType;

/**
 * @author hasnaer
 *
 */
@BindType(key=CP_Info.CONSTANT_NameAndType)
public class NameAndType_Info implements Info {
  @Bind(codec = "unsignedshort")
  private int nameIndex;
  @Bind(codec = "unsignedshort")
  private int descritorIndex;  
}