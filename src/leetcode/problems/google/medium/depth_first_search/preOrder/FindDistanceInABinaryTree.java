package leetcode.problems.google.medium.depth_first_search.preOrder;

import leetcode.TreeNode;

public class FindDistanceInABinaryTree {

    public class Solution {

        public int findDistance(TreeNode root, int p, int q) {
            return distance(root, p, q, 0);
        }

        // Helper function
        private int distance(TreeNode root, int p, int q, int depth) {
            if (root == null || p == q) {
                return 0;
            }

            // If either p or q is found, calculate the retDistance as the maximum
            // of depth and retDistance value for left and right subtrees.
            if (root.val == p || root.val == q) {
                int left = distance(root.left, p, q, 1);
                int right = distance(root.right, p, q, 1);

                return (left > 0 || right > 0) ? Math.max(left, right) : depth;
            }

            // Otherwise, calculate the retDistance as sum of retDistance of left
            // and right subtree.
            int left = distance(root.left, p, q, depth + 1);
            int right = distance(root.right, p, q, depth + 1);
            int retDistance = left + right;

            // If current node is the LCA, subtract twice of depth.
            if (left != 0 && right != 0) {
                retDistance -= 2 * depth;
            }

            return retDistance;
        }
    }
}
