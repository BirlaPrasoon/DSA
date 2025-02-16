package algoexpert.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class BoggleBoard {

    // Note: We can't keep root as a static variable
    static class TrieNode {
        boolean isEOW = false;
        HashMap<Character, TrieNode> childs = new HashMap<>();
    }


    static void insertWord(TrieNode root, String word) {
        TrieNode cur = root;
        int i = 0;
        while(i < word.length()) {
            char c = word.charAt(i);
            if(cur.childs.containsKey(c)) {
                cur = cur.childs.get(c);
            } else {
                TrieNode newNode = new TrieNode();
                cur.childs.put(c, newNode);
                cur = newNode;
            }
            i++;
        }
        cur.isEOW = true;
    }

    static void makeTrie(TrieNode root, String[] words) {
        for (String word : words) {
            insertWord(root, word);
        }
    }

    static boolean exists(TrieNode root, String word) {
        TrieNode cur = root;
        int i = 0;
        while(i < word.length()) {
            char c= word.charAt(i);
            if(cur.childs.containsKey(c)) {
                cur = cur.childs.get(c);
            } else {
                return false;
            }
            i++;
        }
        return cur.isEOW;
    }

    static class Pair {
        int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "{" + "a=" + a + ", b=" + b + '}';
        }

        // Note: For HashSet/HashMap comparison, both equals and hashcode are important to override.
        @Override
        public boolean equals(Object o ) {
            if(this == o) return true;
            if(!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return p.a == a && p.b == b;
        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            return result;
        }
    }

    public static List<String> boggleBoard(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        makeTrie(root, words);

        HashSet<String> foundWords = new HashSet<>();
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j< board[i].length; j++) {
                HashSet<Pair> visited = new HashSet<>();
                if(root.childs.containsKey(board[i][j])) {
                    dfs(i, j, board, visited, foundWords, root, "");
                }
            }
        }

        // Write your code here.
        return new ArrayList<>(foundWords);
    }

    private static void dfs(int i, int j, char[][] board, HashSet<Pair> visited, HashSet<String> foundWords, TrieNode cur, String curWord) {
        if(isOutOfBound(board, i, j)) return;
        Pair p = new Pair(i, j);
        if(visited.contains(p)) return;
        visited.add(p);
        char c = board[i][j];
        curWord += c;
        // Note: Make sure to check that have the ! symbol for not checking, I forgot and this caused 15 mins of time
        if(!cur.childs.containsKey(c)) {
            // Note: It's important to remove any element in the history we're adding in the same function call
            // otherwise it will mess up the history we're tracking!!!
            visited.remove(p);
            return;
        }
        cur = cur.childs.get(c);
        if(cur.isEOW) {
            foundWords.add(curWord);
        }
        List<Pair> dirs = getAll8Directions(i, j);

        for(Pair dir: dirs) {
            dfs(dir.a, dir.b, board, visited, foundWords, cur, curWord);
        }
        visited.remove(p);
    }

    static List<Pair> getAll8Directions(int i , int j) {
        List<Pair> dirs = new ArrayList<>();
        dirs.add(new Pair(i+1, j));
        dirs.add(new Pair(i+1, j+1));
        dirs.add(new Pair(i+1, j-1));
        dirs.add(new Pair(i, j-1));
        dirs.add(new Pair(i, j+1));
        dirs.add(new Pair(i-1, j));
        dirs.add(new Pair(i-1, j+1));
        dirs.add(new Pair(i-1, j-1));
        return dirs;
    }

    static boolean isOutOfBound(char[][] board, int i, int j) {
        return i<0 || j< 0 || i>= board.length || j >= board[0].length;
    }

    public static void main(String[] args) {
        char[][] board = {
                "thisisa".toCharArray(),
                "simplex".toCharArray(),
                "bxxxxeb".toCharArray(),
                "xogglxo".toCharArray(),
                "xxxDTra".toCharArray(),
                "REPEADx".toCharArray(),
                "xxxxxxx".toCharArray(),
                "NOTRE-P".toCharArray(),
                "xxDETAE".toCharArray()
        };
        String[] words = {"this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED"};
        System.out.println(boggleBoard(board, words));
    }
}
