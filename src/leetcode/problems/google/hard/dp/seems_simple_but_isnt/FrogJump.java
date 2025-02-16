package leetcode.problems.google.hard.dp.seems_simple_but_isnt;

import java.util.Arrays;
import java.util.HashMap;

public class FrogJump {
/**

    A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.</br></br>

    Given a list of stones positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.</br></br>

    If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.</br></br>



    Example 1:</br>

    Input: stones = [0,1,3,5,6,8,12,17]</br>
    Output: true</br>
    Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.</br></br>
    Example 2:</br>

    Input: stones = [0,1,2,3,4,8,9,11]</br>
    Output: false</br>
    Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.</br>
 </br>

            Constraints:</br></br>

            2 <= stones.length <= 2000</br>
            0 <= stones[i] <= 2<sup>31</sup> - 1</br>
    stones[0] == 0</br>
    stones is sorted in a strictly increasing order.</br>

*/
    class SolutionStandardDP {
        HashMap<Integer, Integer> mark = new HashMap<>();
        int[][] dp = new int[2001][2001];

        boolean solve(int[] stones, int n, int index, int prevJump) {
            // If reached the last stone, return 1.
            if (index == n - 1) {
                return true;
            }

            // If this subproblem has already been calculated, return it.
            if (dp[index][prevJump] != -1) {
                return dp[index][prevJump] == 1;
            }

            boolean ans = false;
            // Iterate over the three possibilities {k - 1, k, k + 1}.
            for (int nextJump = prevJump - 1; nextJump <= prevJump + 1; nextJump++) {
                if (nextJump > 0 && mark.containsKey(stones[index] + nextJump)) {
                    ans = ans || solve(stones, n, mark.get(stones[index] + nextJump), nextJump);
                }
            }

            // Store the result to fetch later.
            dp[index][prevJump] = (ans ? 1 :0);
            return ans;
        }

        public boolean canCross(int[] stones) {
            // Mark stones in the map to identify if such stone exists or not.
            for (int i = 0 ; i < stones.length; i++) {
                mark.put(stones[i], i);
            }

            //Mark all states as -1 to denote they're not calculated.
            for (int i = 0; i < 2000; i++) {
                Arrays.fill(dp[i], -1);
            }

            return solve(stones, stones.length, 0, 0);
        }
    }

    class SolutionOptimizedDP {
        public boolean canCross(int[] stones) {
            int n = stones.length;

            boolean[][] dp = new boolean[n][n];
            dp[0][0] = true;

            for (int i = 0; i < n; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    int distance = stones[i] - stones[j];
                    if (distance > j + 1) {
                        break;
                    }

                    if (dp[j][distance - 1] || dp[j][distance] || dp[j][distance + 1]) {
                        dp[i][distance] = true;
                        if (i == n - 1) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }

}
