package leetcode.problems.google.hard.strings;

import java.util.Arrays;

public class L2472_MaxPalindromes {
    public static void main(String[] args) {
        L2472_MaxPalindromes obj = new L2472_MaxPalindromes();
        System.out.println(obj.maxPalindromesN2("0000000", 5));
    }

    /*
     * Main catch here was to avoid picking a palindromic substring with size greater than k+1
     * because if there exist a palindromic substring with size greater than k+1 then it would
     *  definitely contain a palindromic substring with size at least k ( It was just basic observation )
     *  And we are trying to minimize the length of the palindromic string ( with size >= k ) to maximize our answer.
     * */
    public int maxPalindromes(String s, int k) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < s.length(); i++) {
            int j = i + k - 1;
            if (j < n && isPalindrome(s, i, j)) {
                ans++;
                i = j;
                continue;
            }
            j++;
            if (j < n && isPalindrome(s, i, j)) {
                i = j;
                ans++;
            }
        }
        return ans;
    }

    boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

    public int maxPalindromesN2(String s, int k) {
        int[] dp = new int[2005];
        Arrays.fill(dp, -1);
        return solve(0, k, s, dp);
    }

    private int solve(int index, int k, String s, int[] dp) {
        if (k > s.length()) {
            dp[index] = 0;
            return 0;
        }
        if (dp[index] != -1) return dp[index];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 >= k) {
                String t = s.substring(0, i + 1);
                if (isPalindrome(t)) {
                    int count = solve(index + i + 1, k, s.substring(i + 1), dp);
                    max = Math.max(count + 1, max);
                    break;
                } else {
                    int count = solve(index + i + 1, k, s.substring(i + 1), dp);
                    max = Math.max(count, max);
                }
            } else {
                int count = solve(index + i + 1, k, s.substring(i + 1), dp);
                max = Math.max(count, max);
            }
        }

        if (max == Integer.MIN_VALUE) {
            dp[index] = 0;
            return 0;
        }
        dp[index] = max;
        return max;
    }

    private boolean isPalindrome(String s) {
        if (s.length() == 1) return true;
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else return false;
        }

        return true;
    }
}
