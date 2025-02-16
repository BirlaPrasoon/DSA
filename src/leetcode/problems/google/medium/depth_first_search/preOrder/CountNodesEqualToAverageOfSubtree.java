package leetcode.problems.google.medium.depth_first_search.preOrder;

import leetcode.Pair;
import leetcode.TreeNode;

public class CountNodesEqualToAverageOfSubtree {

/*    Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

    Note:
    The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
    A subtree of root is a tree consisting of root and all of its descendants.

        */

    class Solution {
        int count = 0;

        Pair<Integer, Integer> postOrder(TreeNode root) {
            if (root == null) {
                return new Pair(0, 0);
            }

            // First iterate over left and right subtrees.
            Pair<Integer, Integer> left = postOrder(root.left);
            Pair<Integer, Integer> right = postOrder(root.right);

            int nodeSum = left.getKey() + right.getKey() + root.val;
            int nodeCount = left.getValue() + right.getValue() + 1;

            // Check if the average of the subtree is equal to the node value.
            if (root.val == nodeSum / (nodeCount)) {
                count++;
            }

            // Return the sum of nodes and the count in the subtree.
            return new Pair(nodeSum, nodeCount);
        }

        public int averageOfSubtree(TreeNode root) {
            postOrder(root);
            return count;
        }
    }
}
