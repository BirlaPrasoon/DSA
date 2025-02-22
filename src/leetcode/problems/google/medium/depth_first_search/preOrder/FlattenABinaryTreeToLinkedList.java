package leetcode.problems.google.medium.depth_first_search.preOrder;

import leetcode.TreeNode;

public class FlattenABinaryTreeToLinkedList {


/*
    Given the root of a binary tree, flatten the tree into a "linked list":

    The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
    The "linked list" should be in the same order as a pre-order traversal of the binary tree.
*/
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        private TreeNode flattenTree(TreeNode node) {
            // Handle the null scenario
            if (node == null) {
                return null;
            }

            // For a leaf node, we simply return the
            // node as is.
            if (node.left == null && node.right == null) {
                return node;
            }

            //Recursively flatten the left subtree
            TreeNode leftTail = this.flattenTree(node.left);

            // Recursively flatten the right subtree
            TreeNode rightTail = this.flattenTree(node.right);

            // If there was a left subtree, we shuffle the connections
            // around so that there is nothing on the left side
            // anymore.
            if (leftTail != null) {
                leftTail.right = node.right;
                node.right = node.left;
                node.left = null;
            }

            // We need to return the "rightmost" node after we are
            // done wiring the new connections.
            return rightTail == null ? leftTail : rightTail;
        }

        public void flatten(TreeNode root) {
            this.flattenTree(root);
        }
    }

}
