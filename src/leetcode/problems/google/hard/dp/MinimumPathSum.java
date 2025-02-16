package leetcode.problems.google.hard.dp;

import java.util.Arrays;

public class MinimumPathSum {


    class Solution {
        public int minPathSum(int[][] grid) {
            if(grid.length == 0) return 0;
            int dp[][] = new int [grid.length][grid[0].length];
            Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));
            dp[dp.length-1][dp[0].length-1] = grid[dp.length-1][dp[0].length-1];
            return solve(0,0, grid, dp);
        }

        private int solve(int i, int j, int [][]grid, int dp[][]) {
            if(dp[i][j] >=0) return dp[i][j];

            int ans = Integer.MAX_VALUE;
            if(!isOutOfBoundary(i+1, j, grid)){
                ans = Math.min(ans, solve(i+1,j, grid, dp));
            }
            if(!isOutOfBoundary(i, j+1, grid)) {
                ans = Math.min(ans, solve(i,j+1, grid, dp));
            }
            return dp[i][j] = ans + grid[i][j];
        }

        private boolean isOutOfBoundary(int i, int j, int [][]grid) {
            return !(i>=0 && j>=0 && i<grid.length && j<grid[0].length);
        }
    }

}
