package leetcode.problems.google.hard.dp.seems_simple_but_isnt;

import java.util.Arrays;

public class MaximumSumOf3NonOverlappingSubArrays {
/*
    Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.

    Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.



    Example 1:

    Input: nums = [1,2,1,2,6,7,5,1], k = 2
    Output: [0,3,5]
    Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
    We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
    Example 2:

    Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
    Output: [0,2,4]


    Constraints:

    1 <= nums.length <= 2 * 104
    1 <= nums[i] < 216
    1 <= k <= floor(nums.length / 3)
        */

    // Much better approach is in notes...

    class SolutionFromNotes {
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            if(nums == null || nums.length < k * 3) return new int[]{};

            int numSub = nums.length - k + 1;
            //1. find sum of k len sub array start at index i
            int[] subSum = new int[numSub];
            int sum = 0;
            //initialize window
            for(int i=0; i<k; i++) sum += nums[i];
            subSum[0] = sum;
            //moving subarray window and calculate sum
            for(int i=k; i<nums.length; i++){
                sum -= nums[i-k];
                sum += nums[i];
                subSum[i-k+1] = sum;
            }

            //2. find the max sub at left
            int[] maxAtLeft = new int[numSub];
            for(int i=1; i<numSub; i++){
                maxAtLeft[i] = (subSum[i] > subSum[maxAtLeft[i-1]])? i : maxAtLeft[i-1];
            }
            //3. find the max sub at right
            int[] maxAtRight = new int[numSub];
            maxAtRight[numSub-1] = numSub-1;
            for(int i=numSub-2; i>=0; i--){
                maxAtRight[i] = (subSum[i] >= subSum[maxAtRight[i+1]])? i : maxAtRight[i+1];//equal to keep smallest index
            }
            //4.find the max 3 array such that i is the middle array
            int[] maxThree = new int[3];
            int maxSum = 0;
            for(int i=k; i<numSub-k; i++){
                int curSum = subSum[maxAtLeft[i-k]] + subSum[i] + subSum[maxAtRight[i+k]];
                if(curSum > maxSum){
                    maxSum = curSum;
                    maxThree = new int[]{maxAtLeft[i-k], i, maxAtRight[i+k]};
                }
            }
            return maxThree;
        }
    }

    class Solution {
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int n = nums.length;

            // Calculate prefix sums for efficient subarray sum calculation
            int[] prefixSum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            // dp[i][j] represents the maximum sum possible using j + 1 subarrays
            // ending at or before index i
            int[][] dp = new int[n][3];
            // pos[i][j] stores the starting position of the last subarray for state (i,j)
            int[][] pos = new int[n][3];

            // Initialize first subarray (j = 0)
            for (int i = k - 1; i < n; i++) {
                int subarraySum = prefixSum[i + 1] - prefixSum[i - k + 1];
                if (i == k - 1 || subarraySum > dp[i - 1][0]) {
                    dp[i][0] = subarraySum;
                    pos[i][0] = i - k + 1;
                } else {
                    dp[i][0] = dp[i - 1][0];
                    pos[i][0] = pos[i - 1][0];
                }
            }

            // Fill dp table for j = 1 and j = 2
            for (int j = 1; j < 3; j++) {
                // Need at least (j+1)*k length for j+1 subarrays
                for (int i = (j + 1) * k - 1; i < n; i++) {
                    // Get current subarray sum
                    int subarraySum = prefixSum[i + 1] - prefixSum[i - k + 1];

                    // Try adding current subarray to previous solution
                    int combinedSum = dp[i - k][j - 1] + subarraySum;

                    // Update if we found better sum or
                    // equal sum with lexicographically smaller index
                    if (i == (j + 1) * k - 1 || combinedSum > dp[i - 1][j] ||
                            (combinedSum == dp[i - 1][j] && isLexicographicallySmaller(
                                    pos, i - k + 1, i - 1, j))) {
                        dp[i][j] = combinedSum;
                        pos[i][j] = i - k + 1;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                        pos[i][j] = pos[i - 1][j];
                    }
                }
            }

            // Construct result array
            int[] result = new int[3];
            int curr = n - 1;

            // Work backwards to find positions
            for (int j = 2; j >= 0; j--) {
                result[j] = pos[curr][j];
                if (j > 0) {
                    // Move back k positions and look at previous subarray
                    curr = pos[curr][j] - 1;
                }
            }

            return result;
        }

        // Helper method to check if using position pos1 gives lexicographically
        // smaller array than using previous solution ending at pos2
        private boolean isLexicographicallySmaller(int[][] pos, int pos1, int pos2, int j) {
            int[] arr1 = new int[j + 1];
            int[] arr2 = new int[j + 1];

            // Construct arrays for comparison
            int curr = pos2;
            for (int i = j - 1; i >= 0; i--) {
                arr2[i] = pos[curr][i];
                curr = pos[curr][i] - 1;
            }
            arr2[j] = pos[pos2][j];

            curr = pos1 - 1;
            for (int i = j - 1; i >= 0; i--) {
                arr1[i] = pos[curr][i];
                curr = pos[curr][i] - 1;
            }
            arr1[j] = pos1;

            // Compare arrays
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] != arr2[i]) {
                    return arr1[i] < arr2[i];
                }
            }
            return false;
        }
    }
    class SolutionTopDown {
        private int[] nums;
        private int[] prefixSum;
        private int k;
        private Integer[][] memo;
        private int[][] nextPos;
        private int n;

        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            this.nums = nums;
            this.k = k;
            this.n = nums.length;
            this.memo = new Integer[n][4];  // 4 states: 0,1,2,3 subarrays used
            this.nextPos = new int[n][4];   // track positions for reconstruction

            // Calculate prefix sums
            this.prefixSum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            // Initialize nextPos array
            for (int i = 0; i < n; i++) {
                Arrays.fill(nextPos[i], -1);
            }

            // Get maximum sum
            dfs(0, 3);

            // Reconstruct the result
            int[] result = new int[3];
            int count = 0;
            int pos = 0;

            // Follow the nextPos array to build result
            while (count < 3) {
                if (nextPos[pos][3 - count] != -1) {
                    result[count] = nextPos[pos][3 - count];
                    pos = nextPos[pos][3 - count] + k;
                    count++;
                } else {
                    pos++;
                }
            }

            return result;
        }

        private int dfs(int start, int remainingArrays) {
            // Base cases
            if (remainingArrays == 0) return 0;
            if (start > n - remainingArrays * k) return Integer.MIN_VALUE;

            // If already computed
            if (memo[start][remainingArrays] != null) {
                return memo[start][remainingArrays];
            }

            int maxSum = Integer.MIN_VALUE;

            // Try skipping current position
            if (start < n - remainingArrays * k) {
                int skipSum = dfs(start + 1, remainingArrays);
                if (skipSum > maxSum) {
                    maxSum = skipSum;
                    nextPos[start][remainingArrays] = nextPos[start + 1][remainingArrays];
                }
            }

            // Try using current position if possible
            if (start + k <= n) {
                int currentSum = getSubarraySum(start);
                int nextSum = dfs(start + k, remainingArrays - 1);

                if (nextSum != Integer.MIN_VALUE) {
                    int totalSum = currentSum + nextSum;

                    // Update if better sum or lexicographically smaller
                    if (totalSum > maxSum || (totalSum == maxSum &&
                            (nextPos[start][remainingArrays] == -1 ||
                                    start < nextPos[start][remainingArrays]))) {
                        maxSum = totalSum;
                        nextPos[start][remainingArrays] = start;
                    }
                }
            }

            return memo[start][remainingArrays] = maxSum;
        }

        private int getSubarraySum(int start) {
            return prefixSum[start + k] - prefixSum[start];
        }

        // Helper method for debugging
        private void printState(int start, int remaining, int sum) {
            System.out.printf("Start: %d, Remaining: %d, Sum: %d%n",
                    start, remaining, sum);
        }
    }

}
