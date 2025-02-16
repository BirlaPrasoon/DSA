package leetcode.problems.google.medium.union_find;

import datastructures.UnionFind;

import java.util.*;

public class PossibleBipartition {

/*    We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

    Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.
            */

/*    Example 1:

    Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
    Output: true
    Explanation: The first group has [1,4], and the second group has [2,3].
    Example 2:

    Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
    Output: false
    Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.
    */

    class SolutionBFS {
        public boolean bfs(int source, Map<Integer, List<Integer>> adj, int[] color) {
            Queue<Integer> q = new LinkedList<>();
            q.offer(source);
            color[source] = 0; // Start with marking source as `RED`.

            while (!q.isEmpty()) {
                int node = q.poll();
                if (!adj.containsKey(node))
                    continue;
                for (int neighbor : adj.get(node)) {
                    if (color[neighbor] == color[node])
                        return false;
                    if (color[neighbor] == -1) {
                        color[neighbor] = 1 - color[node];
                        q.add(neighbor);
                    }
                }
            }
            return true;
        }

        public boolean possibleBipartition(int n, int[][] dislikes) {
            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (int[] edge : dislikes) {
                int a = edge[0], b = edge[1];
                adj.computeIfAbsent(a, value -> new ArrayList<Integer>()).add(b);
                adj.computeIfAbsent(b, value -> new ArrayList<Integer>()).add(a);
            }

            int[] color = new int[n + 1];
            Arrays.fill(color, -1); // 0 stands for red and 1 stands for blue.

            for (int i = 1; i <= n; i++) {
                if (color[i] == -1) {
                    // For each pending component, run BFS.
                    if (!bfs(i, adj, color))
                        // Return false, if there is conflict in the component.
                        return false;
                }
            }
            return true;
        }
    }

    class SolutionUF {
        public boolean possibleBipartition(int n, int[][] dislikes) {
            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (int[] edge : dislikes) {
                int a = edge[0], b = edge[1];
                adj.computeIfAbsent(a, value -> new ArrayList<Integer>()).add(b);
                adj.computeIfAbsent(b, value -> new ArrayList<Integer>()).add(a);
            }

            UnionFind dsu = new UnionFind(n + 1);
            for (int node = 1; node <= n; node++) {
                if (!adj.containsKey(node))
                    continue;
                for (int neighbor : adj.get(node)) {
                    // Check if the node and its neighbor is in the same set.
                    if (dsu.find(node) == dsu.find(neighbor))
                        return false;
                    // Move all the neighbours into same set as the first neighbour.
                    dsu.union(adj.get(node).get(0), neighbor);
                }
            }
            return true;
        }
    }
}
