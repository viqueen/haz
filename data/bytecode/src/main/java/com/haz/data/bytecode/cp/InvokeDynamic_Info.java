/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.bytecode.cp;

import com.haz.data.bytecode.cp.CP_Info.Info;
import com.haz.data.codec.annotation.Bind;

/**
 * @author hasnaer
 *
 */
public class InvokeDynamic_Info implements Info {
  @Bind(codec="unsignedshort")
  private int bootstrapMethodAttrIndex;
  @Bind(codec="unsignedshort")
  private int nameAndTypeIndex;
}