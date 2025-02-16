package leetcode;

import java.util.HashMap;

class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

public class Trie {
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

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("hello");
        trie.insert("helium");
        trie.insert("oath");
        trie.insert("oat");
        trie.insert("eat");

        System.out.println(trie.search("hello")); // true
        System.out.println(trie.search("oath"));
        System.out.println(trie.startsWith("oat"));
        System.out.println(trie.search("eat"));
        System.out.println(trie.startsWith("ea"));
        System.out.println(trie.search("oat"));
        System.out.println(trie.search("hel")); // false
    }
}
