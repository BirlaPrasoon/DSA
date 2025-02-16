package leetcode.problems.google.hard.backtracking;

import java.util.*;

public class VerbalArithmeticPuzzle {

    /**
     * Given an equation, represented by words on the left side and the result on the right side.
     * <p>
     * You need to check if the equation is solvable under the following rules:
     * <p>
     * Each character is decoded as one digit (0 - 9).
     * No two characters can map to the same digit.
     * Each words[i] and result are decoded as one number without leading zeros.
     * Sum of numbers on the left side (words) will equal to the number on the right side (result).
     * Return true if the equation is solvable, otherwise return false.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: words = ["SEND","MORE"], result = "MONEY"
     * Output: true
     * Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
     * Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
     * Example 2:
     * <p>
     * Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
     * Output: true
     * Explanation: Map 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
     * Such that: "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214
     * Example 3:
     * <p>
     * Input: words = ["LEET","CODE"], result = "POINT"
     * Output: false
     * Explanation: There is no possible mapping to satisfy the equation, so we return false.
     * Note that two different characters cannot map to the same digit.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 2 <= words.length <= 5
     * 1 <= words[i].length, result.length <= 7
     * words[i], result contain only uppercase English letters.
     * The number of different characters used in the expression is at most 10.
     */

    class Solution {

        public boolean checkEquation(String[] words, String resultWord, int[] map) {
            int sumOfWords = 0;
            for (String word : words) {
                int wordValue = 0;
                for (int i = 0; i < word.length(); i++) {
                    char letter = word.charAt(i);
                    wordValue = wordValue * 10 + map[letter];
                }
                sumOfWords += wordValue;
            }

            int resultValue = 0;
            for (int i = 0; i < resultWord.length(); i++) {
                char letter = resultWord.charAt(i);
                resultValue = resultValue * 10 + map[letter];
            }

            return sumOfWords == resultValue;
        }

        public boolean dfs(int index, int totalChars, String uniqueChars, String firstLetters,
                           String[] words, String resultWord, boolean[] usedDigits, int[] map) {
            if (index == totalChars) {
                return checkEquation(words, resultWord, map);
            }

            char currentChar = uniqueChars.charAt(index);
            for (int digit = 0; digit <= 9; digit++) {
                if (usedDigits[digit]) continue;
                if (digit == 0 && firstLetters.indexOf(currentChar) != -1) continue;

                map[currentChar] = digit;
                usedDigits[digit] = true;

                if (dfs(index + 1, totalChars, uniqueChars, firstLetters, words, resultWord, usedDigits, map)) {
                    return true;
                }

                map[currentChar] = 0;
                usedDigits[digit] = false;
            }

            return false;
        }

        public boolean isSolvable(String[] words, String resultWord) {
            Set<Character> firstLetters = new HashSet<>();
            Set<Character> uniqueChars = new HashSet<>();

            for (String word : words) {
                for (int i = 0; i < word.length(); i++) {
                    char letter = word.charAt(i);
                    if (i == 0 && word.length() > 1) firstLetters.add(letter);
                    uniqueChars.add(letter);
                }
            }

            for (int i = 0; i < resultWord.length(); i++) {
                char letter = resultWord.charAt(i);
                if (i == 0 && resultWord.length() > 1) firstLetters.add(letter);
                uniqueChars.add(letter);
            }

            String firstLettersString = firstLetters.toString().replaceAll("[,\\[\\]\\s]", "");
            String uniqueCharsString = uniqueChars.toString().replaceAll("[,\\[\\]\\s]", "");

            return dfs(0, uniqueCharsString.length(), uniqueCharsString, firstLettersString,
                    words, resultWord, new boolean[10], new int[128]);
        }
    }


}
