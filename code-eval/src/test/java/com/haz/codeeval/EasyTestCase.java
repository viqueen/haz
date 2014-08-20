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
}