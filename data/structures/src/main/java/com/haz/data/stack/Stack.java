/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.stack;

import java.util.Optional;

/**
 * @author hasnaer
 *
 */
public class Stack<T> {

  private Node<T> top;
  private int size;

  public Optional<T> peek() {
    return top == null ? Optional.empty() : Optional.of(top.value);
  }

  public Optional<T> pop() {
    if (top == null) {
      return Optional.empty();
    }
    Node<T> t = top;
    top = top.next;
    t.next = null;
    size--;
    return Optional.of(t.value);
  }
  
  public void push (T pValue) {
    push (new Node<T>(pValue));
  }
  
  private void push (Node<T> pNode) {
    pNode.next = top;
    top = pNode;
    size++;
  }

  public boolean isEmpty() {
    return top == null;
  }

  public int size () {
    return size;
  }
  
  private static class Node<T> {
    private T value;

    public Node(T pValue) {
      value = pValue;
    }

    private Node<T> next;
  }
}