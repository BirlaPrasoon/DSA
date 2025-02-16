package leetcode.problems.google.hard.arrays;

public class KthSmallestInLexicographicalOrder {

    static class Solution {

        /**
         * <h1>Problem Statement</h1>
         * Finds the kth smallest number in lexicographical order up to n.
         *
         * @param n The upper limit of numbers to consider
         * @param k The position of the number to find
         * @return The kth smallest number in lexicographical order
         * <p>
         * <br>
         * Example:</br>
         * findKthNumber(15, 5) returns 13</br>
         * <b>Explanation:</b></br>
         * The lexicographical order up to 15 is:</br>
         * 1, 10, 11, 12, 13, 14, 15, 2, 3, 4, 5, 6, 7, 8, 9</br>
         * The 5th number in this order is 13.</br>
         */
        public int findKthNumber(int n, int k) {
            // Start with the smallest lexicographical number
            int curr = 1;

            // Decrement k because we're starting at 1
            k--;

            // Continue until we find the kth number
            while (k > 0) {
                // Count the steps between curr and curr+1 up to n
                int steps = countSteps(n, curr, curr + 1);

                // If steps <= k, it means kth element is not under current prefix
                if (steps <= k) {
                    // Move to next prefix
                    curr++;
                    // Decrease k by the number of elements we're skipping
                    k -= steps;
                } else {
                    // If steps > k, kth element is under current prefix
                    // Go one level deeper in the prefix tree
                    curr *= 10;
                    // Decrease k as we're going one step deeper
                    k--; // we've included the current number so we reduce the search space by 1 (3-> 30 means 3 is included, now search starts from 30)
                }
            }

            // When k becomes 0, we've found our answer
            return curr;
        }

        /**
         * Counts the number of nodes in the prefix tree between n1 and n2, up to n.
         *
         * @param n The upper limit of numbers to consider
         * @param n1 The starting prefix
         * @param n2 The ending prefix (exclusive)
         * @return The count of numbers between n1 and n2 up to n
         * <p><br>
         * Example:
         * countSteps(15, 1, 2) returns 5
         * Explanation:
         * This counts numbers starting with 1 up to 15:
         * 1, 10, 11, 12, 13, 14, 15 (7 numbers, but we don't count 1 itself, so 5)
         * <p>
         *     <br>
         * Example:
         * countSteps(15, 10, 11) returns 1
         * Explanation:
         * This counts numbers starting with 10 up to 15:
         * 10 (only 1 number)
         */
        private int countSteps(int n, long n1, long n2) {
            int steps = 0;

            // Continue until n1 exceeds n
            while (n1 <= n) {
                // Add the count of numbers between n1 and n2, but not exceeding n
                steps += (int) (Math.min(n + 1, n2) - n1);
                // Move to the next level of the prefix tree
                n1 *= 10;
                n2 *= 10;
            }

            return steps;
        }
    }
}
