package leetcode.problems.google.hard.graphs.toposort;

import java.util.*;

public class BuildAMatrixWithConditions {
    // Time: O(max(k⋅k,n))
    // Space: O(max(k⋅k,n))
    static class SolutionTopologicalSorting {

        public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
            // Store the topologically sorted sequences.
            List<Integer> orderRows = topoSort(rowConditions, k);
            List<Integer> orderColumns = topoSort(colConditions, k);

            // If no topological sort exists, return empty array.
            if (orderRows.isEmpty() || orderColumns.isEmpty()) return new int[0][0];

            int[][] matrix = new int[k][k];
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    if (orderRows.get(i).equals(orderColumns.get(j))) {
                        matrix[i][j] = orderRows.get(i);
                    }
                }
            }
            return matrix;
        }

        private List<Integer> topoSort(int[][] edges, int n) {
            // Build adjacency list
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                adj.get(edge[0]).add(edge[1]);
            }

            List<Integer> order = new ArrayList<>();
            // 0: not visited, 1: visiting, 2: visited
            int[] visited = new int[n + 1];
            boolean[] hasCycle = {false};

            // Perform DFS for each node
            for (int i = 1; i <= n; i++) {
                if (visited[i] == 0) {
                    dfs(i, adj, visited, order, hasCycle);
                    // Return empty if cycle detected
                    if (hasCycle[0]) return new ArrayList<>();
                }
            }

            // Reverse to get the correct order
            Collections.reverse(order);
            return order;
        }

        private void dfs(int node, List<List<Integer>> adj, int[] visited, List<Integer> order, boolean[] hasCycle) {
            visited[node] = 1; // Mark node as visiting
            for (int neighbor : adj.get(node)) {
                if (visited[neighbor] == 0) {
                    dfs(neighbor, adj, visited, order, hasCycle);
                    // Early exit if a cycle is detected
                    if (hasCycle[0]) return;
                } else if (visited[neighbor] == 1) {
                    // Cycle detected
                    hasCycle[0] = true;
                    return;
                }
            }
            // Mark node as visited
            visited[node] = 2;
            // Add node to the order
            order.add(node);
        }
    }

    // Kahn's Algorithm
    public static class Solution {

        public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
            int[] orderRows = topoSort(rowConditions, k);
            int[] orderColumns = topoSort(colConditions, k);

            if (orderRows.length == 0 || orderColumns.length == 0) return new int[0][0];

            int[][] matrix = new int[k][k];
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    // What is happening here?
                    // We need to place the coins. where both row and column conditions are meeting.
                    if (orderRows[i] == orderColumns[j]) {
                        matrix[i][j] = orderRows[i];
                    }
                }
            }
            return matrix;
        }

        private int[] topoSort(int[][] edges, int n) {
            List<Integer>[] adj = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            int[] deg = new int[n + 1], order = new int[n];
            int idx = 0;
            for (int[] x : edges) {
                adj[x[0]].add(x[1]);
                deg[x[1]]++;
            }
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (deg[i] == 0) q.offer(i);
            }
            while (!q.isEmpty()) {
                int f = q.poll();
                order[idx++] = f;
                n--;
                for (int v : adj[f]) {
                    if (--deg[v] == 0) q.offer(v);
                }
            }
            if (n != 0) return new int[0];
            return order;
        }

        public static void main(String[] args) {
            Solution s = new Solution();
            int k = 3;
            int[][] rowConditions = {
                    {1,2},
                    {3,2}
            };
            int[][] colConditions = {
                    {2,1},
                    {3,2}
            };
            int[][] matrix = s.buildMatrix(k, rowConditions, colConditions);
            for(int[] a : matrix) {
                System.out.println(Arrays.toString(a));
            }
        }

    }

}
