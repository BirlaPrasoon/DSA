package leetcode.problems.google.medium.arrays;

public class DeleteAndEarn {

    // Tabulation
    public class Solution {
        public int deleteAndEarn(int[] nums) {
            // Define the maximum value for array size
            final int MAX = 10005;
            int[] sums = new int[MAX];

            // Calculate the total points for each value
            for (int num : nums) {
                sums[num] += num;
            }

            // Initialize the DP array
            int[] dp = new int[MAX];
            dp[1] = sums[1];
            dp[2] = Math.max(sums[1], sums[2]);

            // Fill the DP array
            for (int i = 3; i < MAX; i++) {
                dp[i] = Math.max(sums[i] + dp[i - 2], dp[i - 1]);
            }

            // Return the maximum value from the last two elements of DP
            return Math.max(dp[MAX - 1], dp[MAX - 2]);
        }
    }
}
