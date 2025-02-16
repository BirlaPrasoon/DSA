package leetcode.problems.google.hard.graphs.diff;

import java.util.*;

public class DivideNodesIntoMaximumNumberOfGroups {

    /**
     * You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled from 1 to n.<br><br>
     * <p>
     * You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional edge between nodes ai and bi. Notice that the given graph may be disconnected.<br><br>
     *
     * <br>Divide the nodes of the graph into m groups (1-indexed) such that:<br><br>
     * <p>
     * Each node in the graph belongs to exactly one group.<br>
     * For every pair of nodes in the graph that are connected by an edge [a<sub>i</sub>, b<sub>i</sub>], if a<sub>i</sub> belongs to the group with index x,
     * and b<sub>i</sub> belongs to the group with index y, then |y - x| = 1.<br><br>
     * Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes. Return -1 if it is impossible to group the nodes with the given conditions.<br><br>
     */

    // Look in notes for understanding...
    class Solution {
        public int magnificentSets(int n, int[][] edges) {
            List<List<Integer>> graph = buildUndirectedGraph(n, edges);
            Map<Integer, Integer> componentMaxDistanceMap = new HashMap<>();
            for (int start = 1; start <= n; start++) {
                int d = 0;
                int smallestId = Integer.MAX_VALUE;
                int[] level = new int[n + 1];

                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(start);
                level[start] = 1;

                while (!queue.isEmpty()) {
                    d++;
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        int current = queue.poll();
                        smallestId = Math.min(smallestId, current);
                        for (int next : graph.get(current)) {
                            if (level[next] == 0) {
                                level[next] = d + 1;
                                queue.offer(next);
                            } else if (level[next] == d) {
                                return -1;
                            }
                        }
                    }
                }
                int distance = componentMaxDistanceMap.containsKey(smallestId) ? Math.max(d, componentMaxDistanceMap.get(smallestId)) : d;
                componentMaxDistanceMap.put(smallestId, distance);
            }
            int result = 0;
            for (Map.Entry<Integer, Integer> entry : componentMaxDistanceMap.entrySet()) {
                result += entry.getValue();
            }
            return result;
        }

        private static List<List<Integer>> buildUndirectedGraph(int n, int[][] edges) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n + 1; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                int current = edge[0];
                int next = edge[1];
                graph.get(current).add(next);
                graph.get(next).add(current);
            }
            return graph;
        }
    }
}
