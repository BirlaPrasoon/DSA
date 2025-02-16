package leetcode.problems.google.medium.depth_first_search.diff.much_asked;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BoundaryOfABinaryTree {
/*

    The boundary of a binary tree is the concatenation of the root, the left boundary, the leaves ordered from left-to-right, and the reverse order of the right boundary.

    The left boundary is the set of nodes defined by the following:

    The root node's left child is in the left boundary. If the root does not have a left child, then the left boundary is empty.
    If a node in the left boundary and has a left child, then the left child is in the left boundary.
    If a node is in the left boundary, has no left child, but has a right child, then the right child is in the left boundary.
    The leftmost leaf is not in the left boundary.
    The right boundary is similar to the left boundary, except it is the right side of the root's right subtree. Again, the leaf is not part of the right boundary, and the right boundary is empty if the root does not have a right child.

    The leaves are nodes that do not have any children. For this problem, the root is not a leaf.

    Given the root of a binary tree, return the values of its boundary.

*/


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public class Solution {

        public boolean isLeaf(TreeNode t) {
            return t.left == null && t.right == null;
        }

        public void addLeaves(List<Integer> res, TreeNode root) {
            if (isLeaf(root)) {
                res.add(root.val);
            } else {
                if (root.left != null) {
                    addLeaves(res, root.left);
                }
                if (root.right != null) {
                    addLeaves(res, root.right);
                }
            }
        }

        public List<Integer> boundaryOfBinaryTree(TreeNode root) {
            ArrayList<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            if (!isLeaf(root)) {
                res.add(root.val);
            }
            TreeNode t = root.left;
            while (t != null) {
                if (!isLeaf(t)) {
                    res.add(t.val);
                }
                if (t.left != null) {
                    t = t.left;
                } else {
                    t = t.right;
                }

            }
            addLeaves(res, root);
            Stack<Integer> s = new Stack<>();
            t = root.right;
            while (t != null) {
                if (!isLeaf(t)) {
                    s.push(t.val);
                }
                if (t.right != null) {
                    t = t.right;
                } else {
                    t = t.left;
                }
            }
            while (!s.empty()) {
                res.add(s.pop());
            }
            return res;
        }
    }


}
