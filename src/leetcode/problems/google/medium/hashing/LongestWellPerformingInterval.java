package leetcode.problems.google.medium.hashing;

import java.util.HashMap;
import java.util.Map;

public class LongestWellPerformingInterval {

/*
    We are given hours, a list of the number of hours worked per day for a given employee.

    A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.

    A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.

    Return the length of the longest well-performing interval.



    Example 1:

    Input: hours = [9,9,6,0,6,6,9]
    Output: 3
    Explanation: The longest well-performing interval is [9,9,6].
    Example 2:

    Input: hours = [6,6,6]
    Output: 0
*/

    class Solution {
        public int longestWPI(int[] hours) {
            if (hours.length == 0) return 0;
            int maxLen = 0;
            Map<Integer, Integer> map = new HashMap();  // key is possible sum in hours array, value is index where that sum appears for the first time
            int prefSum = 0;  // sum at index i indicates the sum of hours[0:i] after transformation

            for (int i = 0; i < hours.length; i++) {
                prefSum += hours[i] > 8 ? 1 : -1;  // transform each hour to 1 or -1
                if (!map.containsKey(prefSum)) {
                    map.put(prefSum, i);  // record where the sum appears for the first time
                }

                if (prefSum > 0) {  // in hours[0:i], more 1s than -1s
                    maxLen = i + 1;
                } else if (map.containsKey(prefSum - 1)) {  // get the index j where sum of hours[0:j] is sum - 1, so that sum of hours[j+1:i] is 1
                    maxLen = Math.max(maxLen, i - map.get(prefSum - 1));
                }

            }

            return maxLen;
        }
    }
}
