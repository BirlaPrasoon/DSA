package leetcode.problems.google.hard.backtracking;

public class MaximumScoreOfWords {
    /**
     * Given a list of words, list of  single letters (might be repeating) and score of every character.
     * <p>
     * Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).
     * <p>
     * It is not necessary to use all characters in letters and each letter can only be used once.
     * Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]</br>
     * Output: 23</br>
     * Explanation:</br>
     * Score  a=1, c=9, d=5, g=3, o=2</br>
     * Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.</br>
     * Words "dad" and "dog" only get a score of 21.</br></br>
     * Example 2:</br>
     * <p>
     * Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]</br>
     * Output: 27</br>
     * Explanation:</br>
     * Score  a=4, b=4, c=4, x=5, z=10</br>
     * Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.</br>
     * Word "xxxz" only get a score of 25.</br></br>
     * Example 3:
     * <p>
     * Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
     * Output: 0
     * Explanation:
     * Letter "e" can only be used once.
     */

    class Solution {

        public int maxScoreWords(String[] words, char[] letters, int[] score) {
            int W = words.length;
            // Count how many times each letter occurs
            freq = new int[26];
            for (char c : letters) {
                freq[c - 'a']++;
            }
            maxScore = 0;
            check(W - 1, words, score, new int[26], 0);
            return maxScore;
        }

        // Check if adding this word exceeds the frequency of any letter
        private boolean isValidWord(int[] subsetLetters) {
            for (int c = 0; c < 26; c++) {
                if (freq[c] < subsetLetters[c]) {
                    return false;
                }
            }
            return true;
        }

        private int maxScore;
        private int[] freq;

        private void check(int w, String[] words, int[] score, int[] subsetLetters, int totalScore) {
            if (w == -1) {
                // If all words have been iterated, check the score of this subset
                maxScore = Math.max(maxScore, totalScore);
                return;
            }
            // Not adding words[w] to the current subset
            check(w - 1, words, score, subsetLetters, totalScore);
            // Adding words[w] to the current subset
            int L = words[w].length();
            for (int i = 0; i < L; i++) {
                int c = words[w].charAt(i) - 'a';
                subsetLetters[c]++;
                totalScore += score[c];
            }

            if (isValidWord(subsetLetters)) {
                // Consider the next word if this subset is still valid
                check(w - 1, words, score, subsetLetters, totalScore);
            }
            // Rollback effects of this word
            for (int i = 0; i < L; i++) {
                int c = words[w].charAt(i) - 'a';
                subsetLetters[c]--;
                totalScore -= score[c];
            }
        }
    }

}
