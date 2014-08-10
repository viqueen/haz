/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.heap;

import java.util.Arrays;
import java.util.Optional;

import com.haz.data.ArraysUtil;

/**
 * @author hasnaer
 *
 */
public class HeapImpl<Data extends Comparable<Data>> implements Heap<Data> {

  private int capacity;
  private int size;
  private Data[] entries;
  private Type type;

  public HeapImpl() {
    this(100, Type.MAX);
  }

  public HeapImpl (Type pType) {
    this (100, pType);
  }
  
  
  @SuppressWarnings("unchecked")
  public HeapImpl(int pCapacity, Type pType) {
    capacity = pCapacity;
    type = pType;
    entries = ((Data[]) new Comparable[capacity]);
  }

  @Override
  public int capacity() {
    return capacity;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean insert(Data pData) {
    if (size < capacity) {
      entries[size] = pData;
      heapUp(size);
      size++;
      return true;
    }
    return false;
  }

  private void heapUp(int pIndex) {
    if (pIndex > 0) {
      int parentIndex = (pIndex - 1) / 2;
      if (minOrMax(entries[pIndex], entries[parentIndex])) {
        ArraysUtil.swap(entries, pIndex, parentIndex);
        heapUp(parentIndex);
      }
    }
  }
  
  private boolean minOrMax(Data pNode, Data pParent) {
    switch (type) {
      case MIN:
        return pNode.compareTo(pParent) < 0;
      case MAX:
      default:
        return pNode.compareTo(pParent) > 0;
    }
  }
  
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public Optional<Data> peek() {
    if (isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(entries[0]);
  }

  @Override
  public Optional<Data> remove() {
    if (!isEmpty()) {
      ArraysUtil.swap(entries, 0, --size);
      heapDown(0);
      return Optional.of(entries[size]);
    }
    return Optional.empty();
  }

  private void heapDown(int pIndex) {
    int left = 2 * pIndex + 1;
    int right = left + 1;
    int child = left;
    if (left < size) {
      if (right < size) {
        child = minOrMaxChild(left, right);
      }
      if (!minOrMax(entries[pIndex], entries[child])) {
        ArraysUtil.swap(entries, pIndex, child);
        heapDown(child);
      }
    }
  }

  private int minOrMaxChild(int pLeft, int pRight) {
    return minOrMax(entries[pLeft], entries[pRight]) ? pLeft : pRight;
  }
  
  
  
  @Override
  public Data[] asArray() {
    return Arrays.copyOfRange(entries, 0, size);
  }

}
