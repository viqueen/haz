/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.tree;

import java.util.List;
import java.util.Optional;

/**
 * @author hasnaer
 *
 */
public interface TreeNode<T extends Comparable<T>> {

  public Optional<T> data();

  public List<TreeNode<T>> children();

  public Optional<TreeNode<T>> parent();

  default boolean isRoot() {
    return !parent().isPresent();
  }

  default boolean isLeaf() {
    return children().isEmpty();
  }

  default boolean isEmpty() {
    return !data().isPresent();
  }

  public void insert(T pData);

  default void insertAll(List<T> pData) {
    for (T data : pData) {
      insert(data);
    }
  }
}