package leetcode.problems.google.medium.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {

    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
            int ans = 0;
            int previous_end = Integer.MIN_VALUE;

            for (int[] interval : intervals) {
                int start = interval[0];
                int end = interval[1];

                if (start >= previous_end) {
                    // Case 1: Now we'll compare with this end value later on...
                    previous_end = end;
                } else {
                    // Case 2: Remove the one that has larger ending, it may create problem later on.
                    ans++;
                }
            }

            return ans;
        }
    }

}
