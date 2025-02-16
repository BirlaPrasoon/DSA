package leetcode.problems.google.medium.greedy;

public class LongestBinarySubSequenceSmallerOrEqualToK {

    /**
     * Problem Statement: <br>
     * You are given a binary string `s` consisting of digits '0' and '1', and an integer `k`. <br>
     * Your task is to find the **longest subsequence** of the string such that the integer value of the subsequence <br>
     * is **less than or equal to k**. <br>
     * <br>
     * A **subsequence** of a string is obtained by deleting some (or no) characters from the string without changing <br>
     * the order of the remaining characters. <br>
     * <br>
     * Return the **length of the longest subsequence** such that its integer value is **less than or equal to `k`**. <br>
     * <br>
     * Constraints: <br>
     * - `1 <= s.length <= 100000` <br>
     * - `s[i]` is either '0' or '1'. <br>
     * - `1 <= k <= 10^9` <br>
     * <br>
     * Example 1: <br>
     * Input: <br>
     *   s = "1001010", k = 5 <br>
     * Output: <br>
     *   5 <br>
     * Explanation: <br>
     *   The longest subsequence that can be selected is "10010", which has a value of 18. <br>
     *   This subsequence's value is less than or equal to `k = 5`, so the length of the subsequence is 5. <br>
     * <br>
     * Example 2: <br>
     * Input: <br>
     *   s = "1101101", k = 10 <br>
     * Output: <br>
     *   6 <br>
     * Explanation: <br>
     *   The longest subsequence that can be selected is "110101", which has a value of 53. <br>
     *   This subsequence's value is less than or equal to `k = 10`, so the length of the subsequence is 6. <br>
     */

    class Solution {

        /**
         * Finds the length of the longest subsequence of the binary string `s` <br>
         * whose value is less than or equal to `k`. <br>
         *
         * @param s The binary string representing the sequence of bits. <br>
         * @param k The integer value that the subsequence's value must be less than or equal to. <br>
         * @return The length of the longest subsequence that satisfies the condition. <br>
         */
        public int longestSubsequence(String s, int k) {
            int n = s.length();  // Length of the binary string. <br>
            int cost = 1;        // Initialize cost to 1 (representing the value of the least significant bit). <br>
            int res = 0;         // Initialize result to track the valid subsequence length. <br>

            // Iterate through the string from right to left (starting from the least significant bit). <br>
            for (int i = n - 1; i >= 0; i--) {

                // If the current bit is '0' or adding this bit to the subsequence doesn't exceed k, <br>
                // include the bit in the subsequence. <br>
                if (s.charAt(i) == '0' || cost <= k) {
                    k -= cost * (s.charAt(i) - '0');  // If it's '1', subtract the value from k. <br>
                    res++;  // Increment result as this bit is included in the subsequence. <br>
                }

                // Double the cost for the next more significant bit. <br>
                if (cost <= k) {
                    cost *= 2;
                }
            }

            // Return the length of the longest valid subsequence. <br>
            return res;
        }
    }


}
