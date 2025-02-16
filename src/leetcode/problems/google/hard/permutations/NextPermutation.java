package leetcode.problems.google.hard.permutations;

public class NextPermutation {
    public static class Solution {

        // 123987653
        public void nextPermutation(int[] digits) {
            int i = digits.length - 2;
            // find the first decreasing element
            while (i >= 0 && digits[i] >= digits[i+1]) {
                i--;
            }

            if (i >= 0) {
                int j = digits.length - 1;
                // find just larger element
                while (digits[j] <= digits[i]) {
                    j--;
                }
                swap(digits, i, j);
            }
            reverse(digits, i + 1);
        }

        private void reverse(int[] nums, int start) {
            int i = start, j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
