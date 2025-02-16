package leetcode.problems.google.medium.dynamicprogramming;

public class CountSubMatricesWithAllOnes {

    /*Given an m x n binary matrix mat, return the number of submatrices that have all ones.*/


/*
    Input: mat = [[1,0,1],[1,1,0],[1,1,0]]
    Output: 13
    Explanation:
    There are 6 rectangles of side 1x1.
    There are 2 rectangles of side 1x2.
    There are 3 rectangles of side 2x1.
    There is 1 rectangle of side 2x2.
    There is 1 rectangle of side 3x1.
    Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.

    Input: mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
    Output: 24
    Explanation:
    There are 8 rectangles of side 1x1.
    There are 5 rectangles of side 1x2.
    There are 2 rectangles of side 1x3.
    There are 4 rectangles of side 2x1.
    There are 2 rectangles of side 2x2.
    There are 2 rectangles of side 3x1.
    There is 1 rectangle of side 3x2.
    Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
*/


    class Solution {

        public int numSubmat (int[][] mat) {

            int submatrices = 0;
            int rowLength = mat.length;
            int colLength = mat[0].length;

            for (int row = 1; row < rowLength; row++) {
                for (int col = 0; col < colLength; col++) {
                    mat[row][col] = mat[row][col] == 1 ? mat[row - 1][col] + 1 : 0;
                }
            }

            for (int row = 0; row < rowLength; row++) {
                for (int col = 0; col < colLength; col++) {
                    if (mat[row][col] != 0) {
                        int min = mat[row][col];
                        submatrices += min;
                        for (int k = col + 1; k < colLength && mat[row][k] != 0; k++) {
                            min = Math.min (min, mat[row][k]);
                            submatrices += min;
                        }
                    }
                }
            }

            return submatrices;
        }
    }

}
