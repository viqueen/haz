/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.codeeval;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author hasnaer
 *
 */
public class EasyTestCase {

  @Test
  public void testPrimeSum() {
    assertEquals(17, Easy.primeSum(10));
    assertEquals(3682913, Easy.primeSum(7925));
  }

  @Test
  public void testSumOfDigits() {
    assertEquals(5, Easy.sumOfDigits("23"));
    assertEquals(19, Easy.sumOfDigits("496"));
  }

  @Test
  public void testMultiplesOfaNumber() {
    assertEquals(16, Easy.multiplesOfaNumber(13, 8));
    assertEquals(32, Easy.multiplesOfaNumber(17, 16));
  }

  @Test
  public void testUniqueElements() {
    assertEquals("1,2,3,4", Easy.uniqueElements("1,1,2,3,4,4,4,4"));
    assertEquals("1", Easy.uniqueElements("1,1,1,1,1"));
    assertEquals("", Easy.uniqueElements(""));
  }

  @Test
  public void testSimpleSorting() {
    assertEquals("-38.797 7.581 14.354 70.920 90.374 99.323",
        Easy.simpleSorting("70.920 -38.797 14.354 99.323 90.374 7.581"));
    assertEquals("-55.552 -37.507 -3.263 27.999 40.079 65.213",
        Easy.simpleSorting("-55.552 -37.507 -3.263 27.999 40.079 65.213"));

  }
}