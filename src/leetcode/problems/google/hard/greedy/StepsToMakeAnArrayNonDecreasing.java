package leetcode.problems.google.hard.greedy;

public class StepsToMakeAnArrayNonDecreasing {
    /**
     * Problem Statement:
     * You are given a 0-indexed integer array nums. In one operation, you can:
     * - Choose an index i (0 <= i < nums.length) such that nums[i] > 0.
     * - Subtract 1 from nums[i].
     * </br>
     * </br>
     * The score of the array is defined as the sum of all elements in the array.
     * </br>
     * </br>
     * Return the minimum number of operations required to make the score of the array equal to target.
     * If it is impossible to achieve the target score, return -1.
     * </br>
     * </br>
     * Example 1:
     * Input: nums = [1,2,3], target = 4
     * Output: 3
     * Explanation:
     * - Subtract 1 from nums[2] to get [1,2,2]. Score = 1 + 2 + 2 = 5.
     * - Subtract 1 from nums[2] to get [1,2,1]. Score = 1 + 2 + 1 = 4.
     * - Total operations = 2.
     * </br>
     * </br>
     * Example 2:
     * Input: nums = [1,1,1,1,1], target = 3
     * Output: -1
     * Explanation:
     * It is impossible to reduce the score to 3 since the minimum possible score is 5.
     * </br>
     * </br>
     * Constraints:
     * - 1 <= nums.length <= 10^5
     * - 1 <= nums[i] <= 10^9
     * - 1 <= target <= 10^9
     */
    public int totalSteps(int[] A) {
        int n = A.length, res = 0, j = -1;
        int[] dp = new int[n];
        int[] stack = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            while (j >= 0 && A[i] > A[stack[j]]) {
                dp[i] = Math.max(++dp[i], dp[stack[j--]]);
                res = Math.max(res, dp[i]);
            }
            stack[++j] = i;
        }
        return res;
    }
}
