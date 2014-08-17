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
class Number_Info<T extends Number> implements Info {
  @Bind
  T value;
  @BindType(key=CP_Info.CONSTANT_Integer)
  static class Integer_Info extends Number_Info<Integer>{ }
  @BindType(key=CP_Info.CONSTANT_Double)
  static class Double_Info extends Number_Info<Double> {}
  @BindType(key=CP_Info.CONSTANT_Float)
  static class Float_Info extends Number_Info<Float> {}
  @BindType(key=CP_Info.CONSTANT_Long)
  static class Long_Info extends Number_Info<Long> {}
}