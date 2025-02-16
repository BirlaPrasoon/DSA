package leetcode.problems.google.medium.arrays;

public class NumberOfSubarraysOfSizeKWithAverageGreaterThanOrEqualToThreshold {
    // Java implementation
    class Solution {
        public int numOfSubarrays(int[] arr, int k, int threshold) {
            int len = arr.length;
            int sum = 0;
            int count = 0;

            // Calculate the sum of the first k elements
            for (int i = 0; i < k; i++) {
                sum += arr[i];
            }

            // Check if the first window meets the threshold
            if (sum / k >= threshold) {
                count++;
            }

            // Slide the window and check each subsequent window
            for (int i = k; i < len; i++) {
                sum += arr[i] - arr[i - k]; // Slide window by adding next element and subtracting first element of the window
                if (sum / k >= threshold) {
                    count++;
                }
            }

            return count;
        }
    }
}
