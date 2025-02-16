package leetcode.problems.google.hard.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexicoGraphicallySmallestCoinPath {

/*
    You are given an integer array coins (1-indexed) of length n and an integer maxJump. You can jump to any index i of the array coins if coins[i] != -1 and you have to pay coins[i] when you visit index i. In addition to that, if you are currently at index i, you can only jump to any index i + k where i + k <= n and k is a value in the range [1, maxJump].

    You are initially positioned at index 1 (coins[1] is not -1). You want to find the path that reaches index n with the minimum cost.

    Return an integer array of the indices that you will visit in order so that you can reach index n with the minimum cost. If there are multiple paths with the same cost, return the lexicographically smallest such path. If it is not possible to reach index n, return an empty array.

    A path p1 = [Pa1, Pa2, ..., Pax] of length x is lexicographically smaller than p2 = [Pb1, Pb2, ..., Pbx] of length y, if and only if at the first j where Paj and Pbj differ, Paj < Pbj; when no such j exists, then x < y.

    Example 1:

    Input: coins = [1,2,4,-1,2], maxJump = 2
    Output: [1,3,5]
    Example 2:

    Input: coins = [1,2,4,-1,2], maxJump = 1
    Output: []

    Constraints:
    1 <= coins.length <= 1000
    -1 <= coins[i] <= 100
    coins[1] != -1
    1 <= maxJump <= 100
    */

    class Solution {
        private int[] coins;
        private int maxJump;
        private int n;
        private Long[] dp;
        private int[] next;

        public List<Integer> cheapestJump(int[] coins, int maxJump) {
            this.coins = coins;
            this.maxJump = maxJump;
            this.n = coins.length;
            this.dp = new Long[n];
            this.next = new int[n];
            List<Integer> result = new ArrayList<>();

            // If start or end is blocked, return empty list
            if (n == 0 || coins[0] == -1 || coins[n-1] == -1) {
                return result;
            }

            // Initialize next array
            Arrays.fill(next, -1);

            // If no valid path exists or start position is blocked
            long minCost = dfs(0);
            if (minCost == Long.MAX_VALUE) {
                return result;
            }

            // Construct the path using next array
            int pos = 0;
            while (pos != -1) {
                result.add(pos + 1); // Convert to 1-based indexing
                pos = next[pos];
            }

            return result;
        }

        private long dfs(int pos) {
            // Base case: reached the end
            if (pos == n-1) {
                return coins[pos];
            }

            // If already computed
            if (dp[pos] != null) {
                return dp[pos];
            }

            // If current position is blocked
            if (coins[pos] == -1) {
                return dp[pos] = Long.MAX_VALUE;
            }

            long minCost = Long.MAX_VALUE;
            int bestNext = -1;

            // Try all possible jumps
            for (int j = 1; j <= maxJump && pos + j < n; j++) {
                int nextPos = pos + j;

                // Skip if next position is blocked
                if (coins[nextPos] == -1) {
                    continue;
                }

                // Recursively calculate minimum cost
                long cost = dfs(nextPos);

                // If path exists from next position
                if (cost != Long.MAX_VALUE) {
                    cost += coins[pos];

                    // Update if we found a better path or
                    // same cost but lexicographically smaller path
                    if (cost < minCost || (cost == minCost && nextPos < bestNext)) {
                        minCost = cost;
                        bestNext = nextPos;
                    }
                }
            }

            // Update next array for path reconstruction
            next[pos] = bestNext;
            return dp[pos] = minCost;
        }
    }
}
