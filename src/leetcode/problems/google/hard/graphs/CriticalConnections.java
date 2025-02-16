package leetcode.problems.google.hard.graphs;

import java.util.*;

public class CriticalConnections {
    class Solution {
        int[] disc, low;
        int time = 1;
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer,List<Integer>> graph = new HashMap<>();

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            disc = new int[n];
            low = new int[n];
            for (int i = 0; i < n; i++)
                graph.put(i, new ArrayList<>());
            for (List<Integer> conn : connections) {
                graph.get(conn.get(0)).add(conn.get(1));
                graph.get(conn.get(1)).add(conn.get(0));
            }
            dfs(0, -1);
            return ans;
        }

        public void dfs(int curr, int parent) {
            disc[curr] = low[curr] = time++;
            for (int nei : graph.get(curr)) {
                if (disc[nei] == 0) {
                    dfs(nei, curr);
                    low[curr] = Math.min(low[curr], low[nei]);
                } else if (nei != parent)
                    low[curr] = Math.min(low[curr], disc[nei]);
                if (low[nei] > disc[curr])
                    ans.add(Arrays.asList(curr, nei));
            }
        }
    }
}
