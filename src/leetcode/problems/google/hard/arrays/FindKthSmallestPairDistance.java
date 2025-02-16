package leetcode.problems.google.hard.arrays;

import java.util.Arrays;

public class FindKthSmallestPairDistance {

    /**
     * <h1>Problem Statement</h1>
     * The distance of a pair of integers a and b is defined as the absolute difference between a and b.
     * <p>
     * Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.
     * <p>
     * <p>
     * <p>
     * <h4>Example 1:</h4>
     * <p>
     * Input: nums = [1,3,1], k = 1</br>
     * Output: 0</br>
     * Explanation: </br>Here are all the pairs:</br>
     * (1,3) -> 2</br>
     * (1,1) -> 0</br>
     * (3,1) -> 2</br>
     * Then the 1st smallest distance pair is (1,1), and its distance is 0.
     * <h4>Example 2:</h4>
     * <p>
     * Input: nums = [1,1,1], k = 2</br>
     * Output: 0</br>
     * <h4>Example 3:</h4>
     * <p>
     * Input: nums = [1,6,1], k = 3
     * Output: 5
     * <p>
     * <p>
     * Constraints:
     * <p>
     * n == nums.length</br>
     * 2 <= n <= 10<sup>4</sup></br>
     * 0 <= nums[i] <= 10<sup>6</sup></br>
     * 1 <= k <= n * (n - 1) / 2
     */

    // O(n^2)
    class SolutionBruteforceAccepted {
        public int smallestDistancePair(int[] nums, int k) {
            int max = Arrays.stream(nums).max().getAsInt();
            int[] dp = new int[max + 1];
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    int diff = Math.abs(nums[i] - nums[j]);
                    dp[diff]++;
                }
            }
            int i = 0;
            int t = 0;
            for (int x : dp) {
                t += x;
                if (t >= k) {
                    return i;
                }
                i++;
            }

            return -1;
        }
    }


    // O(nlogM+nlogn)
    class SolutionTwoPointer {
        public int smallestDistancePair(int[] nums, int k) {
            Arrays.sort(nums);
            int arraySize = nums.length;

            int low = 0;
            int high = nums[arraySize - 1] - nums[0];

            while (low < high) {
                int mid = (low + high) / 2;
                int count = countPairsWithMaxDistance(nums, mid);

                if (count < k) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        private int countPairsWithMaxDistance(int[] nums, int maxDistance) {
            int count = 0;
            int arraySize = nums.length;
            int left = 0;

            for (int right = 0; right < arraySize; ++right) {
                while (nums[right] - nums[left] > maxDistance) {
                    ++left;
                }
                count += right - left;
            }
            return count;
        }
    }
}
