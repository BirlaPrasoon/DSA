package leetcode.problems.google.medium.greedy;

public class ScoreAfterFlippingMatrix {


    class Solution {
        public int matrixScore(int[][] grid) {
            int col[] = new int[grid[0].length];
            for(int i= 0; i<grid.length; i++) {
                if(grid[i][0] == 0) {
                    flipRow(grid, i);
                }
                for(int j = 0; j<grid[i].length; j++) {
                    col[j] += grid[i][j];
                }
            }

            int N = (grid.length+1)/2;
            for(int j=0; j<grid[0].length; j++) {
                if(col[j] < N) flipCol(grid, j);
            }
            int sum = 0;
            for(int i = 0; i<grid.length; i++) {
                int rowSum = getRowSum(grid, i);
                sum += rowSum;
            }
            return sum;
        }

        private void flipRow(int[][] grid, int j) {
            for(int i = 0; i<grid[0].length; i++) {
                grid[j][i] ^= 1;
            }
        }

        private void flipCol(int[][] grid, int j) {
            for(int i=0; i<grid.length; i++) {
                grid[i][j] ^=1;
            }
        }

        private int getRowSum(int[][] grid, int i) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<grid[0].length; j++) {
                sb.append(grid[i][j]);
            }
            return Integer.parseInt(sb.toString(), 2);
        }
    }

}
