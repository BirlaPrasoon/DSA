package leetcode.problems.google.medium.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimizeProductSumOf2Arrays {

    class Solution {
        public int minProductSum(int[] nums1, int[] nums2) {
            // Sort nums1 and nums2 in ascending order.
            Arrays.sort(nums1);
            Arrays.sort(nums2);

            // Initialize sum as 0.
            int ans = 0;
            int n = nums2.length;

            // Iterate over two sorted arrays and calculate the
            // cumulative product sum.
            for (int i = 0; i < n; ++i) {
                // Since we also sort nums2 in ascending order,
                // we need to iterate over nums2 in reverse order.
                ans += nums1[i] * nums2[n - 1 - i];
            }

            return ans;
        }
    }

}
