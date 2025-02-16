package leetcode.problems.google.hard.graphs;

import java.util.*;

public class WordLadder {

    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            Set<String> wordSet = new HashSet<>(wordList);
            if(!wordSet.contains(endWord)) return 0;
            Queue<String> wordQueue = new LinkedList<>();
            wordQueue.add(beginWord);

            int distance = 0;
            while (!wordQueue.isEmpty()) {
                int size = wordQueue.size();
                distance++;

                while (size-- != 0) {
                    String currWord = wordQueue.poll();
                    for (int i = 0; i < currWord.length(); i++) {
                        char[] temp = currWord.toCharArray();
                        for (char j = 'a'; j <= 'z'; j++) {
                            temp[i] = j;
                            String newWord = new String(temp);
                            if (newWord.equals(endWord)) return distance + 1;
                            if (wordSet.contains(newWord)) {
                                wordQueue.add(newWord);
                                wordSet.remove(newWord);
                            }
                        }
                    }
                }
            }
            return 0;
        }
    }
}
