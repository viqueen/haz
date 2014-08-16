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
public class MethodHandle_Info implements Info {

  @Bind(codec="unsignedbyte")
  private int referenceKind;

  @Bind(codec = "unsignedshort")
  private int  referenceIndex;
  
  
}