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
  protected int magic;

  @Bind(codec = "unsignedshort")
  protected int minorVersion;

  @Bind(codec = "unsignedshort")
  protected int majorVersion;

  @Bind(codec = "unsignedshort")
  protected int constantPoolCount;

  @Bind(count = "$constantPoolCount - 1")
  protected CP_Info[] constantPool;

  @Bind(codec = "unsignedshort")
  protected int accessFlags;

  @Bind(codec = "unsignedshort")
  protected int thisClass;

  @Bind(codec = "unsignedshort")
  protected int superClass;

  @Bind(codec = "unsignedshort")
  protected int interfacesCount;

  @Bind(subCodec = "unsignedshort", count = "$interfacesCount")
  protected int[] interfaces; // add a mapper or transformer, read
                              // unsigned short but map it to an
                              // entry in the constant pool

  // @Bind(codec = "unsignedshort")
  private int fieldsCount;

  // @Bind(count = "$fieldsCount")
  private FieldInfo[] fields;

  // @Bind(codec = "unsignedshort")
  private int methodsCount;

  // @Bind(count = "$methodsCount")
  private MethodInfo[] methods;

  // @Bind(codec = "unsignedshort")
  private int attributesCount;

  // @Bind(count = "$attributesCount")
  private Attribute[] attributes;

}