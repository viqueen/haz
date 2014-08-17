/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.bytecode.cp;

import com.haz.data.bytecode.cp.Number_Info.Double_Info;
import com.haz.data.bytecode.cp.Number_Info.Float_Info;
import com.haz.data.bytecode.cp.Number_Info.Integer_Info;
import com.haz.data.bytecode.cp.Number_Info.Long_Info;
import com.haz.data.codec.annotation.Bind;
import com.haz.data.codec.annotation.BindSelect;

/**
 * @author hasnaer
 *
 */
public class CP_Info {

  @Bind(codec = "unsignedbyte")
  protected int  tag;

  @BindSelect(keyExpr = "$tag", types = { Class_Info.class, FMI_Info.class,
      MethodHandle_Info.class, NameAndType_Info.class, Integer_Info.class,
      Double_Info.class, Float_Info.class, Long_Info.class, String_Info.class,
      UTF8_Info.class, MethodType_Info.class, InvokeDynamic_Info.class })
  protected Info info;

  public Info getInfo () {
    return info;
  }
  
  public static interface Info {
  }
  
  static final String CONSTANT_Class  = "7";
  static final String CONSTANT_Fieldref   = "9";
  static final String CONSTANT_Methodref  = "10";
  static final String CONSTANT_InterfaceMethodref   = "11";
  static final String CONSTANT_FMI = "9|10|11";
  static final String CONSTANT_String   = "8";
  static final String CONSTANT_Integer  = "3";
  static final String CONSTANT_Float  = "4";
  static final String CONSTANT_Long   = "5";
  static final String CONSTANT_Double   = "6";
  static final String CONSTANT_NameAndType  = "12";
  static final String CONSTANT_Utf8  = "1";
  static final String CONSTANT_MethodHandle  = "15";
  static final String CONSTANT_MethodType   = "16";
  static final String CONSTANT_InvokeDynamic = "18";
}