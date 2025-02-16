package leetcode.problems.google.hard.graphs.must_practice.dijkstra_with_2_distancearrays;

import java.util.*;

public class SecondMinimumTimeToReachDestination {
    /**
     * A city is represented as a bi-directional connected graph with n vertices where each vertex is labeled from 1 to n (inclusive).
     * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge
     * between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
     * The time taken to traverse any edge is time minutes.
     * <p>
     * Each vertex has a traffic signal which changes its color from green to red and vice versa every change minutes.
     * All signals change at the same time. You can enter a vertex at any time, but can leave a vertex only when the signal is green.
     * You cannot wait at a vertex if the signal is green.
     * <p>
     * The second minimum value is defined as the smallest value strictly larger than the minimum value.
     * <p>
     * For example the second minimum value of [2, 3, 4] is 3, and the second minimum value of [2, 2, 4] is 4.
     * Given n, edges, time, and change, return the second minimum time it will take to go from vertex 1 to vertex n.
     * <p>
     * Notes:
     * <p>
     * You can go through any vertex any number of times, including 1 and n.
     * You can assume that when the journey starts, all signals have just turned green.
     */

    static class Solution {

        public int secondMinimum(int n, int[][] edges, int time, int change) {
            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (int[] edge : edges) {
                int a = edge[0], b = edge[1];
                adj.computeIfAbsent(a, value -> new ArrayList<Integer>()).add(b);
                adj.computeIfAbsent(b, value -> new ArrayList<Integer>()).add(a);
            }
            int[] dist1 = new int[n + 1], dist2 = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                dist1[i] = dist2[i] = -1;
            }
            System.out.println("Graph: " + adj);
            Queue<int[]> queue = new LinkedList<>();
            // Start with node 1, with its minimum distance.
            queue.offer(new int[]{1, 1});
            dist1[1] = 0;

            while (!queue.isEmpty()) {
                int[] temp = queue.poll();
                int node = temp[0];
                int freq = temp[1];

                int timeTaken = freq == 1 ? dist1[node] : dist2[node];
                // If the time_taken falls under the red bracket, wait till the path turns
                // green.
                System.out.println("NODE: " + node);
                System.out.println("TimeTaken initial: " + timeTaken);
                if ((timeTaken / change) % 2 == 1) {
                    // This is simple, cur time is 6, we're blocked till 10 i.e (6/5 + 1) * 5 + time (3) => unblocked at 13
                    timeTaken = change * (timeTaken / change + 1) + time;
                } else {
                    timeTaken = timeTaken + time;
                }
                System.out.println("TimeTaken final: " + timeTaken);

                for (int neighbor : adj.get(node)) {
                    if (dist1[neighbor] == -1) {
                        // Allow first time visiting the same node.
                        dist1[neighbor] = timeTaken;
                        queue.offer(new int[]{neighbor, 1});
                    } else if (dist2[neighbor] == -1 && dist1[neighbor] != timeTaken) {
                        if (neighbor == n) return timeTaken;
                        // Allow second time visiting the same node.
                        dist2[neighbor] = timeTaken;
                        queue.offer(new int[]{neighbor, 2});
                    }
                }
            }
            return 0;
        }

        public static void main(String[] args) {
            SecondMinimumTimeToReachDestination.Solution destination = new SecondMinimumTimeToReachDestination.Solution();
            int[][] edges = {
                    {1,2},
                    {2,3},
                    {2,4},
                    {4,5},
                    {5,6},
                    {5,7},
                    {5,8}
            };
            System.out.println(destination.secondMinimum(8, edges, 3, 5));
        }
    }
}
