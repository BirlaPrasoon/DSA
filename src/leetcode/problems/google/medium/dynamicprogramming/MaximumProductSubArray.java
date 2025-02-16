package leetcode.problems.google.medium.dynamicprogramming;

import java.util.Arrays;

public class MaximumProductSubArray {
    // This is a trick question, we need to keep track of both min and max products, and balance out with current element when it comes to 0.
    
    class Solution {
        public int maxProduct(int[] nums) {

            int max = nums[0], min = nums[0], ans = nums[0];

            for (int i = 1; i < nums.length; i++) {

                int temp = max;  // store the max because before updating min your max will already be updated

                max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
                min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);

                if (max > ans) {
                    ans = max;
                }
            }

            return ans;

        }
    }
}
