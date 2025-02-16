package leetcode.problems.google.hard.graphs;

import java.util.ArrayList;
import java.util.List;

public class FrogPositionAfterTimeT {

    class Solution {
        public double frogPosition(int n, int[][] edges, int t, int target) {

            List<Integer>[] graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
            double[] prob = new double[n + 1];
            prob[1] = 1.0;
            boolean[] visited = new boolean[n + 1];
            return dfs(graph, prob, visited, 1, t, target);
        }

        private double dfs(List<Integer>[] graph, double[] prob, boolean[] visited, int vertex, int t, int target) {
            if (t == 0) {
                return vertex == target ? prob[vertex] : 0.0;
            }

            visited[vertex] = true;
            int unvisitedChildren = 0;
            for (int neighbor : graph[vertex]) {
                if (!visited[neighbor]) {
                    unvisitedChildren++;
                }
            }
            if (unvisitedChildren == 0) {
                return vertex == target ? prob[vertex] : 0.0;
            }

            double result = 0.0;
            for (int neighbor : graph[vertex]) {
                if (!visited[neighbor]) {
                    prob[neighbor] = prob[vertex] / unvisitedChildren;
                    result += dfs(graph, prob, visited, neighbor, t - 1, target);
                }
            }

            visited[vertex] = false;
            return result;
        }
    }
}
