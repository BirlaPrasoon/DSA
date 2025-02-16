package leetcode.problems.google.hard.binarySearch.most_asked;

import leetcode.Pair;

import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class ShortestSubArrayWithSumAtLeastK {
/*
    Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.

    A subarray is a contiguous part of an array.



            Example 1:

    Input: nums = [1], k = 1
    Output: 1
    Example 2:

    Input: nums = [1,2], k = 4
    Output: -1
    Example 3:

    Input: nums = [2,-1,2], k = 3
    Output: 3


    Constraints:

            1 <= nums.length <= 105
            -105 <= nums[i] <= 105
            1 <= k <= 109
    */

    // O(NLogN)
    class SolutionPriorityQueue {

        public int shortestSubarray(int[] nums, int k) {
            int n = nums.length;

            // Initialize result to the maximum possible integer value
            int shortestSubarrayLength = Integer.MAX_VALUE;

            long cumulativeSum = 0;

            // Min-heap to store cumulative sum and its corresponding index
            PriorityQueue<Pair<Long, Integer>> prefixSumHeap = new PriorityQueue<>(Comparator.comparingLong(Pair::getKey));

            // Iterate through the array
            for (int i = 0; i < n; i++) {
                // Update cumulative sum
                cumulativeSum += nums[i];

                // If cumulative sum is already >= k, update shortest length
                if (cumulativeSum >= k) {
                    shortestSubarrayLength = Math.min(shortestSubarrayLength, i + 1);
                }

                // Remove subarrays from heap that can form a valid subarray
                while (!prefixSumHeap.isEmpty() && cumulativeSum - prefixSumHeap.peek().getKey() >= k) {
                    // Update shortest subarray length
                    shortestSubarrayLength = Math.min(shortestSubarrayLength, i - prefixSumHeap.poll().getValue());
                }

                // Add current cumulative sum and index to heap
                prefixSumHeap.offer(new Pair<>(cumulativeSum, i));
            }

            // Return -1 if no valid subarray found
            return shortestSubarrayLength == Integer.MAX_VALUE ? -1 : shortestSubarrayLength;
        }
    }

    // O(NLogN)
    class SolutionMonotonicStackPlusBinarySearch {

        public int shortestSubarray(int[] nums, int k) {
            int n = nums.length;

            // Stack-like list to store cumulative sums and their indices
            List<Pair<Long, Integer>> cumulativeSumStack = new ArrayList<>();
            cumulativeSumStack.add(new Pair<>(0L, -1));

            long runningCumulativeSum = 0;
            int shortestSubarrayLength = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                // Update cumulative sum
                runningCumulativeSum += nums[i];

                // Remove entries from stack that are larger than current cumulative sum
                while (!cumulativeSumStack.isEmpty() && runningCumulativeSum <= cumulativeSumStack.get(cumulativeSumStack.size() - 1).getKey()) {
                    cumulativeSumStack.remove(cumulativeSumStack.size() - 1);
                }

                // Add current cumulative sum and index to stack
                cumulativeSumStack.add(new Pair<>(runningCumulativeSum, i));

                int candidateIndex = findCandidateIndex(cumulativeSumStack, runningCumulativeSum - k);

                // If a valid candidate is found, update the shortest subarray length
                if (candidateIndex != -1) {
                    shortestSubarrayLength = Math.min(shortestSubarrayLength, i - cumulativeSumStack.get(candidateIndex).getValue());
                }
            }

            // Return -1 if no valid subarray found
            return shortestSubarrayLength == Integer.MAX_VALUE ? -1 : shortestSubarrayLength;
        }

        // Binary search to find the largest index where cumulative sum is <= target
        private int findCandidateIndex(List<Pair<Long, Integer>> nums, long target) {
            int left = 0, right = nums.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums.get(mid).getKey() <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return right;
        }
    }


    // O(N)
    class SolutionDeque {

        public int shortestSubarray(int[] nums, int targetSum) {
            int n = nums.length;

            // Size is n+1 to handle subarrays starting from index 0
            long[] prefixSums = new long[n + 1];

            // Calculate prefix sums
            for (int i = 1; i <= n; i++) {
                prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
            }

            Deque<Integer> candidateIndices = new ArrayDeque<>();

            int shortestSubarrayLength = Integer.MAX_VALUE;

            for (int i = 0; i <= n; i++) {
                // Remove candidates from front of deque where subarray sum meets target
                while (!candidateIndices.isEmpty() && prefixSums[i] - prefixSums[candidateIndices.peekFirst()] >= targetSum) {
                    // Update shortest subarray length
                    shortestSubarrayLength = Math.min(shortestSubarrayLength, i - candidateIndices.pollFirst());
                }

                // Maintain monotonicity by removing indices with larger prefix sums
                while (!candidateIndices.isEmpty() && prefixSums[i] <= prefixSums[candidateIndices.peekLast()]) {
                    candidateIndices.pollLast();
                }

                // Add current index to candidates
                candidateIndices.offerLast(i);
            }

            // Return -1 if no valid subarray found
            return shortestSubarrayLength == Integer.MAX_VALUE ? -1 : shortestSubarrayLength;
        }
    }


}
