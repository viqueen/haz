/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.codeeval;

/**
 * @author hasnaer
 *
 */
public class Easy {

  public static int primeSum(int pLimit) {
    boolean[] isNotPrime = new boolean[pLimit];
    isNotPrime[0] = true;
    int sum = 0;
    int count = 0;
    for (int value = 2; value <= pLimit; value++) {
      if (!isNotPrime[value - 1]) {
        sum += value;
        count++;
        for (int mult = 2; mult * value <= pLimit; mult++) {
          isNotPrime[(mult * value) - 1] = true;
        }
      }
    }
    System.err.println(count);
    return sum;  
  }
  
  
}