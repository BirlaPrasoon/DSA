package leetcode.problems.google.hard.union_find;

import java.util.*;

public class NuberOfDistinctIslands {


    /*
     *
     * You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
     * connected 4-directionally (horizontal or vertical.)
     * You may assume all four edges of the grid are surrounded by water.
     *
     * An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or
     * reflection (left/right direction or up/down direction).
     *
     * Return the number of distinct islands.
     *
     * */


    class Solution {
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[][] transforms = new int[][]{{0, 1, 1, 0}, {0, 1, -1, 0}, {0, -1, 1, 0}, {0, -1, -1, 0}, {1, 0, 0, 1}, {-1, 0, 0, 1}, {1, 0, 0, -1}, {-1, 0, 0, -1}};

        public int numDistinctIslands2(int[][] grid) {
            Set<String> uniquIslands = new HashSet<>();

            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[r].length; c++) {
                    if (grid[r][c] == 1) {
                        List<int[]> points = new ArrayList<>();
                        dfs(r, c, grid, points);
                        uniquIslands.add(transform(points));

                    }
                }
            }

            return uniquIslands.size();
        }

        public void dfs(int r, int c, int[][] grid, List<int[]> points) {
            points.add(new int[]{r, c});
            grid[r][c] = 0;

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[r].length) {
                    if (grid[nr][nc] == 1) {
                        dfs(nr, nc, grid, points);
                    }
                }
            }
        }

        public String transform(List<int[]> points) {
            List<String> shapes = new ArrayList<>();

            for (int[] transform : transforms) {
                List<int[]> newShape = new ArrayList<>();
                for (int[] point : points) {
                    int nr = point[0] * transform[0] + point[1] * transform[1];
                    int nc = point[0] * transform[2] + point[1] * transform[3];
                    newShape.add(new int[]{nr, nc});
                }

                newShape.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
                int[] minPoint = newShape.get(0);

                StringBuilder sb = new StringBuilder();
                for (int[] point : newShape) {
                    sb.append(point[0] - minPoint[0]).append(":").append(point[1] - minPoint[1]);
                }
                shapes.add(sb.toString().trim());
            }

            Collections.sort(shapes);

            return shapes.get(0);
        }
    }
}
