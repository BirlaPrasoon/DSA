package leetcode.problems.google.medium.greedy;

import java.util.Arrays;

public class MinimumSidewaysJumps {

    /**
    *
    * There is a 3 lane road of length n that consists of n + 1 points labeled from 0 to n. A frog starts at point 0 in the second lane and wants to jump to point n. However, there could be obstacles along the way.

    You are given an array obstacles of length n + 1 where each obstacles[i] (ranging from 0 to 3) describes an obstacle on the lane obstacles[i] at point i. If obstacles[i] == 0, there are no obstacles at point i. There will be at most one obstacle in the 3 lanes at each point.

    For example, if obstacles[2] == 1, then there is an obstacle on lane 1 at point 2.
    The frog can only travel from point i to point i + 1 on the same lane if there is not an obstacle on the lane at point i + 1. To avoid obstacles, the frog can also perform a side jump to jump to another lane (even if they are not adjacent) at the same point if there is no obstacle on the new lane.

    For example, the frog can jump from lane 3 at point 3 to lane 1 at point 3.
    Return the minimum number of side jumps the frog needs to reach any lane at point n starting from lane 2 at point 0.

    Note: There will be no obstacles on points 0 and n.



    Example 1:


    Input: obstacles = [0,1,2,3,0]
    Output: 2
    Explanation: The optimal solution is shown by the arrows above. There are 2 side jumps (red arrows).
    Note that the frog can jump over obstacles only when making side jumps (as shown at point 2).
    Example 2:


    Input: obstacles = [0,1,1,3,3,0]
    Output: 0
    Explanation: There are no obstacles on lane 2. No side jumps are required.
    Example 3:


    Input: obstacles = [0,2,1,0,3,0]
    Output: 2
    Explanation: The optimal solution is shown by the arrows above. There are 2 side jumps.


    Constraints:

    obstacles.length == n + 1
    1 <= n <= 5 * 10^5
    0 <= obstacles[i] <= 3
    obstacles[0] == obstacles[n] == 0
    * */

    class SolutionBottomUp {
        public int minSideJumps(int[] obstacles) {
            int n = obstacles.length;

            // dp[lane] represents minimum jumps needed to reach current position in that lane
            int[] dp = new int[]{1, 0, 1};  // Initial state: frog starts in lane 2

            // Process each position
            for (int position = 1; position < n; position++) {
                // Get current obstacle
                int currentObstacle = obstacles[position];

                // Initialize next state
                int[] nextDp = new int[3];
                Arrays.fill(nextDp, Integer.MAX_VALUE - 1);

                // Process each lane
                for (int currentLane = 0; currentLane < 3; currentLane++) {
                    // Skip if there's obstacle in current lane
                    if (currentObstacle == currentLane + 1) {
                        continue;
                    }

                    // Try staying in same lane
                    nextDp[currentLane] = dp[currentLane];

                    // Try jumping from other lanes
                    for (int otherLane = 0; otherLane < 3; otherLane++) {
                        if (otherLane != currentLane && currentObstacle != otherLane + 1) {
                            nextDp[currentLane] = Math.min(nextDp[currentLane], dp[otherLane] + 1);
                        }
                    }
                }

                dp = nextDp;
            }

            // Return minimum jumps among all lanes at end position
            return Math.min(dp[0], Math.min(dp[1], dp[2]));
        }
    }


}
