package leetcode.problems.google.hard.graphs;

import java.util.*;

public class WordSearch2 {


/*
    Given an m x n board of characters and a list of strings words, return all words on the board.

    Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells
    are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
    */

    class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    class Trie {
        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the Trie
        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                current = current.children.computeIfAbsent(c, k -> new TrieNode());
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


    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for(String word: words){
            trie.insert(word);
        }
        char[][] dp = new char[board.length][board[0].length];
        Set<String> matchingWords = new HashSet<>();
        for(int i = 0; i<board.length; i++){
            for (int j= 0; j<board[0].length; j++){
                solve(board, dp, i,j,"",trie.root, matchingWords);
            }
        }
        return new ArrayList<>(matchingWords);
    }

    public void solve(char[][] board, char[][] dp, int i, int j, String s, TrieNode trieRoot, Set<String> matchingWords){
        if(i>=board.length || i<0 || j>=board[0].length || j<0 || dp[i][j] =='V' || s.length() > 10 || trieRoot == null || trieRoot.children.size() == 0) {
            return;
        }
        String temp = s + board[i][j];
        dp[i][j] = 'V';
        if(!trieRoot.children.containsKey(board[i][j])){
            dp[i][j] = 'N';
            return;
        }
        trieRoot = trieRoot.children.get(board[i][j]);
        if(trieRoot.isEndOfWord){
            matchingWords.add(temp);
        }
        solve(board, dp, i+1, j, temp,trieRoot, matchingWords);
        solve(board, dp, i, j+1, temp, trieRoot, matchingWords);
        solve(board, dp, i-1, j, temp, trieRoot, matchingWords);
        solve(board, dp, i, j-1, temp, trieRoot, matchingWords);
        dp[i][j] = 'N';
    }
}
