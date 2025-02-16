package leetcode.problems.google.medium.monotonic_stack;

import java.util.List;
import java.util.Stack;

public class BeautifulTowers1 {
    /**
     * You are given an array heights of n integers representing the number of bricks in n consecutive towers.
     * Your task is to remove some bricks to form a mountain-shaped tower arrangement.
     * In this arrangement, the tower heights are non-decreasing, reaching a maximum peak value with one or multiple consecutive towers and then non-increasing.
     * <p>
     * Return the maximum possible sum of heights of a mountain-shaped tower arrangement.
     * <p>
     * <p>
     * Example 1:
     * Input: heights = [5,3,4,1,1]
     * Output: 13
     * Explanation:
     * We remove some bricks to make heights = [5,3,3,1,1], the peak is at index 0.
     * <p>
     * Example 2:
     * Input: heights = [6,5,3,9,2,7]
     * Output: 22
     * Explanation:
     * We remove some bricks to make heights = [3,3,3,9,2,2], the peak is at index 3.
     * <p>
     * Example 3:
     * Input: heights = [3,2,5,5,2,3]
     * Output: 18
     * Explanation:
     * We remove some bricks to make heights = [2,2,5,5,2,2], the peak is at index 2 or 3.
     */

    // On2 Brute Force
    static class Solution {
        public long maximumSumOfHeights(List<Integer> h) {
            int n = h.size();
            long max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                long sum = h.get(i), left = h.get(i), right = h.get(i);
                // go left till we can go.
                for (int j = i + 1; j < n; j++) {
                    left = Math.min(left, h.get(j));
                    sum += left;
                }
                // go right till we can go.
                for (int j = i - 1; j >= 0; j--) {
                    right = Math.min(right, h.get(j));
                    sum += right;
                }
                max = Math.max(max, sum);
            }
            return max;
        }
    }


    static class SolutionMonotonicStack {
        class Solution {
            public long maximumSumOfHeights(List<Integer> maxHeights) {
                int len = maxHeights.size();

                var left = new long[len];
                var right = new long[len];

                var stack = new Stack<Integer>();
                for (int i = 0; i < len; i++) {
                    long mh = maxHeights.get(i);
                    while (!stack.isEmpty() && maxHeights.get(stack.peek()) > mh) stack.pop();
                    left[i] = stack.isEmpty() ? mh * (i + 1) : left[stack.peek()] + mh * (i - stack.peek());
                    stack.push(i);
                }
                stack = new Stack<>();
                for (int i = len - 1; i >= 0; i--) {
                    long mh = maxHeights.get(i);
                    while (!stack.isEmpty() && maxHeights.get(stack.peek()) > mh) stack.pop();
                    right[i] = stack.isEmpty() ? mh * (len - i) : right[stack.peek()] + mh * (stack.peek() - i);
                    stack.push(i);
                }
                long res = 0;
                for (int i = 0; i < len; i++) {
                    res = Math.max(res, left[i] + right[i] - maxHeights.get(i));
                }
                return res;
            }
        }
    }


}
