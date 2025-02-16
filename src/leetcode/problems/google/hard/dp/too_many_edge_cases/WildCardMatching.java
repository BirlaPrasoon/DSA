package leetcode.problems.google.hard.dp.too_many_edge_cases;

import java.util.Arrays;

public class WildCardMatching {


    // "adceb"
    // "*a*b"


    class SolutionLinearTime {
        public boolean isMatch(final String s, final String p) {
            int i = 0, j = 0, lastStarAt = -1, lastMatchedAt = -1;

            while(i < s.length()) {
                if(j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                    i++;
                    j++;
                } else if(j < p.length() && p.charAt(j) == '*') {
                    lastStarAt = j++;
                    lastMatchedAt = i;
                } else if(lastStarAt != -1) {
                    j = lastStarAt + 1;
                    i = ++lastMatchedAt;
                } else {
                    return false;
                }
            }

            while(j < p.length() && p.charAt(j) == '*')
                j++;

            return i == s.length() && j == p.length();
        }
    }

    static class Solution {

        public boolean isMatch(String s, String p) {
            if (p.isEmpty() && !s.isEmpty()) return false;
            if (allStars(p)) return true;
            Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];

            dp[0][0] = true;
            for (int j = 1; j <= p.length(); j++) {
                boolean ans = true;
                for (int ii = 1; ii <= j; ii++) {
                    if (p.charAt(ii - 1) != '*') {
                        ans = false;
                        break;
                    }
                }
                dp[0][j] = ans;
            }
            for (int i = 1; i <= s.length(); i++) {
                dp[i][0] = false;
            }

            for (int i = 1; i <= s.length(); i++) {
                for (int j = 1; j <= p.length(); j++) {
                    if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (p.charAt(j - 1) == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }

            return dp[s.length()][p.length()];
        }

        public boolean isMatchRecursive(String s, String p) {
            if (p.isEmpty() && !s.isEmpty()) return false;
            if (allStars(p)) return true;
            Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];
            return f(s.length(), p.length(), s, p, dp);
        }

        private boolean f(int i, int j, String s, String p, Boolean[][] dp) {
            if (j == 0 && i == 0) return true;
            if (j == 0 && i > 0) {
                return false;
            }
            if (i == 0 && j >= 0) {
                for (int ii = 1; ii <= j; ii++) {
                    if (p.charAt(ii - 1) != '*') return false;
                }
                return true;
            }

            if (dp[i][j] != null) return dp[i][j];
            if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?') {
                return dp[i][j] = f(i - 1, j - 1, s, p, dp);
            } else if (p.charAt(j - 1) == '*') {
                return dp[i][j] = f(i - 1, j, s, p, dp) || f(i, j - 1, s, p, dp);
            }

            dp[i][j] = false;
            return false;
        }


        boolean allStars(String x) {
            for (char c : x.toCharArray()) if (c != '*') return false;
            return true;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.isMatch("aa", "a"));
        }
    }

    static class SolutionBottomUp {
        public boolean isMatch(String s, String p) {
            Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];
            if (allStars(p)) return true;
            return solve(s, p, 0, 0, dp);
        }

        boolean allStars(String x) {
            for (char c : x.toCharArray()) if (c != '*') return false;
            return true;
        }

        private boolean solve(String s, String p, int i, int j, Boolean[][] dp) {
            if (i > s.length() || j > p.length()) return false;
            if (dp[i][j] != null) return dp[i][j];
            boolean ans = false;
            if (i == s.length() && j == p.length()) {
                ans = true;
            } else if (i == s.length() || j == p.length()) {
                if (i == s.length()) {
                    ans = allStars(p.substring(j));
                }
            } else {
                char charAtI = s.charAt(i);
                char charAtJ = p.charAt(j);
                if (charAtJ == '?' || charAtJ == charAtI) {
                    ans = solve(s, p, i + 1, j + 1, dp);
                } else if (charAtJ == '*') {
                    ans = solve(s, p, i + 1, j, dp) || solve(s, p, i + 1, j + 1, dp) || solve(s, p, i, j + 1, dp);
                }
            }

            dp[i][j] = ans;
            return ans;
        }
    }

}
