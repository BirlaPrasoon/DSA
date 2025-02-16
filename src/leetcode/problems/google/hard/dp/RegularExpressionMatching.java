package leetcode.problems.google.hard.dp;

public class RegularExpressionMatching {
/*
    Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

            '?' Matches any single character.
            '*' Matches any sequence of characters (including the empty sequence).
    The matching should cover the entire input string (not partial).



    Example 1:

    Input: s = "aa", p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".
    Example 2:

    Input: s = "aa", p = "*"
    Output: true
    Explanation: '*' matches any sequence.
            Example 3:

    Input: s = "cb", p = "?a"
    Output: false
    Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.


    Constraints:

            0 <= s.length, p.length <= 2000
    s contains only lowercase English letters.
    p contains only lowercase English letters, '?' or '*'.
    */

    // "", "*****"
    // "abc", "*"
    // "bcd", "b*d"


    static class Solution {
        public boolean isMatch(String s, String p) {
            Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];
            if(s.isEmpty()) {
                return emptyPattern(p);
            }
            return solve(s, p, 0, 0, dp);
        }

        boolean emptyPattern(String x) {
            for(char c: x.toCharArray()) if(c != '*') return false;
            return true;
        }

        private boolean solve(String s, String p, int i, int j, Boolean[][] dp) {
            if (i > s.length() || j > p.length())
                return false;
            if (dp[i][j] != null)
                return dp[i][j];
            boolean ans = false;
            if (i == s.length() && j == p.length()) {
                ans = true;
            } else if (i == s.length() || j == p.length()) {
                if(i == s.length()) {
                    ans = emptyPattern(p.substring(j));
                } else ans = false;
            } else {
                char charAtI = s.charAt(i);
                char charAtJ = p.charAt(j);
                if (charAtJ == '?' || charAtJ == charAtI) {
                    ans = solve(s, p, i + 1, j + 1, dp);
                } else if (charAtJ == '*') {
                    ans = solve(s, p, i + 1, j, dp) || solve(s, p, i + 1, j + 1, dp) || solve(s, p, i, j+1, dp);
                } else ans = false;
            }

            dp[i][j] = ans;
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution r = new Solution();
        System.out.println(r.isMatch("mississippi", "mis*is*p*?"));
    }
}
