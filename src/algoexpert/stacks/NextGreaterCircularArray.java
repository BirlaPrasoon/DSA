package algoexpert.stacks;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterCircularArray {

    // This is a good question, though I solved it in first go.
    public int[] nextGreaterElement(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int temp[] = new int[array.length * 2];
        System.arraycopy(array, 0, temp, 0, array.length);
        System.arraycopy(array, 0, temp, array.length, array.length);
        int[] nge = new int[array.length * 2];
        for(int i = temp.length-1; i>=0; i--) {
            while(!stack.isEmpty() && stack.peek() <= temp[i]){
                stack.pop();
            }
            if(stack.isEmpty()) {
                nge[i] = -1;
            } else {
                nge[i] = stack.peek();
            }
            stack.push(temp[i]);
        }
        // Write your code here.
        int nge2[] = new int[array.length];
        System.arraycopy(nge, 0, nge2, 0, array.length);
        return nge2;
    }

    public static void main(String[] args) {
        int a[] = new int[]{2, 5, -3, -4, 6, 7, 2};
        NextGreaterCircularArray ng = new NextGreaterCircularArray();
        System.out.println(Arrays.toString(ng.nextGreaterElement(a)));
    }

}
