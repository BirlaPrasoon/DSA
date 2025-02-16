package leetcode.problems.google.hard.dp;

import leetcode.Pair;

import java.util.HashMap;

public class DistinctSubSequences {
/*
    Given two strings s and t, return the number of distinct subsequences of s which equals t.

    The test cases are generated so that the answer fits on a 32-bit signed integer.

    Example 1:

    Input: s = "rabbbit", t = "rabbit"
    Output: 3
    Explanation:
    As shown below, there are 3 ways you can generate "rabbit" from s.
    rabbbit
    rabbbit
    rabbbit
    Example 2:

    Input: s = "babgbag", t = "bag"
    Output: 5
    Explanation:
    As shown below, there are 5 ways you can generate "bag" from s.
    babgbag
    babgbag
    babgbag
    babgbag
    babgbag

    Constraints:

    1 <= s.length, t.length <= 1000
    s and t consist of English letters.

        */

    class Solution {
        // Dictionary that we will use for memoization
        private Integer dp[][];

        private int recurse(String s, String t, int i, int j) {
            int M = s.length();
            int N = t.length();

            // Base case
            if (i == M || j == N || M - i < N - j) {
                return j == t.length() ? 1 : 0;
            }
            if(dp[i][j] != null) return dp[i][j];


            // Always calculate this result since it's
            // required for both the cases
            int ans = this.recurse(s, t, i + 1, j);

            // If the characters match, then we make another
            // recursion call and add the result to "ans"
            if (s.charAt(i) == t.charAt(j)) {
                ans += this.recurse(s, t, i + 1, j + 1);
            }

            dp[i][j] = ans;
            return ans;
        }

        public int numDistinct(String s, String t) {
            dp = new Integer[s.length()][t.length()];
            return this.recurse(s, t, 0, 0);
        }
    }

}
