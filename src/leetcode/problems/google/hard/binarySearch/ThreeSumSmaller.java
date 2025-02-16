package leetcode.problems.google.hard.binarySearch;

import java.util.Arrays;

public class ThreeSumSmaller {
    /**
     * Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [-2,0,1,3], target = 2
     * Output: 2
     * Explanation: Because there are two triplets which sums are less than 2:
     * [-2,0,1]
     * [-2,0,3]
     * Example 2:
     * <p>
     * Input: nums = [], target = 0
     * Output: 0
     * Example 3:
     * <p>
     * Input: nums = [0], target = 0
     * Output: 0
     * <p>
     * Constraints:
     * <p>
     * n == nums.length
     * 0 <= n <= 3500
     * -100 <= nums[i] <= 100
     * -100 <= target <= 100
     */


    class Solution {
        public int threeSumSmaller(int[] nums, int target) {
            Arrays.sort(nums);
            int sum = 0;
            for (int i = 0; i < nums.length - 2; i++) {
                sum += twoSumSmaller(nums, i + 1, target - nums[i]);
            }
            return sum;
        }

        private int twoSumSmaller(int[] nums, int startIndex, int target) {
            int sum = 0;
            int left = startIndex;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] < target) {
                    sum += right - left;
                    left++;
                } else {
                    right--;
                }
            }
            return sum;
        }
    }
}
