package leetcode.problems.google.medium.union_find;

import java.util.*;

public class MinimumScoreOfAPathBetween2Cities {

    /*
    *
    * You are given a positive integer n representing n cities numbered from 1 to n.
    * You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a
    * bidirectional road between cities ai and bi with a distance equal to distancei.
    * The cities graph is not necessarily connected.
    *
    * The score of a path between two cities is defined as the minimum distance of a road in this path.
    *
    * Return the minimum possible score of a path between cities 1 and n.
    *
    * Note:
    *
    * A path is a sequence of roads between two cities.
    * It is allowed for a path to contain the same road multiple times,
    * and you can visit cities 1 and n multiple times along the path.
    *
    * The test cases are generated such that there is at least one path between 1 and n.
    *
    * Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
Output: 5
Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 4. The score of this path is min(9,5) = 5.
It can be shown that no other path has less score.
*
* Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
Output: 2
Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 -> 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.
    * */

    class SolutionBFS {
        public int bfs(int n, Map<Integer, List<List<Integer>>> adj) {
            boolean[] visit = new boolean[n + 1];
            Queue<Integer> q = new LinkedList<>();
            int answer = Integer.MAX_VALUE;

            q.offer(1);
            visit[1] = true;

            while (!q.isEmpty()) {
                int node = q.poll();

                if (!adj.containsKey(node)) {
                    continue;
                }
                for (List<Integer> edge : adj.get(node)) {
                    answer = Math.min(answer, edge.get(1));
                    if (!visit[edge.get(0)]) {
                        visit[edge.get(0)] = true;
                        q.offer(edge.get(0));
                    }
                }
            }
            return answer;
        }

        public int minScore(int n, int[][] roads) {
            Map<Integer, List<List<Integer>>> adj = new HashMap<>();
            for (int[] road : roads) {
                adj.computeIfAbsent(road[0], k -> new ArrayList<List<Integer>>()).add(
                        Arrays.asList(road[1], road[2]));
                adj.computeIfAbsent(road[1], k -> new ArrayList<List<Integer>>()).add(
                        Arrays.asList(road[0], road[2]));
            }
            return bfs(n, adj);
        }
    }

    class SolutionDFS {
        int answer = Integer.MAX_VALUE;

        public void dfs(int node, Map<Integer, List<List<Integer>>> adj, boolean[] visit) {
            visit[node] = true;
            if (!adj.containsKey(node)) {
                return;
            }
            for (List<Integer> edge : adj.get(node)) {
                answer = Math.min(answer, edge.get(1));
                if (!visit[edge.get(0)]) {
                    dfs(edge.get(0), adj, visit);
                }
            }
        }

        public int minScore(int n, int[][] roads) {
            Map<Integer, List<List<Integer>>> adj = new HashMap<>();
            for (int[] road : roads) {
                adj.computeIfAbsent(road[0], k -> new ArrayList<List<Integer>>()).add(
                        Arrays.asList(road[1], road[2]));
                adj.computeIfAbsent(road[1], k -> new ArrayList<List<Integer>>()).add(
                        Arrays.asList(road[0], road[2]));
            }

            boolean[] visit = new boolean[n + 1];
            dfs(1, adj, visit);

            return answer;
        }
    }

    class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++)
                parent[i] = i;
            rank = new int[size];
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union_set(int x, int y) {
            int xset = find(x), yset = find(y);
            if (xset == yset) {
                return;
            } else if (rank[xset] < rank[yset]) {
                parent[xset] = yset;
            } else if (rank[xset] > rank[yset]) {
                parent[yset] = xset;
            } else {
                parent[yset] = xset;
                rank[xset]++;
            }
        }
    }

    class Solution {
        public int minScore(int n, int[][] roads) {
            UnionFind dsu = new UnionFind(n + 1);
            int answer = Integer.MAX_VALUE;

            for (int[] road : roads) {
                dsu.union_set(road[0], road[1]);
            }

            for (int[] road : roads) {
                if (dsu.find(1) == dsu.find(road[0])) {
                    answer = Math.min(answer, road[2]);
                }
            }

            return answer;
        }
    }

}
