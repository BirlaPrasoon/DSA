package leetcode.problems.google.medium.depth_first_search.diff.must_practice;

import leetcode.TreeNode;

public class BinaryTreeLongestConsecutiveSequence {
/*

    Given the root of a binary tree, return the length of the longest consecutive sequence path.

    A consecutive sequence path is a path where the values increase by one along the path.

    Note that the path can start at any node in the tree, and you cannot go from a node to its parent in the path.

*/


    public int longestConsecutive(TreeNode root) {
        return dfs(root, null, 0);
    }

    private int dfs(TreeNode p, TreeNode parent, int length) {
        if (p == null) return length;

        int newLength = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;

        return Math.max(newLength, Math.max(dfs(p.left, p, newLength), dfs(p.right, p, newLength)));
    }

}
