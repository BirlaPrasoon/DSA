package leetcode.problems.google.medium.greedy.non_intuitive;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindromeByConcatenatingTwoLetterWords {
/*
    You are given an array of strings words. Each element of words consists of two lowercase English letters.

    Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.

    Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.

    A palindrome is a string that reads the same forward and backward.



    Example 1:

    Input: words = ["lc","cl","gg"]
    Output: 6
    Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
    Note that "clgglc" is another longest palindrome that can be created.
    Example 2:

    Input: words = ["ab","ty","yt","lc","cl","ab"]
    Output: 8
    Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
    Note that "lcyttycl" is another longest palindrome that can be created.
    Example 3:

    Input: words = ["cc","ll","xx"]
    Output: 2
    Explanation: One longest palindrome is "cc", of length 2.
    Note that "ll" is another longest palindrome that can be created, and so is "xx".


    Constraints:

            1 <= words.length <= 105
    words[i].length == 2
    words[i] consists of lowercase English letters.
        */

    class Solution {

        /*
        *
        * Complexity Analysis
Let N be the number of words in the input array and ∣Σ∣
be the size of the English alphabet (∣Σ∣=26).

Time complexity: O(N+min(N,∣Σ∣
2
 )).

We count the words in O(N) time (assuming one
operation with a hash map takes O(1) time). Calculating the
answer after that takes O(min(N,∣Σ∣
2
 )) time as we
iterate all hash map elements, and the size of the hash map is
O(min(N,∣Σ∣
2
 )).

Space complexity: O(min(N,∣Σ∣
2
 )).

There can be up to ∣Σ∣
2
  distinct words of two letters
(∣Σ∣ options for the first letter and ∣Σ∣ options
for the second one). Also, the total number of words is N.
        * */

        public int longestPalindrome(String[] words) {
            HashMap<String, Integer> count = new HashMap<String, Integer>();
            for (String word : words) {
                Integer countOfTheWord = count.get(word);
                if (countOfTheWord == null) {
                    count.put(word, 1);
                } else {
                    count.put(word, countOfTheWord + 1);
                }
            }
            int answer = 0;
            boolean central = false;
            for (Map.Entry<String, Integer> entry : count.entrySet()) {
                String word = entry.getKey();
                int countOfTheWord = entry.getValue();
                // if the word is a palindrome
                if (word.charAt(0) == word.charAt(1)) {
                    if (countOfTheWord % 2 == 0) {
                        answer += countOfTheWord;
                    } else {
                        answer += countOfTheWord - 1;
                        central = true;
                    }
                    // consider a pair of non-palindrome words such that one is the reverse of another
                } else if (word.charAt(0) < word.charAt(1)) {
                    String reversedWord = "" + word.charAt(1) + word.charAt(0);
                    if (count.containsKey(reversedWord)) {
                        answer += 2 * Math.min(countOfTheWord, count.get(reversedWord));
                    }
                }
            }
            if (central) {
                answer++;
            }
            return 2 * answer;
        }
    };
}
