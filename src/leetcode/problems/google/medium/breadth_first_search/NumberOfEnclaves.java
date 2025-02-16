package leetcode.problems.google.medium.breadth_first_search;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfEnclaves {
    /*
    *
    * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.


    *
    * */

    class SolutionDFS {
        public void fill(int row, int col, int[][] grid) {
            if (row < 0 || col < 0 || row == grid.length || col == grid[0].length || grid[row][col] == 0) {
                return;
            }
            grid[row][col] = 0;
            fill(row + 1, col, grid);
            fill(row, col - 1, grid);
            fill(row, col + 1, grid);
            fill(row - 1, col, grid);
        }

        public int numEnclaves(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                fill(i, 0, grid);
                fill(i, grid[0].length - 1, grid);
            }
            for (int i = 0; i < grid[0].length; i++) {
                fill(0, i, grid);
                fill(grid.length - 1, i, grid);
            }
            int count = 0;
            for (int[] i : grid) {
                for (int j : i) {
                    count += j;
                }
            }
            return count;
        }
    }

    class SolutionBFS {
        public void bfs(int x, int y, int m, int n, int[][] grid, boolean[][] visit) {
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{x, y});
            visit[x][y] = true;

            int[] dirx = {0, 1, 0, -1};
            int[] diry = {-1, 0, 1, 0};

            while (!q.isEmpty()) {
                int[] temp = q.poll();
                x = temp[0];  // row nnumber
                y = temp[1];  // column number

                for (int i = 0; i < 4; i++) {
                    int r = x + dirx[i];
                    int c = y + diry[i];
                    if (r < 0 || r >= m || c < 0 || c >= n) {
                        continue;
                    } else if (grid[r][c] == 1 && !visit[r][c]) {
                        q.offer(new int[]{r, c});
                        visit[r][c] = true;
                    }
                }
            }
            return;
        }

        public int numEnclaves(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visit = new boolean[m][n];

            for (int i = 0; i < m; ++i) {
                // First column.
                if (grid[i][0] == 1 && !visit[i][0]) {
                    bfs(i, 0, m, n, grid, visit);
                }
                // Last column.
                if (grid[i][n - 1] == 1 && !visit[i][n - 1]) {
                    bfs(i, n - 1, m, n, grid, visit);
                }
            }

            for (int i = 0; i < n; ++i) {
                // First row.
                if (grid[0][i] == 1 && !visit[0][i]) {
                    bfs(0, i, m, n, grid, visit);
                }
                // Last row.
                if (grid[m - 1][i] == 1 && !visit[m - 1][i]) {
                    bfs(m - 1, i, m, n, grid, visit);
                }
            }

            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1 && !visit[i][j]) {
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
