package leetcode.problems.google.hard.twoPointer;

import algoexpert.graphs.BoggleBoard;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    static class Solution {
        public String minWindow(String s, String t) {
            // todo: This base case is very very important.
            if (s.length() < t.length()) {
                return "";
            }

            Map<Character, Integer> charCount = new HashMap<>();
            for (char ch : t.toCharArray()) {
                charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
            }

            int targetCharsRemaining = t.length();
            int fStart = 0, fEnd = Integer.MAX_VALUE;
            int startIndex = 0;

            for (int endIndex = 0; endIndex < s.length(); endIndex++) {
                char ch = s.charAt(endIndex);
                if (charCount.containsKey(ch) && charCount.get(ch) > 0) {
                    targetCharsRemaining--;
                }
                charCount.put(ch, charCount.getOrDefault(ch, 0) - 1);

                if (targetCharsRemaining == 0) {
                    while (true) {
                        char charAtStart = s.charAt(startIndex);
                        // What does this represent? When do we break out of this loop, as soon as we have our first mismatch...
                        // startIndex does not represent first character of our target string, instead it represents,
                        // after removing this character we are losing one character from our target string.
                        // Extra characters will be represented by -ve count, which is ok, we can't afford +ve values.
                        // Remove characters till our cur character from start is -ve.
                        if (charCount.containsKey(charAtStart) && charCount.get(charAtStart) == 0) {
                            break;
                        }
                        charCount.put(charAtStart, charCount.getOrDefault(charAtStart, 0) + 1);
                        startIndex++;
                    }

                    if (endIndex - startIndex < fEnd - fStart) {
                        fStart= startIndex;
                        fEnd = endIndex;
                    }

                    // Now remove this character from the string as we've to complete the ending move,
                    charCount.put(s.charAt(startIndex), charCount.getOrDefault(s.charAt(startIndex), 0) + 1);
                    targetCharsRemaining++;
                    startIndex++;
                }
            }

            return fEnd >= s.length() ? "" : s.substring(fStart, fEnd + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minWindow("ADOBECODEBANC", "ABC"));
    }
}
