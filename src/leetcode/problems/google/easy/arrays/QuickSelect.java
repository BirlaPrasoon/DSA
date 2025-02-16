package leetcode.problems.google.easy.arrays;

import java.util.Arrays;

public class QuickSelect {

    static int partition(int[] a, int left, int right) {
        int pivot = a[right];
        int start = left;
        for(int j = left; j<=right; j++) {
            if(a[j] < pivot) {
                swap(a, j,  start);
                start++;
            }
        }
        swap(a, right, start);
        return start;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j]=t;
    }

    private static int kthLargest(int[] a, int low, int high, int k) {
        int partition = partition(a, low, high);
        if(partition == k-1) return a[partition];
        else if(partition < k -1) {
            return kthLargest(a, partition+1, high, k);
        } else {
            return kthLargest(a, low, partition - 1, k);
        }
    }

    public static void main(String[] args)
    {
        int[] arraycopy = new int[] { -1,-2,3,4 };
        int kPosition = 3;
        if (kPosition > arraycopy.length) {
            System.out.println("Index out of bound");
        } else {
            System.out.println(
                    "K-th smallest element in array : "
                            + kthLargest(arraycopy, 0, arraycopy.length - 1,
                            kPosition));
            System.out.println("Array: " + Arrays.toString(arraycopy));
        }
    }
}
