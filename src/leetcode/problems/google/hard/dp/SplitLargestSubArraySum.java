package leetcode.problems.google.hard.dp;

public class SplitLargestSubArraySum {

    // This is also solved using Binary Search on answer... Look at that solution as well, that's much faster.
    class Solution {
        // Defined it as per the maximum size of array and split count
        // But can be defined with the input size as well
        int[][] memo = new int[1001][51];

        public int splitArray(int[] nums, int m) {
            int n = nums.length;

            // Store the prefix sum of nums array
            int[] prefixSum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            for (int subarrayCount = 1; subarrayCount <= m; subarrayCount++) {
                for (int currIndex = 0; currIndex < n; currIndex++) {
                    // Base Case: If there is only one subarray left, then all of the remaining numbers
                    // must go in the current subarray. So return the sum of the remaining numbers.
                    if (subarrayCount == 1) {
                        memo[currIndex][subarrayCount] = prefixSum[n] - prefixSum[currIndex];
                        continue;
                    }

                    // Otherwise, use the recurrence relation to determine the minimum largest subarray
                    // sum between currIndex and the end of the array with subarrayCount subarray remaining.
                    int minimumLargestSplitSum = Integer.MAX_VALUE;
                    for (int i = currIndex; i <= n - subarrayCount; i++) {
                        // Store the sum of the first subarray.
                        int firstSplitSum = prefixSum[i + 1] - prefixSum[currIndex];

                        // Find the maximum subarray sum for the current first split.
                        int largestSplitSum = Math.max(firstSplitSum, memo[i + 1][subarrayCount - 1]);

                        // Find the minimum among all possible combinations.
                        minimumLargestSplitSum = Math.min(minimumLargestSplitSum, largestSplitSum);

                        if (firstSplitSum >= minimumLargestSplitSum) {
                            break;
                        }
                    }

                    memo[currIndex][subarrayCount] = minimumLargestSplitSum;
                }
            }

            return memo[0][m];
        }
    }

}
