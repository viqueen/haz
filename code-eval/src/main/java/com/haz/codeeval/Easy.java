/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.codeeval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    while ((result = pN * multiplier++) < pX)
      ;
    return result;
  }

  public static String uniqueElements(String pStr) {
    if (pStr != null && !pStr.isEmpty()) {
      StringBuilder builder = new StringBuilder();
      String[] tokens = pStr.split(",");
      String previous = "";
      for (String token : tokens) {
        if (!token.equals(previous)) {
          builder.append(token).append(",");
          previous = token;
        }
      }
      return builder.substring(0, builder.length() - 1);
    }
    return "";
  }
  
  public static String simpleSorting(String pStr) {
    StringBuilder builder = new StringBuilder();
    String[] tokens = pStr.split(" ");
    List<SimpleSortingData> data = new ArrayList<>();
    for (String token : tokens) {
      data.add(new SimpleSortingData(token));
    }
    Collections.sort(data);
    for (SimpleSortingData d : data) {
      builder.append(d.string).append(" ");
    }
    return builder.toString().trim();
  }
  
  private static class SimpleSortingData implements Comparable<SimpleSortingData> {
    String string;
    Double value;    
    public SimpleSortingData(String pStr) {
      string = pStr;
      value = Double.parseDouble(pStr);
    }
    @Override
    public int compareTo(SimpleSortingData pOther) {
      return value.compareTo(pOther.value);
    }
  }
}