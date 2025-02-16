package leetcode.problems.google.medium.greedy;

import java.util.Arrays;

public class DestroyingAsteroids {

    // Integer overflow occurs
    // NOTE: We can't store 10^10 in integer
    /**
     * Constraints:</br>
     * 1 <= mass <= 10<sup>5</sup></br>
     * 1 <= asteroids.length <= 10<sup>5</sup></br>
     * 1 <= asteroids[i] <= 10<sup>5</sup></br>
     */
    class Solution {
        public boolean asteroidsDestroyed(int mass, int[] asteroids) {
            Arrays.sort(asteroids);
            long mas = mass;
            for (int a : asteroids) {
                if (mas < (long) a) {
                    return false;
                }
                mas += a;
            }
            return true;
        }
    }

}
