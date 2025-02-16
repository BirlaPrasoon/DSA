package leetcode.problems.google.medium.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PartitionLabels {

    // Interesting one... Remember the whole problem. I could do it if smaller characters appears first.
    // But not when characters are distributed randomnly.

/*
    You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.

    Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

    Return a list of integers representing the size of these parts.



            Example 1:

    Input: s = "ababcbacadefegdehijhklij"
    Output: [9,7,8]
    Explanation:
    The partition is "ababcbaca", "defegde", "hijhklij".
    This is a partition so that each letter appears in at most one part.
    A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
    Example 2:

    Input: s = "eccbbbbdec"
    Output: [10]


    Constraints:

            1 <= s.length <= 500
    s consists of lowercase English letters.
    */


    class Solution {
        public List<Integer> partitionLabels(String s) {
            HashMap<Character, Integer> firstIndexOfCharacter = new HashMap<>();
            HashMap<Character, Integer> lastIndexOfCharacter = new HashMap<>();
            int j = s.length() - 1;
            for (int i = 0; i < s.length(); i++, j--) {
                if (!firstIndexOfCharacter.containsKey(s.charAt(i))) firstIndexOfCharacter.put(s.charAt(i), i);
                if (!lastIndexOfCharacter.containsKey(s.charAt(j))) lastIndexOfCharacter.put(s.charAt(j), j);
            }

            int start = firstIndexOfCharacter.get(s.charAt(0));
            int end = lastIndexOfCharacter.get(s.charAt(0));

            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                if (i == end) {
                    // range complete
                    ans.add(s.substring(start, end + 1).length());
                    if (i + 1 < s.length()) {
                        start = firstIndexOfCharacter.get(s.charAt(i + 1));
                        end = lastIndexOfCharacter.get(s.charAt(i + 1));
                    }
                } else {
                    if (lastIndexOfCharacter.get(s.charAt(i)) > end) {
                        end = lastIndexOfCharacter.get(s.charAt(i));
                    }
                }
            }
            return ans;
        }
    }

}
