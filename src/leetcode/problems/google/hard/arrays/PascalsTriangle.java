package leetcode.problems.google.hard.arrays;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    class SolutionRecursive {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> result = new ArrayList<>();
            if (numRows == 0) {
                return result;
            }

            if (numRows == 1) {
                List<Integer> firstRow = new ArrayList<>();
                firstRow.add(1);
                result.add(firstRow);
                return result;
            }

            result = generate(numRows - 1);
            List<Integer> prevRow = result.get(numRows - 2);
            List<Integer> currentRow = new ArrayList<>();
            currentRow.add(1);

            for (int i = 1; i < numRows - 1; i++) {
                currentRow.add(prevRow.get(i - 1) + prevRow.get(i));
            }

            currentRow.add(1);
            result.add(currentRow);

            return result;
        }
    }
    // DP
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> triangle = new ArrayList<List<Integer>>();

            // Base case; first row is always [1].
            triangle.add(new ArrayList<>());
            triangle.get(0).add(1);

            for (int rowNum = 1; rowNum < numRows; rowNum++) {
                List<Integer> row = new ArrayList<>();
                List<Integer> prevRow = triangle.get(rowNum - 1);

                // The first row element is always 1.
                row.add(1);

                // Each triangle element (other than the first and last of each row)
                // is equal to the sum of the elements above-and-to-the-left and
                // above-and-to-the-right.
                for (int j = 1; j < rowNum; j++) {
                    row.add(prevRow.get(j - 1) + prevRow.get(j));
                }

                // The last row element is always 1.
                row.add(1);

                triangle.add(row);
            }

            return triangle;
        }
    }

}
