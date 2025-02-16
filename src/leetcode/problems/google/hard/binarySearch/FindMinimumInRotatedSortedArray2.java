package leetcode.problems.google.hard.binarySearch;

public class FindMinimumInRotatedSortedArray2 {

    /**
     * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
     * <p>
     * [4,5,6,7,0,1,4] if it was rotated 4 times.
     * [0,1,4,4,5,6,7] if it was rotated 7 times.
     * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
     * <p>
     * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
     * <p>
     * You must decrease the overall operation steps as much as possible.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,3,5]
     * Output: 1
     * Example 2:
     * <p>
     * Input: nums = [2,2,2,0,1]
     * Output: 0
     * <p>
     * <p>
     * Constraints:
     * <p>
     * n == nums.length
     * 1 <= n <= 5000
     * -5000 <= nums[i] <= 5000
     * nums is sorted and rotated between 1 and n times.
     */


    class Solution {
        /*
         *
         * Time complexity: on average O(log base 2 N) where N is the length of the array,
         * since in general it is a binary search algorithm. However, in the worst case where
         * the array contains identical elements (i.e. case #3 nums[pivot]==nums[high]),
         * the algorithm would deteriorate to iterating each element, as a result, the time complexity becomes O(N).
         * */
        public int findMin(int[] nums) {
            int low = 0, high = nums.length - 1;

            while (low < high) {
                int pivot = low + (high - low) / 2;
                if (nums[pivot] < nums[high]) high = pivot;
                else if (nums[pivot] > nums[high]) low = pivot + 1;
                else high -= 1;
            }
            return nums[low];
        }
    }
}

