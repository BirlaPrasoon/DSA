package leetcode.problems.google.hard.arrays;

public class SplitSubarrayLargestSum {

    public int splitArray(int[] nums, int m) {
        // Step 1: Initialize the boundaries for binary search
        int left = 0, right = 0;
        for (int num : nums) {
            left = Math.max(left, num);  // max element in the array
            right += num;                // sum of all elements in the array
        }

        // Step 2: Perform binary search
        while (left < right) {
            int mid = left + (right - left) / 2;  // middle value

            // Step 3: Greedily check if we can split into m parts with max sum <= mid
            if (canSplit(nums, m, mid)) {
                right = mid;  // It's possible, try a smaller largest sum
            } else {
                left = mid + 1;  // Not possible, increase the largest sum
            }
        }
        return left;
    }

    // Helper function to check if we can split nums into <= m subarrays with each sum <= target
    private boolean canSplit(int[] nums, int m, int target) {
        int subarrays = 1;  // Start with one subarray
        int currentSum = 0;  // Sum of the current subarray

        for (int num : nums) {
            // Check if adding this element would exceed the target sum
            if (currentSum + num > target) {
                subarrays++;  // Need a new subarray
                currentSum = num;  // Start the new subarray with the current element

                // If we already need more than m subarrays, return false
                if (subarrays > m) {
                    return false;
                }
            } else {
                currentSum += num;  // Add the current element to the current subarray
            }
        }

        return true;  // It's possible to split into m or fewer subarrays
    }
}
