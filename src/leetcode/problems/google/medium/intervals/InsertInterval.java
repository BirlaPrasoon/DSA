package leetcode.problems.google.medium.intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {


    /*
    *
    * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end
    * of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval
    * newInterval = [start, end] that represents the start and end of another interval.

        Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still
        * does not have any overlapping intervals (merge overlapping intervals if necessary).

        Return intervals after the insertion.

        Note that you don't need to modify intervals in-place. You can make a new array and return it.



        Example 1:

        Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
        Output: [[1,5],[6,9]]
        Example 2:

        Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        Output: [[1,2],[3,10],[12,16]]
        Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

    * */
    class SolutionLinearSearch {
        // O(n) as merging can be O(n)
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int n = intervals.length, i = 0;
            List<int[]> res = new ArrayList<>();

            // Case 1: No overlapping before merging intervals
            while (i < n && intervals[i][1] < newInterval[0]) {
                res.add(intervals[i]);
                i++;
            }

            // Case 2: Overlapping and merging intervals
            while (i < n && newInterval[1] >= intervals[i][0]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
                i++;
            }
            res.add(newInterval);

            // Case 3: No overlapping after merging newInterval
            while (i < n) {
                res.add(intervals[i]);
                i++;
            }

            // Convert List to array
            return res.toArray(new int[res.size()][]);
        }
    }

    class SolutionBinarySearch {
        // O(n) as merging is still O(n)
        public int[][] insert(int[][] intervals, int[] newInterval) {
            // If the intervals vector is empty, return a vector containing the newInterval
            if (intervals.length == 0) {
                return new int[][] { newInterval };
            }

            int n = intervals.length;
            int target = newInterval[0];
            int left = 0, right = n - 1;

            // Binary search to find the position to insert newInterval
            while (left <= right) {
                int mid = (left + right) / 2;
                if (intervals[mid][0] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            // Insert newInterval at the found position
            List<int[]> result = new ArrayList<>();
            for (int i = 0; i < left; i++) {
                result.add(intervals[i]);
            }
            result.add(newInterval);
            for (int i = left; i < n; i++) {
                result.add(intervals[i]);
            }

            // Merge overlapping intervals
            List<int[]> merged = new ArrayList<>();
            for (int[] interval : result) {
                // If res is empty or there is no overlap, add the interval to the result
                if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                    merged.add(interval);
                    // If there is an overlap, merge the intervals by updating the end of the last interval in res
                } else {
                    merged.get(merged.size() - 1)[1] = Math.max(
                            merged.get(merged.size() - 1)[1],
                            interval[1]
                    );
                }
            }

            return merged.toArray(new int[0][]);
        }
    }

}
