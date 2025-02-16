package leetcode.problems.google.medium.greedy.non_intuitive;

public class FindValidMatrixGivenRowSumAndColSum {

    class Solution {
        public int[][] restoreMatrix(int[] rowSum, int[] colSum) {

            int[][] ans = new int[rowSum.length][colSum.length];
            for(int i= 0; i<rowSum.length; i++) {
                for(int j= 0; j<colSum.length; j++) {
                    ans[i][j] = Math.min(colSum[j], rowSum[i]);
                    colSum[j] -= ans[i][j];
                    rowSum[i] -= ans[i][j];
                }
            }
            return ans;
        }
    }

}
