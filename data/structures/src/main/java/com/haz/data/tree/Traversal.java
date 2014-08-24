/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.tree;

import java.util.List;

/**
 * @author hasnaer
 *
 */
public interface Traversal {

  public <T extends Comparable<T>> List<BinarySearchTreeNode<T>> inorder(
      BinarySearchTreeNode<T> pNode);

  public <T extends Comparable<T>> List<BinarySearchTreeNode<T>> postorder(
      BinarySearchTreeNode<T> pNode);

  public <T extends Comparable<T>> List<BinarySearchTreeNode<T>> preorder(
      BinarySearchTreeNode<T> pNode);
}