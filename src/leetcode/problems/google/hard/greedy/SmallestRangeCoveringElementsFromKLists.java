package leetcode.problems.google.hard.greedy;

import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeCoveringElementsFromKLists {

    /**
     * Problem Statement:<br><br>
     * You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.<br><br>
     *
     * The range [a, b] is smaller than range [c, d] if:<br>
     * - b - a < d - c, or<br>
     * - b - a == d - c and a < c.<br><br>
     *
     * Example 1:<br>
     * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]<br>
     * Output: [20,24]<br>
     * Explanation:<br>
     * - List 1: [4, 10, 15, 24, 26]<br>
     * - List 2: [0, 9, 12, 20]<br>
     * - List 3: [5, 18, 22, 30]<br>
     * - The smallest range covering elements from all three lists is [20, 24].<br><br>
     *
     * Example 2:<br>
     * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]<br>
     * Output: [1,1]<br>
     * Explanation:<br>
     * - All lists contain the number 1, so the smallest range is [1, 1].<br><br>
     *
     * Constraints:<br>
     * - nums.length == k (1 <= k <= 3500)<br>
     * - 1 <= nums[i].length <= 50<br>
     * - -10^5 <= nums[i][j] <= 10^5<br>
     * - nums[i] is sorted in non-decreasing order.<br>
     */
    class Solution {
        public int[] smallestRange(List<List<Integer>> nums) {
            // Min-Heap: stores (value, list index, element index)
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            int curMax = Integer.MIN_VALUE;

            // Initialize the heap with the first element of each list
            for (int i = 0; i < nums.size(); i++) {
                minHeap.offer(new int[]{nums.get(i).get(0), i, 0});
                curMax = Math.max(curMax, nums.get(i).get(0));
            }

            // Track the smallest range
            int[] smallRange = new int[]{0, Integer.MAX_VALUE};

            while (true) {
                // Get the minimum element from the heap
                int[] curr = minHeap.poll();
                int curMin = curr[0], listIdx = curr[1], elemIdx = curr[2];

                // Update the smallest range if a better one is found
                if (curMax - curMin < smallRange[1] - smallRange[0]) {
                    smallRange[0] = curMin;
                    smallRange[1] = curMax;
                }

                // Move to the next element in the same list
                if (elemIdx + 1 < nums.get(listIdx).size()) {
                    int nextVal = nums.get(listIdx).get(elemIdx + 1);
                    minHeap.offer(new int[]{nextVal, listIdx, elemIdx + 1});
                    curMax = Math.max(curMax, nextVal);
                } else {
                    // If any list is exhausted, stop
                    break;
                }
            }
            return smallRange;
        }
    }
}
