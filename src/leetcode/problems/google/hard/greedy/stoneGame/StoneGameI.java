package leetcode.problems.google.hard.greedy.stoneGame;

public class StoneGameI {


    /*
    *
    * Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

    The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.

    Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.

    Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.



    Example 1:

    Input: piles = [5,3,4,5]
    Output: true
    Explanation:
    Alice starts first, and can only take the first 5 or the last 5.
    Say she takes the first 5, so that the row becomes [3, 4, 5].
    If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
    If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
    This demonstrated that taking the first 5 was a winning move for Alice, so we return true.
    Example 2:

    Input: piles = [3,7,2,3]
    Output: true


    Constraints:

    2 <= piles.length <= 500
    piles.length is even.
    1 <= piles[i] <= 500
    sum(piles[i]) is odd.

    *
    * */

    public class Solution {
        public boolean stoneGame(int[] piles) {
            // Shortcut, return true. Alice will always be able to pick up all the odd elements or all even elements
            // if she wants to as she has the first mover advantage.

            int n = piles.length;
            int[][] dp = new int[n][n];

            // Base case: when there's only one pile
            for (int i = 0; i < n; i++) {
                dp[i][i] = piles[i];
            }

            /*
            * Base Case: Each dp[i][i] is initialized to piles[i], representing the maximum stones a player can get when there's only
            * one pile at index i.

* DP Table Filling: For each subarray length from 2 to n, the algorithm calculates dp[i][j],
representing the maximum stones the current player can achieve from the subarray piles[i] to piles[j].

Choice 1: Pick piles[i] and let the opponent play on the subarray piles[i+1] to piles[j].
Choice 2: Pick piles[j] and let the opponent play on the subarray piles[i] to piles[j-1].

* The goal is to maximize Alex’s advantage, so we choose the option with the higher value.
Final Decision: The result in dp[0][n-1] will be positive if Alex can win by picking the optimal moves.
            *
            * */

            // Fill the DP table
            for (int length = 2; length <= n; length++) {  // length of the subarray
                for (int i = 0; i <= n - length; i++) {
                    int j = i + length - 1;
                    dp[i][j] = Math.max(
                            piles[i] - dp[i + 1][j],
                            piles[j] - dp[i][j - 1]
                    );
                }
            }

            // If Alice has a positive score difference, they win
            return dp[0][n - 1] > 0;
        }
    }

    public class SolutionMemoization {
        public boolean stoneGame(int[] piles) {
            int n = piles.length;
            Integer[][] memo = new Integer[n][n]; // memoization array
            return maxDifference(piles, 0, n - 1, memo) > 0;
        }

        private int maxDifference(int[] piles, int i, int j, Integer[][] memo) {
            // Base case: only one pile left
            if (i == j) {
                return piles[i];
            }

            // Check if result is already computed
            if (memo[i][j] != null) {
                return memo[i][j];
            }

            // Recurrence relation
            int takeLeft = piles[i] - maxDifference(piles, i + 1, j, memo);
            int takeRight = piles[j] - maxDifference(piles, i, j - 1, memo);

            // Store the result in memo and return it
            memo[i][j] = Math.max(takeLeft, takeRight);
            return memo[i][j];
        }
    }

}
