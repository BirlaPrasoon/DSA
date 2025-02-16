package leetcode.problems.google.hard.dp.dp_with_two_pointer;

import java.util.Arrays;

public class MinimumWindowSubsequence {


    public class Solution {
/*

        In this approach, we'll use a DP table to store the starting index in S of each subsequence ending at each character in T.
        Here's the optimized solution:

        Optimized Dynamic Programming Solution
        The idea is to use a 2D DP array where:

        dp[i][j] represents the starting index in S of the subsequence that matches the first j characters of T ending at S[i].
        If dp[i][j] == -1, it means it’s not possible to match the first j characters of T ending at S[i].
        */

        public String minWindow(String S, String T) {
            int sLen = S.length(), tLen = T.length();
            int[][] dp = new int[sLen][tLen];

            // Initialize DP array
            for (int i = 0; i < sLen; i++) {
                Arrays.fill(dp[i], -1);
            }

            // Set up the DP base case for the first character of T
            dp[0][0] = (S.charAt(0) == T.charAt(0)) ? 0 : -1;
            for (int i = 1; i < sLen; i++) {
                dp[i][0] = (S.charAt(i) == T.charAt(0)) ? i : dp[i - 1][0];
            }

            // Fill in the DP table
            for (int j = 1; j < tLen; j++) {
                int previousIndex = -1; // Track the last valid index for `dp[i][j-1]`
                for (int i = 1; i < sLen; i++) {
                    if (dp[i - 1][j - 1] != -1 && S.charAt(i) == T.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            // Find the minimum window length
            int minLength = Integer.MAX_VALUE;
            int start = -1;
            for (int i = 0; i < sLen; i++) {
                if (dp[i][tLen - 1] != -1) {
                    int windowLength = i - dp[i][tLen - 1] + 1;
                    if (windowLength < minLength) {
                        minLength = windowLength;
                        start = dp[i][tLen - 1];
                    }
                }
            }

            return start == -1 ? "" : S.substring(start, start + minLength);
        }
    }


}
