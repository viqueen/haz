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
@BindType(key=CP_Info.CONSTANT_Utf8)
public class UTF8_Info implements Info {  
  @Bind
  private String value;
}
