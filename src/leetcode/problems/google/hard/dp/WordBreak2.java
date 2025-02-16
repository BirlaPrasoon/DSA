package leetcode.problems.google.hard.dp;

import java.util.*;

public class WordBreak2 {
    /**
     * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.<br>
     * <p>
     * Note that the same word in the dictionary may be reused multiple times in the segmentation.<br>
     * <p>
     * Example 1:<br>
     * <p>
     * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]<br>
     * Output: ["cats and dog","cat sand dog"]<br><br>
     * Example 2:<br>
     * <p>
     * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]<br>
     * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]<br>
     * Explanation: Note that you are allowed to reuse a dictionary word.<br><br>
     * Example 3:<br>
     * <p>
     * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]<br>
     * Output: []<br>
     *
     * <br><br>
     * Constraints:<br>
     * 1 <= s.length <= 20<br>
     * 1 <= wordDict.length <= 1000<br>
     * 1 <= wordDict[i].length <= 10<br>
     * s and wordDict[i] consist of only lowercase English letters.<br>
     * All the strings of wordDict are unique.<br>
     * Input is generated in a way that the length of the answer doesn't exceed 10<sup>5</sup>.<br>
     */

    class Solution {

        // Main function to break the string into words
        public List<String> wordBreak(String s, List<String> wordDict) {
            Set<String> wordSet = new HashSet<>(wordDict);
            Map<String, List<String>> memoization = new HashMap<>();
            return dfs(s, wordSet, memoization);
        }

        // Depth-first search function to find all possible word break combinations
        private List<String> dfs(String remainingStr, Set<String> wordSet, Map<String, List<String>> memoization) {
            // Check if result for this substring is already memoized
            if (memoization.containsKey(remainingStr)) {
                return memoization.get(remainingStr);
            }

            // Base case: when the string is empty, return a list containing an empty string
            if (remainingStr.isEmpty()) return Collections.singletonList("");
            List<String> results = new ArrayList<>();
            for (int i = 1; i <= remainingStr.length(); ++i) {
                String currentWord = remainingStr.substring(0, i);
                // If the current substring is a valid word
                if (wordSet.contains(currentWord)) {
                    for (String nextWord : dfs(remainingStr.substring(i), wordSet, memoization)) {
                        // Append current word and next word with space in between if next word exists
                        results.add(currentWord + (nextWord.isEmpty() ? "" : " ") + nextWord);
                    }
                }
            }
            // Memoize the results for the current substring
            memoization.put(remainingStr, results);
            return results;
        }
    }

}
