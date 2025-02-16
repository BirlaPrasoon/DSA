package leetcode.problems.google.hard.union_find.must_practice;

import datastructures.UnionFind;

import java.util.*;

public class NumberOfGoodPaths {


    /**
     * There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges.
     * </br>
     * You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
     * </br>
     * A good path is a simple path that satisfies the following conditions:
     * </br>
     * <p>
     * The starting node and the ending node have the same value.
     * </br>
     * All nodes between the starting node and the ending node have values less than or equal to the starting node (i.e. the starting node's value should be the maximum value along the path).
     *
     * </br>
     * Return the number of distinct good paths.
     * </br>
     * <b>
     * Note that a path and its reverse are counted as the same path. For example, 0 -> 1 is considered to be the same as 1 -> 0. A single node is also considered as a valid path.
     * </b>
     */


    static class Solution {
        public int numberOfGoodPaths(int[] vals, int[][] edges) {
            Map<Integer, List<Integer>> graph = new HashMap<>();
            buildUndirectedGraph(edges, graph);

            int n = vals.length;
            // Mapping from value to all the nodes having the same value in sorted order of
            // values.
            TreeMap<Integer, List<Integer>> valuesToNodes = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                valuesToNodes.computeIfAbsent(vals[i], value -> new ArrayList<Integer>()).add(i);
            }

            UnionFind dsu = new UnionFind(n);
            int goodPaths = 0;

            // Iterate over all the nodes with the same value in sorted order, starting from
            // the lowest value.
            for (int value : valuesToNodes.keySet()) {
                // For every node in nodes, combine the sets of the node and its neighbors into
                // one set.
                for (int node : valuesToNodes.get(value)) {
                    if (!graph.containsKey(node)) continue;
                    for (int neighbor : graph.get(node)) {
                        // Only choose neighbors with a smaller value, as there is no point in
                        // traversing to other neighbors.
                        if (vals[node] >= vals[neighbor]) {
                            dsu.union(node, neighbor);
                        }
                    }
                }
                // Map to compute the number of nodes under observation (with the same values)
                // in each of the sets.
                // There can be multiple groups as there can be multiple subgraphs where nodes with this value lies and
                // between them there are nodes with values greater than this value. Or the graph is disconnected in itself.
                Map<Integer, Integer> group = new HashMap<>();
                // Iterate over all the nodes. Get the set of each node and increase the count
                // of the set by 1.

                for (int u : valuesToNodes.get(value)) {
                    // parent of nodes with value - value that belong together will have the same parent, otherwise different.
                    int parent = dsu.find(u);
                    int prevCount = group.getOrDefault(parent, 0);
                    group.put(parent, prevCount + 1);
                }
                // For each set of "size", add size * (size + 1) / 2 to the number of goodPaths.
                // In every subgraph, count the number of good paths and combine them together.
                for (int key : group.keySet()) {
                    int size = group.get(key);
                    // number of ways to choose 2 elements from multiple set of elements.
                    goodPaths += size * (size + 1) / 2;
                }
            }
            return goodPaths;
        }

        private static void buildUndirectedGraph(int[][] edges, Map<Integer, List<Integer>> graph) {
            for (int[] edge : edges) {
                int a = edge[0], b = edge[1];
                graph.computeIfAbsent(a, value -> new ArrayList<Integer>()).add(b);
                graph.computeIfAbsent(b, value -> new ArrayList<Integer>()).add(a);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        int[] vals1 = {1, 3, 2, 1, 3};
        int[][] edges1 = {{0, 1}, {0, 2}, {2, 3}, {2, 4}};
        System.out.println("Test case 1 result: " + solution.numberOfGoodPaths(vals1, edges1));
        // Expected output: 6

        // Test case 2
        int[] vals2 = {1, 1, 2, 2, 3};
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {2, 4}};
        System.out.println("Test case 2 result: " + solution.numberOfGoodPaths(vals2, edges2));
        // Expected output: 7
//
//        // Test case 3
        int[] vals3 = {1};
        int[][] edges3 = {};
        System.out.println("Test case 3 result: " + solution.numberOfGoodPaths(vals3, edges3));
//        // Expected output: 1
//
//        // Additional test case
        int[] vals4 = {1, 3, 2, 1, 3, 3, 2};
        int[][] edges4 = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {4, 5}, {5, 6}};
        System.out.println("Test case 4 result: " + solution.numberOfGoodPaths(vals4, edges4));
        // Expected output: 10
    }

}
