package leetcode.problems.google.hard.greedy;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class LexicographicallySmallestBeautifulString {

    /**
     * LeetCode Problem 2663: Lexicographically Smallest Beautiful String
     * <p>
     * You are given a string `s` of length `n` consisting of the first `k` lowercase English letters (i.e., 'a' to the `k`-th letter).
     * A string is considered *beautiful* if it does not contain any palindromic substring of length 2 or more.
     * <p>
     * Your task is to find the lexicographically smallest string of length `n` that is greater than `s` and is beautiful.
     * If no such string exists, return an empty string.
     *
     * <p>
     * A string is lexicographically smaller than another string if it is smaller in terms of dictionary order.
     * For example, "abc" < "abd", and "acb" < "adc".
     * </p>
     *
     * <p>
     * A string is beautiful if there are no palindromic substrings. A palindrome is a word or phrase that reads the same forward and backward.
     * </p>
     * <p>
     Example 1:

     Input: s = "abcz", k = 26
     Output: "abda"
     Explanation: The string "abda" is beautiful and lexicographically larger than the string "abcz".
     It can be proven that there is no string that is lexicographically larger than the string "abcz", beautiful, and lexicographically smaller than the string "abda".
     Example 2:

     Input: s = "dc", k = 4
     Output: ""
     Explanation: It can be proven that there is no string that is lexicographically larger than the string "dc" and is beautiful.
     * <p>
     * Constraints:
     * <ul>
     * <li>1 <= n <= 10^5</li>
     * <li>2 <= k <= 26</li>
     * <li>s consists of the first k lowercase English letters.</li>
     * </ul>
     * <p>
     * Note:
     * If there is no lexicographically greater string that satisfies the condition, return an empty string.
     */
    private static char next(char ch) {
        return (char) (ch + 1);
    }

    private static boolean isValid(char[] chars, int i) {
        return (i == 0 || chars[i - 1] != chars[i]) && (i < 2 || chars[i - 2] != chars[i]);
    }

    public String smallestBeautifulString(String s, int k) {
        var chars = s.toCharArray();
        int i = s.length() - 1;
        while (i >= 0 && i < chars.length) {
            chars[i] = next(chars[i]);
            while (chars[i] < 'a' + k && !isValid(chars, i)) {
                chars[i] = next(chars[i]);
            }
            if (chars[i] >= 'a' + k) {
                chars[i--] = 'a' - 1;
            } else {
                i++;
            }
        }
        return i < 0 ? "" : new String(chars);
    }
}
