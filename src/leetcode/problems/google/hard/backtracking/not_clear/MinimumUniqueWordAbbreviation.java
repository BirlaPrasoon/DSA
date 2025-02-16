package leetcode.problems.google.hard.backtracking.not_clear;

import java.util.*;

public class MinimumUniqueWordAbbreviation {
    /**
     * A string can be abbreviated by replacing any number of non-adjacent substrings with their lengths. For example, a string such as "substitution" could be abbreviated as (but not limited to):
     * <p>
     * "s10n" ("s ubstitutio n")
     * "sub4u4" ("sub stit u tion")
     * "12" ("substitution")
     * "su3i1u2on" ("su bst i t u ti on")
     * "substitution" (no substrings replaced)
     * Note that "s55n" ("s ubsti tutio n") is not a valid abbreviation of "substitution" because the replaced substrings are adjacent.
     * <p>
     * The length of an abbreviation is the number of letters that were not replaced plus the number of substrings that were replaced. For example, the abbreviation "s10n" has a length of 3 (2 letters + 1 substring) and "su3i1u2on" has a length of 9 (6 letters + 3 substrings).
     * <p>
     * Given a target string target and an array of strings dictionary, return an abbreviation of target with the shortest possible length such that it is not an abbreviation of any string in dictionary. If there are multiple shortest abbreviations, return any of them.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: target = "apple", dictionary = ["blade"]
     * Output: "a4"
     * Explanation: The shortest abbreviation of "apple" is "5", but this is also an abbreviation of "blade".
     * The next shortest abbreviations are "a4" and "4e". "4e" is an abbreviation of blade while "a4" is not.
     * Hence, return "a4".
     * Example 2:
     * <p>
     * Input: target = "apple", dictionary = ["blade","plain","amber"]
     * Output: "1p3"
     * Explanation: "5" is an abbreviation of both "apple" but also every word in the dictionary.
     * "a4" is an abbreviation of "apple" but also "amber".
     * "4e" is an abbreviation of "apple" but also "blade".
     * "1p3", "2p2", and "3l1" are the next shortest abbreviations of "apple".
     * Since none of them are abbreviations of words in the dictionary, returning any of them is correct.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * m == target.length
     * n == dictionary.length
     * 1 <= m <= 21
     * 0 <= n <= 1000
     * 1 <= dictionary[i].length <= 100
     * log2(n) + m <= 21 if n > 0
     * target and dictionary[i] consist of lowercase English letters.
     * dictionary does not contain target.
     */

    class Solution {
        public String minAbbreviation(String target, String[] dictionary) {
            Set<String> abbrList = new HashSet<>();
            ansFound = false;
            for (String word : dictionary) {
                if (word.length() == target.length()) {
                    dfs(word.toCharArray(), abbrList, 0, word.length(), "");
                }
            }
            if (abbrList.isEmpty()) return String.valueOf(target.length());
            Set<String> targetWords = new HashSet<>();
            dfs(target.toCharArray(), targetWords, 0, target.length(), "");
            List<String> list = new ArrayList<>(targetWords);

            list.sort((n1, n2) -> n1.length() == n2.length() ? n1.compareTo(n2) : n1.length() - n2.length());

            for (String str : list) {
                if (!abbrList.contains(str)) return str;
            }

            return "";
        }

        String ans1;
        boolean ansFound;

        void dfs(char[] word, Set<String> ans, int st, int ed, String temp) {
            if (st > ed) return;
            if (st == ed) {
                ans.add(temp);
                return;
            }
            int num = 0;
            int i = temp.length() - 1;
            int len = temp.length() - 1;
            while (i >= 0 && temp.charAt(i) >= '0' && temp.charAt(i) <= '9') {
                num = (temp.charAt(i) - '0') * (int) Math.pow(10, (len - i)) + num;
                i--;
            }
            num = num + 1;
            dfs(word, ans, st + 1, ed, temp + word[st]);
            dfs(word, ans, st + 1, ed, temp.substring(0, i + 1) + num);

        }
    }

}
