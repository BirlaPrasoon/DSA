package leetcode.problems.google.hard.monotonic_stack;

import java.util.*;

public class SubArrayWithElementsGreaterThanVaryingThreshold {


    /**
     * <h1>Problem Statement</h1>
     * You are given an integer array nums and an integer threshold.
     * Find any subarray of nums of length k such that every element in the subarray is greater than threshold / k.
     * <p>
     *     <h2>ASK</h2>
     * Return the size of any such subarray. If there is no such subarray, return -1.
     * <p>
     * A subarray is a contiguous non-empty sequence of elements within an array.
     * <p>
     * <p>
     *     <h3>Examples</h3>
     * <h4>Example 1:</h4>
     * <p>
     * Input: nums = [1,3,4,3,1], threshold = 6</br>
     * Output: 3</br>
     * Explanation: The subarray [3,4,3] has a size of 3, and every element is greater than 6 / 3 = 2.</br>
     * Note that this is the only valid subarray.</br>
     * <h4>Example 2:</h4>
     * <p>
     * Input: nums = [6,5,6,5,8], threshold = 7</br>
     * Output: 1</br>
     * Explanation: The subarray [8] has a size of 1, and 8 > 7 / 1 = 7. So 1 is returned.</br>
     * Note that the subarray [6,5] has a size of 2, and every element is greater than 7 / 2 = 3.5.</br>
     * Similarly, the subarrays [6,5,6], [6,5,6,5], [6,5,6,5,8] also satisfy the given conditions.</br>
     * Therefore, 2, 3, 4, or 5 may also be returned.</br>
     */

    // Much easier solution using DSU - https://www.youtube.com/watch?v=j7hXhjuwqaY

    // DSU Approach

    /**
     * DSU Approach
     * <p>
     * <strong>FOR K [1...N]:</strong>
     * <ul>
     * <li>active_threshold = THRESHOLD/K</li>
     * <li>FOR [indices which are greater than active_threshold -->i ]:
     * <ul>
     * <li>activate index i</li>
     * <li>if i-1 is activated: merge (i-1, i)</li>
     * <li>if i+1 is activated: merge (i, i+1)</li>
     * </ul>
     * </li>
     * <li>IF merge group size &ge; K return K;</li>
     * </ul>
     * <p>return -1;</p>
     * </p>
     *
     * <p><strong>Example:</strong></p>
     * [4,6,5,6,5,6,3,8], threshold = 15</br>
     * k = 1, active_threshold => 15/1 => 15, since no element is greater than 15, none of them is activated.</br>
     * k = 2, active_threshold => 15/2 => 7.5, since 8 is greater than 15, its marked as done</br>
     * k = 3, active_threshold => 15/3 => 5, elements greater than 5 -> 6, 6, 6 => now activated groups -> [6], [6], [6], [8]</br>
     * k = 4, active_threshold => 15/4 => 3.8, elements greater than 3.8 which are not already activated -> 4, 5, 5 => now activated groups => [4,6,5,6,5,6],[8]</br>
     * since 1st group size is 6 which is >=4, our answer is 4.
     */


    class SolutionMonotonicStack {
        public int validSubarraySize(int[] nums, int threshold) {
            int n = nums.length;
            int[] next_small = new int[n];
            int[] prev_small = new int[n];
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            Arrays.fill(prev_small, -1);
            for (int i = 1; i < n; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    prev_small[i] = stack.peek();
                }
                stack.push(i);
            }
            stack = new Stack<>();
            stack.push(n - 1);
            Arrays.fill(next_small, n);
            for (int i = n - 2; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    next_small[i] = stack.peek();
                }
                stack.push(i);
            }
            for (int i = 0; i < n; i++) {
                int len = next_small[i] - prev_small[i] - 1;
                if (threshold / (double) len < nums[i]) { // nums[i] is the smallest element in this range.
                    return len;
                }
            }
            return -1;
        }
    }

    // O nlogn
    class Solution {

        /*
        *
        * Ideas
        For each num, we will find the minimum size required.
        If that number is greater than the size of the array, we add its index to dead treeset.
        If not, we add it to a maxheap sorted by min size needed.
        Go through all the elements from the heap, call treeset.higher() and lower() to get the best length.
        If this length is less than what is required for the current index, add this to the dead treeset.
        Repeat until we find something that works, otherwise return -1.
        *
        * */

        public int validSubarraySize(int[] nums, int threshold) {
            int n = nums.length;
            int[] min = new int[n];
            TreeSet<Integer> dead = new TreeSet<>(Set.of(n, -1)); // base case
            PriorityQueue<Integer> maxheap = new PriorityQueue<>(Comparator.comparingInt(o -> -min[o]));
            for (int i = 0; i < n; i++) {
                min[i] = threshold / nums[i] + 1;
                if (min[i] > nums.length) {
                    dead.add(i); // dead, this element should never appear in the answer
                } else {
                    maxheap.offer(i);
                }
            }
            while (!maxheap.isEmpty()) {
                int cur = maxheap.poll();
                if (dead.higher(cur) - dead.lower(cur) - 1 < min[cur]) {
                    dead.add(cur);  // widest open range < minimum required length, this index is also bad.
                } else {
                    return min[cur]; // otherwise we've found it!
                }
            }
            return -1;
        }
    }

    class SolutionDeque {
        /**
         * This is the same solution as first solution, looks different but functions the same.
         * For right smaller element, left smaller element will be at the same position as in first solution as all elements
         * in between will be greater of equal to this element.
         */
        public int validSubarraySize(int[] nums, int threshold) {

            int n = nums.length;
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i <= n; i++) {
                int currNum = (i == n) ? 0 : nums[i];
                while (!stack.isEmpty() && currNum < nums[stack.peek()]) {
                    int prevNum = nums[stack.pop()];
                    int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                    if (prevNum > threshold / width) return width;
                }
                stack.push(i);
            }
            return -1;
        }
    }
}
