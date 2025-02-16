# QuickSort Algorithm

## Overview

The QuickSort algorithm is a widely-used and efficient sorting algorithm that employs a divide-and-conquer strategy to sort an array. It works by selecting a "pivot" element from the array and partitioning the other elements into two subarrays, according to whether they are less than or greater than the pivot. The subarrays are then sorted recursively.

- **Average Time Complexity**: O(n log n)
- **Worst Case Time Complexity**: O(n²) (this occurs when the pivot selection is consistently poor, such as always picking the smallest or largest element, but this can be mitigated with good pivot choices)
- **Space Complexity**: O(log n) (due to recursive stack space)

QuickSort is often faster in practice than other O(n log n) algorithms like MergeSort and HeapSort, making it a popular choice for large datasets.

## Problem Statement

Given an unsorted array `arr`, the task is to sort the array in ascending order.

## Example

```java
import java.util.Arrays;

public class QuickSort {

    // Function to perform QuickSort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array
            int pivotIndex = partition(arr, low, high);
            
            // Recursively sort the subarrays
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // Partition function to rearrange the array
    private static int partition(int[] arr, int low, int high) {
        // Pivot element is the last element
        int pivot = arr[high];
        int i = low - 1;
        
        // Rearrange elements such that elements smaller than pivot are on the left
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }
        // Place pivot element in the correct position
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Utility function to swap two elements in the array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 4};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
```
