package leetcode.problems.google.medium.monotonic_stack.different;

import java.util.Stack;

public class ShortestContinuousUnsortedSubArray {

/*
    Given an integer array nums, you need to find one continuous subarray such that if you only sort this subarray in non-decreasing order,
    then the whole array will be sorted in non-decreasing order.

    Return the shortest such subarray and output its length.
    */

    // O(N)
    public class Solution {
        public int findUnsortedSubarray(int[] nums) {
            Stack< Integer > stack = new Stack < Integer > ();
            int l = nums.length, r = 0;
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                    l = Math.min(l, stack.pop());
                stack.push(i);
            }
            stack.clear();
            for (int i = nums.length - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                    r = Math.max(r, stack.pop());
                stack.push(i);
            }
            return r - l > 0 ? r - l + 1 : 0;
        }
    }
}
