package leetcode.problems.google.hard.backtracking;

public class ConfusingNumber2 {

    /**
     * We can rotate digits of a number by 180 degrees to form new digits.
     * <p>
     * When 0, 1, 6, 8, and 9 are rotated 180 degrees, they become 0, 1, 9, 8, and 6 respectively.
     * When 2, 3, 4, 5, and 7 are rotated 180 degrees, they become invalid.
     * Note that after rotating a number, we can ignore leading zeros.
     * <p>
     * For example, after rotating 8000, we have 0008 which is considered as just 8.
     * Given an integer n, return the number of confusing numbers in the inclusive range [1, n].
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: n = 20
     * Output: 6
     * Explanation: The confusing numbers are [6,9,10,16,18,19].
     * 6 converts to 9.
     * 9 converts to 6.
     * 10 converts to 01 which is just 1.
     * 16 converts to 91.
     * 18 converts to 81.
     * 19 converts to 61.
     * Example 2:
     * <p>
     * Input: n = 100
     * Output: 19
     * Explanation: The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= n <= 10<sup>9</sup>
     */

    //2.DFS
    //Runtime: 108ms 89%; Memory: 38.8MB 99%
    //Time:O(5^(logN)); Space: O(logN)
    //Time:O(N^log5); Space: O(logN)
    public int confusingNumberII_2(int n) {
        help_dfs(0, 0, 1, n);
        return count;
    }

    private int count = 0;
    private static final int[] DIGITS = {0, 1, 6, 8, 9};
    private static final int[] ROTATED_DIGITS = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

    private void help_dfs(long n, long rotated, int base, int limit) {
        if (n > limit) return;
        if (n != rotated) count++;
        for (int d : DIGITS) {
            if (n == 0 && d == 0) continue;
            help_dfs(n * 10 + d, (long) base * ROTATED_DIGITS[d] + rotated, base * 10, limit);
        }
    }


}
