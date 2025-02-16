package leetcode.problems.google.hard.binarySearch;

import java.util.Arrays;
import java.util.HashSet;

public class MaximumNumberOfOperationsToMakeArrayContinuous {

    /**
     * You are given an integer array nums. In one operation, you can replace any element in nums with any integer.
     * <p>
     * nums is considered continuous if both of the following conditions are fulfilled:
     * <p>
     * All elements in nums are unique.
     * The difference between the maximum element and the minimum element in nums equals nums.length - 1.
     * For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.
     * <p>
     * Return the minimum number of operations to make nums continuous.
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [4,2,5,3]
     * Output: 0
     * Explanation: nums is already continuous.
     * Example 2:
     * <p>
     * Input: nums = [1,2,3,5,6]
     * Output: 1
     * Explanation: One possible solution is to change the last element to 4.
     * The resulting array is [1,2,3,5,4], which is continuous.</br>
     * Example 3:
     * <p>
     * Input: nums = [1,10,100,1000]</br>
     * Output: 3</br>
     * Explanation: One possible solution is to:</br>
     * - Change the second element to 2.</br>
     * - Change the third element to 3.</br>
     * - Change the fourth element to 4.</br>
     * The resulting array is [1,2,3,4], which is continuous.</br>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 10<sup>5</sup>
     * 1 <= nums[i] <= 10<sup>9</sup>
     */

    // Time complexity: O(n⋅logn)
    // Space : O(n)
    class SolutionBinarySearch {
        public int minOperations(int[] nums) {
            int n = nums.length;
            int ans = n;

            HashSet<Integer> unique = new HashSet<>();
            for (int num : nums) {
                unique.add(num);
            }

            int[] newNums = new int[unique.size()];
            int index = 0;

            for (int num : unique) {
                newNums[index++] = num;
            }

            Arrays.sort(newNums);

            for (int i = 0; i < newNums.length; i++) {
                int left = newNums[i];
                int right = left + n - 1;
                int j = binarySearch(newNums, right);
                int count = j - i;
                ans = Math.min(ans, n - count);
            }

            return ans;
        }

        public int binarySearch(int[] newNums, int target) {
            int left = 0;
            int right = newNums.length;

            while (left < right) {
                int mid = (left + right) / 2;
                if (target < newNums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    // TimeComplexity: O(n.logn)
    // Space: O(n)
    // Easier to understand
    static class SolutionSlidingWindow {
        public int minOperations(int[] nums) {
            int n = nums.length;
            int ans = n;

            HashSet<Integer> unique = new HashSet<>();
            for (int num : nums) {
                unique.add(num);
            }

            int[] newNums = new int[unique.size()];
            int index = 0;

            for (int num : unique)
                newNums[index++] = num;

            Arrays.sort(newNums);
            // any element in the new array can become the minimum element... and its range will be nums[i] : nums[i]+ n-1
            int j = 0;
            for (int i = 0; i < newNums.length; i++) {
                // consider elements in the range
                // j will become our right
                while (j < newNums.length && newNums[j] < newNums[i] + n) j++;
                int count = j - i; // in range
                ans = Math.min(ans, n - count);
            }
            return ans;
        }
    }

}
