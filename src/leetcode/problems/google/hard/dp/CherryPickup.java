package leetcode.problems.google.hard.dp;

import java.util.Arrays;

public class CherryPickup {


    class Solution {
        int[][][] memo;
        int[][] grid;
        int N;

        public int cherryPickup(int[][] grid) {
            this.grid = grid;
            N = grid.length;
            memo = new int[N][N][N];
            for (int[][] layer : memo) {
                for (int[] row : layer) {
                    Arrays.fill(row, Integer.MIN_VALUE);
                }
            }
            return Math.max(0, dp(0, 0, 0));
        }

        public int dp(int r1, int c1, int c2) {
            int r2 = r1 + c1 - c2; // space optimization...
            // why above works? (r1+c1+1) First person's movement (r2+c2+1) second person's movenent. And both will always be equal.
            // So r1 + c1 = r2 + c2, gives me r2 = r1 + c1 - c2;
            if (N == r1 || N == r2 || N == c1 || N == c2 || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
                return -999999;
            } else if (r1 == N - 1 && c1 == N - 1) {
                // We only count this one time when both persons are at same place.
                return grid[r1][c1];
            } else if (memo[r1][c1][c2] != Integer.MIN_VALUE) {
                return memo[r1][c1][c2];
            } else {
                // We only count this one time when both persons are at same place.
                int ans = grid[r1][c1];
                if (c1 != c2) {
                    ans += grid[r2][c2];
                }
                ans += Math.max(
                        Math.max(dp(r1, c1 + 1, c2 + 1), dp(r1 + 1, c1, c2 + 1)),
                        Math.max(dp(r1, c1 + 1, c2), dp(r1 + 1, c1, c2))
                );
                memo[r1][c1][c2] = ans;
                return ans;
            }
        }
    }
}
