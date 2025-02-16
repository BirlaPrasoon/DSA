package leetcode.problems.google.hard.arrays;

public class KthElementOf2SortedArrays {

    // arr1:[2,3,6,7,9], arr2:[1,4,8,10]

    // O(k)
    public class FindKthElement {
        public static int find(int[] arr1, int[] arr2, int m, int n, int k) {
            int d = 0, i = 0, j = 0;

            // Traverse both arrays until we reach the k-th element
            while (i < m && j < n) {
                if (arr1[i] < arr2[j]) {
                    d++;
                    if (d == k)
                        return arr1[i];
                    i++;
                } else {
                    d++;
                    if (d == k)
                        return arr2[j];
                    j++;
                }
            }

            // If arr2 is completely traversed
            while (i < m) {
                d++;
                if (d == k)
                    return arr1[i];
                i++;
            }

            // If arr1 is completely traversed
            while (j < n) {
                d++;
                if (d == k)
                    return arr2[j];
                j++;
            }

            // Return -1 if k is out of the bounds of the merged array
            return -1;
        }

        public static void main(String[] args) {
            int[] A = {2, 3, 6, 7, 9};
            int[] B = {1, 4, 8, 10};
            int k = 5;
            int m = A.length;
            int n = B.length;

            System.out.println(find(A, B, m, n, k));
        }
    }


    // O(log(k) + log(m))
    public class KthElement {
        public static int kth(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2, int k) {
            if (start1 == end1) {
                return arr2[start2 + k];
            }
            if (start2 == end2) {
                return arr1[start1 + k];
            }
            int mid1 = (start1 + end1) / 2;
            int mid2 = (start2 + end2) / 2;
            if (mid1 - start1 + mid2 - start2 < k) {
                if (arr1[mid1] > arr2[mid2]) {
                    return kth(arr1, start1, end1, arr2, mid2 + 1, end2, k - (mid2 - start2) - 1);
                } else {
                    return kth(arr1, mid1 + 1, end1, arr2, start2, end2, k - (mid1 - start1) - 1);
                }
            } else {
                if (arr1[mid1] > arr2[mid2]) {
                    return kth(arr1, start1, mid1, arr2, start2, end2, k);
                } else {
                    return kth(arr1, start1, end1, arr2, start2, mid2, k);
                }
            }
        }

        public static void main(String[] args) {
            int[] arr1 = {2, 3, 6, 7, 9};
            int[] arr2 = {1, 4, 8, 10};
            int k = 5;
            System.out.println(kth(arr1, 0, arr1.length, arr2, 0, arr2.length, k - 1));
        }
    }



}
