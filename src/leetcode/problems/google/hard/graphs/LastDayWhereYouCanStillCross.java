package leetcode.problems.google.hard.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LastDayWhereYouCanStillCross {
/*

    There is a 1-based binary matrix where 0 represents land and 1 represents water.
    You are given integers row and col representing the number of rows and columns in the matrix, respectively.

    Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water.
    You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the cell
    on the rith row and cith column (1-based coordinates) will be covered with water (i.e., changed to 1).

    You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells.
    You can start from any cell in the top row and end at any cell in the bottom row. You can only travel in the four
    cardinal directions (left, right, up, and down).

    Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.

*/


    // O(row⋅col⋅log(row⋅col))
    class SolutionBinarySearchWithBSF {
        private int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        public boolean canCross(int row, int col, int[][] cells, int day) {
            int[][] grid = new int[row][col];
            Queue<int[]> queue = new LinkedList<>();

            for (int i = 0; i < day; i++) {
                grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
            }

            for (int i = 0; i < col; i++) {
                if (grid[0][i] == 0) {
                    queue.offer(new int[]{0, i});
                    grid[0][i] = -1;
                }
            }

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1];
                if (r == row - 1) {
                    return true;
                }

                for (int[] dir : directions) {
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];
                    if (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col && grid[newRow][newCol] == 0) {
                        grid[newRow][newCol] = -1;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }

            return false;
        }

        public int latestDayToCross(int row, int col, int[][] cells) {
            int left = 1;
            int right = row * col;

            while (left < right) {
                int mid = right - (right - left) / 2;
                if (canCross(row, col, cells, mid)) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }

            return left;
        }
    }

    class SolutionBinarySearchWithDFS {
        private static final int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        public boolean canCross(int row, int col, int[][] cells, int day) {
            int[][] grid = new int[row][col];
            for (int i = 0; i < day; ++i) {
                int r = cells[i][0] - 1, c = cells[i][1] - 1;
                grid[r][c] = 1;
            }

            for (int i = 0; i < day; ++i) {
                grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
            }

            for (int i = 0; i < col; ++i) {
                if (grid[0][i] == 0 && dfs(grid, 0, i, row, col)) {
                    return true;
                }
            }
            return false;

        }

        private boolean dfs(int[][] grid, int r, int c, int row, int col) {
            if (r < 0 || r >= row || c < 0 || c >= col || grid[r][c] != 0) {
                return false;
            }
            if (r == row - 1) {
                return true;
            }
            grid[r][c] = -1;
            for (int[] dir : directions) {
                int newR = r + dir[0], newC = c + dir[1];
                if (dfs(grid, newR, newC, row, col)) {
                    return true;
                }
            }
            return false;
        }

        public int latestDayToCross(int row, int col, int[][] cells) {
            int left = 1, right = row * col;
            while (left < right) {
                int mid = right - (right - left) / 2;
                if (canCross(row, col, cells, mid)) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }
    }

    class DSU {
        int[] root, size;

        public DSU(int n) {
            root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
            size = new int[n];
            Arrays.fill(size, 1);
        }

        public int find(int x) {
            if (root[x] != x) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            if (size[rootX] > size[rootY]) {
                int tmp = rootX;
                rootX = rootY;
                rootY = tmp;
            }
            root[rootX] = rootY;
            size[rootY] += size[rootX];
        }
    }

    // O(row*col*alpha(row*col))
    class SolutionDSU {
        public int latestDayToCross(int R, int C, int[][] cells) {
            DSU dsu = new DSU(R * C + 2);
            int[][] grid = new int[R][C];
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            for (int i = cells.length - 1; i >= 0; i--) {
                int r = cells[i][0] - 1, c = cells[i][1] - 1;
                grid[r][c] = 1;
                int index1 = r * C + c + 1; // 0'th index is reserved for upper boundary, and last index is reserved for bottom boundary.
                for (int[] d : directions) {
                    int newR = r + d[0], newC = c + d[1];
                    int index2 = newR * C + newC + 1;
                    if (insideBoundary(newR, newC, R, C) && grid[newR][newC] == 1) {
                        dsu.union(index1, index2);
                    }
                }
                if (r == 0) {
                    dsu.union(0, index1);
                }
                if (r == R - 1) {
                    dsu.union(R * C + 1, index1);
                }
                if (dsu.find(0) == dsu.find(R * C + 1)) {
                    return i;
                }
            }
            return -1;
        }
    }

    private static boolean insideBoundary(int newR, int newC, int row, int col) {
        return newR >= 0 && newR < row && newC >= 0 && newC < col;
    }
}
