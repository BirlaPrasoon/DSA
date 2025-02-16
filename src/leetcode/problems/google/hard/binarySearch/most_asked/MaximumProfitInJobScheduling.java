package leetcode.problems.google.hard.binarySearch.most_asked;

import java.util.*;

public class MaximumProfitInJobScheduling {

    /**
     * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
     * <p>
     * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
     * <p>
     * If you choose a job that ends at time X you will be able to start another job that starts at time X.
     * <p>
     * Example 1:
     * <p>
     * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
     * Output: 120
     * Explanation: The subset chosen is the first and fourth job.
     * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
     * Example 2:
     * <p>
     * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
     * Output: 150
     * Explanation: The subset chosen is the first, fourth and fifth job.
     * Profit obtained 150 = 20 + 70 + 60.
     * Example 3:
     * <p>
     * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
     * Output: 6
     * <p>
     * Constraints:
     * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4 (Requires O(nlogN))
     * 1 <= startTime[i] < endTime[i] <= 10^9
     * 1 <= profit[i] <= 10^4
     */

    class SolutionBinarySearchDP {

        /**
         * Let me explain how this solution works:
         * <p>
         * Data Structure:
         * Created a Job class to hold startTime, endTime, and profit for each job
         * This makes it easier to sort and manage the jobs
         * <p>
         * Algorithm Steps:
         * a. Create array of Job objects from input arrays
         * b. Sort jobs by end time (crucial for dynamic programming)
         * c. Use dp array where dp[i] represents maximum profit up to index i
         * d. For each job, we have two choices:
         * <p>
         * Include current job and find compatible jobs before it
         * Exclude current job and take profit from previous state
         * e. Use binary search to efficiently find the latest non-conflicting job
         * <p>
         * Key Components:
         * Sorting: O(nlogn) - Essential for binary search to work
         * Binary Search: O(logn) - To find latest non-conflicting job
         * Dynamic Programming: Build solution using previous results
         * <p>
         * <p>
         * Time Complexity: O(nlogn)
         * <p>
         * Sorting jobs: O(nlogn)
         * For each job, binary search: O(logn)
         * Overall: O(nlogn)
         * <p>
         * <p>
         * Space Complexity: O(n)
         * <p>
         * Jobs array: O(n)
         * DP array: O(n)
         * <p>
         * <p>
         * Key Optimizations:
         * <p>
         * Binary search instead of linear search for finding non-conflicting jobs
         * Sorting by end time makes finding non-conflicting jobs easier
         * Dynamic programming to avoid recalculating overlapping sub problems
         * <p>
         * The solution efficiently handles the constraints:
         * <p>
         * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
         * 1 <= startTime[i] < endTime[i] <= 10^9
         * 1 <= profit[i] <= 10^4
         */

        record Job(int startTime, int endTime, int profit) {
        }

        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int n = startTime.length;
            Job[] jobs = new Job[n];

            // Create array of Job objects
            for (int i = 0; i < n; i++) {
                jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
            }

            // Sort jobs by end time
            Arrays.sort(jobs, Comparator.comparingInt(a -> a.endTime));

            // dp[i] represents maximum profit we can make till index i
            int[] dp = new int[n];
            dp[0] = jobs[0].profit;  // Base case

            // Fill dp array
            for (int i = 1; i < n; i++) {
                // Profit including current job
                int profitIncludingCurrent = jobs[i].profit;

                // Find latest non-conflicting job before current job
                int latestNonConflict = binarySearch(jobs, i);

                // Add profit from previous non-conflicting job if exists
                if (latestNonConflict != -1) {
                    profitIncludingCurrent += dp[latestNonConflict];
                }

                // Maximum profit will be maximum of including and excluding current job
                dp[i] = Math.max(profitIncludingCurrent, dp[i - 1]);
            }

            return dp[n - 1];
        }

        // Binary search to find latest non-conflicting job
        private int binarySearch(Job[] jobs, int currentIndex) {
            int low = 0;
            int high = currentIndex - 1;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (jobs[mid].endTime <= jobs[currentIndex].startTime) {
                    if (jobs[mid + 1].endTime <= jobs[currentIndex].startTime) {
                        low = mid + 1;
                    } else {
                        return mid;
                    }
                } else {
                    high = mid - 1;
                }
            }

            return high;
        }
    }

    class SolutionTreeMapDP {
        record Job(int startTime, int endTime, int profit) {
        }

        int jobScheduling(int[] startTimes, int endTimes[], int profits[]) {
            ArrayList<Job> jobs = new ArrayList<>();
            for (int i = 0; i < startTimes.length; i++) {
                jobs.add(new Job(startTimes[i], endTimes[i], profits[i]));
            }
            jobs.sort(Comparator.comparingInt(a -> a.endTime));
            TreeMap<Integer, Integer> dp = new TreeMap<>();
            int ans = 0;
            for (Job curr : jobs) {
                Integer entryTillStartTime = dp.floorKey(curr.startTime);
                int maxProfitTillStartTime = entryTillStartTime == null ? 0 : dp.get(entryTillStartTime);
                ans = Math.max(ans, maxProfitTillStartTime + curr.profit);
                dp.put(curr.endTime, ans);
            }
            return ans;
        }
    }

}
