package leetcode.problems.google.easy.arrays;

import java.util.Arrays;

public class FindSubsequencesOfLengthKWithLargestSum {
    public int[] maxSubsequence(int[] nums, int k) {
        int[] resTemp = Arrays.copyOf(nums, nums.length);
        int l = 0, n = nums.length, r = n - 1;

        int pivotIndex = quickSelect(resTemp, l, r, n - k);
        int pivotValue = resTemp[pivotIndex];

        int countTop = 0;
        for (int i = pivotIndex; i < n; i++) {
            if (resTemp[i] > pivotValue)
                countTop++;
        }
        int countK = k - countTop;

        int[] res = new int[k];
        int j = 0;
        for (int num : nums) {
            if (j == k)
                break;
            if (num == pivotValue && countK > 0) {
                res[j++] = num;
                countK--;
            } else if (num > pivotValue) {
                res[j++] = num;
            }
        }
        return res;

    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right)
            return left;
        int pivotIndex = partition(nums, left, right);
        if (pivotIndex == k) {
            return pivotIndex;
        } else if (pivotIndex < k) {
            return quickSelect(nums, pivotIndex + 1, right, k);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, k);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int l = left, r = right - 1;
        while (l <= r) {
            if (nums[l] < pivot)
                l++;
            else if (nums[r] >= pivot)
                r--;
            else
                swap(nums, l++, r--);
        }
        swap(nums, l, right);
        return l;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
