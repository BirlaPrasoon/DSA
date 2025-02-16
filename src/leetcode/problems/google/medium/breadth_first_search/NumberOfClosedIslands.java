package leetcode.problems.google.medium.breadth_first_search;

public class NumberOfClosedIslands {

/*    Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

    Return the number of closed islands.
    */

    class Solution {
        public int closedIsland(int[][] grid) {
            int count = 0;
            for(int i = 0; i<grid.length; i++) {
                for(int j = 0; j<grid[i].length; j++) {
                    if(grid[i][j] == 0) {
                        Boundary b = new Boundary();
                        dfs(i, j, grid, b);
                        if(b.touched == false) {
                            count++;
                        }
                    }
                }
            }
            return count;
        }

        private void dfs(int i, int j, int [][]grid, Boundary b) {
            if(boundaryTouch(i, j, grid)) {
                b.touched = true;
                return;
            }
            if(grid[i][j] != 0) return;
            grid[i][j] = 2;
            // left
            dfs(i, j-1, grid, b);
            //right
            dfs(i, j+1, grid, b);
            // top
            dfs(i-1, j, grid, b);
            // bottom
            dfs(i+1, j, grid, b);
        }

        class Boundary {
            boolean touched = false;
        }

        private boolean boundaryTouch(int i, int j, int grid[][]) {
            return i<0 || j<0 || i>=grid.length || j>= grid[i].length;
        }
    }
}
