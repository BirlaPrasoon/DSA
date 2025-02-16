package leetcode.problems.google.medium.depth_first_search.diff.must_practice;

import java.util.HashMap;
import java.util.Map;

public class ImplementMagicDictionary {

/*
    Design a data structure that is initialized with a list of different words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.

    Implement the MagicDictionary class:

    MagicDictionary() Initializes the object.
    void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
    bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.


    Example 1:

    Input
    ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
    [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
    Output
    [null, null, false, true, false, false]

    Explanation
    MagicDictionary magicDictionary = new MagicDictionary();
    magicDictionary.buildDict(["hello", "leetcode"]);
    magicDictionary.search("hello"); // return False
    magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
    magicDictionary.search("hell"); // return False
    magicDictionary.search("leetcoded"); // return False

    */

    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;
        TrieNode() {
            children = new HashMap<>();
            isEnd = false;
        }
    }

    class Trie {
        private final TrieNode root = new TrieNode();
        Trie(){}

        public void insert(String str) {
            TrieNode current = root;
            for (Character c: str.toCharArray()) {
                TrieNode child = current.children.computeIfAbsent(c, k -> new TrieNode());
                current = child;
            }
            current.isEnd = true;
        }

        public boolean search(String str) {
            return dfs(root, 0, str, 0);
        }

        private  boolean dfs(TrieNode node, int index, String str, int changed) {
            if(index == str.length() && node.isEnd && changed == 1) return true;
            if(changed == 2 || index >= str.length()) return false;
            for (Map.Entry<Character, TrieNode> child: node.children.entrySet()) {
                if(dfs(child.getValue(), index+1, str, child.getKey() == str.charAt(index) ? changed : changed + 1))
                    return true;
            }
            return false;
        }
    }


    class MagicDictionary {
        private final Trie trie = new Trie();
        public MagicDictionary() {

        }

        public void buildDict(String[] dictionary) {
            for (String word: dictionary)
                trie.insert(word);
        }

        public boolean search(String searchWord) {
            return trie.search(searchWord);
        }
    }

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */

}
