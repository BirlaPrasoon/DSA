package leetcode.problems.google.hard.dp;

import java.util.Arrays;
import java.util.List;

public class WordBreak {
/*
    Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

    Note that the same word in the dictionary may be reused multiple times in the segmentation.



    Example 1:

    Input: s = "leetcode", wordDict = ["leet","code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".
    Example 2:

    Input: s = "applepenapple", wordDict = ["apple","pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
    Note that you are allowed to reuse a dictionary word.
    Example 3:

    Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
    Output: false


    Constraints:

            1 <= s.length <= 300
            1 <= wordDict.length <= 1000
            1 <= wordDict[i].length <= 20
    s and wordDict[i] consist of only lowercase English letters.
    All the strings of wordDict are unique.
        */

    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            int dp[] = new int[s.length()+1];
            Arrays.fill(dp, -1);
            // -2, can't go any further;
            return dfs(0, s, wordDict, dp);
        }

        private boolean dfs(int index, String s, List<String> dict, int dp[]) {
            if(s.isEmpty()) {
                dp[index] = 1;
                return true;
            }
            if(dp[index] == -2) return false;
            if(dp[index] == 1) return true;
            boolean found = false;
            boolean ans = false;
            for(String word: dict) {
                if(s.startsWith(word)) {
                    found = true;
                    ans = dfs(index + word.length(), s.substring(word.length()), dict, dp);
                    if(ans) break;
                }
            }
            if(!found)  {
                dp[index] = -2;
                return false;
            }
            dp[index] = ans ? 1: -2;
            return ans;
        }
    }

}
