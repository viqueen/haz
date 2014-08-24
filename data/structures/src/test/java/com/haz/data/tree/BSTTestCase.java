/**
 * Hasnae Rehioui (hasnae.rehioui@gmail.com)
 */
package com.haz.data.tree;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.haz.data.tree.BinarySearchTreeNode;

/**
 * @author hasnaer
 *
 */
public class BSTTestCase {

  private BinarySearchTreeNode<Integer> root;
  private Traversal traversal;

  @Before
  public void setUp() {
    root = new BinarySearchTreeNode<>();
    root.insertAll(Arrays.asList(10, 4, 5, 19, 12, 17, 1, 8, 9));
    traversal = new RecursiveTraversal();
  }

  @Test
  public void testInorder() {
    assertEquals(
        Arrays.asList(1, 4, 5, 8, 9, 10, 12, 17, 19),
        traversal.inorder(root).stream().map(node -> node.data)
            .collect(Collectors.toList()));
  }
}