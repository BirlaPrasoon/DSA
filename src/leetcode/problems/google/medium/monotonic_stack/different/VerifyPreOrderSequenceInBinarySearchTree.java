package leetcode.problems.google.medium.monotonic_stack.different;

import java.util.Stack;

public class VerifyPreOrderSequenceInBinarySearchTree {

/*
    Given an array of unique integers preorder, return true if it is the correct preorder traversal sequence of a binary search tree.
*/

/*
    Input: preorder = [5,2,1,3,6]
    Output: true
    Example 2:

    Input: preorder = [5,2,6,1,3]
    Output: false
*/

    class Solution {
        public boolean verifyPreorder(int[] preorder) {
            int minLimit = Integer.MIN_VALUE;
            Stack<Integer> stack = new Stack<>();

            for (int num: preorder) {
                while (!stack.isEmpty() && stack.peek() < num) {
                    minLimit = stack.pop();
                }

                if (num <= minLimit) {
                    return false;
                }

                stack.push(num);
            }

            return true;
        }
    }

    // O N
    class SolutionRecursion {
        int idx;
        public boolean verifyPreorder(int[] preorder) {
            int[] i = {0};
            idx = 0;
            return helper(preorder, i, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        public boolean helper(int[] preorder, int[] i, int minLimit, int maxLimit) {
            if (idx == preorder.length) {
                return true;
            }

            int root = preorder[idx];
            if (root <= minLimit || root >= maxLimit) {
                return false;
            }

            idx++;
            boolean left = helper(preorder, i, minLimit, root);
            boolean right = helper(preorder, i, root, maxLimit);
            return left || right;
        }
    }

}
