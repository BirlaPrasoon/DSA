package leetcode.problems.google.medium.depth_first_search.tries;

public class LongestWordWithAllPrefixes {

/*
    Given an array of strings words, find the longest string in words such that every prefix of it is also in words.

    For example, let words = ["a", "app", "ap"]. The string "app" has prefixes "ap" and "a", all of which are in words.
    Return the string described above. If there is more than one string with the same length, return the lexicographically smallest one,
    and if no string exists, return "".



    Example 1:

    Input: words = ["k","ki","kir","kira", "kiran"]
    Output: "kiran"
    Explanation: "kiran" has prefixes "kira", "kir", "ki", and "k", and all of them appear in words.
            Example 2:

    Input: words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
    Output: "apple"
    Explanation: Both "apple" and "apply" have all their prefixes in words.
    However, "apple" is lexicographically smaller, so we return that.
            Example 3:

    Input: words = ["abc", "bc", "ab", "qwe"]
    Output: ""
*/

    class Solution {

        public String longestWord(String[] words) {
            Trie trie = new Trie();
            String longestValidWord = "";

            // Insert all words into the trie
            for (String word : words) {
                trie.insert(word);
            }

            // Check each word and update the longest valid word
            for (String word : words) {
                if (trie.hasAllPrefixes(word)) {
                    if (word.length() > longestValidWord.length() ||
                            (word.length() == longestValidWord.length() && word.compareTo(longestValidWord) < 0)) {
                        longestValidWord = word;
                    }
                }
            }

            return longestValidWord;
        }

        private static class Trie {

            private static class TrieNode {

                TrieNode[] children = new TrieNode[26];
                boolean isEndOfWord;
            }

            private final TrieNode root = new TrieNode();

            // Insert a word into the trie
            public void insert(String word) {
                TrieNode node = root;
                for (char c : word.toCharArray()) {
                    int index = c - 'a';
                    if (node.children[index] == null) {
                        node.children[index] = new TrieNode();
                    }
                    node = node.children[index];
                }
                node.isEndOfWord = true;
            }

            // Check if all prefixes of the word exist in the trie
            public boolean hasAllPrefixes(String word) {
                TrieNode node = root;
                for (char c : word.toCharArray()) {
                    node = node.children[c - 'a'];
                    if (node == null || !node.isEndOfWord) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
}
