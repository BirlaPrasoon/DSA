package leetcode.problems.google.hard.dp.seems_simple_but_isnt;

import java.util.Arrays;

public class DungeonGame {

    // This is similar to coin distribution in Binary Tree problem.
    // If my child needs something, it will return me -ve value else zero.
    // If my child's return value +my return value is negative, I'll return the total else if its positive, I'll return 0.

    class Solution {
        int inf = Integer.MAX_VALUE;
        int[][] dp;
        int rows, cols;

        public int getMinHealth(int currCell, int nextRow, int nextCol) {
            if (nextRow >= this.rows || nextCol >= this.cols) return inf;
            int nextCell = this.dp[nextRow][nextCol];
            // hero needs at least 1 point to survive
            return Math.max(1, nextCell - currCell);
        }

        public int calculateMinimumHP(int[][] dungeon) {
            this.rows = dungeon.length;
            this.cols = dungeon[0].length;
            this.dp = new int[rows][cols];
            for (int[] arr : this.dp) {
                Arrays.fill(arr, this.inf);
            }
            int currCell, rightHealth, downHealth, nextHealth, minHealth;
            for (int row = this.rows - 1; row >= 0; --row) {
                for (int col = this.cols - 1; col >= 0; --col) {
                    currCell = dungeon[row][col];

                    rightHealth = getMinHealth(currCell, row, col + 1);
                    downHealth = getMinHealth(currCell, row + 1, col);
                    nextHealth = Math.min(rightHealth, downHealth);

                    if (nextHealth != inf) {
                        minHealth = nextHealth;
                    } else {
                        minHealth = currCell >= 0 ? 1 : 1 - currCell;
                    }
                    this.dp[row][col] = minHealth;
                }
            }
            return this.dp[0][0];
        }
    }


    class SolutionRecursive {
        Integer[][] dp;
        int ROW, COL;
        int DUMMY = Integer.MIN_VALUE;
        int[][] dungeon;

        public int calculateMinimumHP(int[][] dungeon) {
            if (dungeon.length == 0)
                return -1;
            ROW = dungeon.length;
            COL = dungeon[0].length;
            this.dungeon = dungeon;
            if (dungeon.length == 1 && dungeon[0].length == 1)
                return dungeon[0][0] >= 0 ? 1 : 1 - dungeon[0][0];
            dp = new Integer[dungeon.length][dungeon[0].length];
            int ans = solve(0, 0);
            return Math.abs(ans) + 1;
        }

        private int solve(int i, int j) {
            if (i == ROW - 1 && j == COL - 1) {
                dp[i][j] = Math.min(dungeon[i][j], 0);
                return Math.min(dungeon[i][j], 0);
            }
            if(dp[i][j] != null) return dp[i][j];
            int bottom = DUMMY;
            if (insideBoundary(i + 1, j)) {
                bottom = solve(i + 1, j);
            }

            int right = DUMMY;
            if (insideBoundary(i, j + 1)) {
                right = solve(i, j + 1);
            }
            int val = Integer.max(bottom, right);
            val = Math.min(val + dungeon[i][j], 0);
            dp[i][j] = val;
            return val;
        }

        boolean insideBoundary(int i, int j) {
            return i >= 0 && j >= 0 && i < ROW && j < COL;
        }
    }
}
