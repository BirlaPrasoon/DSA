package leetcode.problems.google.medium.union_find;

import datastructures.UnionFind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RedundantConnection {

    /*
    * In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

    *   Input: edges = [[1,2],[1,3],[2,3]]
        Output: [2,3]
        *
        Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
        Output: [1,4]
    *
    * * */

    class SolutionDFS {
        Set<Integer> seen = new HashSet();
        int MAX_EDGE_VAL = 1000;

        public int[] findRedundantConnection(int[][] edges) {
            ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
            for (int i = 0; i <= MAX_EDGE_VAL; i++) {
                graph[i] = new ArrayList();
            }

            for (int[] edge: edges) {
                seen.clear();
                if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
                        dfs(graph, edge[0], edge[1])) {
                    return edge;
                }
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
            throw new AssertionError();
        }
        public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
            if (!seen.contains(source)) {
                seen.add(source);
                if (source == target) return true;
                for (int nei: graph[source]) {
                    if (dfs(graph, nei, target)) return true;
                }
            }
            return false;
        }
    }

    class SolutionUF {
        int MAX_EDGE_VAL = 1000;

        public int[] findRedundantConnection(int[][] edges) {
            UnionFind dsu = new UnionFind(MAX_EDGE_VAL + 1);
            for (int[] edge: edges) {
                int x= dsu.find(edge[0]);
                int y = dsu.find(edge[1]);
                if(x == y) return edge;
                dsu.union(edge[0], edge[1]);
            }
            return new int[]{};
        }
    }

}
