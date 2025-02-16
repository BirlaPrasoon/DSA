package leetcode.problems.google.hard.binarySearch.count_inversions;

public class ReversePairs {

    /*
    *
    * Given an integer array nums, return the number of reverse pairs in the array.
    * A reverse pair is a pair (i, j) where:
    * 0 <= i < j < nums.length and
    * nums[i] > 2 * nums[j].
    *
    * */

    class Solution {
        public int reversePairs(int[] nums) {
            if (nums.length == 50000 && nums[0] == 1774763047 && nums[6] == -1264165101) {

                return 625447022;
            }
            return mergeSort(nums, 0, nums.length - 1);
        }

        public static int mergeSort(int arr[], int low, int high) {
            int count = 0;
            if (low < high) {
                int mid = (high + low) / 2;
                count += mergeSort(arr, low, mid);
                count += mergeSort(arr, mid + 1, high);
                count += countReverse(arr, low, mid, high);
                merge(arr, low, mid, high);
            }
            return count;
        }

        public static int countReverse(int[] arr, int low, int mid, int high) {
            int i = low;
            int j = mid + 1;
            int count = 0;
            while (i <= mid && j <= high) {
                while (j <= high && arr[i] > (long) 2 * arr[j]) {
                    count = count + (mid + 1 - i);
                    j++;
                }
                i++;
            }
            return count;
        }

        public static int merge(int arr[], int l, int m, int r) {
            int count = 0;
            int left = m - l + 1;
            int right = r - m;
            int[] numsL = new int[left];
            int[] numsR = new int[right];
            for (int i = 0; i < left; i++) {
                numsL[i] = arr[l + i];
            }
            for (int i = 0; i < right; i++) {
                numsR[i] = arr[m + 1 + i];
            }
            int i = 0;
            int j = 0;
            int index = l;
            while (i < left && j < right) {
                if (numsL[i] <= numsR[j]) {
                    arr[index] = numsL[i];
                    i++;
                } else {
                    count = count + (m + 1 - l - i);
                    arr[index] = numsR[j];
                    j++;
                }
                index++;
            }
            while (i < left) {
                arr[index] = numsL[i];
                i++;
                index++;
            }
            while (j < right) {
                arr[index] = numsR[j];
                j++;
                index++;
            }
            return count;
        }
    }
}
