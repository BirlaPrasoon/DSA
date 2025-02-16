package leetcode.problems.google.hard.graphs.dpOnGraph;

import java.util.*;

public class MinimumNumberOfEdgeReversalsSoEveryEdgeIsReachable {

    /**
     * There is a simple directed graph with n nodes labeled from 0 to n - 1. The graph would form a tree if its edges were bi-directional.</br></br>
     * <p>
     * You are given an integer n and a 2D integer array edges, where edges[i] = [ui, vi] represents a directed edge going from node ui to node vi.</br></br>
     * <p>
     * An edge reversal changes the direction of an edge, i.e., a directed edge going from node ui to node vi becomes a directed edge going from node vi to node ui.</br></br>
     * <p>
     * For every node i in the range [0, n - 1], your task is to independently calculate the minimum number of edge reversals required so it is possible to reach any other node starting from node i through a sequence of directed edges.</br></br>
     * <p>
     * Return an integer array answer, where answer[i] is the minimum number of edge reversals required so it is possible to reach any other node starting from node i through a sequence of directed edges.</br></br>
     * <p>
     * <p>
     * <p>
     * Example 1:</br>
     * <p>
     * <p>
     * <p>
     * Input: n = 4, edges = [[2,0],[2,1],[1,3]]</br>
     * Output: [1,1,0,2]</br>
     * Explanation: The image above shows the graph formed by the edges.</br>
     * For node 0: after reversing the edge [2,0], it is possible to reach any other node starting from node 0.</br>
     * So, answer[0] = 1.</br>
     * For node 1: after reversing the edge [2,1], it is possible to reach any other node starting from node 1.</br>
     * So, answer[1] = 1.</br>
     * For node 2: it is already possible to reach any other node starting from node 2.</br>
     * So, answer[2] = 0.</br>
     * For node 3: after reversing the edges [1,3] and [2,1], it is possible to reach any other node starting from node 3.</br>
     * So, answer[3] = 2.</br>
     * Example 2:</br>
     * <p>
     * <p>
     * <p>
     * Input: n = 3, edges = [[1,2],[2,0]]</br>
     * Output: [2,0,1]</br>
     * Explanation: The image above shows the graph formed by the edges.</br>
     * For node 0: after reversing the edges [2,0] and [1,2], it is possible to reach any other node starting from node 0.</br>
     * So, answer[0] = 2.</br>
     * For node 1: it is already possible to reach any other node starting from node 1.</br>
     * So, answer[1] = 0.</br>
     * For node 2: after reversing the edge [1, 2], it is possible to reach any other node starting from node 2.</br>
     * So, answer[2] = 1.</br>
     * <p>
     * <p>
     * Constraints:</br>
     * <p>
     * 2 <= n <= 105</br>
     * edges.length == n - 1</br>
     * edges[i].length == 2</br>
     * 0 <= ui == edges[i][0] < n</br>
     * 0 <= vi == edges[i][1] < n</br>
     * ui != vi</br>
     * The input is generated such that if the edges were bi-directional, the graph would be a tree.</br></br>
     */

    // O(n), O(n)
    class Solution {

        public int[] minEdgeReversals(int n, int[][] edges) {
            List<List<Pair>> graph = buildUndirectedGraphWithReverseEdgesHavingWeightOfOne(n, edges);
            int[] temp = new int[n];
            HashMap<String, Integer> dp = new HashMap<>();

            for (int i = 0; i < n; i++) {
                temp[i] = dfs(graph, -1, i, dp);
            }
            return temp;
        }

        private static List<List<Pair>> buildUndirectedGraphWithReverseEdgesHavingWeightOfOne(int n, int[][] edges) {
            List<List<Pair>> graph = new ArrayList<>();

            for (int i = 0; i < n; i++)
                graph.add(new ArrayList<>());

            for (int[] edge : edges) {
                graph.get(edge[0]).add(new Pair(edge[1], 0));
                graph.get(edge[1]).add(new Pair(edge[0], 1));
            }
            return graph;
        }

        public int dfs(List<List<Pair>> graph, int parent, int cur, HashMap<String, Integer> dp) {
            // base
            String t = parent + "." + cur;
            if (dp.containsKey(t)) return dp.get(t);
            int count = 0;
            for (Pair nei : graph.get(cur)) {
                int dest = nei.destination;
                if (dest == parent) continue;
                count += nei.cost + dfs(graph, cur, dest, dp);
            }
            dp.put(t, count);
            return count;
        }

        record Pair(int destination, int cost) {
        }
    }

    // O(n), O(n)
    class SolutionMine {
        record Edge(int dest, int weight){}
        public int[] minEdgeReversals(int n, int[][] edges) {
            HashSet<Edge>[] graph = new HashSet[n];
            for(int i = 0; i<n; i++) graph[i] = new HashSet<>();
            for(int[] edge : edges) {
                graph[edge[0]].add(new Edge(edge[1], 0));
                graph[edge[1]].add(new Edge(edge[0], 1));
            }
            int[] cost = new int[n];
            HashMap<String, Integer> dp = new HashMap<>();
            for (int i = 0; i < n; i++) {
                cost[i] = dfs(i, graph, -1, dp);
            }
            return cost;
        }

        private int dfs(int cur, HashSet<Edge>[] graph, int parent, HashMap<String, Integer> dp){
            int cost = 0;
            String key = cur +":" + parent;
            if(dp.containsKey(key)) return dp.get(key);

            for(Edge neighbor: graph[cur]){
                if(neighbor.dest() == parent) continue;
                cost += neighbor.weight() + dfs(neighbor.dest(), graph, cur, dp);
            }

            dp.put(key, cost);
            return cost;
        }
    }
}
