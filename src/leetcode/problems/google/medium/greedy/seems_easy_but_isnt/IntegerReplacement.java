package leetcode.problems.google.medium.greedy.seems_easy_but_isnt;

import java.util.HashMap;
import java.util.Map;

public class IntegerReplacement {

/*
    Given a positive integer n, you can apply one of the following operations:

    If n is even, replace n with n / 2.
    If n is odd, replace n with either n + 1 or n - 1.
    Return the minimum number of operations needed for n to become 1.



    Example 1:

    Input: n = 8
    Output: 3
    Explanation: 8 -> 4 -> 2 -> 1
    Example 2:

    Input: n = 7
    Output: 4
    Explanation: 7 -> 8 -> 4 -> 2 -> 1
    or 7 -> 6 -> 3 -> 2 -> 1
    Example 3:

    Input: n = 4
    Output: 2


    Constraints:

            1 <= n <= 231 - 1
    */


    /*
    *
    * 1: U-nderstand
        What are the constraints on the argument n?
        n is in the range 1…2
        31
         −1.
        Can this be solved using recursion?
        Yes, integerReplacement(n)=
        0, if n is 1
        1+integerReplacement(n/2), if n is even
        1+min(integerReplacement(n−1),integerReplacement(n+1)), if n is odd
        Are there any special cases?
        In Java, 2^31−1 is the maximum int value. Adding 1 to it would cause wraparound.
        We can precalculate the value of integerReplacement(2^31−1)
        Step 1: Add 1 to get 2
        31

        Step 2: Divide by 2 to get 2
        30
        Step 32: Divide by 2 to get 2
        0
          (1)
        Good test cases would be:

        integerReplacement(1)=0 (edge case)
        integerReplacement(2_147_483_647)=0 (edge case)
        integerReplacement(8)=3 (happy case)
        2: M-atch
        This can be solved with recursion and memoization.

        3: P-lan
        Create a hash table to cache results.
        When integerReplacement(n) is called
        If n is 1, return 0.
        If n is 2
        31
         −1, return 32.
        If n is not in the hash table
        If n is even, add n→1+integerReplacement(n/2) to the hash table.
        Otherwise, if n is odd, add n→min(
        integerReplacement(n−1),
        integerReplacement(n+1))
        Return 1 + the retrieved value.

    *
    * */

    class Solution {
        Map<Integer, Integer> results = new HashMap<>();

        public int integerReplacement(int n) {
            if (n == 1) {
                return 0;
            }
            if (n == Integer.MAX_VALUE) {
                return 32;
            }
            if (!results.containsKey(n)) {
                if (n % 2 == 0) {
                    results.put(n, integerReplacement(n / 2));
                } else {
                    // n is odd
                    results.put(n, Math.min(
                            integerReplacement(n - 1),
                            integerReplacement(n + 1)));
                }
            }
            return 1 + results.get(n);
        }
    }
}
