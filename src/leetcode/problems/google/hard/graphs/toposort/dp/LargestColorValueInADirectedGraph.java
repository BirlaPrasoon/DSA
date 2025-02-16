package leetcode.problems.google.hard.graphs.toposort.dp;

import java.util.*;

public class LargestColorValueInADirectedGraph {

    /**
     * There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.</br></br>
     * <p>
     * You are given a string colors where colors[i] is a lowercase English letter representing the color of
     * the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates
     * that there is a directed edge from node aj to node bj.</br></br>
     * <p>
     * A valid path in the graph is a sequence of nodes <code>X1 -> X2 -> X3 -> ... -> Xk</code> such that there is a directed edge
     * from <code>xi</code> to <code>Xi+1</code> for every <code>1 <= i < k</code>. The color value of the path is the number of nodes that are colored the most
     * frequently occurring color along that path.</br></br>
     * <p>
     * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.</br></br>
     * <p>
     * Example 1:</br>
     * Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]</br>
     * Output: 3</br>
     * Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).</br></br>
     * <p>
     * Example 2:</br>
     * Input: colors = "a", edges = [[0,0]]</br>
     * Output: -1</br>
     * Explanation: There is a cycle from 0 to 0.</br></br>
     */

    // It's a simple Graph with DP problem, like most graph DP problems, this also will be solved using Topological sort.
    // Although we can simply run DFS as well, that by default is Topo order execution which we want.

    // Kahn's Topological Sort
    class SolutionKahn {
        public int largestPathValue(String colors, int[][] edges) {
            int n = colors.length();
            Map<Integer, List<Integer>> adj = new HashMap<>();
            int[] indegree = new int[n];

            for (int[] edge : edges) {
                adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
                indegree[edge[1]]++;
            }

            int[][] count = new int[n][26];
            Queue<Integer> q = new LinkedList<>();

            // Push all the nodes with indegree zero in the queue.
            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }

            int answer = 1, nodesSeen = 0;
            while (!q.isEmpty()) {
                int node = q.poll();
                answer = Math.max(answer, ++count[node][colors.charAt(node) - 'a']);
                nodesSeen++;

                if (!adj.containsKey(node)) {
                    continue;
                }

                for (int neighbor : adj.get(node)) {
                    for (int i = 0; i < 26; i++) {
                        // Try to update the frequency of colors for the neighbor to include paths
                        // that use node->neighbor edge.
                        count[neighbor][i] = Math.max(count[neighbor][i], count[node][i]);
                    }

                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        q.offer(neighbor);
                    }
                }
            }

            return nodesSeen < n ? -1 : answer;
        }
    }

    class SolutionDFSTopo {
        private int dfs(int node, String colors, Map<Integer, List<Integer>> adj, int[][] count,
                        boolean[] visit, boolean[] inStack) {
            // If the node is already in the stack, we have a cycle.
            if (inStack[node]) {
                return Integer.MAX_VALUE;
            }
            if (visit[node]) {
                return count[node][colors.charAt(node) - 'a'];
            }
            // Mark the current node as visited and part of current recursion stack.
            visit[node] = true;
            inStack[node] = true;

            for (int neighbor : adj.get(node)) {
                if (dfs(neighbor, colors, adj, count, visit, inStack) == Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                for (int i = 0; i < 26; i++) {
                    count[node][i] = Math.max(count[node][i], count[neighbor][i]);
                }
            }


            // After all the incoming edges to the node are processed,
            // we count the color on the node itself.
            count[node][colors.charAt(node) - 'a']++;
            // Remove the node from the stack.
            inStack[node] = false;
            return count[node][colors.charAt(node) - 'a'];
        }

        public int largestPathValue(String colors, int[][] edges) {
            int n = colors.length();
            Map<Integer, List<Integer>> adj = new HashMap<>();

            for (int i = 1; i <= n; i++) {
                adj.putIfAbsent(i, new ArrayList<>());
            }
            for (int[] edge : edges) {
                adj.get(edge[0]).add(edge[1]);
            }

            int[][] count = new int[n][26];
            boolean[] visit = new boolean[n];
            boolean[] inStack = new boolean[n];
            int answer = 0;
            for (int i = 0; i < n; i++) {
                answer = Math.max(answer, dfs(i, colors, adj, count, visit, inStack));
            }

            return answer == Integer.MAX_VALUE ? -1 : answer;
        }
    }

}
