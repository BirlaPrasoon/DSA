package leetcode.problems.google.medium.greedy;

public class MaximumSwap {
/*
    You are given an integer num. You can swap two digits at most once to get the maximum valued number.

    Return the maximum valued number you can get.



    Example 1:

    Input: num = 2736
    Output: 7236
    Explanation: Swap the number 2 and the number 7.
    Example 2:

    Input: num = 9973
    Output: 9973
    Explanation: No swap.


    Constraints:

            0 <= num <= 108
    */

    class Solution {
        public int maximumSwap(int num) {
            String nu = Integer.toString(num);
            char[] chars = nu.toCharArray();
            int[] largest = new int[chars.length];
            largest[chars.length-1] = chars.length-1;
            for(int i = chars.length-2; i>=0; i--) {
                if(chars[i] > chars[largest[i+1]]) {
                    largest[i] = i;
                } else {
                    largest[i] = largest[i+1];
                }
            }
            for(int i= 0;i<chars.length; i++) {
                if(i!= largest[i] && chars[i] != chars[largest[i]]) {
                    char temp = chars[i];
                    chars[i] = chars[largest[i]];
                    chars[largest[i]] =temp;
                    break;
                }
            }

            return Integer.parseInt(new String(chars));
        }

    }

}
