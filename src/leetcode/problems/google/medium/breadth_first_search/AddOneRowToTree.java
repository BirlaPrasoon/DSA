package leetcode.problems.google.medium.breadth_first_search;

import leetcode.TreeNode;

public class AddOneRowToTree {

    /**
     * Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
     * <p>
     * Note that the root node is at depth 1.
     * <p>
     * The adding rule is:
     * <p>
     * Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
     * cur's original left subtree should be the left subtree of the new left subtree root.
     * cur's original right subtree should be the right subtree of the new right subtree root.
     * If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and the original tree is the new root's left subtree.
     * <p>
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public class Solution {
        public TreeNode addOneRow(TreeNode t, int v, int d) {
            if (d == 1) {
                TreeNode n = new TreeNode(v);
                n.left = t;
                return n;
            }
            insert(v, t, 1, d);
            return t;
        }

        public void insert(int val, TreeNode node, int depth, int n) {
            if (node == null)
                return;
            if (depth == n - 1) {
                TreeNode t = node.left;
                node.left = new TreeNode(val);
                node.left.left = t;
                t = node.right;
                node.right = new TreeNode(val);
                node.right.right = t;
            } else {
                insert(val, node.left, depth + 1, n);
                insert(val, node.right, depth + 1, n);
            }
        }
    }

}
