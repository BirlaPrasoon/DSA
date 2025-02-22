package leetcode.problems.google.medium.monotonic_stack.different;

import java.util.LinkedList;

public class RemoveKDigits {
    /*
    *
    * Given string num representing a non-negative integer num, and an integer k,
    *
    * return the smallest possible integer after removing k digits from num.
    *
    *
    * Example 1:

        Input: num = "1432219", k = 3
        Output: "1219"
        Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
        Example 2:

        Input: num = "10200", k = 1
        Output: "200"
        Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
        Example 3:

        Input: num = "10", k = 2
        Output: "0"
        Explanation: Remove all the digits from the number and it is left with nothing which is 0.

    * * */

    class Solution {
        public String removeKdigits(String num, int k) {
            LinkedList<Character> stack = new LinkedList<Character>();

            for(char digit : num.toCharArray()) {
                while(stack.size() > 0 && k > 0 && stack.peekLast() > digit) {
                    stack.removeLast();
                    k -= 1;
                }
                stack.addLast(digit);
            }

            /* remove the remaining digits from the tail. */
            for(int i=0; i<k; ++i) {
                stack.removeLast();
            }

            // build the final string, while removing the leading zeros.
            StringBuilder ret = new StringBuilder();
            boolean leadingZero = true;
            for(char digit: stack) {
                if(leadingZero && digit == '0') continue;
                leadingZero = false;
                ret.append(digit);
            }

            /* return the final string  */
            if (ret.length() == 0) return "0";
            return ret.toString();
        }
    }
}
