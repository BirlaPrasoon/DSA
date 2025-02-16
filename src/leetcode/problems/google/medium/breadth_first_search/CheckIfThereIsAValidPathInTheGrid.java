package leetcode.problems.google.medium.breadth_first_search;

import java.util.LinkedList;
import java.util.Queue;

public class CheckIfThereIsAValidPathInTheGrid {
    /*
    *
    * You are given an m x n grid. Each cell of grid represents a street. The street of grid[i][j] can be:

        1 which means a street connecting the left cell and the right cell.
        2 which means a street connecting the upper cell and the lower cell.
        3 which means a street connecting the left cell and the lower cell.
        4 which means a street connecting the right cell and the lower cell.
        5 which means a street connecting the left cell and the upper cell.
        6 which means a street connecting the right cell and the upper cell.
    *
    * */

    class Solution {
        int[][][] dirs = {
                {{0, -1}, {0, 1}},
                {{-1, 0}, {1, 0}},
                {{0, -1}, {1, 0}},
                {{0, 1}, {1, 0}},
                {{0, -1}, {-1, 0}},
                {{0, 1}, {-1, 0}}
        };

        private static class Cell {
            int x;
            int y;

            public Cell(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public boolean hasValidPath(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            boolean[][] visited = new boolean[m][n];
            Queue<Cell> queue = new LinkedList<>();
            queue.offer(new Cell(0, 0));
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                Cell curr = queue.poll();
                int x = curr.x;
                int y = curr.y;

                for (int[] dir : dirs[grid[x][y] - 1]) {
                    int i = x + dir[0];
                    int j = y + dir[1];

                    // Travel to the adjacent cell and come back to ensure both directions are the same
                    if (isValidPosition(i, j, m, n) && !visited[i][j]) {
                        for (int[] backDir : dirs[grid[i][j] - 1]) {
                            if (i + backDir[0] == x && j + backDir[1] == y) {
                                visited[i][j] = true;
                                queue.offer(new Cell(i, j));
                            }
                        }
                    }
                }
            }

            return visited[m - 1][n - 1];
        }

        private boolean isValidPosition(int x, int y, int m, int n) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }
    }
}
