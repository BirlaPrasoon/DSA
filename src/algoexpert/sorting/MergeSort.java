package algoexpert.sorting;

import java.util.Arrays;

public class MergeSort {

    public static int[] mergeSort(int[] array) {
        int[] ans = new int[array.length];
        mergeSort(array, 0, array.length - 1, ans);
        return array;
    }

    private static void mergeSort(int[] arr, int low, int high, int[] ans) {
        if(low >= high) return;
        int mid = low  + (high - low) / 2;
        mergeSort(arr, low, mid, ans);
        mergeSort(arr, mid+1, high, ans);
        int i = low, j = mid+1, k = low;
        while(i<= mid && j<= high) {
            if(arr[i] < arr[j]) {
                ans[k++] = arr[i++];
            } else {
                ans[k++] = arr[j++];
            }
        }
        while(i <= mid) {
            ans[k++] = arr[i++];
        }
        while(j <= high) {
            ans[k++] = arr[j++];
        }

        if (high + 1 - low >= 0) System.arraycopy(ans, low, arr, low, high + 1 - low);
    }

    public static void main(String[] args) {
        int[] a = new int[]{8, 5, 2, 9, 5, 6, 3};
        System.out.println(Arrays.toString(mergeSort(a)));
    }
}
