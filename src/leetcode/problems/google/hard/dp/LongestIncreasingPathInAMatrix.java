package leetcode.problems.google.hard.dp;

public class LongestIncreasingPathInAMatrix {

    class Solution {
        private static final int[][] MOVES = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        private Integer[][] dp;
        public int longestIncreasingPath(int[][] matrix) {
            dp = new Integer[matrix.length][matrix[0].length];
            int longestPath = 0;
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix[0].length; ++j) {
                    if (dp[i][j] == null) {
                        longestPath = Math.max(longestPath, longestIncreasingPath(matrix, i, j));
                    }
                }
            }
            return longestPath;
        }

        private int longestIncreasingPath(int[][] matrix, int c, int r) {
            if (dp[c][r] != null) {
                return dp[c][r];
            }
            int max = 0;
            for (var move: MOVES) {
                int x = c + move[0];
                int y = r + move[1];
                if (isValid(matrix, x, y) && matrix[x][y] > matrix[c][r]) {
                    max = Math.max(max, longestIncreasingPath(matrix, x, y));
                }
            }
            return dp[c][r] = max + 1;
        }

        private boolean isValid(int[][] m, int x, int y) {
            return x >= 0 && y >= 0 && x < m.length && y < m[0].length;
        }
    }

}
