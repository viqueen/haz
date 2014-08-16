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
class Number_Info<T extends Number> implements Info {
  @Bind
  T value;
  
  static class Integer_Info extends Number_Info<Integer>{ }
  static class Double_Info extends Number_Info<Double> {}
  static class Float_Info extends Number_Info<Float> {}
  static class Long_Info extends Number_Info<Long> {}
}