package leetcode.problems.google.hard.greedy;

import java.util.Arrays;

public class MinimumNumberOfTapsToOpen {


    class SolutionGreedy {
        public int minTaps(int n, int[] ranges) {
            // Create an array to track the maximum reach for each position
            int[] maxReach = new int[n + 1];

            // Calculate the maximum reach for each tap
            for (int i = 0; i < ranges.length; i++) {
                // Calculate the leftmost position the tap can reach
                int start = Math.max(0, i - ranges[i]);
                // Calculate the rightmost position the tap can reach
                int end = Math.min(n, i + ranges[i]);

                // Update the maximum reach for the leftmost position
                maxReach[start] = Math.max(maxReach[start], end);
            }

            // Number of taps used
            int taps = 0;
            // Current rightmost position reached
            int currEnd = 0;
            // Next rightmost position that can be reached
            int nextEnd = 0;

            // Iterate through the garden
            for (int i = 0; i <= n; i++) {
                // Current position cannot be reached
                if (i > nextEnd) {
                    return -1;
                }

                // Increment taps when moving to a new tap
                if (i > currEnd) {
                    taps++;
                    // Move to the rightmost position that can be reached
                    currEnd = nextEnd;
                }

                // Update the next rightmost position that can be reached
                nextEnd = Math.max(nextEnd, maxReach[i]);
            }

            // Return the minimum number of taps used
            return taps;
        }
    }


    class SolutionDP {
        public int minTaps(int n, int[] ranges) {
            // Define an infinite value
            final int INF = (int) 1e9;

            // Create an array to store the minimum number of taps needed for each position
            int[] dp = new int[n + 1];
            Arrays.fill(dp, INF);

            // Initialize the starting position of the garden
            dp[0] = 0;

            for (int i = 0; i <= n; i++) {
                // Calculate the leftmost position reachable by the current tap
                int tapStart = Math.max(0, i - ranges[i]);
                // Calculate the rightmost position reachable by the current tap
                int tapEnd = Math.min(n, i + ranges[i]);

                for (int j = tapStart; j <= tapEnd; j++) {
                    // Update with the minimum number of taps
                    dp[tapEnd] = Math.min(dp[tapEnd], dp[j] + 1);
                }
            }

            // Check if the garden can be watered completely
            if (dp[n] == INF) {
                // Garden cannot be watered
                return -1;
            }

            // Return the minimum number of taps needed to water the entire garden
            return dp[n];
        }
    }
}
