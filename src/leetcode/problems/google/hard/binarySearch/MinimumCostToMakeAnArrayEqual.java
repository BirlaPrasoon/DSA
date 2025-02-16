package leetcode.problems.google.hard.binarySearch;

import java.util.ArrayList;
import java.util.List;

public class MinimumCostToMakeAnArrayEqual {
    /**
     * You are given two 0-indexed arrays nums and cost consisting each of n positive integers.
     * <p>
     * You can do the following operation any number of times:
     * <p>
     * Increase or decrease any element of the array nums by 1.
     * The cost of doing one operation on the ith element is cost[i].
     * <p>
     * Return the minimum total cost such that all the elements of the array nums become equal.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,3,5,2], cost = [2,3,1,14]
     * <p>
     * Output: 8
     * <p>
     * Explanation: We can make all the elements equal to 2 in the following way:
     * <p>
     * - Increase the 0th element one time. The cost is 2.
     * <p>
     * - Decrease the 1st element one time. The cost is 3.
     * <p>
     * - Decrease the 2nd element three times. The cost is 1 + 1 + 1 = 3.
     * <p>
     * The total cost is 2 + 3 + 3 = 8.
     * <p>
     * It can be shown that we cannot make the array equal with a smaller cost.
     * <p>
     * Example 2:
     * <p>
     * Input: nums = [2,2,2,2,2], cost = [4,2,8,1,3]
     * <p>
     * Output: 0
     * <p>
     * Explanation: All the elements are already equal, so no operations are needed.
     * <p>
     * Constraints:
     * <p>
     * n == nums.length == cost.length
     * 1 <= n <= 105
     * 1 <= nums[i], cost[i] <= 106
     * Test cases are generated in a way that the output doesn't exceed 253-1
     */

    //This can be easily solved using weighted median.
    class WeightedNum implements Comparable<WeightedNum> {
        public int num;
        public int weight;

        public WeightedNum(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(WeightedNum other) {
            return num - other.num;
        }
    }

    class Solution {
        public long minCost(int[] nums, int[] cost) {
            List<WeightedNum> weightedNums = new ArrayList<>();
            for (int i = 0; i < nums.length; i++)
                weightedNums.add(new WeightedNum(nums[i], cost[i]));

            long totalWeight = 0;
            for (int weight : cost) totalWeight += weight;

            long currentRunningWeight = 0L;
            int numberToMinimizeTo = 0;
            for (WeightedNum weightedNum : weightedNums) {
                currentRunningWeight += weightedNum.weight;

                // If weight tips above 50% percentile we found the target num
                if ((double) currentRunningWeight / (double) totalWeight >= 0.5) {
                    numberToMinimizeTo = weightedNum.num;
                    break;
                }
            }

            final int number = numberToMinimizeTo;
            return weightedNums
                    .stream()
                    .filter(x -> x.num != number)
                    .reduce(0L, (acc, weightedNum) ->
                            acc + (long) Math.abs(weightedNum.num - number) * weightedNum.weight, Long::sum);

        }
    }

}
