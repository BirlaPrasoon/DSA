package leetcode.problems.google.medium.monotonic_stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BeautifulTowers2 {


    /**
     * This is the ssame problem as Beautiful towers 1, just the constraints are different. This one is n: 10^5, previous is 10^3
     *
     */

    class Solution {
        public long maximumSumOfHeights(List<Integer> maxHeights) {
            long sum = 0;
            for (int i = 0; i < maxHeights.size(); i++) {
                int mh = maxHeights.get(i);
                int prev = i == 0 ? 0 : maxHeights.get(i - 1);
                if (prev == mh)
                    continue;
                int next = i == maxHeights.size() - 1 ? 0 : maxHeights.get(i + 1);
                if (mh < prev || mh < next)
                    continue;
                sum = Math.max(sum, maximumSumOfHeights(maxHeights, i));
            }
            return sum;
        }

        public long maximumSumOfHeights(List<Integer> maxHeights, int idx) {
            long sum = maxHeights.get(idx);
            long last = sum;
            for (int j = idx - 1; j >= 0; j--) {
                int curr = maxHeights.get(j);
                if (curr < last)
                    last = curr;
                sum += last;
            }
            last = maxHeights.get(idx);
            for (int j = idx + 1; j < maxHeights.size(); j++) {
                int curr = maxHeights.get(j);
                if (curr < last)
                    last = curr;
                sum += last;
            }
            return sum;
        }
    }

    // Updating existing Solution

    static class SolutionMonotonicStack {
        public long maximumSumOfHeights(List<Integer> maxHeights) {
            int len = maxHeights.size();

            var left = new long[len];
            var right = new long[len];

            var stack = new Stack<Integer>();
            for (int i = 0; i < len; i++) {
                long mh = maxHeights.get(i);
                while (!stack.isEmpty() && maxHeights.get(stack.peek()) > mh) stack.pop();
                left[i] = stack.isEmpty() ? mh * (i - 0 + 1) : left[stack.peek()] + mh * (i - stack.peek());
                stack.push(i);
            }
            System.out.println(Arrays.toString(left));
            System.out.println("Left");
            stack = new Stack<>();
            for (int i = len - 1; i >= 0; i--) {
                long mh = maxHeights.get(i);
                while (!stack.isEmpty() && maxHeights.get(stack.peek()) > mh) stack.pop();
                right[i] = stack.isEmpty() ? mh * (len - i) : right[stack.peek()] + mh * (stack.peek() - i);
                stack.push(i);
            }
            long res = 0;
            System.out.println(maxHeights);
            System.out.println("Right");
            System.out.println(Arrays.toString(right));
            List<Integer> indexHeights = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                indexHeights.add((int)(left[i] + right[i] - maxHeights.get(i)));
                res = Math.max(res, left[i] + right[i] - maxHeights.get(i)); // double count..
            }
            System.out.println(indexHeights);
            return res;
        }
    }

    public static void main(String[] args) {
        SolutionMonotonicStack stack = new SolutionMonotonicStack();
        System.out.println(stack.maximumSumOfHeights(Arrays.asList(4,7,3,4,8,6,5,2)));
    }
}
