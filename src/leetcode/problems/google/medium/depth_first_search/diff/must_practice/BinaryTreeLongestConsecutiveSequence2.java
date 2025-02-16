package leetcode.problems.google.medium.depth_first_search.diff.must_practice;

import leetcode.TreeNode;

public class BinaryTreeLongestConsecutiveSequence2 {

/*
    Given the root of a binary tree, return the length of the longest consecutive path in the tree.

    A consecutive path is a path where the values of the consecutive nodes in the path differ by one. This path can be either increasing or decreasing.

    For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
    On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
*/

    public class Solution {
        int maxval = 0;

        public int longestConsecutive(TreeNode root) {
            longestPath(root);
            return maxval;
        }

        public int[] longestPath(TreeNode root) {
            if (root == null) {
                return new int[] {0,0};
            }

            int inr = 1, dcr = 1;
            if (root.left != null) {
                int[] left = longestPath(root.left);
                if (root.val == root.left.val + 1) {
                    dcr = left[1] + 1;
                } else if (root.val == root.left.val - 1) {
                    inr = left[0] + 1;
                }
            }

            if (root.right != null) {
                int[] right = longestPath(root.right);
                if (root.val == root.right.val + 1) {
                    dcr = Math.max(dcr, right[1] + 1);
                } else if (root.val == root.right.val - 1) {
                    inr = Math.max(inr, right[0] + 1);
                }
            }

            maxval = Math.max(maxval, dcr + inr - 1);
            return new int[] {inr, dcr};
        }
    }



}
