package leetcode.problems.google.medium.topologicalSort.still_not_clear;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindEventualSafeStates {

/*
    There is a directed graph of n nodes with each node labeled from 0 to n - 1.
    The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i,
    meaning there is an edge from node i to each node in graph[i].

    A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path
    starting from that node leads to a terminal node (or another safe node).

    Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
    */

    class SolutionTopoSort {
        public List<Integer> eventualSafeNodes(int[][] graph) {
            int n = graph.length;
            int[] indegree = new int[n];
            List<List<Integer>> adj = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < n; i++) {
                for (int node : graph[i]) {
                    adj.get(node).add(i);
                    indegree[i]++;
                }
            }

            Queue<Integer> q = new LinkedList<>();
            // Push all the nodes with indegree zero in the queue.
            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }

            boolean[] safe = new boolean[n];
            while (!q.isEmpty()) {
                int node = q.poll();
                safe[node] = true;

                for (int neighbor : adj.get(node)) {
                    // Delete the edge "node -> neighbor".
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }

            List<Integer> safeNodes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (safe[i]) {
                    safeNodes.add(i);
                }
            }
            return safeNodes;
        }
    }

    class SolutionDFS {
        public boolean dfs(int node, int[][] adj, boolean[] visit, boolean[] inStack) {
            // If the node is already in the stack, we have a cycle.
            if (inStack[node]) {
                return true;
            }
            if (visit[node]) {
                return false;
            }
            // Mark the current node as visited and part of current recursion stack.
            visit[node] = true;
            inStack[node] = true;
            for (int neighbor : adj[node]) {
                if (dfs(neighbor, adj, visit, inStack)) {
                    return true;
                }
            }
            // Remove the node from the stack.
            inStack[node] = false;
            return false;
        }

        public List<Integer> eventualSafeNodes(int[][] graph) {
            int n = graph.length;
            boolean[] visit = new boolean[n];
            boolean[] inStack = new boolean[n];

            for (int i = 0; i < n; i++) {
                dfs(i, graph, visit, inStack);
            }

            List<Integer> safeNodes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (!inStack[i]) {
                    safeNodes.add(i);
                }
            }

            return safeNodes;
        }
    }
}
