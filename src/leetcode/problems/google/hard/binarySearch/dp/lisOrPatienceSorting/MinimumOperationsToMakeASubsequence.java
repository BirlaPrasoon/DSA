package leetcode.problems.google.hard.binarySearch.dp.lisOrPatienceSorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumOperationsToMakeASubsequence {
    /**
     * You are given an array target that consists of distinct integers and another integer array arr that can have duplicates.
     * <p>
     * In one operation, you can insert any integer at any position in arr. For example, if arr = [1,4,1,2], you can add 3 in the middle and make it [1,4,3,1,2]. Note that you can insert the integer at the very beginning or end of the array.
     * <p>
     * Return the minimum number of operations needed to make target a subsequence of arr.
     * <p>
     * A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: target = [5,1,3], arr = [9,4,2,3,4]
     * Output: 2
     * Explanation: You can add 5 and 1 in such a way that makes arr = [5,9,4,1,2,3,4], then target will be a subsequence of arr.</br>
     * Example 2:
     * <p>
     * Input: target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]</br>
     * Output: 3
     * <p>
     * Constraints:
     * <p>
     * 1 <= target.length, arr.length <= 10<sup>5</sup></br>
     * 1 <= target[i], arr[i] <= 10<sup>9</sup></br>
     * target contains no duplicates.
     */

    class Solution {

/*
        Let me explain how this solution works:

        Problem Analysis:

        We need to find minimum operations to convert arr into a subsequence of target
        This is equivalent to finding the longest common subsequence that maintains relative order


        Key Insights:

        Since all numbers in target are unique, we can convert this to an LIS problem
        Convert arr into indices based on target array positions
        Find the longest increasing subsequence in these indices


        Solution Steps:

        Create a mapping of values to indices for target array
        Convert arr to indices based on target positions
        Find length of LIS using patience sorting (O(nlogn))
        Result is length of target minus length of LIS


        Time Complexity: O(nlogn) where n is the length of arr
        Space Complexity: O(n) for the hashmap and indices array
        */

        public int minOperations(int[] target, int[] arr) {
            // Create a map for target values to indices
            Map<Integer, Integer> valueToIndex = new HashMap<>();
            for (int i = 0; i < target.length; i++) {
                valueToIndex.put(target[i], i);
            }

            // Convert arr to indices based on target array
            List<Integer> indices = new ArrayList<>();
            for (int num : arr) {
                if (valueToIndex.containsKey(num)) {
                    indices.add(valueToIndex.get(num));
                }
            }

            // Find length of Longest Increasing Subsequence using patience sort
            return target.length - lengthOfLIS(indices);
        }

        // Calculate length of Longest Increasing Subsequence using patience sorting
        private int lengthOfLIS(List<Integer> nums) {
            if (nums.isEmpty()) return 0;

            // dp array to store the potential values
            List<Integer> dp = new ArrayList<>();

            for (int num : nums) {
                int pos = binarySearch(dp, num);
                if (pos == dp.size()) {
                    dp.add(num);
                } else {
                    dp.set(pos, num);
                }
            }

            return dp.size();
        }

        // Binary search to find insertion position
        private int binarySearch(List<Integer> dp, int target) {
            int left = 0;
            int right = dp.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (dp.get(mid) >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }
}
