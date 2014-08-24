/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.tree;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hasnaer
 *
 */
public class BinarySearchTreeNode<T extends Comparable<T>> extends
    AbstractTreeNode<T> {

  protected BinarySearchTreeNode<T> left, right;

  public BinarySearchTreeNode() {
    super();
  }

  public BinarySearchTreeNode(T pData) {
    super(pData);
  }

  public BinarySearchTreeNode(T pData, BinarySearchTreeNode<T> pParent) {
    super(pData, pParent);
  }

  @Override
  public List<TreeNode<T>> children() {
    return Stream.of(left, right).filter(child -> child != null)
        .collect(Collectors.toList());
  }

  @Override
  public void insert(T pData) {
    if (isEmpty()) {
      data = pData;
    } else {
      switch (pData.compareTo(data)) {
        case -1:
          left = follow(left, pData);
          break;
        case 1:
          right = follow(right, pData);
          break;
        case 0:
        default:
          data = pData;
          break;
      }
    }
  }

  private BinarySearchTreeNode<T> follow(BinarySearchTreeNode<T> pNode, T pData) {
    if (pNode == null) {
      pNode = create(pData, this);
    } else {
      pNode.insert(pData);
    }
    return pNode;
  }

  protected BinarySearchTreeNode<T> create(T pData, BinarySearchTreeNode<T> pParent) {
    return new BinarySearchTreeNode<T>(pData, pParent);
  }
}