package algoexpert.sorting;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class ThreeNumberSort {
    public int[] threeNumberSort(int[] array, int[] order) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for(int a: order){
            map.put(a, 0);
        }
        for (int j : array) {
            map.put(j, map.get(j) + 1);
        }
        int k = 0;
        int i = 0;
        while(k < order.length) {
            while (map.get(order[k]) > 0) {
                array[i++] = order[k];
                map.put(order[k], map.get(order[k]) -1);
            }
            k++;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, 0, 0, -1, -1, 0, 1, 1};
        int[] order = new int[] {0, 1, -1};
        int[] expected = new int[] {0, 0, 0, 1, 1, 1, -1, -1};
        int[] actual = new ThreeNumberSort().threeNumberSort(array, order);
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Actual: " + Arrays.toString(actual));
    }
}
