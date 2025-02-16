package leetcode.problems.google.hard.graphs;

import datastructures.UnionFind;

public class SimilarStringGroups {

    class Solution {
        public boolean isSimilar(String a, String b) {
            int diff = 0;
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    diff++;
                }
            }
            return diff == 0 || diff == 2;
        }

        public int numSimilarGroups(String[] strs) {
            int n = strs.length;
            UnionFind dsu = new UnionFind(n);
            int count = n;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isSimilar(strs[i], strs[j]) && dsu.find(i) != dsu.find(j)) {
                        count--;
                        dsu.union(i, j);
                    }
                }
            }
            return count;
        }
    }

}
