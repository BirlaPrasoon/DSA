package leetcode.problems.google.hard.dp;

import java.util.Arrays;
import java.util.Stack;

public class MaximalRectangle {

    /*Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

    Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
    Output: 6
    Explanation: The maximal rectangle is shown in the above picture.
            Example 2:

    Input: matrix = [["0"]]
    Output: 0
    Example 3:

    Input: matrix = [["1"]]
    Output: 1

    Constraints:

rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.

     */

    // Use maximum area in a rectangle approach using previous smaller, next smaller via indexes using stack.
    class SolutionPreviousSmallerNextSmaller {
        // Get the maximum area in a histogram given its heights
        public int leetcode84(int[] heights) {
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            int maxarea = 0;
            for (int i = 0; i < heights.length; ++i) {
                while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                    maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
                stack.push(i);
            }
            while (stack.peek() != -1)
                maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
            return maxarea;
        }

        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) return 0;
            int maxarea = 0;
            int[] dp = new int[matrix[0].length];

            for (char[] row : matrix) {
                for (int j = 0; j < matrix[0].length; j++) {
                    // update the state of this row's histogram using the last row's histogram
                    // by keeping track of the number of consecutive ones

                    dp[j] = row[j] == '1' ? dp[j] + 1 : 0;
                }
                // update maxarea with the maximum area from this row's histogram
                maxarea = Math.max(maxarea, leetcode84(dp));
            }
            return maxarea;
        }
    }

    class Solution {
        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int m = matrix.length;
            int n = matrix[0].length;

            int[] heights = new int[n];
            int[] leftBoundaries = new int[n];
            int[] rightBoundaries = new int[n];
            Arrays.fill(rightBoundaries, n);

            int maxRectangle = 0;

            for (int i = 0; i < m; i++) {
                int left = 0;
                int right = n;

                updateHeightsAndLeftBoundaries(matrix[i], heights, leftBoundaries, left);

                updateRightBoundaries(matrix[i], rightBoundaries, right);

                maxRectangle = calculateMaxRectangle(heights, leftBoundaries, rightBoundaries, maxRectangle);
            }

            return maxRectangle;
        }

        private void updateHeightsAndLeftBoundaries(char[] row, int[] heights, int[] leftBoundaries, int left) {
            for (int j = 0; j < heights.length; j++) {
                if (row[j] == '1') {
                    heights[j]++;
                    leftBoundaries[j] = Math.max(leftBoundaries[j], left);
                } else {
                    heights[j] = 0;
                    leftBoundaries[j] = 0;
                    left = j + 1;
                }
            }
        }

        private void updateRightBoundaries(char[] row, int[] rightBoundaries, int right) {
            for (int j = rightBoundaries.length - 1; j >= 0; j--) {
                if (row[j] == '1') {
                    rightBoundaries[j] = Math.min(rightBoundaries[j], right);
                } else {
                    rightBoundaries[j] = right;
                    right = j;
                }
            }
        }

        private int calculateMaxRectangle(int[] heights, int[] leftBoundaries, int[] rightBoundaries, int maxRectangle) {
            for (int j = 0; j < heights.length; j++) {
                int width = rightBoundaries[j] - leftBoundaries[j];
                int area = heights[j] * width;
                maxRectangle = Math.max(maxRectangle, area);
            }
            return maxRectangle;
        }
    }

}
