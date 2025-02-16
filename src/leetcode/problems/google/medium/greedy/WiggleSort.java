package leetcode.problems.google.medium.greedy;

import java.util.Arrays;

public class WiggleSort {

    // Follow up is On

    class SolutionGreedy {
        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public void wiggleSort(int[] nums) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (((i % 2 == 0) && nums[i] > nums[i + 1])
                        || ((i % 2 == 1) && nums[i] < nums[i + 1])) {
                    swap(nums, i, i + 1);
                }
            }
        }
    }

    class Solution {
        public void wiggleSort(int[] nums) {
            Arrays.sort(nums);
            for(int i = 1; i<nums.length; i+=2) {
                swap(i, i+1, nums);
            }
            // System.out.println(Arrays.toString(nums));
        }

        void swap(int i, int j, int[] nums) {
            if(i>=nums.length || j>= nums.length) return;
            int a= nums[i];
            nums[i] = nums[j];
            nums[j] = a;
        }

        // 3,5,2,1,6,4
        // 1,2,3,4,5,6
        // 1,3,2,5,4,6
    }

}
