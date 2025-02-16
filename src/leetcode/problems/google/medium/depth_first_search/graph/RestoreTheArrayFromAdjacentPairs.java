package leetcode.problems.google.medium.depth_first_search.graph;

import java.util.*;

public class RestoreTheArrayFromAdjacentPairs {

/**
    There is an integer array nums that consists of n unique elements, but you have forgotten it.
    However, you do remember every pair of adjacent elements in nums.

    You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi]
    indicates that the elements ui and vi are adjacent in nums.

    It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs,
    either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.

    Return the original array nums. If there are multiple solutions, return any of them.



    Example 1:

    Input: adjacentPairs = [[2,1],[3,4],[3,2]]
    Output: [1,2,3,4]
    Explanation: This array has all its adjacent pairs in adjacentPairs.
    Notice that adjacentPairs[i] may not be in left-to-right order.

    Example 2:

    Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
    Output: [-2,4,1,-3]
    Explanation: There can be negative numbers.
    Another solution is [-3,1,4,-2], which would also be accepted.

    Example 3:

    Input: adjacentPairs = [[100000,-100000]]
    Output: [100000,-100000]
*/

    static class Solution {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        public int[] restoreArray(int[][] adjacentPairs) {
            System.out.println("EDGES:");
            for (int[] edge : adjacentPairs) {
                int x = edge[0];
                int y = edge[1];

                System.out.println(Arrays.toString(edge));
                if (!graph.containsKey(x)) {
                    graph.put(x, new ArrayList<>());
                }

                if (!graph.containsKey(y)) {
                    graph.put(y, new ArrayList<>());
                }

                graph.get(x).add(y);
                graph.get(y).add(x);
            }

            System.out.println("Graph: " + graph);
            int root = 0;
            for (int num : graph.keySet()) {
                if (graph.get(num).size() == 1) {
                    root = num;
                }
            }

            System.out.println("Root: " + root);
            int[] ans = new int[graph.size()];
            HashSet<Integer> visited = new HashSet<>();
            dfs(root, ans, 0, visited);
            return ans;
        }

        private void dfs(int node, int[] ans, int i, HashSet<Integer> visited) {
            ans[i] = node;
            visited.add(node);
            for (int neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, ans, i + 1, visited);
                }
            }
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int pairs[][] = {
                    {2,1},
                    {3,4},
                    {3,2}
            };
            System.out.println(Arrays.toString(solution.restoreArray(pairs)));
        }
    }

}
