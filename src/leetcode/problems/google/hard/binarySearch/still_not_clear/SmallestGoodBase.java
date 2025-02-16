package leetcode.problems.google.hard.binarySearch.still_not_clear;

public class SmallestGoodBase {

/*    Given an integer n represented as a string, return the smallest good base of n.

    We call k >= 2 a good base of n, if all digits of n base k are 1's.



    Example 1:

    Input: n = "13"
    Output: "3"
    Explanation: 13 base 3 is 111.
    Example 2:

    Input: n = "4681"
    Output: "8"
    Explanation: 4681 base 8 is 11111.
    Example 3:

    Input: n = "1000000000000000000"
    Output: "999999999999999999"
    Explanation: 1000000000000000000 base 999999999999999999 is 11.


    Constraints:

    n is an integer in the range [3, 1018].
    n does not contain any leading zeros.
        */

    //https://leetcode.com/problems/smallest-good-base/solutions/3787685/binary-search-100-faster-in-both-java-and-cpp-explanation-with-visualization-tc-o-log-2n/

    class Solution {
        public String smallestGoodBase(String n) {
            long low = Long.parseLong(n);
            int high = (int) (Math.log(low + 1) / Math.log(2)) - 1;

            long result = low - 1;
            for (int m = high; m > 1; m--) {
                long k = (long) Math.pow(low, 1.0 / m);
                if (geometric(k, m) == low) return String.valueOf(k);
            }
            return String.valueOf(result);
        }

        private long geometric(long base, int m) {
            long result = 0;
            for (int i = 0; i <= m; i++) {
                result = 1 + result * base;
            }
            return result;
        }
    }

}
