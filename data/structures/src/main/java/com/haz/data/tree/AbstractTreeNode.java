/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.tree;

import java.util.Optional;

/**
 * @author hasnaer
 *
 */
abstract class AbstractTreeNode<T extends Comparable<T>> implements TreeNode<T> {

  protected T data;
  protected TreeNode<T> parent;

  public AbstractTreeNode() {

  }

  public AbstractTreeNode(T pData) {
    this(pData, null);
  }

  public AbstractTreeNode(T pData, TreeNode<T> pParent) {
    data = pData;
    parent = pParent;
  }

  public final Optional<T> data() {
    return data == null ? Optional.empty() : Optional.of(data);
  }

  public final Optional<TreeNode<T>> parent() {
    return parent == null ? Optional.empty() : Optional.of(parent);
  }
}
