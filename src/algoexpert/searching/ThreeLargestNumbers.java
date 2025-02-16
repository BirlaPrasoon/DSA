package algoexpert.searching;

import java.util.Arrays;

public class ThreeLargestNumbers {

    public static int[] findThreeLargestNumbers(int[] array) {
        int f = Integer.MIN_VALUE, s = Integer.MIN_VALUE, t = Integer.MIN_VALUE;
        int inf = -1, ins = -1, ink = -1;
        for(int i = 0; i<array.length; i++) {
            if(array[i] > f) {
                inf = i;
                f = array[i];
            }
        }
        array[inf] = Integer.MIN_VALUE;
        for(int i = 0; i<array.length; i++) {
            if(array[i] > s) {
                ins = i;
                s = array[i];
            }
        }
        array[ins] = Integer.MIN_VALUE;

        for(int i = 0; i<array.length; i++) {
            if(array[i] > t) {
                ink = i;
                t = array[i];
            }
        }
        array[ink] = Integer.MIN_VALUE;
        int ans[] = new int[]{f,s,t};
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {

    }
}
