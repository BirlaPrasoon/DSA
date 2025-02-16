package leetcode.problems.google.medium.union_find.different;

import datastructures.UnionFind;

public class DetectCyclesInA2DGrid {

    /*
    *
    * Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.
    *
    * */

    class SolutionUF {
        public boolean containsCycle(char[][] grid) {
            int n = grid.length, m = grid[0].length;
            UnionFind uf = new UnionFind(n*m);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i+1 < n && grid[i+1][j] == grid[i][j]) { // connect to bottom
                        if (!uf.union(i*m +j, (i+1)*m + j)) return true;
                    }
                    if (j+1 < m && grid[i][j+1] == grid[i][j]) { // connect to right
                        if (!uf.union(i*m +j, i*m + (j+1))) return true;
                    }
                }
            }
            return false;
        }
    }

}
