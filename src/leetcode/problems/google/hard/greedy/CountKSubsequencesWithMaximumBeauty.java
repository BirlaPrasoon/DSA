package leetcode.problems.google.hard.greedy;

import java.util.Arrays;

public class CountKSubsequencesWithMaximumBeauty {

    /**
     * LeetCode Problem 2842: Count K-Subsequences of a String With Maximum Beauty
     * <p>
     * Given a string s of length n, you need to count the number of k-subsequences of s
     * that have the maximum possible beauty. A k-subsequence is defined as a subsequence
     * of length k where all characters are unique. The beauty of a k-subsequence is
     * defined as the sum of the frequencies of its characters in the original string s.
     * <p>
     * The number of k-subsequences with maximum beauty needs to be returned modulo 10^9 + 7.
     *
     * <p>
     * A string's beauty is the sum of the frequencies of the characters present in the
     * string in the original string. For example, if 'a' appears 3 times and 'b' appears 2
     * times, then the beauty of the subsequence 'ab' is 3 + 2 = 5.
     * </p>
     * <p>
     * You need to find how many such k-subsequences exist in the string s with the maximum beauty.
     * If no such subsequence exists, return 0.
     * <p>
     * Example 1:
     * <pre>
     * Input: s = "aab", k = 2
     * Output: 3
     * Explanation: The three subsequences that have maximum beauty are: "aa", "ab", "ba".
     * </pre>
     * <p>
     * Example 2:
     * <pre>
     * Input: s = "aaa", k = 2
     * Output: 0
     * Explanation: It's not possible to have a subsequence of length 2 with unique characters.
     * </pre>
     * <p>
     * Constraints:
     * <ul>
     * <li>1 <= n <= 100</li>
     * <li>1 <= k <= 26</li>
     * <li>s consists of lowercase English letters only.</li>
     * </ul>
     * <p>
     * Note:
     * The result should be returned modulo 10^9 + 7.
     */

    public int countKSubsequencesWithMaxBeauty(String s, int k) {

        /**
         *
         * Intuition
            We will pick k characters with the biggest frequency.
            There is a bar to be selected.
            If freq > bar, will be selected.
            If freq < bar, won't be selected.
            If freq == bar, will become a pending candidates.


            Explanation
            We firstly frequencies the frequency of all characters.
            Sort the frequency, and find out the kth biggest frequency, which is the bar.

            Then we iterate all frequencies,
            If freq > bar, will be selected, res = res * freq % mod
            If freq == bar,
            will become a pending candidates, and we only need some of them.

            Finally, we return product(k biggest frequencies) * combination(pending, need),


        Complexity
        Time O(n)
        Space O(26)
        * */

        int n = s.length();
        int[] frequencies = new int[26];
        for (int i = 0; i < n; ++i)
            frequencies[s.charAt(i) - 'a']++;

        Arrays.sort(frequencies);

        if (k > 26 || frequencies[26 - k] == 0)
            return 0;

        long res = 1, comb = 1, mod = (long) 1e9 + 7, bar = frequencies[26 - k], pend = 0;
        for (int freq : frequencies) {
            if (freq > bar) {
                k--;
                res = res * freq % mod;
            }
            if (freq == bar) pend++;
        }
        for (int i = 0; i < k; ++i) {
            comb = comb * (pend - i) / (i + 1);
            res = res * bar % mod;
        }
        return (int) (res * comb % mod);
    }

}
