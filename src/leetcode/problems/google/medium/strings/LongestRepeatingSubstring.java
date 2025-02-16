package leetcode.problems.google.medium.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestRepeatingSubstring {
    //Given a string s, return the length of the longest repeating substrings. If no repeating substring exists, return 0.

    /*
    * Example 1:

Input: s = "abcd"
Output: 0
Explanation: There is no repeating substring.
Example 2:

Input: s = "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
Example 3:

Input: s = "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.
    *
    * */

    // Very good article
    // https://leetcode.com/problems/longest-repeating-substring/editorial/
    // O(N2)
    static class SolutionDP {

        /*
        *
        Setting Up the DP Table:

        Imagine a table where each cell (i, j) records the length of the longest common suffix of substrings ending at i and j.
*       A suffix here means the end portion of a substring.

        * Filling the DP Table:

        We start filling the table by comparing each character in the string with every other character that comes after it. If S[i] == S[j] and i != j (to avoid comparing the same character), it means the characters match, and we can extend the length of the common suffix we found previously by 1.
        This is recorded in the DP table as dp[i][j] = dp[i-1][j-1] + 1.
        Extracting the Result:

        The maximum value in the DP table represents the length of the longest repeating substring found.

        * */
        public int longestRepeatingSubstring(String s) {
            int length = s.length();
            int[][] dp = new int[length + 1][length + 1];
            int maxLength = 0;

            // Use DP to find the longest common substring
            for (int i = 1; i <= length; i++) {
                for (int j = i + 1; j <= length; j++) {
                    // If characters match, extend the length of
                    // the common substring
                    if (s.charAt(i - 1) == s.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        maxLength = Math.max(maxLength, dp[i][j]);
                    }
                }
            }
            return maxLength;
        }
    }
    // O(N2LogN)
    class SolutionSuffixArrayWithSorting {

        public int longestRepeatingSubstring(String s) {
            int length = s.length();
            String[] suffixes = new String[length];

            // Create suffix array
            for (int i = 0; i < length; i++) {
                suffixes[i] = s.substring(i);
            }
            // Sort the suffixes
            Arrays.sort(suffixes);

            int maxLength = 0;
            // Find the longest common prefix between consecutive sorted suffixes
            for (int i = 1; i < length; i++) {
                int j = 0;
                while (
                        j < Math.min(suffixes[i].length(), suffixes[i - 1].length()) &&
                                suffixes[i].charAt(j) == suffixes[i - 1].charAt(j)
                ) {
                    j++;
                }
                maxLength = Math.max(maxLength, j);
            }
            return maxLength;
        }
    }
    // O(N2LogN)
    class SolutionBinarySearchWithSet {

        /*
        *

        Convert the input string s into a character array characters.

        Initialize start to 1 and end to characters.length - 1.

        Use binary search to find the maximum length of a repeating substring:

        Calculate mid as the average of start and end.
        If a repeating substring of length mid exists (hasRepeatingSubstring), set start to mid + 1.
        Otherwise, set end to mid - 1.
        Return start - 1 as the length of the longest repeating substring.

        Define hasRepeatingSubstring function:

        Initialize seenSubstrings as a Set to store substrings of length length.
        Iterate over the characters array to extract substrings of the specified length.
        If a substring is already in seenSubstrings, return true.
        Add the substring to seenSubstrings.
        If no repeating substring is found, return false.

        * */
        public int longestRepeatingSubstring(String s) {
            char[] characters = s.toCharArray();
            int start = 1, end = characters.length - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                // Check if there's a repeating substring of length mid
                if (hasRepeatingSubstring(characters, mid)) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            return start - 1;
        }

        private boolean hasRepeatingSubstring(char[] characters, int length) {
            Set<String> seenSubstrings = new HashSet<>();
            for (int i = 0; i <= characters.length - length; i++) {
                String substring = new String(characters, i, length);
                if (!seenSubstrings.add(substring)) {
                    return true;
                }
            }
            return false;
        }
    }
}
