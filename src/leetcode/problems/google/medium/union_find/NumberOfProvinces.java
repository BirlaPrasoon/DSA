package leetcode.problems.google.medium.union_find;

import datastructures.UnionFind;

public class NumberOfProvinces {

/*
        There are n cities. Some of them are connected, while some are not.
        If city a is connected directly with city b, and city b is connected directly with city c,
        then city a is connected indirectly with city c.

        A province is a group of directly or indirectly connected cities and no other cities outside of the group.

        You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and
        the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

        Return the total number of provinces.
    */

    class Solution {
        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            UnionFind dsu = new UnionFind(n);
            int numberOfComponents = n;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isConnected[i][j] == 1 && dsu.find(i) != dsu.find(j)) {
                        numberOfComponents--;
                        dsu.union(i, j);
                    }
                }
            }

            return numberOfComponents;
        }
    }
}
