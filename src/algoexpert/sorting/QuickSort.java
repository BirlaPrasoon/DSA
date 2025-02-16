package algoexpert.sorting;

import java.util.Arrays;

public class QuickSort {
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int partitionIndex = low;
        for(int i = low; i<high; i++) {
            if(arr[i] < pivot) {
                swap(arr, partitionIndex, i);
                partitionIndex++;
            }
        }
        swap(arr, partitionIndex , high);
        return partitionIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot-1);
            quickSort(arr, pivot +1, high);
        }
    }

    public static int[] quickSort(int[] array) {
        quickSort(array, 0, array.length -1);
        return array;
    }

    public static void main(String[] args) {
        int[] expected = {2, 3, 5, 5, 6, 8, 9};
        int[] input = {8, 5, 2, 9, 5, 6, 3};
        System.out.println(Arrays.toString(QuickSort.quickSort(input)));
    }
}
