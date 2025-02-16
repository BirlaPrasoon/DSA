package leetcode.problems.google.medium.depth_first_search.tries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReplaceWords {

/*
    In English, we have a concept called root, which can be followed by some other word to form another longer word -
    let's call this word derivative. For example, when the root "help" is followed by the word "ful", we can form a derivative "helpful".

    Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces,
     replace all the derivatives in the sentence with the root forming it. If a derivative can be replaced by more than one root,
     replace it with the root that has the shortest length.

    Return the sentence after the replacement.



    Example 1:

    Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
    Output: "the cat was rat by the bat"
    Example 2:

    Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
    Output: "a a b c"
*/


    TrieNode root = new TrieNode();

    class TrieNode {
        boolean isEndOfWord = false;
        HashMap<Character, TrieNode> children = new HashMap<>();
    }

    void insert(String word) {
        TrieNode current = root;
        for(char c: word.toCharArray()) {
            if(current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                current.children.put(c, new TrieNode());
                current = current.children.get(c);
            }
        }
        current.isEndOfWord = true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        for(String w: dictionary) {
            insert(w);
        }
        StringBuilder sb = new StringBuilder();
        String ss[]= sentence.split(" ");
        for(int i = 0; i<ss.length; i++) {
            String s = ss[i];
            TrieNode current = root;
            String w = "";
            for(char c: s.toCharArray()) {
                if(current.children.containsKey(c)) {
                    w += c;
                    current = current.children.get(c);
                    if(current.isEndOfWord) {
                        break;
                    }
                } else {
                    w = s;
                    break;
                }
            }
            sb.append(w);
            if(i != ss.length-1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReplaceWords r = new ReplaceWords();
        String s= "the cattle was rattled by the battery";
        String words[] = {"cat","bat","rat"};
        System.out.println(r.replaceWords(Arrays.asList(words), s));
    }
}
