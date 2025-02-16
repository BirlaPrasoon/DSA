package algoexpert.strings;

import algoexpert.searching.MultiStringSearch;

import java.util.HashMap;

public class LongestMostFrequentPrefix {

    static class TrieNode {
        HashMap<Character, TrieNode> children;
        int count = 0;
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    static class Trie {
        private final TrieNode root;
        private String longest = "";
        private int maxCount = Integer.MIN_VALUE;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the Trie
        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                current = current.children.computeIfAbsent(c, k -> new TrieNode());
                current.count++;
            }
            current.isEndOfWord = true;
        }

        // Searches for a word in the Trie
        public boolean search(String word) {
            TrieNode node = searchNode(word);
            return node != null && node.isEndOfWord;
        }

        // Searches for a prefix in the Trie
        public boolean startsWith(String prefix) {
            return searchNode(prefix) != null;
        }

        // Helper method to search for a node
        private TrieNode searchNode(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                current = current.children.get(c);
                if (current == null) {
                    return null;
                }
            }
            return current;
        }

        // Deletes a word from the Trie
        public boolean delete(String word) {
            return delete(root, word, 0);
        }

        private String longestMostFrequentPrefix() {
            dfs(root, "");
            return longest;
        }

        private void dfs(TrieNode root, String cur){
            if(root == null) return;
            if(root.count > maxCount) {
                maxCount = root.count;
                longest = cur;
            } else if(root.count == maxCount) {
                longest = longest.length() > cur.length() ? longest : cur;
            }
            root.children.forEach((k,v) -> {
                dfs(v, cur+k);
            });
        }

        private boolean delete(TrieNode current, String word, int index) {
            if (index == word.length()) {
                if (!current.isEndOfWord) {
                    return false; // word doesn't exist
                }
                current.isEndOfWord = false;
                return current.children.isEmpty(); // if true, delete this node
            }
            char c = word.charAt(index);
            TrieNode node = current.children.get(c);
            if (node == null) {
                return false; // word doesn't exist
            }
            boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

            if (shouldDeleteCurrentNode) {
                current.children.remove(c);
                return current.children.isEmpty() && !current.isEndOfWord; // return true if no children and not end of another word
            }
            return false;
        }

    }


    public String longestMostFrequentPrefix(String[] strings) {
        Trie trie = new Trie();
        for(String s: strings) {
            trie.insert(s);
        }
        return trie.longestMostFrequentPrefix();
    }

    public static void main(String[] args) {
        String[] strings =
                new String[] {"algorithm", "frontendexpert", "mlexpert"};
        String expected = "algo";
        String actual = new LongestMostFrequentPrefix().longestMostFrequentPrefix(strings);
        System.out.println(actual);
    }
}
