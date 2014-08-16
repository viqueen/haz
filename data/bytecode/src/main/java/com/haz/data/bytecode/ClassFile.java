/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.bytecode;

import com.haz.data.bytecode.att.Attribute;
import com.haz.data.bytecode.cp.CP_Info;
import com.haz.data.codec.annotation.Bind;

/**
 * @author hasnaer
 *
 */
public class ClassFile {

  @Bind
  private int          magic;

  @Bind(codec = "unsignedshort")
  private int          minorVersion;

  @Bind(codec = "unsignedshort")
  private int          majorVersion;

  @Bind(codec = "unsignedshort")
  private int          constantPoolCount;

  @Bind(count = "$constantPoolCount - 1")
  private CP_Info[]    constantPool;

  @Bind(codec = "unsignedshort")
  private int          accessFlags;

  @Bind(codec = "unsignedshort")
  private int          thisClass;

  @Bind(codec = "unsignedshort")
  private int          superClass;

  @Bind(codec = "unsignedshort")
  private int          interfacesCount;

  @Bind(codec = "unsignedshort", count = "$interfacesCount")
  private int[]        interfaces;       // add a mapper or transformer, read
                                          // unsigned short but map it to an
                                          // entry in the constant pool

  @Bind(codec = "unsignedshort")
  private int          fieldsCount;

  @Bind(count = "$fieldsCount")
  private FieldInfo[]  fields;

  @Bind(codec = "unsignedshort")
  private int          methodsCount;

  @Bind(count = "$methodsCount")
  private MethodInfo[] methods;

  @Bind(codec = "unsignedshort")
  private int          attributesCount;

  @Bind(count = "$attributesCount")
  private Attribute[]  attributes;

}