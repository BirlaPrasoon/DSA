package leetcode.problems.google.medium.breadth_first_search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SteppingNumbers {


/*    A stepping number is an integer such that all of its adjacent digits have an absolute difference of exactly 1.

    For example, 321 is a stepping number while 421 is not.
    Given two integers low and high, return a sorted list of all the stepping numbers in the inclusive range [low, high].



    Example 1:

    Input: low = 0, high = 21
    Output: [0,1,2,3,4,5,6,7,8,9,10,12,21]
    Example 2:

    Input: low = 10, high = 15
    Output: [10,12]
    */

    class Solution {
        public ArrayList<Integer> countSteppingNumbers(int low, int high) {
            ArrayList<Integer> res = new ArrayList<>();
            if (low > high) return res;

            Queue<Long> queue = new LinkedList<>();
            for (long i = 1; i <= 9; i++) queue.add(i);

            if (low == 0) res.add(0);
            while (!queue.isEmpty()) {
                long p = queue.poll();
                if (p < high) {
                    long last = p % 10;
                    if (last > 0) queue.add(p * 10 + last - 1);
                    if (last < 9) queue.add(p * 10 + last + 1);
                }
                if (p >= low && p <= high) {
                    res.add((int) p);
                }
            }
            return res;
        }
    }
}
