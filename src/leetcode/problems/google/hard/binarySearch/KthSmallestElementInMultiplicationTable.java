package leetcode.problems.google.hard.binarySearch;

public class KthSmallestElementInMultiplicationTable {
    /**
     * Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).
     * <p>
     * Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.
     * <p>
     * Example 1:
     * <p>
     * Input: m = 3, n = 3, k = 5
     * Output: 3
     * Explanation: The 5th smallest number is 3.
     * Example 2:
     * <p>
     * Input: m = 2, n = 3, k = 6
     * Output: 6
     * Explanation: The 6th smallest number is 6.
     * <p>
     * Constraints:
     * 1 <= m, n <= 3 * 104
     * 1 <= k <= m * n
     */

    class Solution {
        public int countOfElementsSmallerThanOrEqualToX(int x, int m, int n) {
            int count = 0;
            for (int i = 1; i <= m; i++) {
                count += Math.min(x / i, n); // Look in notes, this will be explained, but easily it can be explained using a 5*5 matrix.
            }
            return count;
        }

        public int findKthNumber(int m, int n, int k) {
            int lo = 1, hi = m * n;
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (!(countOfElementsSmallerThanOrEqualToX(mi, m, n) >= k)) lo = mi + 1;
                else hi = mi;
            }
            return lo;
        }
    }

}
