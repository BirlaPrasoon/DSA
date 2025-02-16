package leetcode.problems.google.medium.binarySearch;

import java.util.*;

public class BinarySearchExperiments {

    public static void main(String[] args) {
        BinarySearchExperiments binarySearchExperiments = new BinarySearchExperiments();
        FirstAndLastIndex firstAndLastIndex = new FirstAndLastIndex();
        int n = 5;
        int arr[] = binarySearchExperiments.getSortedArray(n);

        HashSet<Integer> set = new HashSet<>();
        arr = new int[]{12, 38, 156, 165, 165, 165, 192};
        System.out.println(Arrays.toString(arr));
        int index = upper_bound(arr, 200);
        System.out.println("Indexes: " + index);

//        if(index < 0) index = ~index;
        System.out.println("Index: " + index + " element: " + arr[index]);

    }

    static int lower_bound(int array[], int key) {
        // Initialize starting index and
        // ending index
        int low = 0, high = array.length;
        int mid;

        // Till high does not crosses low
        while (low < high) {

            // Find the index of the middle element
            mid = low + (high - low) / 2;

            // If key is less than or equal
            // to array[mid], then find in
            // left subarray
            if (key <= array[mid]) {
                high = mid;
            }

            // If key is greater than array[mid],
            // then find in right subarray
            else {
                low = mid + 1;
            }
        }

        // If key is greater than last element which is
        // array[n-1] then lower bound
        // does not exists in the array
        if (low < array.length && array[low] < key) {
            low++;
        }

        // Returning the lower_bound index
        return low;
    }

    static int binarySearch(int a[], int target) {
        int n = a.length;
        int left = 0, right = n - 1;
        if (n == 0) return -1;
        if (n == 1) {
            if (a[0] == target) return 0;
            else return -1;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (a[mid] == target) return mid;

            if (a[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    static int upper_bound(int arr[], int key) {
        int mid, N = arr.length;

        // Initialise starting index and
        // ending index
        int low = 0;
        int high = N;

        // Till low is less than high
        while (low < high) {
            // Find the index of the middle element
            mid = low + (high - low) / 2;

            // If key is greater than or equal
            // to arr[mid], then find in
            // right subarray
            if (key >= arr[mid]) {
                low = mid + 1;
            }

            // If key is less than arr[mid]
            // then find in left subarray
            else {
                high = mid;
            }
        }

        // If key is greater than last element which is
        // array[n-1] then upper bound
        // does not exists in the array
        if (low == N) {
            System.out.print("The upper bound of " + key + " does not exist.");
            return low;
        }

        // Print the upper_bound index
        System.out.print("The upper bound of " + key + " is " + arr[low] + " at index " + low);
        return low;
    }


    public int[] getSortedArray(int size) {
        int min = 0, max = 250;
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(min, max + 1);
        }
        Arrays.sort(arr);
        reverse(arr);
        return arr;
    }

    private void reverse(int a[]) {
        int left = 0, right = a.length;
        while(left < right) {
            int t = a[left];
            a[left] = a[right];
            a[right] = t;
            left++;
            right--;
        }
    }

}
