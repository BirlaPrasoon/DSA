package leetcode.problems.google.hard.strings;

public class CountNumberOfPalindromicSubstrings {
    static int countPS(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Every single character is a palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill the table for substrings of length greater than 1
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {

                int j = i + length - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1]
                            - dp[i + 1][j - 1];
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "geeksforgeeks";
        System.out.println(countPS(s));
    }

}
