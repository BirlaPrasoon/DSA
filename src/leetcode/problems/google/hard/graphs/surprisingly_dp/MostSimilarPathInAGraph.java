package leetcode.problems.google.hard.graphs.surprisingly_dp;

import java.util.*;

public class MostSimilarPathInAGraph {
    /**
     * We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi.</br></br>
     * Each city has a name consisting of exactly three upper-case English letters given in the string array names.</br></br>
     * Starting at any city x, you can reach any city y where y != x (i.e., the cities and the roads are forming an undirected connected graph).</br></br>

     * You will be given a string array targetPath. You should find a path in the graph of the same length and with the minimum edit distance
     * to targetPath.</br></br>

     * You need to return the order of the nodes in the path with the minimum edit distance.</br></br>
     * The path should be of the same length of targetPath and should be valid (i.e., there should be a direct road between ans[i] and ans[i + 1]).</br></br>
     * If there are multiple answers return any one of them.</br></br>
     */

    // Time: O(m⋅k), Space: O(n⋅k). m -> Edges, n -> vertices, k -> target path length;

    class SolutionDP {
        public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
            Integer[][] dp = new Integer[targetPath.length][n];
            int[][] p = new int[targetPath.length][n];
            // initialize DP
            for (int i = 0; i < n; i++) {
                dp[0][i] = names[i].equals(targetPath[0]) ? 0 : 1;
            }
            // calculate DP
            for (int i = 1; i < targetPath.length; i++) {
                Arrays.fill(dp[i], targetPath.length + 1);
                for (int[] road : roads) {
                    // consider both edges (u, v) and (v, u)
                    for (int j = 0; j < 2; j++) {
                        int u = road[j];
                        int v = road[j ^ 1];

                        int cur = dp[i - 1][u] + (names[v].equals(targetPath[i]) ? 0 : 1);

                        if (cur < dp[i][v]) {
                            dp[i][v] = cur;
                            p[i][v] = u;
                        }
                    }
                }
            }
            List<Integer> lastDP = Arrays.asList(dp[targetPath.length - 1]);
            // the last vertex in the path
            int v = lastDP.indexOf(Collections.min(lastDP));
            List<Integer> ans = new ArrayList<Integer>();
            ans.add(v);
            for (int i = targetPath.length - 1; i > 0; i--) {
                // the previous vertex in the path
                v = p[i][v];
                ans.add(v);
            }
            Collections.reverse(ans);
            return ans;
        }
    }

    // Special BFS
    class Solution {
        public List<Integer> mostSimilar(final int N, final int[][] roads, final String[] names, final String[] targetPath) {
            // Build adjacency list.
            final List<Integer>[] adjList = new List[N];
            for (int i = 0; i < N; i++) adjList[i] = new ArrayList<>();
            for (int[] road : roads) {
                int a = road[0], b = road[1];
                adjList[a].add(b);
                adjList[b].add(a);
            }

            final int l = targetPath.length;

            // Special BFS.
            // During transition:
            // 		if the edit distance doesn't change => add to the front.
            // 		if edit distance changes (increases by 1) => add to the last of the queue.
            final Deque<Integer> q = new LinkedList<>();
            boolean[][] seen = new boolean[N][l];
            // Since the Deque will be sorted by the edit distance, it is redundant to add a node to the queue if one is already present.
            boolean[][] inQ = new boolean[N][l];

            // used for building the path.
            final int[][] prev = new int[N][l];
            for (int u = 0; u < N; u++) {
                if (names[u].equals(targetPath[0])) {
                    q.addFirst(0);
                    q.addFirst(u);
                } else {
                    q.addLast(u);
                    q.addLast(0);
                }
                inQ[u][0] = true;
                prev[u][0] = -1;
            }

            // last node. Point of termination of the below procedure.
            int sId = -1;
            while (!q.isEmpty()) {
                int u = q.pollFirst();
                int pos = q.pollFirst();

                if (pos == (l - 1)) {
                    sId = u;
                    break;
                }

                if (seen[u][pos]) continue;
                seen[u][pos] = true;

                for (int v : adjList[u]) {
                    int nPos = pos + 1;
                    if (seen[v][nPos] || inQ[v][nPos]) continue;
                    if (names[v].equals(targetPath[nPos])) {
                        q.addFirst(nPos);
                        q.addFirst(v);
                    } else {
                        q.addLast(v);
                        q.addLast(nPos);
                    }
                    inQ[v][nPos] = true;
                    prev[v][nPos] = u;
                }
            }

            LinkedList<Integer> res = new LinkedList<>();
            int len = l - 1;
            while (sId > -1) {
                res.addFirst(sId);
                sId = prev[sId][len];
                len--;
            }
            return res;
        }
    }

}
