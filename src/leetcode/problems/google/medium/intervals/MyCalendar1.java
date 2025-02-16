package leetcode.problems.google.medium.intervals;

import java.util.BitSet;
import java.util.TreeMap;

public class MyCalendar1 {

    class MyCalendar {
        TreeMap<Integer, Integer> calendar;

        MyCalendar() {
            calendar = new TreeMap();
        }

        public boolean book(int start, int end) {
            Integer prev = calendar.floorKey(start),
                    next = calendar.ceilingKey(start);
            if ((prev == null || calendar.get(prev) <= start) &&
                    (next == null || end <= next)) {
                calendar.put(start, end);
                return true;
            }
            return false;
        }
    }

    class MyCalendar2 {
        BitSet bitSet;
        MyCalendar2() {
            bitSet = new BitSet();
        }
        public boolean book(int start, int end) {
            int nextSetIndex = bitSet.nextSetBit(start-1);
            if(nextSetIndex > start && nextSetIndex <end) return false;
            bitSet.set(start, end);
            return true;
        }

    }
}
