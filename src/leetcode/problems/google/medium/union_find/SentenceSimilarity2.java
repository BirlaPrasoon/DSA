package leetcode.problems.google.medium.union_find;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceSimilarity2 {

    /*
    *
    * We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].

Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.

Return true if sentence1 and sentence2 are similar, or false if they are not similar.

Two sentences are similar if:

They have the same length (i.e., the same number of words)
sentence1[i] and sentence2[i] are similar.
Notice that a word is always similar to itself, also notice that the similarity relation is transitive. For example, if the words a and b are similar, and the words b and c are similar, then a and c are similar.
    *
    * */

    /*
    *
    * Example 1:

Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]
Output: true
Explanation: The two sentences have the same length and each word i of sentence1 is also similar to the corresponding word in sentence2.
Example 2:

Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","onepiece"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
Output: true
Explanation: "leetcode" --> "platform" --> "anime" --> "manga" --> "onepiece".
Since "leetcode is similar to "onepiece" and the first two words are the same, the two sentences are similar.
Example 3:

Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","hunterXhunter"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
Output: false
Explanation: "leetcode" is not similar to "onepiece".
    *
    *
    * */

    class UnionFind {
        Map<String, String> parent = new HashMap<>();

        public String find(String word) {
            String result = parent.getOrDefault(word, word);
            if (!result.equals(word)) {
                // Optimize during find to mininize the trace from x -> y > x
                String temp = find(result);
                parent.put(word, temp);
                return temp;
            }

            return result;
        }

        public void union(String word1, String word2) {
            String parentA = find(word1);
            String parentB = find(word2);

            if (parentA.equals(parentB)) {
                return;
            }

            parent.put(parentA, parentB);
        }
    }

    class Solution {

        public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
            if (sentence1.length != sentence2.length) {
                return false;
            }

            UnionFind dsu = new UnionFind();
            for (List<String> pair : similarPairs) {
                dsu.union(pair.get(0), pair.get(1));
            }

            for (int i = 0; i < sentence1.length; i++) {
                String left = sentence1[i];
                String right = sentence2[i];
                if (left.equals(right)) {
                    continue;
                }

                if (dsu.find(left).equals(dsu.find(right))) {
                    continue;
                }
                return false;
            }
            return true;
        }
    }
}
