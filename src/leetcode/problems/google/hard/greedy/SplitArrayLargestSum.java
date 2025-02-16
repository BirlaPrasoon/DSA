package leetcode.problems.google.hard.greedy;

public class SplitArrayLargestSum {

    class BinarySearchSolution {
        private int minimumSubarraysRequired(int[] nums, int maxSumAllowed) {
            int currentSum = 0;
            int splitsRequired = 0;

            for (int element : nums) {
                // Add element only if the sum doesn't exceed maxSumAllowed
                if (currentSum + element <= maxSumAllowed) {
                    currentSum += element;
                } else {
                    // If the element addition makes sum more than maxSumAllowed
                    // Increment the splits required and reset sum
                    currentSum = element;
                    splitsRequired++;
                }
            }

            // Return the number of subarrays, which is the number of splits + 1
            return splitsRequired + 1;
        }

        /// Time complexity: O(N⋅log(S))
        public int splitArray(int[] nums, int m) {
            // Find the sum of all elements and the maximum element
            int sum = 0;
            int maxElement = Integer.MIN_VALUE;
            for (int element : nums) {
                sum += element;
                maxElement = Math.max(maxElement, element);
            }

            // Define the left and right boundary of binary search
            int left = maxElement;
            int right = sum;
            int minimumLargestSplitSum = 0;
            while (left <= right) {
                // Find the mid value
                int maxSumAllowed = left + (right - left) / 2;

                // Find the minimum splits. If splitsRequired is less than
                // or equal to m move towards left i.e., smaller values
                if (minimumSubarraysRequired(nums, maxSumAllowed) <= m) {
                    right = maxSumAllowed - 1;
                    minimumLargestSplitSum = maxSumAllowed;
                } else {
                    // Move towards right if splitsRequired is more than m
                    left = maxSumAllowed + 1;
                }
            }

            return minimumLargestSplitSum;
        }
    }

    class SolutionRecursiveDP {
        // Defined it as per the maximum size of array and split count
        // But can be defined with the input size as well
        Integer[][] memo = new Integer[1001][51];

        private int getMinimumLargestSplitSum(int[] prefixSum, int currIndex, int subarrayCount) {
            int n = prefixSum.length - 1;

            // We have already calculated the answer so no need to go into recursion
            if (memo[currIndex][subarrayCount] != null) {
                return memo[currIndex][subarrayCount];
            }

            // Base Case: If there is only one subarray left, then all of the remaining numbers
            // must go in the current subarray. So return the sum of the remaining numbers.
            if (subarrayCount == 1) {
                return memo[currIndex][subarrayCount] = prefixSum[n] - prefixSum[currIndex];
            }

            // Otherwise, use the recurrence relation to determine the minimum largest subarray
            // sum between currIndex and the end of the array with subarrayCount subarrays remaining.
            int minimumLargestSplitSum = Integer.MAX_VALUE;
            for (int i = currIndex; i <= n - subarrayCount; i++) {
                // Store the sum of the first subarray.
                int firstSplitSum = prefixSum[i + 1] - prefixSum[currIndex];

                // Find the maximum subarray sum for the current first split.
                int largestSplitSum = Math.max(firstSplitSum,
                        getMinimumLargestSplitSum(prefixSum, i + 1, subarrayCount - 1));

                // Find the minimum among all possible combinations.
                minimumLargestSplitSum = Math.min(minimumLargestSplitSum, largestSplitSum);

                if (firstSplitSum >= minimumLargestSplitSum) {
                    break;
                }
            }

            return memo[currIndex][subarrayCount] = minimumLargestSplitSum;
        }

        public int splitArray(int[] nums, int m) {
            // Store the prefix sum of nums array.
            int n = nums.length;
            int[] prefixSum = new int[n + 1];

            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            return getMinimumLargestSplitSum(prefixSum, 0, m);
        }
    }
}
