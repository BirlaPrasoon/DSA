package leetcode.problems.google.medium.monotonic_stack.different;

import java.util.Stack;

public class SumOfSubArrayRanges {

    /*
     *
     * You are given an integer array nums.
     * The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
     *
     * Return the sum of all subarray ranges of nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * */

//    Example 1:
//
//    Input: nums = [1,2,3]
//    Output: 4
//    Explanation: The 6 subarrays of nums are the following:
//            [1], range = largest - smallest = 1 - 1 = 0
//            [2], range = 2 - 2 = 0
//            [3], range = 3 - 3 = 0
//            [1,2], range = 2 - 1 = 1
//            [2,3], range = 3 - 2 = 1
//            [1,2,3], range = 3 - 1 = 2
//    So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
//    Example 2:
//
//    Input: nums = [1,3,3]
//    Output: 4
//    Explanation: The 6 subarrays of nums are the following:
//            [1], range = largest - smallest = 1 - 1 = 0
//            [3], range = 3 - 3 = 0
//            [3], range = 3 - 3 = 0
//            [1,3], range = 3 - 1 = 2
//            [3,3], range = 3 - 3 = 0
//            [1,3,3], range = 3 - 1 = 2
//    So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
//    Example 3:
//
//    Input: nums = [4,-2,-3,4,1]
//    Output: 59
//    Explanation: The sum of all subarray ranges of nums is 59.
//

    class Solution {

        public long subArrayRanges(int[] nums) {
            int n = nums.length;
            long answer = 0;
            Stack<Integer> stack = new Stack<>();

            // Find the sum of all the minimum.
            for (int right = 0; right <= n; ++right) {
                while (!stack.isEmpty() && (right == n || nums[stack.peek()] >= nums[right])) {
                    int mid = stack.peek();
                    stack.pop();
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    answer -= (long) nums[mid] * (right - mid) * (mid - left);
                }
                stack.add(right);
            }

            // Find the sum of all the maximum.
            stack.clear();
            for (int right = 0; right <= n; ++right) {
                while (!stack.isEmpty() && (right == n || nums[stack.peek()] <= nums[right])) {
                    int mid = stack.peek();
                    stack.pop();
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    answer += (long) nums[mid] * (right - mid) * (mid - left);
                }
                stack.add(right);
            }
            return answer;
        }
    }
}
