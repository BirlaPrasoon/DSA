package leetcode.problems.google.medium.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumLengthOfPairChain {
/*
    You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.

    A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.

    Return the length longest chain which can be formed.

            You do not need to use up all the given intervals. You can select pairs in any order.



            Example 1:

    Input: pairs = [[1,2],[2,3],[3,4]]
    Output: 2
    Explanation: The longest chain is [1,2] -> [3,4].
    Example 2:

    Input: pairs = [[1,2],[7,8],[4,5]]
    Output: 3
    Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].


    Constraints:

    n == pairs.length
1 <= n <= 1000
            -1000 <= lefti < righti <= 1000
    */

    class Solution {
        public int findLongestChain(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
            int ans = 0;
            int previous_end = Integer.MIN_VALUE;

            for (int[] interval : intervals) {
                int start = interval[0];
                int end = interval[1];

                if (start > previous_end) {
                    // Case 1: Now we'll compare with this end value later on...
                    previous_end = end;
                } else {
                    // Case 2: Remove the one that has larger ending, it may create problem later on.
                    ans++;
                }
            }

            return intervals.length - ans;
        }
    }
}
