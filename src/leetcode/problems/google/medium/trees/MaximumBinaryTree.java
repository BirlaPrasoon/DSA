package leetcode.problems.google.medium.trees;

import leetcode.TreeNode;

public class MaximumBinaryTree {

/*
    You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

    Create a root node whose value is the maximum value in nums.
    Recursively build the left subtree on the subarray prefix to the left of the maximum value.
    Recursively build the right subtree on the subarray suffix to the right of the maximum value.
    Return the maximum binary tree built from nums.


    Input: nums = [3,2,1,6,0,5]
    Output: [6,3,5,null,2,0,null,null,1]
    Explanation: The recursive calls are as follow:
            - The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
            - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
            - Empty array, so no child.
            - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
            - Empty array, so no child.
            - Only one element, so child is a node with value 1.
            - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
            - Only one element, so child is a node with value 0.
            - Empty array, so no child.


    Input: nums = [3,2,1]
    Output: [3,null,2,null,1]
*/


    // N2
    public class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return construct(nums, 0, nums.length);
        }
        public TreeNode construct(int[] nums, int l, int r) {
            if (l == r)
                return null;
            int max_i = max(nums, l, r);
            TreeNode root = new TreeNode(nums[max_i]);
            root.left = construct(nums, l, max_i);
            root.right = construct(nums, max_i + 1, r);
            return root;
        }
        public int max(int[] nums, int l, int r) {
            int max_i = l;
            for (int i = l; i < r; i++) {
                if (nums[max_i] < nums[i])
                    max_i = i;
            }
            return max_i;
        }
    }
}
