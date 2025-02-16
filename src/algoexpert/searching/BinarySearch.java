package algoexpert.searching;

import java.util.Arrays;

public class BinarySearch {

    public static int binarySearch(int[] array, int target) {
        int index = Arrays.binarySearch(array, target);
        return index >= 0? index : -1;
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 21, 33, 45, 45, 61, 71, 72, 73};
        System.out.println(binarySearch(a, 65));
    }
}
