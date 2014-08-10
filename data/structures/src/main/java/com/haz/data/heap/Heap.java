/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.heap;

import java.util.Optional;

/**
 * @author hasnaer
 *
 */
public interface Heap<Data extends Comparable<Data>> {

  public int capacity ();
  
  public int size();
  
  public boolean insert(Data pData);
  
  public boolean isEmpty();
  
  public Optional<Data> peek();
  
  public Optional<Data> remove();
  
  public Data[] asArray();
  
  public static enum Type {
    MIN, MAX;
  }
  
}