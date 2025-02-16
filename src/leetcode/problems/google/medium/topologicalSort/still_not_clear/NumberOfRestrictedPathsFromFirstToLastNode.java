package leetcode.problems.google.medium.topologicalSort.still_not_clear;

import java.util.*;

public class NumberOfRestrictedPathsFromFirstToLastNode {

    /**
     * There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n, and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.
     * <p>
     * A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.
     * <p>
     * The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x) denote the shortest distance of a path between node n and node x. A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.
     * <p>
     * Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.
     *
     *
     * </br>
     * Constraints:</br>
     * <code>
     * 1 <= n <= 2 * 104
     * n - 1 <= edges.length <= 4 * 104
     * edges[i].length == 3
     * 1 <= ui, vi <= n
     * ui != vi
     * 1 <= weighti <= 105
     * There is at most one edge between any two nodes.
     * There is at least one path between any two nodes.
     * </code>
     */

    class Pair {
        int node, weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    /**
    *
    * Approach:</br>
The approach to solve this problem is based on a combination of Dijkstra's algorithm for shortest paths and dynamic programming.</br>

Shortest Path Calculation (Dijkstra's Algorithm):</br>

We start by calculating the shortest distance from every node to node n using Dijkstra's algorithm.</br>
We use a priority queue (min-heap) to efficiently get the next node with the smallest distance.</br>

Counting Restricted Paths:</br>

Using the distances calculated, we determine the number of restricted paths from node 1 to node n.</br>
We use a priority queue to process nodes in the order of their distance from node n, ensuring that when we process a node, all nodes with shorter distances have already been processed.</br>
For each node x, for each neighbor y, if the distance to n from y is greater than from x (dist[y] > dist[x]), it means we can move from x to y in a restricted path. We then update the number of restricted paths to y by adding the number of restricted paths to x.</br>
    * */

    class Solution {
        public int countRestrictedPaths(int n, int[][] edges) {
            List<List<Pair>> adj = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adj.add(new ArrayList<>());
            }

            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                adj.get(u).add(new Pair(v, w));
                adj.get(v).add(new Pair(u, w));
            }

            int[] dist = new int[n + 1];
            int[] ans = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[n] = 0;
            ans[n] = 1;
            PriorityQueue<Pair> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
            heap.offer(new Pair(n, 0));
            int mod = (int) 1e9 + 7;

            while (!heap.isEmpty()) {
                Pair top = heap.poll();
                int d = top.weight, x = top.node;
                if (d > dist[x]) continue;
                if (x == 1) break;
                for (Pair neighbor : adj.get(x)) {

                    int y = neighbor.node;
                    int w = neighbor.weight;

                    if (dist[y] > dist[x] + w) {
                        dist[y] = dist[x] + w;
                        heap.offer(new Pair(y, dist[y]));
                    }
                    if (dist[y] > dist[x]) {
                        ans[y] = (ans[y] + ans[x]) % mod;
                    }
                }
            }
            return ans[1];
        }
    }


}
