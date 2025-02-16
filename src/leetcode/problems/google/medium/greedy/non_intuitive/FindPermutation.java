package leetcode.problems.google.medium.greedy.non_intuitive;

import java.util.Stack;

public class FindPermutation {

/*
    A permutation perm of n integers of all the integers in the range [1, n] can be represented as a string s of length n - 1 where:

    s[i] == 'I' if perm[i] < perm[i + 1], and
    s[i] == 'D' if perm[i] > perm[i + 1].
    Given a string s, reconstruct the lexicographically smallest permutation perm and return it.

    Example 1:
    Input: s = "I"
    Output: [1,2]
    Explanation: [1,2] is the only legal permutation that can represented by s, where the number 1 and 2 construct an increasing relationship.

    Example 2:
    Input: s = "DI"
    Output: [2,1,3]
    Explanation: Both [2,1,3] and [3,1,2] can be represented as "DI", but since we want to find the smallest lexicographical permutation, you should return [2,1,3]

    Constraints:
    1 <= s.length <= 105
    s[i] is either 'I' or 'D'.
    */


    public class Solution {
        public int[] findPermutation(String s) {
            int[] res = new int[s.length() + 1];
            Stack<Integer> stack = new Stack<>();
            int j = 0;
            for (int i = 1; i <= s.length(); i++) {
                if (s.charAt(i - 1) == 'I') {
                    stack.push(i);
                    while (!stack.isEmpty())
                        res[j++] = stack.pop();
                } else
                    stack.push(i);
            }
            stack.push(s.length() + 1);
            while (!stack.isEmpty())
                res[j++] = stack.pop();
            return res;
        }
    }

}
