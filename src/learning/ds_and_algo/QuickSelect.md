# QuickSelect Algorithm

## Overview

The QuickSelect algorithm is a highly efficient selection algorithm to find the `k`-th smallest (or largest) element in an unsorted list. It operates similarly to the well-known QuickSort algorithm but selectively partitions the array only around the target index, allowing it to achieve better performance on average compared to sorting the entire array.

QuickSelect has an **average-case time complexity of O(n)**, which is better than the **O(n log n)** required for sorting-based approaches.

## Problem Statement

Given an unsorted array `arr` and an integer `k`, the task is to find the `k`-th smallest element in the array (1-based index). For example, if `k = 1`, you are looking for the smallest element in the array, and if `k = arr.length`, you are looking for the largest element.

## Key Concepts

1. **Pivot Selection**: QuickSelect, like QuickSort, relies on choosing a pivot element to partition the array. Once the array is partitioned, we know the exact position of the pivot in the sorted array.

2. **Partitioning**: We rearrange elements into two parts: one where all elements are smaller than the pivot and one where all elements are greater. The pivot itself ends up in its correct sorted position.

3. **Recursive or Iterative Search**: Depending on the pivot’s position after partitioning, we either search to the left (for smaller elements) or to the right (for larger elements), effectively reducing the problem size.

## Pseudocode

1. Choose a random pivot.
2. Partition the array into two halves: elements smaller than the pivot and elements larger than the pivot.
3. If the pivot's index matches `k-1`, return the pivot.
4. Recursively or iteratively search the appropriate half.

## Complexity

- **Time Complexity**:
    - **Average Case**: O(n) — The algorithm performs well when the pivot divides the array into reasonably equal parts.
    - **Worst Case**: O(n²) — If the pivot always ends up being the smallest or largest element, reducing the size of the array by only one element in each step (similar to worst-case QuickSort).

- **Space Complexity**:
    - **O(1)** (in-place partitioning).
    - **O(log n)** for recursive calls (stack space).

## Example Usage

### Input:
```bash
arr = [3, 6, 8, 2, 5, 9, 1]
k = 3

The 3rd smallest element is 3.
```

```java
public class QuickSelect {

    // Function to find the k-th smallest element in an unsorted array
    public static int quickSelect(int[] arr, int low, int high, int k) {
        if (low <= high) {
            // Partition the array
            int pivotIndex = partition(arr, low, high);
            
            // If pivot is the k-th element, return its value
            if (pivotIndex == k) {
                return arr[pivotIndex];
            } 
            // If pivot index is greater than k, search in the left part
            else if (pivotIndex > k) {
                return quickSelect(arr, low, pivotIndex - 1, k);
            } 
            // If pivot index is less than k, search in the right part
            else {
                return quickSelect(arr, pivotIndex + 1, high, k);
            }
        }
        return Integer.MAX_VALUE; // This case should not happen if input is valid
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
        int k = 3; // Find the 3rd smallest element (0-based index, so pass k-1)
        int result = quickSelect(arr, 0, arr.length - 1, k - 1);
        System.out.println("The " + k + "-th smallest element is " + result);
    }
}

```
