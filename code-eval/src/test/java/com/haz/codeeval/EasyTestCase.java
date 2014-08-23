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
  public void testPrimeSum () {
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
}