package leetcode.problems.google.hard.topoSort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StrangePrinter2 {

    static class Solution {
/*
        The algorithm still works in these steps:

        Find boundaries for each color (minimum and maximum row/column)
        Build a dependency graph where an edge from color A to B means A must be printed before B
        Check for cycles using DFS - if there's a cycle, return false

        The time complexity remains O(mnc) where:

        m = number of rows
        n = number of columns
        c = number of colors

        Space complexity is O(c²) for the graph storage.
        */

        public boolean isPrintable(int[][] targetGrid) {
            if (targetGrid == null || targetGrid.length == 0 || targetGrid[0].length == 0) {
                return true;
            }

            int m = targetGrid.length;
            int n = targetGrid[0].length;

            // Store min/max boundaries for each color
            // [minRow, minCol, maxRow, maxCol]
            Map<Integer, int[]> colorBounds = new HashMap<>();

            // Find boundaries for each color
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int color = targetGrid[i][j];
                    colorBounds.putIfAbsent(color, new int[]{i, j, i, j});
                    int[] bounds = colorBounds.get(color);
                    bounds[0] = Math.min(bounds[0], i);  // minRow
                    bounds[1] = Math.min(bounds[1], j);  // minCol
                    bounds[2] = Math.max(bounds[2], i);  // maxRow
                    bounds[3] = Math.max(bounds[3], j);  // maxCol
                }
            }

            // Build dependency graph
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for (Map.Entry<Integer, int[]> entry : colorBounds.entrySet()) {
                int color = entry.getKey();
                int[] bounds = entry.getValue();
                graph.putIfAbsent(color, new HashSet<>());

                // Check each cell in the rectangle
                for (int i = bounds[0]; i <= bounds[2]; i++) {
                    for (int j = bounds[1]; j <= bounds[3]; j++) {
                        if (targetGrid[i][j] != color) {
                            // Current color must be printed before targetGrid[i][j]
                            graph.get(color).add(targetGrid[i][j]);
                        }
                    }
                }
            }

            // Check for cycles using DFS
            Set<Integer> visited = new HashSet<>();
            Set<Integer> onPath = new HashSet<>();

            for (int color : graph.keySet()) {
                if (!visited.contains(color)) {
                    if (hasCycle(color, graph, visited, onPath)) {
                        return false;
                    }
                }
            }

            return true;
        }

        private boolean hasCycle(int node, Map<Integer, Set<Integer>> graph,
                                 Set<Integer> visited, Set<Integer> onPath) {
            if (onPath.contains(node)) {
                return true;
            }
            if (visited.contains(node)) {
                return false;
            }

            visited.add(node);
            onPath.add(node);

            for (int neighbor : graph.get(node)) {
                if (hasCycle(neighbor, graph, visited, onPath)) {
                    return true;
                }
            }

            onPath.remove(node);
            return false;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();

            // Example 1: Printable grid
            int[][] grid1 = {
                    {1, 1, 1},
                    {3, 1, 3},
                    {3, 3, 3}
            };
        /*
        This grid can be printed in following steps:
        1. Print color 1 in rectangle (0,0) to (1,2)
        2. Print color 3 in rectangle (1,0) to (2,2)
        */

            System.out.println("Example 1 (Should be true): " + solution.isPrintable(grid1));

            // Example 2: Unprintable grid
            int[][] grid2 = {
                    {1, 2, 1},
                    {2, 1, 2},
                    {1, 2, 1}
            };
        /*
        This grid cannot be printed because:
        - Color 1 needs to be printed after color 2 (to cover the 2s)
        - Color 2 needs to be printed after color 1 (to cover the 1s)
        Creates a cycle: 1 -> 2 -> 1
        */

            System.out.println("Example 2 (Should be false): " + solution.isPrintable(grid2));

            // Example 3: More complex printable grid
            int[][] grid3 = {
                    {1, 1, 1, 1},
                    {1, 2, 2, 1},
                    {1, 2, 2, 1},
                    {1, 1, 1, 1}
            };
        /*
        This grid can be printed in following steps:
        1. Print color 1 in rectangle (0,0) to (3,3)
        2. Print color 2 in rectangle (1,1) to (2,2)
        */

            System.out.println("Example 3 (Should be true): " + solution.isPrintable(grid3));
        }
    }

}
