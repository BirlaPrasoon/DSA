package leetcode.problems.google.medium.depth_first_search.preOrder;

import leetcode.TreeNode;

public class LongestUniValuedPath {
/*

    Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value.
    This path may or may not pass through the root.

    The length of the path between two nodes is represented by the number of edges between them.

    Constraints:

    The number of nodes in the tree is in the range [0, 104].
    -1000 <= Node.val <= 1000
    The depth of the tree will not exceed 1000.
*/

    // Returns the length of the longest path (number of nodes) under the root
// that have the value same as the root. The path could either be
// on the left or right child of the root. The length includes the root as well.
    class Solution {
        int ans;

        int solve(TreeNode root, int parent) {
            if (root == null) {
                return 0;
            }

            //The longest univalue path will cover nodes on both sides of the root.
            int left = solve(root.left, root.val);
            int right = solve(root.right, root.val);

            //The longest univalue path will cover nodes on both sides of the root.
            ans = Math.max(ans, left + right);

            // The number of nodes will be zero if the root value isn't equal to the root.
            // Otherwise, return the max of left and right nodes plus one for the root itself.
            return root.val == parent ? Math.max(left, right) + 1 : 0;
        }

        public int longestUnivaluePath(TreeNode root) {
            // Use -1 for the parent value for the tree root node.
            solve(root, -1);

            return ans;
        }
    }
}
