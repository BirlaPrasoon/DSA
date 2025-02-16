package leetcode.problems.google.hard.dp;

import java.util.Arrays;

public class NumberOfDigitOne {
/*
    Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.



    Example 1:

    Input: n = 13
    Output: 6
    Example 2:

    Input: n = 0
    Output: 0


    Constraints:

            0 <= n <= 109

    */


    private int[][] dp;
    private String num;

    public int countDigitOne(int n) {
        num = String.valueOf(n);
        int len = num.length();
        dp = new int[len][10];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, true);
    }

    private int dfs(int pos, int count, boolean tight) {

        if (pos == num.length()) {
            return count;
        }

        if (!tight && dp[pos][count] != -1) {
            return dp[pos][count];
        }

        int limit = tight ? num.charAt(pos) - '0' : 9;
        int result = 0;

        for (int digit = 0; digit <= limit; digit++) {
            boolean newTight = tight && (digit == limit);
            int newCount = count + (digit == 1 ? 1 : 0);
            result += dfs(pos + 1, newCount, newTight);
        }

        if (!tight) {
            dp[pos][count] = result;
        }

        return result;
    }
}
