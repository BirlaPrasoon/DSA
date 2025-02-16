package leetcode.problems.google.easy.greedy;

import java.util.Arrays;

public class MaximumSumOfArrayAfterKNegations {

    /**
     * Given an integer array nums and an integer k, modify the array in the following way:
     * <p>
     * choose an index i and replace nums[i] with -nums[i].
     * You should apply this process exactly k times. You may choose the same index i multiple times.
     * <p>
     * Return the largest possible sum of the array after modifying it in this way.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [4,2,3], k = 1
     * Output: 5
     * Explanation: Choose index 1 and nums becomes [4,-2,3].
     * Example 2:
     * <p>
     * Input: nums = [3,-1,0,2], k = 3
     * Output: 6
     * Explanation: Choose indices (1, 2, 2) and nums becomes [3,1,0,2].
     * Example 3:
     * <p>
     * Input: nums = [2,-3,-1,5,-4], k = 2
     * Output: 13
     * Explanation: Choose indices (1, 4) and nums becomes [2,3,-1,5,4].
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 10<sup>4</sup>
     * -100 <= nums[i] <= 100
     * 1 <= k <= 10<sup>4</sup>
     */

    class Solution {
        public int largestSumAfterKNegations(int[] nums, int k) {
            int smallestNum = Integer.MAX_VALUE;
            int countOfNegs = 0;
            int sum = 0;
            Arrays.sort(nums);
            for (int a : nums) {
                if (a < 0 && k-- > 0) a = -a;
                sum += a;
                if (a < smallestNum) smallestNum = a;
            }
            if (k == 0) {
                if (smallestNum >= 0)
                    return sum;
                return sum + -1 * 2 * smallestNum;
            }
            if (k > 0) {
                if (k % 2 == 0) {
                    return sum;
                } else {
                    if (smallestNum >= 0)
                        return sum + -1 * 2 * smallestNum;
                }
            }
            return sum;
        }
    }

}
