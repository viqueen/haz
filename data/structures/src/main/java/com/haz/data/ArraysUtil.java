/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data;

/**
 * @author hasnaer
 *
 */
public class ArraysUtil {

  public static <Data> void swap (Data[] pArray, int p1, int p2) {
    Data temp = pArray[p1];
    pArray[p1] = pArray[p2];
    pArray[p2] = temp;
  }
}