package algoexpert.arrays;

import java.util.Arrays;

public class SortedSquaredArrays {

    public int[] sortedSquaredArray(int[] array) {
        int[] a = new int[array.length];
        int k = 0, j = array.length-1;
        for(int i = array.length-1; i>=0; i--) {
            if(Math.abs(array[k]) > Math.abs(array[j])) {
                a[i] = array[k] * array[k];
                k++;
            } else {
                a[i] = array[j] * array[j];
                j--;
            }
        }
        return a;
    }


    // This is good, but this is not the most optimal one. It should have been clear
//    public int[] sortedSquaredArray(int[] array) {
//        int[] a = new int[array.length];
//        for(int i = 0; i<array.length; i++) {
//            a[i] = array[i] * array[i];
//        }
//        Arrays.sort(a);
//        return a;
//    }

    public static void main(String[] args) {
        int[] a = {-12, 2, 3, 5, 6, 8, 9};
        SortedSquaredArrays sq = new SortedSquaredArrays();
        System.out.println(Arrays.toString(sq.sortedSquaredArray(a)));
    }
}
