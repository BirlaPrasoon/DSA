package leetcode.problems.google.medium.union_find;

import datastructures.UnionFind;

public class GraphValidTree {

    /*
    * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges
    * where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
    *
    * Return true if the edges of the given graph make up a valid tree, and false otherwise.
    * */

    class Solution {

        public boolean validTree(int n, int[][] edges) {

            // Condition 1: The graph must contain n - 1 edges.
            if (edges.length != n - 1) return false;

            // Condition 2: The graph must contain a single connected component.
            // Create a new UnionFind object with n nodes.
            UnionFind unionFind = new UnionFind(n);
            // Add each edge. Check if a merge happened, because if it
            // didn't, there must be a cycle.
            for (int[] edge : edges) {
                int A = edge[0];
                int B = edge[1];
                if (!unionFind.union(A, B)) {
                    return false;
                }
            }

            // If we got this far, there's no cycles!
            return true;
        }

    }
}
