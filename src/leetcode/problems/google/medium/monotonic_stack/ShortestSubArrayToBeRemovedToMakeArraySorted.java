package leetcode.problems.google.medium.monotonic_stack;

public class ShortestSubArrayToBeRemovedToMakeArraySorted {


    /*
        Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.

        Return the length of the shortest subarray to remove.

        A subarray is a contiguous subsequence of the array.


        Example 1:
        Input: arr = [1,2,3,10,4,2,3,5]
        Output: 3
        Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
        Another correct solution is to remove the subarray [3,10,4].

        Example 2:
        Input: arr = [5,4,3,2,1]
        Output: 4
        Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].

        Example 3:
        Input: arr = [1,2,3]
        Output: 0
        Explanation: The array is already non-decreasing. We do not need to remove any elements.
    */
    class Solution {
        public int findLengthOfShortestSubarray(int[] arr) {
            int n = arr.length;
            int l = 0;
            // Find non-decreasing sub array from left side including left most element
            while (l < (n - 1) && arr[l] <= arr[l + 1]) {
                l++;
            }

            // if non-decreasing sub array from left side went all the way to the right,
            // then whole array is already non-decreasing.
            // return 0 cause nothing needs to be deleted
            if (l == n - 1)
                return 0;

            // Find non-decreasing sub array from right side including right most element
            int r = n - 1;
            while (r > 0 && arr[r] >= arr[r - 1]) {
                r--;
            }

            // Connect two subarrays and find the "connecting" elements that make both
            // subarrays on left and right non-decreasnig when combined
    /*
                                              /
                                            /
                                          /
               /                / --       /
            /    L  \         /      \__ /
         /           \ ___ /          R
      /
      i                             j

      You need to "connect" i and j pointers


                                             /
                                            /
                                          /
                                         /
                                    __ /
         /                          R
      /
      i                             j

      All the elements in the middle would be removed
      to make remaining sub array non-decreasing

      Use two pointer approach i, j
      i++ when arr[i] <= arr[j]
      j++ when arr[i] > arr[j]
    */
            int result = Math.min(n - l - 1, r);
            int i = 0;
            while (i <= l && r < n) {
                if (arr[r] >= arr[i]) {
                    result = Math.min(result, r - i - 1);
                    i++;
                } else {
                    r++;
                }
            }

            return result;
        }
    }
}