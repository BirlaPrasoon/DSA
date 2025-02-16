package leetcode.problems.google.medium.greedy;

import java.util.Arrays;

public class EliminateTheMaximumNumberOfMonsters {
/*
    You are playing a video game where you are defending your city from a group of n monsters. You are given a 0-indexed integer array dist of size n, where dist[i] is the initial distance in kilometers of the ith monster from the city.

    The monsters walk toward the city at a constant speed. The speed of each monster is given to you in an integer array speed of size n, where speed[i] is the speed of the ith monster in kilometers per minute.

    You have a weapon that, once fully charged, can eliminate a single monster. However, the weapon takes one minute to charge. The weapon is fully charged at the very start.

    You lose when any monster reaches your city. If a monster reaches the city at the exact moment the weapon is fully charged, it counts as a loss, and the game ends before you can use your weapon.

    Return the maximum number of monsters that you can eliminate before you lose, or n if you can eliminate all the monsters before they reach the city.



            Example 1:

    Input: dist = [1,3,4], speed = [1,1,1]
    Output: 3
    Explanation:
    In the beginning, the distances of the monsters are [1,3,4]. You eliminate the first monster.
    After a minute, the distances of the monsters are [X,2,3]. You eliminate the second monster.
    After a minute, the distances of the monsters are [X,X,2]. You eliminate the third monster.
            All 3 monsters can be eliminated.
    Example 2:

    Input: dist = [1,1,2,3], speed = [1,1,1,1]
    Output: 1
    Explanation:
    In the beginning, the distances of the monsters are [1,1,2,3]. You eliminate the first monster.
    After a minute, the distances of the monsters are [X,0,1,2], so you lose.
    You can only eliminate 1 monster.
            Example 3:

    Input: dist = [3,2,4], speed = [5,3,2]
    Output: 1
    Explanation:
    In the beginning, the distances of the monsters are [3,2,4]. You eliminate the first monster.
    After a minute, the distances of the monsters are [X,0,2], so you lose.
    You can only eliminate 1 monster.


            Constraints:

    n == dist.length == speed.length
1 <= n <= 105
            1 <= dist[i], speed[i] <= 105
    */


    static class Solution {
        public int eliminateMaximum(int[] distances, int[] speeds) {
            int monsterCount = distances.length;
            double[] arrivalTimes = new double[monsterCount];

            // Calculate arrival time for each monster
            for (int i = 0; i < monsterCount; i++) {
                arrivalTimes[i] = (double) distances[i] / speeds[i];
            }

            // Sort by arrival time to eliminate closest monsters first
            Arrays.sort(arrivalTimes);

            // For each minute, check if we can eliminate monster before it arrives
            for (int minute = 0; minute < monsterCount; minute++) {
                // If monster arrives before or at current minute, city is destroyed
                if (arrivalTimes[minute] <= minute) {
                    return minute;
                }
            }

            // If we've eliminated all monsters successfully
            return monsterCount;
        }
    }

    // Test class with various scenarios
    class Main {
        public static void main(String[] args) {
            Solution solution = new Solution();

            // Test scenarios
            testScenario(solution,
                    new int[]{1,3,4}, new int[]{1,1,1},
                    "Equal speeds, different distances"
            );

            testScenario(solution,
                    new int[]{1,1,2,3}, new int[]{1,1,1,1},
                    "Some monsters arrive simultaneously"
            );

            testScenario(solution,
                    new int[]{3,2,4}, new int[]{5,3,2},
                    "Different speeds and distances"
            );
        }

        private static void testScenario(Solution solution, int[] distances, int[] speeds, String description) {
            System.out.println("Test case: " + description);
            System.out.println("Distances: " + Arrays.toString(distances));
            System.out.println("Speeds: " + Arrays.toString(speeds));
            System.out.println("Maximum monsters eliminated: " +
                    solution.eliminateMaximum(distances, speeds));
            System.out.println();
        }
    }
}
