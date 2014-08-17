/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.stack;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @author hasnaer
 *
 */
public class StackTestCase {

  private Stack<Integer> stack;
  
  @Before
  public void setUp () {
    stack = new Stack<>();
    for (int i = 0; i < 10; i++) {
      stack.push(i);
    }
  }
  
  @Test
  public void testStack () {    
    assertEquals(10, stack.size());
    for (int i = 9; i >= 0; i--) {
      assertEquals(i, stack.pop().get().intValue());
    }
    assertTrue(stack.isEmpty());
  }
  
}