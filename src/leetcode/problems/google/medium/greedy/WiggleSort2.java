package leetcode.problems.google.medium.greedy;

import java.util.Arrays;

public class WiggleSort2 {
/*
    Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

    You may assume the input array always has a valid answer.



    Example 1:

    Input: nums = [1,5,1,1,6,4]
    Output: [1,6,1,5,1,4]
    Explanation: [1,4,1,5,1,6] is also accepted.
    Example 2:

    Input: nums = [1,3,2,2,3,1]
    Output: [2,3,1,3,1,2]


    Constraints:

    1 <= nums.length <= 5 * 104
    0 <= nums[i] <= 5000
    It is guaranteed that there will be an answer for the given input nums.

    Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
    */

    class Solution {
        public void wiggleSort(int[] nums) {
            int n=nums.length-1;
            //copy values to new array
            int[] newarr=Arrays.copyOf(nums,nums.length);
            //sort new array
            Arrays.sort(newarr);
            //old arr=1,5,1,1,6,4
            //new arr=1,1,1,4,5,6
            //now lets apply odd position and even position
            //odd position
            for(int i=1;i<nums.length;i+=2)
                nums[i]=newarr[n--];
            //even position
            for(int i=0;i<nums.length;i+=2)
                nums[i]=newarr[n--];
        }
    }

}
