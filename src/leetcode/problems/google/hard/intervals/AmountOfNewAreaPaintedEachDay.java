package leetcode.problems.google.hard.intervals;

import java.util.HashMap;
import java.util.Map;

public class AmountOfNewAreaPaintedEachDay {
    /**
     * There is a long and thin painting that can be represented by a number line. You are given a 0-indexed 2D integer array paint of length n, where paint[i] = [starti, endi].
     * This means that on the ith day you need to paint the area between starti and endi.
     * <p>
     * Painting the same area multiple times will create an uneven painting so you only want to paint each area of the painting at most once.
     * <p>
     * Return an integer array worklog of length n, where worklog[i] is the amount of new area that you painted on the ith day.
     */
    class Solution {
        public int[] amountPainted(int[][] paint) {
            int n = paint.length;
            Map<Integer, Integer> map = new HashMap<>();
            int i = 0;
            int[] res = new int[n];

            for (int[] p : paint) {
                int work = 0;
                int start = p[0];
                int end = p[1];

                while (start < end) {
                    if (map.containsKey(start)) {
                        int prevEnd = map.get(start);

                        // place the max area painted
                        map.put(start, Math.max(map.get(start), end));

                        start = prevEnd;
                    } else {
                        map.put(start, end);
                        start += 1;
                        work += 1;
                    }
                }
                res[i++] = work;
            }

            return res;
        }
    }
}
