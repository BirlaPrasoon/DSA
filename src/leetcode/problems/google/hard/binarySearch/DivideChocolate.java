package leetcode.problems.google.hard.binarySearch;

import java.util.Arrays;

public class DivideChocolate {
    /**
     * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.<br>
     * You want to share the chocolate with your k friends so you start cutting the chocolate bar into k + 1 pieces using k cuts, each piece consists of some consecutive chunks.<br>
     * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.<br>
     * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.<br><br>
     * <p>
     * Example 1:<br>
     * Input: <br>sweetness = [1,2,3,4,5,6,7,8,9], k = 5<br>
     * Output: <br>6<br>
     * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]<br><br>
     * Example 2:<br>
     * Input: <br>sweetness = [5,6,7,8,9,1,2,3,4], k = 8<br>
     * Output: <br>1<br>
     * Explanation: There is only one way to cut the bar into 9 pieces.<br>
     * <br>Example 3:
     * Input: <br>sweetness = [1,2,2,1,2,2,1,2,2], k = 2<br>
     * Output:<br> 5<br>
     * Explanation:<br> You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]<br>
     * <br>
     * Constraints:<br>
     * <br>
     * 0 <= k < sweetness.length <= 10<sup>4</sup><br>
     * 1 <= sweetness[i] <= 10<sup>5</sup><br>
     */

    class Solution {

/*
    Let me explain how this solution works:

    Problem Analysis:

    We need to divide chocolate into K+1 pieces (K for friends, 1 for ourselves)
    We want to maximize the minimum sweetness among all pieces
    Each piece must be continuous
    We need to find the maximum possible value for this minimum sweetness


    Solution Approach:

    Use binary search on the possible sweetness values
    For each potential minimum sweetness:

    Check if we can divide chocolate into K+1 pieces
    Each piece must have at least this sweetness




    Key Components:
    a. Binary Search Range:

    Left: Minimum sweetness value in array
    Right: Sum of all sweetness values

    b. Helper Function (canDivide):

    Greedily divides chocolate into pieces
    Each piece must have at least minSweetness
    Counts number of possible pieces
    Returns true if we can get at least K+1 pieces


    Time Complexity Analysis:

    Binary Search Range: O(log(sum of sweetness))
    For each binary search step: O(n) to check if we can divide
    Total: O(n * log(sum of sweetness))


    Space Complexity:

    O(1) as we only use a constant amount of extra space
        */

        public int maximizeSweetness(int[] sweetness, int k) {
            // k+1 because we also get one piece
            k = k + 1;

            // Find range for binary search
            int left = Integer.MAX_VALUE;  // minimum sweetness
            int right = 0;                 // sum of all sweetness
            for (int sweet : sweetness) {
                left = Math.min(left, sweet);
                right += sweet;
            }

            // Initialize result
            int result = left;

            // Binary search for maximum minimum sweetness
            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (canDivide(sweetness, k, mid)) {
                    // If we can divide, try for higher sweetness
                    result = mid;
                    left = mid + 1;
                } else {
                    // If we can't divide, try lower sweetness
                    right = mid - 1;
                }
            }

            return result;
        }

        // Helper function to check if we can divide chocolate into k pieces
        // where each piece has at least minSweetness
        private boolean canDivide(int[] sweetness, int k, int minSweetness) {
            int pieces = 0;
            int currentSweetness = 0;

            for (int sweet : sweetness) {
                currentSweetness += sweet;

                if (currentSweetness >= minSweetness) {
                    pieces++;
                    currentSweetness = 0;
                }
            }

            return pieces >= k;
        }
    }
}
