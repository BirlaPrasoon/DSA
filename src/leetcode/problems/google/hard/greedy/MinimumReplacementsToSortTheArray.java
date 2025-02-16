package leetcode.problems.google.hard.greedy;

public class MinimumReplacementsToSortTheArray {

    /**
     * Problem Statement:
     * You are given a 0-indexed integer array nums. In one operation, you can:
     * - Choose an index i (0 <= i < nums.length) such that nums[i] > 0.
     * - Subtract 1 from nums[i].
     * <br>
     * <br>
     * The cost of the array is defined as the sum of the squares of all elements in the array.
     * <br>
     * <br>
     * Return the minimum number of operations required to make the cost of the array equal to target.
     * If it is impossible to achieve the target cost, return -1.
     * <br>
     * <br>
     * Example 1:
     * Input: nums = [1,2,3], target = 6
     * Output: 3
     * Explanation:
     * - Subtract 1 from nums[2] to get [1,2,2]. Cost = 1^2 + 2^2 + 2^2 = 1 + 4 + 4 = 9.
     * - Subtract 1 from nums[2] to get [1,2,1]. Cost = 1^2 + 2^2 + 1^2 = 1 + 4 + 1 = 6.
     * - Total operations = 2.
     *
     * <br><br>
     * Example 2:
     * Input: nums = [1,1,1,1,1], target = 3
     * Output: -1
     * Explanation:
     * It is impossible to reduce the cost to 3 since the minimum possible cost is 5.
     * <br><br>
     * Constraints:
     * - 1 <= nums.length <= 10^5
     * - 1 <= nums[i] <= 10^9
     * - 1 <= target <= 10^9
     */
    public long minimumReplacement(int[] nums) {
        long res = 0;
        int n = nums.length;
        long maxMinVal = nums[n-1];
        for(int i = n-2; i>=0; i--) {
            long parts = (long)Math.ceil(nums[i]/(double)maxMinVal);
            res += parts-1;
            maxMinVal = nums[i]/parts;
        }
        return res;
    }
}
