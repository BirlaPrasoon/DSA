package algoexpert.sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SortKSortedArray {

    public int[] sortKSortedArray(int[] array, int k) {
        if(k == 0 || array.length <= 1 ) return array;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // Atmost k distance away from index so we need to consider till i + k + 1 not i+k
        // k = 2 means 0th index element can be at 2nd index. 0 1 2.
        // I made the mistake of choosing i < k.

        // Question says at most k not less than k.
        for(int i = 0; i<=k && i < array.length; i++) {
            pq.add(array[i]);
        }
        for(int i = 0; i<array.length; i++) {
            if(pq.size() > 0) {
                array[i] = pq.poll();
                if (i + k + 1 < array.length)
                    pq.add(array[i + k + 1]);
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a = new int[]{-1, -3, -4, 2, 1, 3};
        SortKSortedArray s = new SortKSortedArray();
        // Other solution was to make k = k+1 that will handle the case. Please redo this eq
        System.out.println(Arrays.toString(s.sortKSortedArray(a, 2)));
    }
}
