package leetcode.problems.google.medium.breadth_first_search;

public class ZeroOneMatrix {

//    Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
//
//    The distance between two adjacent cells is 1.


    class Solution {
        public int[][] updateMatrix(int[][] mat) {
            final int m = mat.length;
            final int n = mat[0].length;
            final int inf = 1000_000;

            int[][] dist = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 1) {
                        int up = (i > 0) ? dist[i - 1][j] : inf;
                        int left = (j > 0) ? dist[i][j - 1] : inf;
                        dist[i][j] = Math.min(up, left) + 1;
                    }
                }
            }

            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (mat[i][j] == 1) {
                        int down = (i < m - 1) ? dist[i + 1][j] : inf;
                        int right = (j < n - 1) ? dist[i][j + 1] : inf;
                        dist[i][j] = Math.min(dist[i][j], Math.min(down, right) + 1);
                    }
                }
            }

            return dist;
        }
    }
}
