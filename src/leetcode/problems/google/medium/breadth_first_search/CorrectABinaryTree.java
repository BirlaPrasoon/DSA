package leetcode.problems.google.medium.breadth_first_search;

import leetcode.TreeNode;

import java.util.*;

public class CorrectABinaryTree {

    /**
     * You have a binary tree with a small defect.
     * There is exactly one invalid node where its right child incorrectly points to another node at the same depth but
     * to the invalid node's right.
     * <p>
     * Given the root of the binary tree with this defect, root, return the root of the binary tree after removing this invalid node
     * and every node underneath it (minus the node it incorrectly points to).
     * <p>
     * Custom testing:
     * <p>
     * The test input is read as 3 lines:
     * <p>
     * TreeNode root
     * int fromNode (not available to correctBinaryTree)
     * int toNode (not available to correctBinaryTree)
     * After the binary tree rooted at root is parsed, the TreeNode with value of fromNode will have its right child pointer pointing to the TreeNode with a value of toNode. Then, root is passed to correctBinaryTree.
     */

    class Solution {
        // Hash Set to store node value of the rightmost branch
        Set<Integer> visited = new HashSet<>();

        // Do Reverse Postorder Traversal. Assume input "root" as recursive parameter "node"
        public TreeNode correctBinaryTree(TreeNode root) {
            // If Empty Node, return
            if (root == null) {
                return null;
            }

            // If node.right is already visited, then the node is defective
            // No need to build tree rooted at "node". Replace it with null
            if (root.right != null && visited.contains(root.right.val)) {
                return null;
            }

            // Add this node's value to the visited
            visited.add(root.val);

            // Recursively build tree rooted at "node"
            // Build right subtree first, so that we can explore right to left
            root.right = correctBinaryTree(root.right);
            root.left = correctBinaryTree(root.left);

            // Return root of built tree
            return root;
        }
    }
}
