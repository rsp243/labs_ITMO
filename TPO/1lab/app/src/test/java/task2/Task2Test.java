package task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task2Test {
    private boolean isBalanced(AVLTree tree, AVLTree.Node node) {
        if (node == null) return true;

        int balance = Math.abs(tree.getBalance(node));
        if (balance > 1) return false;

        return isBalanced(tree, node.left) && isBalanced(tree, node.right);
    }

    @Test
    @DisplayName("Height and balance of tree")
    void testHeightAndBalanceFactor() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        // Verify height and balance factor of the root
        AVLTree.Node root = tree.getRoot();
        assertEquals(2, root.height);
        assertEquals(0, tree.getBalance(root));
    }

    @Test
    void testBalancing() {
        AVLTree tree = new AVLTree();
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);
        tree.insert(50);
        tree.insert(5);

        // Verify that the tree is balanced
        assertTrue(isBalanced(tree, tree.getRoot()));
    }

    @Test
    void testDescendingOrderInsertion() {
        AVLTree tree = new AVLTree();
        tree.insert(50);
        tree.insert(40);
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);

        // Verify that the tree is balanced
        assertTrue(isBalanced(tree, tree.getRoot()));

        // Verify in-order traversal
        List<Integer> result = tree.inOrder();
        List<Integer> expected = new ArrayList<>(); 
        expected.add(10);
        expected.add(20);
        expected.add(30);
        expected.add(40);
        expected.add(50);

        assertEquals(result, expected);
    }

    @Test
    void testDuplicateKeys() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(10); // Duplicate key

        List<Integer> result = tree.inOrder();
        List<Integer> expected = new ArrayList<>(); 
        expected.add(10);

        assertEquals(result, expected);
    }

    @Test
    void testAscendingOrderInsertion() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);

        // Verify that the tree is balanced
        assertTrue(isBalanced(tree, tree.getRoot()));


        List<Integer> result = tree.inOrder();
        List<Integer> expected = new ArrayList<>();
        expected.add(10);
        expected.add(20);
        expected.add(30);
        expected.add(40);
        expected.add(50);

        assertEquals(result, expected);
    }

    @Test
    void testInsertAndInOrderTraversal() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        List<Integer> result = tree.inOrder();
        List<Integer> expected = new ArrayList<>();
        expected.add(10);
        expected.add(20);
        expected.add(25);
        expected.add(30);
        expected.add(40);
        expected.add(50);

        assertEquals(result, expected);
    }
}
