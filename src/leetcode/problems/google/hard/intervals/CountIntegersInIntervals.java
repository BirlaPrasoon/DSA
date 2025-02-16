package leetcode.problems.google.hard.intervals;

import java.util.TreeMap;

public class CountIntegersInIntervals {

    TreeMap<Integer, Integer> intervals = new TreeMap<>();
    int totalCount = 0;

    // Adds a new interval [left, right]
    public void add(int left, int right) {
        // Find the interval that overlaps or is just before `left`
        var currentInterval = intervals.floorEntry(left);

        // If no overlap with `currentInterval`, move to the next interval
        if (currentInterval == null || currentInterval.getValue() < left) {
            currentInterval = intervals.higherEntry(left);
        }

        // Merge all overlapping intervals
        while (currentInterval != null && currentInterval.getKey() <= right) {
            // Update boundaries of the new merged interval
            left = Math.min(left, currentInterval.getKey());
            right = Math.max(right, currentInterval.getValue());

            // Adjust total count by removing the current interval's contribution
            totalCount -= currentInterval.getValue() - currentInterval.getKey() + 1;

            // Remove the current interval
            intervals.remove(currentInterval.getKey());

            // Move to the next overlapping interval
            currentInterval = intervals.higherEntry(left);
        }

        // Insert the new merged interval
        intervals.put(left, right);

        // Update the total count with the size of the merged interval
        totalCount += right - left + 1;
    }


    public int count() {
        return totalCount;
    }
}
