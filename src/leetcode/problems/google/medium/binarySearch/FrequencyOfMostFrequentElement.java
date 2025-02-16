package leetcode.problems.google.medium.binarySearch;

import java.util.Arrays;

public class FrequencyOfMostFrequentElement {


    /**
     * The frequency of an element is the number of times it occurs in an array.
     * <p>
     * You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.
     * <p>
     * Return the maximum possible frequency of an element after performing at most k operations.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,2,4], k = 5
     * Output: 3
     * Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
     * 4 has a frequency of 3.
     * Example 2:
     * <p>
     * Input: nums = [1,4,8,13], k = 5
     * Output: 2
     * Explanation: There are multiple optimal solutions:
     * - Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
     * - Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
     * - Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
     * Example 3:
     * <p>
     * Input: nums = [3,9,6], k = 2
     * Output: 1
     */

    class Solution {
        public int check(int i, int k, int[] nums, long[] prefix) {
            int target = nums[i];
            int left = 0;
            int right = i;
            int best = i;

            while (left <= right) {
                int mid = (left + right) / 2;
                long count = i - mid + 1;
                long finalSum = count * target;
                long originalSum = prefix[i] - prefix[mid] + nums[mid];
                long operationsRequired = finalSum - originalSum;

                if (operationsRequired > k) {
                    left = mid + 1;
                } else {
                    best = mid;
                    right = mid - 1;
                }
            }

            return i - best + 1;
        }

        public int maxFrequency(int[] nums, int k) {
            Arrays.sort(nums);
            long[] prefix = new long[nums.length];
            prefix[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                prefix[i] = nums[i] + prefix[i - 1];
            }

            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                ans = Math.max(ans, check(i, k, nums, prefix));
            }

            return ans;

        }
    }

}
