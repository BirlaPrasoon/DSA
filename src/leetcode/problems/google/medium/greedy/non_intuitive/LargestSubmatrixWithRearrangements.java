package leetcode.problems.google.medium.greedy.non_intuitive;

import java.util.Arrays;

public class LargestSubmatrixWithRearrangements {
/**

    You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.

    Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.
*/
    class Solution {
        /**
        * Time complexity: O(m⋅n⋅logn)

We iterate over m rows. For each row, we update the values which costs O(n). Then, we sort the row, which costs O(n⋅logn). Finally, we iterate over the row to calculate submatrix areas, which costs O(n).

Overall, each of the m iterations costs O(n⋅logn).

Space complexity: O(m⋅n)

Although we are only allocating currRow which has a size of O(n), we are modifying matrix. It is generally considered a bad practice to modify the input and when you do, you should count it as part of the space complexity.
        * */
        public int largestSubmatrix(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int ans = 0;


            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    if (matrix[row][col] != 0 && row > 0) {
                        matrix[row][col] += matrix[row - 1][col];
                    }
                }

                int[] currRow = matrix[row].clone();
                Arrays.sort(currRow);
                for (int i = 0; i < n; i++) {
                    ans = Math.max(ans, currRow[i] * (n - i));
                }
            }

            return ans;
        }
    }

}
