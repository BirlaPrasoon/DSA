package leetcode.problems.google.hard.graphs.dpOnTree;

import java.util.*;

public class SumOfDistancesInTree {
    class Solution {
        int[] ans, count;
        List<Set<Integer>> graph;
        int N;

        public int[] sumOfDistancesInTree(int N, int[][] edges) {
            this.N = N;
            graph = new ArrayList<Set<Integer>>();
            ans = new int[N];
            count = new int[N];
            Arrays.fill(count, 1);

            for (int i = 0; i < N; ++i)
                graph.add(new HashSet<Integer>());
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
            dfs(0, -1);
            dfs2(0, -1);
            return ans;
        }

        /*
        *
            Performs a depth-first search to compute initial values for `count` and `ans`.
            For each node:
            Recursively processes its children.
            Updates `count[node]` by adding the counts of its children.
            Updates `ans[node]` by adding the answers of its children plus their counts.

        * */
        public void dfs(int node, int parent) {
            for (int child : graph.get(node)) {
                if (child != parent) {
                    dfs(child, node);
                    count[node] += count[child];
                    ans[node] += ans[child] + count[child];
                    // now parent remains;
                }
            }
        }

        // for an edge y-> x
        // ans[x] = subtree(x) + subtree(y) + count(y)
        // count -> number of nodes in the subtree Y without y->x edge
        // since y is the root, it doesn't have any parent, its answer in above dfs will be the same no matter what.
        // ans [x] - ans[y] = count[y] - count[x]

        /*
        * Re-roots the tree at each node to compute the final answer.
            For each node:
            Updates `ans[child]` using the formula: `ans[child] = ans[node] - count[child] + (N - count[child])`
            This formula adjusts the distances based on the new root.
        * */
        public void dfs2(int node, int parent) {
            for (int child : graph.get(node)) {
                if (child != parent) {
                    ans[child] = ans[node] + (N - count[child] /* This is count[node]*/) - count[child];
                    dfs2(child, node);
                }
            }
        }
    }
}
