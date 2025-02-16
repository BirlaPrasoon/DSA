package leetcode.problems.google.medium.depth_first_search.preOrder;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KthLargestPerfectSubTreeSizeInBinaryTree {
    /*

    You are given the root of a binary tree and an integer k.

    Return an integer denoting the size of the kth largest perfect binary subtree, or -1 if it doesn't exist.

    A perfect binary tree is a tree where all leaves are on the same level, and every parent has two children.

    */

    private int solve(TreeNode root, List<Integer> list) {
        if(root == null) return 0;

        int left = solve(root.left, list);
        int right = solve(root.right, list);
        int size = left + right + 1;
        if(left > -1 && left == right) {
            list.add(size);
            return size;
        } else {
            // leaves to return -1
            return -1;
        }
    }

    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        solve(root, list);

        if(list.size() < k) return -1;

        list.sort(Collections.reverseOrder());
        return list.get(k-1);
    }

}
