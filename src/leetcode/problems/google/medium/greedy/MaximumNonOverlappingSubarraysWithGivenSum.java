package leetcode.problems.google.medium.greedy;

import java.util.HashMap;
import java.util.Map;

public class MaximumNonOverlappingSubarraysWithGivenSum {

    class Solution {
        public int maxNonOverlapping(int[] nums, int target) {
            int n = nums.length;
            Map<Integer, Integer> prefixSumMap = new HashMap<>();
            int currentSum = 0;
            int lastEndIndex = -1;  // Last ending position of a valid subarray
            int count = 0;          // Count of valid subarrays

            // Initialize map with 0 sum at index -1
            prefixSumMap.put(0, -1);

            for (int i = 0; i < n; i++) {
                currentSum += nums[i];

                // Look for required previous sum
                int requiredSum = currentSum - target;

                // If we find a valid subarray and it doesn't overlap with previous one
                if (prefixSumMap.containsKey(requiredSum) && prefixSumMap.get(requiredSum) >= lastEndIndex) {
                    count++;
                    lastEndIndex = i;
                    // Reset prefix sum map to avoid overlapping
                    prefixSumMap.clear();
                    prefixSumMap.put(0, i);
                    currentSum = 0;
                } else {
                    prefixSumMap.put(currentSum, i);
                }
            }

            return count;
        }
    }

}
