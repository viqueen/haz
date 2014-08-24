/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hasnaer
 *
 */
public class RecursiveTraversal implements Traversal {

  @Override
  public <T extends Comparable<T>> List<BinarySearchTreeNode<T>> inorder(
      BinarySearchTreeNode<T> pNode) {
    List<BinarySearchTreeNode<T>> result = new ArrayList<>();
    inorder(pNode, result);
    return result;
  }

  public <T extends Comparable<T>> void inorder(BinarySearchTreeNode<T> pNode,
      List<BinarySearchTreeNode<T>> pResult) {
    if (pNode != null) {
      inorder(pNode.left, pResult);
      pResult.add(pNode);
      inorder(pNode.right, pResult);
    }
  }

  @Override
  public <T extends Comparable<T>> List<BinarySearchTreeNode<T>> postorder(
      BinarySearchTreeNode<T> pNode) {
    List<BinarySearchTreeNode<T>> result = new ArrayList<>();
    postorder(pNode, result);
    return result;
  }

  public <T extends Comparable<T>> void postorder(
      BinarySearchTreeNode<T> pNode, List<BinarySearchTreeNode<T>> pResult) {
    if (pNode != null) {
      postorder(pNode.left, pResult);
      postorder(pNode.right, pResult);
      pResult.add(pNode);
    }
  }

  @Override
  public <T extends Comparable<T>> List<BinarySearchTreeNode<T>> preorder(
      BinarySearchTreeNode<T> pNode) {
    List<BinarySearchTreeNode<T>> result = new ArrayList<>();
    preorder(pNode, result);
    return result;
  }

  public <T extends Comparable<T>> void preorder(BinarySearchTreeNode<T> pNode,
      List<BinarySearchTreeNode<T>> pResult) {
    if (pNode != null) {
      preorder(pNode.right, pResult);
      preorder(pNode.left, pResult);
      pResult.add(pNode);
    }
  }

}
