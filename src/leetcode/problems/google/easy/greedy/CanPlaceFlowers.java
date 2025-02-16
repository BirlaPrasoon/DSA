package leetcode.problems.google.easy.greedy;

public class CanPlaceFlowers {

    /**
     * You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
     * <p>
     * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
     * <p>
     * Example 1:
     * <p>
     * Input: flowerbed = [1,0,0,0,1], n = 1
     * Output: true
     * Example 2:
     * <p>
     * Input: flowerbed = [1,0,0,0,1], n = 2
     * Output: false
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= flowerbed.length <= 2 * 10<sup>4</sup>
     * flowerbed[i] is 0 or 1.</br>
     * There are no two adjacent flowers in flowerbed.</br>
     * 0 <= n <= flowerbed.length</br>
     */

    class Solution {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            int count = 0;
            if (n == 0)
                return true;
            if (flowerbed.length == 1) {
                if (n > 1) return false;
                return flowerbed[0] == 0;
            }
            if (flowerbed.length == 2) {
                if (n == 1) {
                    return flowerbed[0] == 0 && flowerbed[1] == 0;
                }
                return false;
            }
            if (flowerbed[0] == 0 && flowerbed[1] == 0) {
                flowerbed[0] = 1;
                count++;
            }
            for (int i = 1; i < flowerbed.length - 1; i++) {
                if ((flowerbed[i] != 1 && flowerbed[i - 1] != 1 && flowerbed[i + 1] != 1)) {
                    count++;
                    flowerbed[i] = 1;
                    i++;
                }
            }
            if (flowerbed[flowerbed.length - 1] == 0 && flowerbed[flowerbed.length - 2] == 0) count++;
            return count >= n;
        }
    }

}
