package leetcode.problems.google.hard.binarySearch;

import java.util.Arrays;
import java.util.Stack;

public class MaximumScoreOfAGoodSubarray {
    /**
     * You are given an array of integers nums (0-indexed) and an integer k.
     * <p>
     * The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.
     *
     * <p>
     * Return the maximum possible score of a good subarray.
     * <p>
     * Example 1:
     * Input: nums = [1,4,3,7,4,5], k = 3
     * Output: 15
     * Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.
     * <p>
     * Example 2:
     * Input: nums = [5,5,4,5,4,1,1,1], k = 0
     * Output: 20
     * Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 10<sup>5</sup>
     * <p>
     * 1 <= nums[i] <= 2 * 10<sup>4</sup>
     * <p>
     * 0 <= k < nums.length
     * <p>
     * How is K useful? -
     */

    // This can easily be a 2 pointer solution as well, Neetcode did the same way. If I think I'll reach the same solution.
        // No need to stack.

    class Solution {
        public int maximumScore(int[] nums, int k) {
            int n = nums.length;
            int[] left = new int[n];
            Arrays.fill(left, -1);
            Stack<Integer> stack = new Stack<>();

            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    left[stack.pop()] = i;
                }
                stack.push(i);
            }

            int[] right = new int[n];
            Arrays.fill(right, n);
            stack = new Stack<>();

            for (int i = 0; i < n; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    right[stack.pop()] = i;
                }
                stack.push(i);
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (left[i] < k && right[i] > k) {
                    ans = Math.max(ans, nums[i] * (right[i] - left[i] - 1));
                }
            }

            return ans;
        }
    }
}
