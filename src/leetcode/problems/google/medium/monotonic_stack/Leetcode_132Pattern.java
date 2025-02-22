package leetcode.problems.google.medium.monotonic_stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Leetcode_132Pattern {

/*
    Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

    Return true if there is a 132 pattern in nums, otherwise, return false.



    Example 1:

    Input: nums = [1,2,3,4]
    Output: false
    Explanation: There is no 132 pattern in the sequence.
    Example 2:

    Input: nums = [3,1,4,2]
    Output: true
    Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
    Example 3:

    Input: nums = [-1,3,2,0]
    Output: true
    Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
*/


    // N2
    public class Solution {
        public boolean find132pattern(int[] nums) {
            List<int[]> intervals = new ArrayList<>();
            int i = 1, s = 0;
            while (i < nums.length) {
                if (nums[i] < nums[i - 1]) {
                    if (s < i - 1) intervals.add(
                            new int[] { nums[s], nums[i - 1] }
                    );
                    s = i;
                }
                for (int[] a : intervals) if (
                        nums[i] > a[0] && nums[i] < a[1]
                ) return true;
                i++;
            }
            return false;
        }
    }

    // N
    public class SolutionStack {
        public boolean find132pattern(int[] nums) {
            if (nums.length < 3) return false;
            Stack<Integer> stack = new Stack<>();
            int[] min = new int[nums.length];
            min[0] = nums[0];
            for (int i = 1; i < nums.length; i++) min[i] = Math.min(
                    min[i - 1],
                    nums[i]
            );
            for (int j = nums.length - 1; j >= 0; j--) {
                if (nums[j] > min[j]) {
                    while (!stack.isEmpty() && stack.peek() <= min[j]) stack.pop();
                    if (!stack.isEmpty() && stack.peek() < nums[j]) return true;
                    stack.push(nums[j]);
                }
            }
            return false;
        }
    }
}
