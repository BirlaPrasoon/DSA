package leetcode.problems.google.medium.greedy.tricky_implementation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ArrayOfDoubledPairs {
/*
    Given an integer array of even length arr, return true if it is possible to reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.



            Example 1:

    Input: arr = [3,1,3,6]
    Output: false
    Example 2:

    Input: arr = [2,1,2,6]
    Output: false
    Example 3:

    Input: arr = [4,-2,2,-4]
    Output: true
    Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].


    Constraints:

            2 <= arr.length <= 3 * 104
    arr.length is even.
-105 <= arr[i] <= 105
    */

    class Solution {
        public boolean canReorderDoubled(int[] A) {
            // count[x] = the number of occurrences of x in A
            Map<Integer, Integer> count = new HashMap();
            for (int x: A)
                count.put(x, count.getOrDefault(x, 0) + 1);

            // B = A as Integer[], sorted by absolute value
            Integer[] B = new Integer[A.length];
            for (int i = 0; i < A.length; ++i)
                B[i] = A[i];
            // Very clever technique...
            Arrays.sort(B, Comparator.comparingInt(Math::abs));

            for (int x: B) {
                // If this can't be consumed, skip
                if (count.get(x) == 0) continue;
                // If this doesn't have a doubled partner, the answer is false
                if (count.getOrDefault(2*x, 0) <= 0) return false;

                // Write x, 2*x
                count.put(x, count.get(x) - 1);
                count.put(2*x, count.get(2*x) - 1);
            }

            // If we have written everything, the answer is true
            return true;
        }
    }

}
