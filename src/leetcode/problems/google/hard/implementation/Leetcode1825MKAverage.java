package leetcode.problems.google.hard.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Leetcode1825MKAverage {

    /**
     * You are given two integers, m and k, and a stream of integers.
     * You are tasked to implement a data structure that calculates the MKAverage for the stream.
     * <p>
     * The MKAverage can be calculated using these steps:
     * <p>
     * If the number of the elements in the stream is less than m you should consider the MKAverage to be -1.
     * Otherwise, copy the last m elements of the stream to a separate container.
     * <p>
     * Remove the smallest k elements and the largest k elements from the container.
     * Calculate the average value for the rest of the elements rounded down to the nearest integer.
     * <p>
     * Implement the MKAverage class:
     * MKAverage(int m, int k) Initializes the MKAverage object with an empty stream and the two integers m and k.
     * void addElement(int num) Inserts a new element num into the stream.
     * int calculateMKAverage() Calculates and returns the MKAverage for the current stream rounded down to the nearest integer.
     */
    /*

    Last Resort, not necessary but having an idea is good.

    * Intuition
    * The idea is to have 3 treemaps tl, tm and th. For lowest k (tl), highest k (th) and all others (tm).
    * The trick is we need to balance the maps when an element is added/removed.
    *
    * */
    class MKAverage {
        int mx = (int) 2e5, mn = 0, i = 0, m, k;
        long sm = 0l;

        TreeMap<Integer, Integer> tl = new TreeMap<>(), tm = new TreeMap<>(), th = new TreeMap<>();
        HashMap<Integer, Integer> ind = new HashMap<>();

        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
        }

        public void addElement(int num) {
            addNewNum(num);
            if (i > m) removeOldNum();
        }

        public int calculateMKAverage() {
            return i >= m ? (int) (sm / (m - 2L * k)) : -1;
        }

        void addNewNum(int num) {
            ind.put(i++, num);
            add(tl, num);
            if (i <= k) return;
            int v1 = removeHighest(tl);

            add(tm, v1);
            int v2 = removeHighest(tm);
            sm += v1 - v2;

            add(th, v2);
            if (i <= 2 * k) return;
            int v3 = removeLowest(th);

            add(tm, v3);
            sm += v3;
        }

        void removeOldNum() {
            int prev = ind.get(i - m - 1);

            if (tl.get(prev) != null) {
                remove(tl, prev);
                int low = removeLowest(tm);
                sm -= low;
                add(tl, low);
            } else if (th.get(prev) != null) {
                remove(th, prev);
                int high = removeHighest(tm);
                sm -= high;
                add(th, high);
            } else {
                remove(tm, prev);
                sm -= prev;
            }

            ind.remove(i - m - 1);
        }

        void remove(Map<Integer, Integer> tree, int val) {
            tree.put(val, tree.getOrDefault(val, 0) - 1);
            if (tree.get(val) == 0) tree.remove(val);
        }

        void add(Map<Integer, Integer> tree, int val) {
            tree.put(val, tree.getOrDefault(val, 0) + 1);
        }

        int removeLowest(TreeMap<Integer, Integer> tree) {
            int low = tree.ceilingKey(mn);
            tree.put(low, tree.get(low) - 1);
            if (tree.get(low) == 0) tree.remove(low);
            return low;
        }

        int removeHighest(TreeMap<Integer, Integer> tree) {
            int high = tree.floorKey(mx);
            tree.put(high, tree.get(high) - 1);
            if (tree.get(high) == 0) tree.remove(high);
            return high;
        }
    }
}
