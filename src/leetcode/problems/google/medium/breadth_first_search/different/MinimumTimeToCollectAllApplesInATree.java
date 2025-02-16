package leetcode.problems.google.medium.breadth_first_search.different;

import java.util.*;

public class MinimumTimeToCollectAllApplesInATree {

    /**
     * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices.
     * You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect
     * all apples in the tree, starting at vertex 0 and coming back to this vertex.
     * <p>
     * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that
     * exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple,
     * where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
     * Output: 8
     * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
     * Example 2:
     * <p>
     * <p>
     * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
     * Output: 6
     * Explanation: The figure above represents the given tree where red vertices have an apple.
     * One optimal path to collect all apples is shown by the green arrows.
     * Example 3:
     * <p>
     * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
     * Output: 0
     */

    class Solution {
        public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
            List<List<Integer>> adjList = createGraph(edges, hasApple.size());
            int time = dfs(0, adjList, hasApple, new boolean[hasApple.size()]);
            return time > 0 ? time - 2 : 0;
        }

        private int dfs(int node, List<List<Integer>> adjList, List<Boolean> hasApple, boolean[] visited) {
            if (visited[node]) {
                return 0;
            }
            visited[node] = true;
            int time = 0;
            for (int child : adjList.get(node)) {
                time += dfs(child, adjList, hasApple, visited);
            }
            if (time > 0 || hasApple.get(node)) {
                time += 2;
            }
            return time;
        }

        private List<List<Integer>> createGraph(int[][] edges, int n) {
            List<List<Integer>> adjList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>(3));
            }
            for (int[] e : edges) {
                adjList.get(e[0]).add(e[1]);
                adjList.get(e[1]).add(e[0]);
            }
            return adjList;
        }
    }
}
