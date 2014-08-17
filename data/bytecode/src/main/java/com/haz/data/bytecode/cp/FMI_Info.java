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
@BindType(key=CP_Info.CONSTANT_FMI)
public class FMI_Info implements Info {

  @Bind(codec = "unsignedshort")
  private int classIndex;
  @Bind(codec = "unsignedshort")
  private int nameAndTypeIndex;
}