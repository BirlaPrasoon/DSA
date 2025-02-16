package leetcode.problems.google.medium.depth_first_search.diff;

public class DisconnectPathInABinaryMatrixByAtMostOneBit {
/*
    You are given a 0-indexed m x n binary matrix grid. You can move from a cell (row, col)
    to any of the cells (row + 1, col) or (row, col + 1) that has the value 1. The matrix is disconnected
    if there is no path from (0, 0) to (m - 1, n - 1).

    You can flip the value of at most one (possibly none) cell.
    You cannot flip the cells (0, 0) and (m - 1, n - 1).

    Return true if it is possible to make the matrix disconnect or false otherwise.

    Note that flipping a cell changes its value from 0 to 1 or from 1 to 0.
    */

/*
    Example 1:

    Input: grid = [[1,1,1],[1,0,0],[1,1,1]]
    Output: true
    Explanation: We can change the cell shown in the diagram above. There is no path from (0, 0) to (2, 2) in the resulting grid.
    Example 2:


    Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
    Output: false
    Explanation: It is not possible to change at most one cell such that there is not path from (0, 0) to (2, 2).

    */

    // If there are more than one path, we can not. If not we can. So just count number of paths from (0,0) to (m-1, n-1).
    // Or find one path, and set all bits in this path to 0, now this path is completely destroyed.
    // (Anything common with any other path is also destroyed.)
    // Even after doing this if I'm able to reach dest, it can't be destroyed.

    class Solution {
        private static final int[][] dirs={{0,1}, {1,0}};
        public boolean isPossibleToCutPath(int[][] grid) {
            int first=dfs(grid,0,0);
            int second=dfs(grid,0,0);
            return second >= 1 ? false : true;
        }

        private int dfs(int[][] g, int r, int c) {
            if(r < 0 || r == g.length || c < 0 || c == g[0].length || g[r][c] == 0)
                return 0;
            if(r == g.length-1 && c == g[0].length-1)
                return 1;

            if(!(r == 0 && c == 0))
                g[r][c]=0;

            int count=0;
            for(int[] dir:dirs) {
                count += dfs(g,r+dir[0],c+dir[1]);
                if(count >= 1)
                    break;
            }
            return count;
        }
    }
}
