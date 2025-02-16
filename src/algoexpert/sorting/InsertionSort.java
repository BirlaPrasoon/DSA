package algoexpert.sorting;

import java.util.Arrays;

public class InsertionSort {
    public static int[] insertionSort(int[] array) {
        for(int i= 0; i<array.length; i++) {
            int minIndex = findMinElementIndex(array, i);
            swap(array, i, minIndex);
        }
        return array;
    }

    private static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int findMinElementIndex(int[] array, int startIndex) {
        int minIndex = startIndex;
        for(int i = startIndex; i<array.length; i++) {
            if(array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    public static void main(String[] args) {
        int[] a = {8, 5, 2, 9, 5, 6, 3};
        a = insertionSort(a);
        System.out.println(Arrays.toString(a));
    }
}
