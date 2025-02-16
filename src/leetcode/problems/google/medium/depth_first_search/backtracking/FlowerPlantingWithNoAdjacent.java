package leetcode.problems.google.medium.depth_first_search.backtracking;

import java.util.ArrayList;
import java.util.List;

public class FlowerPlantingWithNoAdjacent {

    // Next available free slot...

    class Solution {
        public int[] gardenNoAdj(int n, int[][] paths) {
            List<List<Integer>> adj = getAdjacencyList(paths, n);
            int[] result = new int[n];
            solve(1, adj, result, n);
            return result;
        }

        private boolean solve(int node, List<List<Integer>> adj, int[] result, int n) {
            if (node == n + 1) return true;

            for (int col = 1; col <= 4; col++) {
                if (isPossible(node, col, adj, result)) {
                    result[node - 1] = col;
                    if (solve(node + 1, adj, result, n)) return true;
                    result[node - 1] = 0;
                }
            }
            return false;
        }

        private boolean isPossible(int node, int col, List<List<Integer>> adj, int[] result) {
            for (int it : adj.get(node)) {
                if (result[it - 1] == col) return false;
            }
            return true;
        }

        private List<List<Integer>> getAdjacencyList(int[][] paths, int n) {
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
            for (int[] path : paths) {
                adj.get(path[0]).add(path[1]);
                adj.get(path[1]).add(path[0]);
            }
            return adj;
        }
    }
}
