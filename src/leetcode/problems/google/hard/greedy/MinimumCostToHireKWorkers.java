package leetcode.problems.google.hard.greedy;

import leetcode.Pair;

import java.util.*;

public class MinimumCostToHireKWorkers {

    /**
     * Problem Statement:<br><br>
     * There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the i-th worker and wage[i] is the minimum wage expectation for the i-th worker.<br><br>
     *
     * We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:<br>
     * 1. Every worker in the paid group must be paid at least their minimum wage expectation.<br>
     * 2. Within the group, each worker should be paid in the ratio of their quality compared to other workers in the group.<br><br>
     *
     * A worker's payout is the amount paid to them, which is equal to their quality multiplied by the wage ratio of the group.<br><br>
     *
     * Return the least amount of money needed to form a paid group satisfying the above conditions.<br><br>
     *
     * Example 1:<br>
     * Input: quality = [10,20,5], wage = [70,50,30], k = 2<br>
     * Output: 105.00000<br>
     * Explanation:<br>
     * - Hire worker 0 and worker 2. The total quality is 10 + 5 = 15.<br>
     * - The wage ratio is 70 / 10 = 7.0.<br>
     * - Worker 0 is paid 10 * 7.0 = 70.<br>
     * - Worker 2 is paid 5 * 7.0 = 35.<br>
     * - The total payout is 70 + 35 = 105.<br><br>
     *
     * Example 2:<br>
     * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3<br>
     * Output: 30.66667<br>
     * Explanation:<br>
     * - Hire worker 0, worker 2, and worker 3. The total quality is 3 + 10 + 10 = 23.<br>
     * - The wage ratio is 4 / 3 ≈ 1.33333.<br>
     * - Worker 0 is paid 3 * 1.33333 = 4.<br>
     * - Worker 2 is paid 10 * 1.33333 ≈ 13.33333.<br>
     * - Worker 3 is paid 10 * 1.33333 ≈ 13.33333.<br>
     * - The total payout is 4 + 13.33333 + 13.33333 ≈ 30.66667.<br><br>
     *
     * Constraints:<br>
     * - n == quality.length == wage.length<br>
     * - 1 <= k <= n <= 10^4<br>
     * - 1 <= quality[i], wage[i] <= 10^4<br>
     */
    class Solution {

        public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
            int n = quality.length;
            double totalCost = Double.MAX_VALUE;
            double currentTotalQuality = 0;
            List<Pair<Double, Integer>> wageToQualityRatio = new ArrayList<>();

            // Calculate wage-to-quality ratio for each worker
            for (int i = 0; i < n; i++) {
                wageToQualityRatio.add(
                        new Pair<>((double) wage[i] / quality[i], quality[i])
                );
            }

            // Sort workers based on their wage-to-quality ratio
            Collections.sort(
                    wageToQualityRatio,
                    Comparator.comparingDouble(Pair::getKey)
            );

            // Use a priority queue to keep track of the highest quality workers
            PriorityQueue<Integer> workers = new PriorityQueue<>(
                    Collections.reverseOrder()
            );

            // Iterate through workers
            for (int i = 0; i < n; i++) {
                workers.add(wageToQualityRatio.get(i).getValue());
                currentTotalQuality += wageToQualityRatio.get(i).getValue();

                // If we have more than k workers,
                // remove the one with the highest quality
                if (workers.size() > k) {
                    currentTotalQuality -= workers.poll();
                }

                // If we have exactly k workers,
                // calculate the total cost and update if it's the minimum
                if (workers.size() == k) {
                    totalCost = Math.min(
                            totalCost,
                            currentTotalQuality * wageToQualityRatio.get(i).getKey()
                    );
                }
            }
            return totalCost;
        }
    }
}
