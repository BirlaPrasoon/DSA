package algoexpert.arrays;

import java.util.Arrays;
import java.util.Stack;

public class SubarraySort {

    public static int[] nse(int[] a) {
        int[] nse = new int[a.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = a.length-1; i>=0; i--){
            while(!stack.isEmpty() && a[stack.peek()] >= a[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                nse[i] = i;
            } else {
                nse[i] = stack.peek();
            }
            stack.push(i);
        }
        return nse;
    }

    public static int[] nge(int[] a) {
        int[] nge = new int[a.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i< a.length; i++){
            while(!stack.isEmpty() && a[stack.peek()] <= a[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                nge[i] = i;
            } else {
                nge[i] = stack.peek();
            }
            stack.push(i);
        }
        return nge;
    }

    public static int[] subarraySort(int[] a) {
        int[] nge = nge(a);
        int[] nse = nse(a);
        System.out.println("NGE: "+ Arrays.toString(nge));
        System.out.println("NSE: " + Arrays.toString(nse));
        int left = -1;
        int right = -1;
        for(int i =a.length-1; i>=0; i--) {
            if(nge[i] != i){
                right = i;
                break;
            }
        }
        for(int i = 0; i<a.length; i++) {
            if(nse[i] != i){
                left = i;
                break;
            }
        }
        return new int[]{left, right};
    }

    public static void main(String[] args) {
        int[]x = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 2};
        System.out.print("IND: [");
        for (int i = 0; i < x.length; i++) {
            if(i != x.length-1) {
                System.out.print(i + ", ");
            } else {
                System.out.print(i);
            }
        }
        System.out.println("]");
        System.out.println(Arrays.toString(subarraySort(x)));
    }

}
