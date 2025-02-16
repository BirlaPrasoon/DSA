package leetcode.problems.google.medium.depth_first_search.inorder;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInABST {

    /*

    Given the root of a binary search tree, and an integer k,
    return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

    */

    class Solution {

        List<Integer> a = new ArrayList<>();

        public int kthSmallest(TreeNode root, int k) {
            this.traversal(root);
            return a.get(k - 1);

        }

        TreeNode traversal(TreeNode root) {
            if (root != null) {
                traversal(root.left);
                a.add(root.val);
                traversal(root.right);
            }
            return null;
        }

    }
}
