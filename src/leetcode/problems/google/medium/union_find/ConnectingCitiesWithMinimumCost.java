package leetcode.problems.google.medium.union_find;

import datastructures.UnionFind;

import java.util.*;

public class ConnectingCitiesWithMinimumCost {

    /*
    *
    * There are n cities labeled from 1 to n. You are given the integer n and an array connections where
    * connections[i] = [xi, yi, costi] indicates that the cost of connecting city xi and city yi (bidirectional connection) is costi.
    *
    * Return the minimum cost to connect all the n cities such that there is at least one path between each pair of cities.
    * If it is impossible to connect all the n cities, return -1,
    *
    * The cost is the sum of the connections' costs used.
    *
    *
        Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
        Output: 6
        Explanation: Choosing any 2 edges will connect all cities so we choose the minimum 2.

        Input: n = 4, connections = [[1,2,3],[3,4,4]]
        Output: -1
        Explanation: There is no way to connect all cities even if all edges are used.

    * */

    class SolutionKruskal {
        public int minimumCost(int n, int[][] connections) {
            Arrays.sort(connections, (a,b) -> a[2] - b[2]);

            UnionFind uf = new UnionFind(n+1);
            int edgesConnected = 0;
            int mstWeight = 0;

            for (int[] connection : connections) {
                if (uf.union(connection[0], connection[1])) {
                    edgesConnected++;
                    mstWeight += connection[2];
                    if (edgesConnected == n - 1) {
                        break;
                    }
                }
            }

            if (edgesConnected < n - 1) {
                return -1;
            }

            return mstWeight;
        }

    }

    class SolutionPrims {
        public int minimumCost(int n, int[][] connections) {
            // Create an adjacency list for the graph
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                graph.put(i, new ArrayList<>());
            }
            for (int[] conn : connections) {
                graph.get(conn[0]).add(new int[]{conn[1], conn[2]});
                graph.get(conn[1]).add(new int[]{conn[0], conn[2]});
            }

            // Prim's algorithm using a Priority Queue
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            boolean[] visited = new boolean[n + 1];
            int totalCost = 0;
            int edgesUsed = 0;

            // Start from any city, here starting from city 1
            pq.offer(new int[]{1, 0});

            while (!pq.isEmpty() && edgesUsed < n) {
                int[] current = pq.poll();
                int city = current[0];
                int cost = current[1];

                // If the city is already visited, skip it
                if (visited[city]) continue;

                // Mark the city as visited
                visited[city] = true;
                totalCost += cost;
                edgesUsed++;

                // Add all edges from the current city to the queue
                for (int[] neighbor : graph.get(city)) {
                    if (!visited[neighbor[0]]) {
                        pq.offer(new int[]{neighbor[0], neighbor[1]});
                    }
                }
            }

            // If we haven't used n edges, it means we couldn't connect all cities
            return edgesUsed == n ? totalCost : -1;
        }
    }


}
