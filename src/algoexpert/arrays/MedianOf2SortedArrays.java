package algoexpert.arrays;

public class MedianOf2SortedArrays {

    // function to find median of two sorted arrays
    static double medianOf2(int[] arr1, int[] arr2) {
        int n = arr1.length, m = arr2.length;
        System.out.println("m: " + m +" n:" + n);

        // If arr1 has more elements, then call medianOf2
        // with reversed parameters
        if (n > m) {
            System.out.println("Swapping arrays");
            return medianOf2(arr2, arr1);
        }

        int low = 0, high = n;
        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = (n + m + 1) / 2 - mid1;
            System.out.println("mid1: " + mid1 + " mid2: " + mid2);

            // Find elements to the left and right of partition in arr1
            int l1 = (mid1 == 0 ? Integer.MIN_VALUE : arr1[mid1 - 1]);
            int r1 = (mid1 == n ? Integer.MAX_VALUE : arr1[mid1]);

            // Find elements to the left and right of partition in arr2
            int l2 = (mid2 == 0 ? Integer.MIN_VALUE : arr2[mid2 - 1]);
            int r2 = (mid2 == m ? Integer.MAX_VALUE : arr2[mid2]);

            System.out.println("l1: " + l1 + " r1: " + r1);
            System.out.println("l2: " + l2 + " r2: " + r2);

            // If it is a valid partition
            if (l1 <= r2 && l2 <= r1) {
                System.out.println("is Valid partition");
                // If the total elements are even, then median is
                // the average of two middle elements
                if ((n + m) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }

                // If the total elements are odd, then median is
                // the middle element
                else {
                    return Math.max(l1, l2);
                }
            }

            // Check if we need to take lesser elements from arr1
            if (l1 > r2) {
                System.out.println("Moving left in X");
                high = mid1 - 1;
            }

            // Check if we need to take more elements from arr1
            else {
                System.out.println("Moving right in X");
                low = mid1 + 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,3,8,9,15};
        int[] arr2 = {7, 11, 18, 19, 21, 25};

        System.out.println(medianOf2(arr1, arr2));
    }
}
