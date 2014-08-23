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
    for (int value = 2; value <= pLimit; value++) {
      if (!isNotPrime[value - 1]) {
        sum += value;
        for (int mult = 2; mult * value <= pLimit; mult++) {
          isNotPrime[(mult * value) - 1] = true;
        }
      }
    }
    return sum;  
  }
  
  public static int sumOfDigits(String pStr) {
	  int sum = 0;
    for (char c : pStr.toCharArray()) {
	    sum += (c - '0');
	  }
    return sum;
  }
  
  public static int multiplesOfaNumber(int pX, int pN) {
    int result = 0;
    int multiplier = 1;    
    while ((result = pN * multiplier++) < pX);
    return result;
  }
}