package algoexpert.arrays;

import java.util.Arrays;

public class MinimumNumberOfIncrementsToFormTargetArray {

    // Bruteforce
//    public int minNumberOperations(int[] target) {
//        int n = target.length;
//        int[] arr = new int[n];  // Start with a zero array
//        int operations = 0;
//
//        while (!Arrays.equals(arr, target)) {
//            int i = 0;
//            while (i < n && arr[i] >= target[i]) {
//                i++;
//            }
//            System.out.println(Arrays.toString(arr));
//            if (i == n) break;
//            while (i < n && arr[i] < target[i]) {
//                arr[i]++;
//                i++;
//            }
//            operations++;
//        }
//
//        return operations;
//    }

    // Optimal - Observation based,
    public int minNumberOperations(int[] target) {
        int operations = target[0];  // Start with the first element

        // Loop through the target array from the second element
        for (int i = 1; i < target.length; i++) {
            if (target[i] > target[i - 1]) {
                // Add the difference if current is greater
                operations += target[i] - target[i - 1];
            }
        }

        return operations;
    }

    public static void main(String[] args) {
        int a[] = {3,1,1,2};
        MinimumNumberOfIncrementsToFormTargetArray m = new MinimumNumberOfIncrementsToFormTargetArray();
        System.out.println(m.minNumberOperations(a));
    }
}
