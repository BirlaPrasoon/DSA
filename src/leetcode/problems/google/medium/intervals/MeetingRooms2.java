package leetcode.problems.google.medium.intervals;

import java.util.*;

public class MeetingRooms2 {
    // Meeting rooms and merge intervals are not same, look at this - [[0, 30], [5, 10], [15, 20]], answer should be 2 not 3.

    public static void main(String[] args) {
        int[][] x = {{0,30}, {5, 10}, {15, 20}};
        Solution1 mergeIntervals = new Solution1();
        System.out.println("Number of rooms needed: "+ mergeIntervals.minMeetingRooms(x));
    }
    static class Solution {
        public int minMeetingRooms(int[][] intervals) {

            // Check for the base case. If there are no intervals, return 0
            if (intervals.length == 0) {
                return 0;
            }

            // Min heap
            PriorityQueue<Integer> allocator = new PriorityQueue<Integer>(new Comparator<Integer>() {public int compare(Integer a, Integer b) {return a - b;}});

            // Sort the intervals by start time
            Arrays.sort(intervals, new Comparator<int[]>() {public int compare(final int[] a, final int[] b) {if(a[0] == b[0]) return a[1] - b[1];return a[0] - b[0];}});

            // Add the first meeting
            allocator.add(intervals[0][1]);

            // Iterate over remaining intervals
            for (int i = 1; i < intervals.length; i++) {

                // If the room due to free up the earliest is free, assign that room to this meeting.
                while (!allocator.isEmpty() && intervals[i][0] >= allocator.peek()) {
                    allocator.poll();
                }

                // If a new room is to be assigned, then also we add to the heap,
                // If an old room is allocated, then also we have to add to the heap with updated end time.
                allocator.add(intervals[i][1]);
            }

            // The size of the heap tells us the minimum rooms required for all the meetings.
            return allocator.size();
        }
    }

    static class Solution1 {
        public int minMeetingRooms(int[][] intervals) {
            int[] starts = new int[intervals.length];
            int[] ends = new int[intervals.length];
            for(int i=0; i<intervals.length; i++) {
                starts[i] = intervals[i][0];
                ends[i] = intervals[i][1];
            }
            Arrays.sort(starts);
            Arrays.sort(ends);
            int rooms = 0;
            int endsItr = 0;
            for (int start : starts) {
                if (start < ends[endsItr])
                    rooms++;
                else
                    endsItr++;
            }
            return rooms;
        }
    }
}
