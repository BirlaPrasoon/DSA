package leetcode.problems.google.medium.depth_first_search.backtracking;

public class CountIntversions {
    // This function merges two sorted subarrays
    // arr[l...m] and arr[m+1 .. r] and also counts
    // inversions in the whole subarray arr[l..r]
    static long countAndMerge(long[] arr, int l, int m, int r) {
        // Counts in two subarrays
        int n1 = m - l + 1, n2 = r - m;

        // Set up two arrays for left and right halves
        long[] left = new long[n1];
        long[] right = new long[n2];
        for (int i = 0; i < n1; i++)
            left[i] = arr[i + l];
        for (int j = 0; j < n2; j++)
            right[j] = arr[m + 1 + j];

        // Initialize inversion count (or result)
        // and merge two halves
        long res = 0;
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {

            // No increment in inversion count
            // if left[] has a smaller or equal element
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
                // all elements in left are smaller than current element in right, so add all.
                res += (n1 - i);
            }
        }

        // Merge remaining elements
        while (i < n1)
            arr[k++] = left[i++];
        while (j < n2)
            arr[k++] = right[j++];

        return res;
    }

    // Function to count inversions in the array
    static long countInv(long[] arr, int l, int r) {
        if (l >= r) return 0;

        long res = 0;
        int m = (r + l) / 2;

        // Recursively count inversions
        // in the left and right halves
        res += countInv(arr, l, m);
        res += countInv(arr, m + 1, r);

        // Count inversions such that greater
        // element is in the left half and
        // smaller in the right half
        res += countAndMerge(arr, l, m, r);
        return res;
    }

    public static void main(String[] args) {
        long[] arr = { 4, 3, 2, 1 };
        int n = arr.length;
        System.out.println(countInv(arr, 0, n - 1));
    }
}
