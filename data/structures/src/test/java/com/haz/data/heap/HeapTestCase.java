/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.heap;

import org.junit.Before;
import org.junit.Test;

import com.haz.data.heap.Heap.Type;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author hasnaer
 *
 */
public class HeapTestCase {

  private Heap<Integer> minHeap;
  private Heap<Integer> maxHeap;

  private Integer[] expectedMaxEntries;
  private Integer[] expectedMinEntries;
  private Integer[] input;

  @Before
  public void setUp() {
    minHeap = createHeap(Type.MIN);
    maxHeap = createHeap(Type.MAX);
    input = new Integer[] { 0, 3, 1, 9, 7, 8, 4, 6, 5, 2 };
    for (int i : input) {
      maxHeap.insert(i);
      minHeap.insert(i);
    }
    expectedMaxEntries = new Integer[] { 9, 7, 8, 6, 3, 1, 4, 0, 5, 2 };
    expectedMinEntries = new Integer[] { 0, 2, 1, 5, 3, 8, 4, 9, 6, 7 };
  }

  private Heap<Integer> createHeap(Type pType) {
    return new HeapImpl<Integer>(pType);
  }

  @Test
  public void testHeapInsert() {
    assertArrayEquals(expectedMaxEntries, maxHeap.asArray());
    assertArrayEquals(expectedMinEntries, minHeap.asArray());
  }

  @Test
  public void testMaxHeapRemove() {
    assertEquals(new Integer(9), maxHeap.peek().get());
    assertEquals(new Integer(9), maxHeap.remove().get());
    assertArrayEquals(new Integer[] { 8, 7, 4, 6, 3, 1, 2, 0, 5 },
        maxHeap.asArray());
  }

  @Test
  public void testMinHeapRemove() {
    assertEquals(new Integer(0), minHeap.peek().get());
    assertEquals(new Integer(0), minHeap.remove().get());
    assertArrayEquals(new Integer[] { 1, 2, 4, 5, 3, 8, 7, 9, 6 },
        minHeap.asArray());
  }

}