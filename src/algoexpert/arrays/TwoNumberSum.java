package algoexpert.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class TwoNumberSum {

    public static int[] twoNumberSum(int[] array, int targetSum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<array.length; i++) {
            int a= array[i];
            int diff = targetSum - a;
            if(map.containsKey(diff)) {
                return new int[]{diff, a};
            }
            map.put(a, i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] a = {3, 5, -4, 8, 11, 1, -1, 6};
        System.out.println(Arrays.toString(twoNumberSum(a, 10)));
    }
}
