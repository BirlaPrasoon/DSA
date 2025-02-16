package leetcode.problems.google.hard.binarySearch;

public class RangeSumQuery2D {

    /**
     * <h1>Problem Statement</h1>
     * Given a 2D matrix matrix, handle multiple queries of the following types:
     * <p>
     * Update the value of a cell in matrix.
     * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
     * <h2>ASK</h2>
     * <h4>Implement the NumMatrix class:</h4>
     * <p>
     *
     * <code>NumMatrix(int[][] matrix)</code> Initializes the object with the integer matrix matrix.</br>
     * <code>void update(int row, int col, int val)</code> Updates the value of matrix[row][col] to be val.</br>
     * <code>int sumRegion(int row1, int col1, int row2, int col2)</code> Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).</br>
     * </code>
     * <p>
     * <b>Input</b></br>
     * ["NumMatrix", "sumRegion", "update", "sumRegion"]</br>
     * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]</br>
     * <b>Output</b></br>
     * [null, 8, null, 10]
     * <p>
     * <b>Explanation</b></br>
     * <code>
     * NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);</br>
     * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e. sum of the left red rectangle)</br>
     * numMatrix.update(3, 2, 2);       // matrix changes from left image to right image</br>
     * numMatrix.sumRegion(2, 1, 4, 3); // return 10 (i.e. sum of the right red rectangle)</br>
     * </code>
     */

    public class NumMatrix {
        int m, n;
        int[][] arr;    // stores matrix[][]
        int[][] BITree; // 2-D binary indexed tree

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return;
            }

            m = matrix.length;
            n = matrix[0].length;

            arr = new int[m][n];
            BITree = new int[m + 1][n + 1];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    update(i, j, matrix[i][j]); // init BITree[][]
                    arr[i][j] = matrix[i][j];   // init arr[][]
                }
            }
        }

        public void update(int i, int j, int val) {
            int diff = val - arr[i][j];  // get the diff
            arr[i][j] = val;             // update arr[][]

            i++;
            j++;
            while (i <= m) {
                int k = j;
                while (k <= n) {
                    BITree[i][k] += diff; // update BITree[][]
                    k += k & (-k); // update column index to that of parent
                }
                i += i & (-i);   // update row index to that of parent
            }
        }

        int getSum(int i, int j) {
            int sum = 0;

            i++;
            j++;
            while (i > 0) {
                int k = j;
                while (k > 0) {
                    sum += BITree[i][k]; // accumulate the sum
                    k -= k & (-k); // move column index to parent node
                }
                i -= i & (-i);   // move row index to parent node
            }
            return sum;
        }

        public int sumRegion(int i1, int j1, int i2, int j2) {
            return getSum(i2, j2) - getSum(i1 - 1, j2) - getSum(i2, j1 - 1) + getSum(i1 - 1, j1 - 1);
        }
    }

}
