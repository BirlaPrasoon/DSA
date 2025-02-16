package leetcode.problems.google.hard.tree;

import java.util.Map;
import java.util.TreeMap;

public class RangeModule {

    // TreeMap to store intervals, where the key is the start of the interval and the value is the end
    private TreeMap<Integer, Integer> intervals;

    public RangeModule() {
        intervals = new TreeMap<>();
    }

    /**
     * Add a range [left, right).
     * The method merges any overlapping intervals with the new range.
     *
     * @param left  - start of the interval (inclusive)
     * @param right - end of the interval (exclusive)
     *
     * Time Complexity: O(k log n), where k is the number of intervals that overlap or are adjacent to [left, right).
     */
    public void addRange(int left, int right) {
        // Find the start of the overlapping range
        Map.Entry<Integer, Integer> start = intervals.floorEntry(left);
        Map.Entry<Integer, Integer> end = intervals.floorEntry(right);

        // If the interval overlaps or touches the one before it, adjust `left`
        if (start != null && start.getValue() >= left) {
            left = Math.min(left, start.getKey());
        }

        // If the interval overlaps or touches the one after it, adjust `right`
        if (end != null && end.getValue() >= right) {
            right = Math.max(right, end.getValue());
        }

        // Remove all intervals that are covered or overlap with [left, right)
        intervals.subMap(left, right).clear();

        // Insert the merged interval [left, right)
        intervals.put(left, right);
    }

    /**
     * Query whether the range [left, right) is fully covered by the intervals.
     *
     * @param left  - start of the query interval (inclusive)
     * @param right - end of the query interval (exclusive)
     * @return true if the entire range [left, right) is covered by existing intervals, false otherwise
     *
     * Time Complexity: O(log n), since we are performing a single lookup in the TreeMap.
     */
    public boolean queryRange(int left, int right) {
        // Find the interval that starts before or at `left`
        Map.Entry<Integer, Integer> entry = intervals.floorEntry(left);

        // The interval must exist and fully cover the range [left, right)
        return entry != null && entry.getValue() >= right;
    }

    /**
     * Remove the range [left, right).
     * Any part of the intervals that overlaps with [left, right) is removed.
     *
     * @param left  - start of the interval to remove (inclusive)
     * @param right - end of the interval to remove (exclusive)
     *
     * Time Complexity: O(k log n), where k is the number of intervals that overlap or are adjacent to [left, right).
     */
    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> start = intervals.floorEntry(left);
        Map.Entry<Integer, Integer> end = intervals.floorEntry(right);

        // If there's an interval that starts before `left` and overlaps with the range,
        // we need to adjust its end to `left`.
        if (start != null && start.getValue() > left) {
            intervals.put(start.getKey(), left);  // Keep the left portion
        }

        // If there's an interval that ends after `right`, keep the right portion
        if (end != null && end.getValue() > right) {
            intervals.put(right, end.getValue());
        }

        // Remove all intervals that fall completely within [left, right)
        intervals.subMap(left, right).clear();
    }

    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();

        // Test case
        rangeModule.addRange(10, 20);
        System.out.println(rangeModule.queryRange(14, 16));  // Output: true
        rangeModule.removeRange(14, 16);
        System.out.println(rangeModule.queryRange(14, 16));  // Output: false
        rangeModule.addRange(10, 20);
        System.out.println(rangeModule.queryRange(10, 14));  // Output: true
    }
}
