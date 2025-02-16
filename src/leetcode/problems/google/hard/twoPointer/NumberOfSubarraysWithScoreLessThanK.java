package leetcode.problems.google.hard.twoPointer;

public class NumberOfSubarraysWithScoreLessThanK {
    /**
     * <h1>Problem Statement</h1>
     * The score of an array is defined as the product of its sum and its length.
     * <p>
     * For example, the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.
     * Given a positive integer array nums and an integer k, return the number of non-empty subarrays of nums whose score is strictly less than k.
     * <p>
     *     <h2>ASK</h2>
     * A subarray is a contiguous sequence of elements within an array.
     * <p>
     * <p>
     * <p>
     *     <h2>Examples</h2>
     * <b>Example 1:</b></br>
     * <p>
     * <b>Input:</b> nums = [2,1,4,3,5], k = 10</br>
     * <b>Output:</b> 6</br>
     * <b>Explanation:</b></br>
     * The 6 subarrays having scores less than 10 are:</br>
     * - [2] with score 2 * 1 = 2.</br>
     * - [1] with score 1 * 1 = 1.</br>
     * - [4] with score 4 * 1 = 4.</br>
     * - [3] with score 3 * 1 = 3.</br>
     * - [5] with score 5 * 1 = 5.</br>
     * - [2,1] with score (2 + 1) * 2 = 6.</br>
     * Note that subarrays such as [1,4] and [4,3,5] are not considered because their scores are 10 and 36 respectively, while we need scores strictly less than 10.</br>
     * <b>Example 2:</b>
     * <p>
     * <b>Input:</b> nums = [1,1,1], k = 5</br>
     * <b>Output:</b> 5</br>
     * <b>Explanation:</b></br>
     * Every subarray except [1,1,1] has a score less than 5.
     * [1,1,1] has a score (1 + 1 + 1) * 3 = 9, which is greater than 5.
     * Thus, there are 5 subarrays having scores less than 5.
     */

    class Solution {
        public long countSubarrays(int[] nums, long k) {
            long sum = 0, res = 0;
            for (int i = 0, j = 0; i < nums.length; ++i) {
                sum += nums[i];
                while (sum * (i - j + 1) >= k)
                    sum -= nums[j++];
                res += i - j + 1;
            }
            return res;
        }
    }
}
