package algoexpert.arrays;

import java.util.Arrays;
import java.util.Stack;

public class LongestPark {
    public int largestPark(boolean[][] land) {
        int R = land.length;
        int C = land[0].length;
        int[][] l = new int[R][C];
        for(int i = 0; i<R; i++) {
            for(int j = 0; j<C; j++) {
                l[i][j] = land[i][j] ? 0: 1;
            }
        }
        int maxArea = maxAreaInAHistogram(l[0]);
        for(int i = 1; i<l.length; i++) {
            for(int j = 0; j<l[i].length; j++) {
                // This is necessary, if we add to 0 this will break.
                if(l[i][j] != 0) {
                    l[i][j] += l[i - 1][j];
                }
            }
            int area = maxAreaInAHistogram(l[i]);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static int maxAreaInAHistogram(int[] a) {
        int[] nsel = new int[a.length];
        int[] nser = new int[a.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i<a.length; i++) {
            while(!stack.isEmpty() && a[stack.peek()] >= a[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                nsel[i] = -1;
            } else {
                nsel[i] = stack.peek();
            }
            stack.push(i);
        }
        stack.clear();
        for(int i = a.length-1; i>=0; i--) {
            while (!stack.isEmpty() && a[stack.peek()] >= a[i]) {
                stack.pop();
            }
            if(stack.isEmpty()){
                nser[i] = -1;
            } else {
                nser[i] = stack.peek();
            }
            stack.push(i);
        }
        int maxArea = 0;
        for(int i = 0; i<a.length; i++) {
            int left = nsel[i] >= 0 ?  i - nsel[i] : i+1;
            int right = nser[i] >= 0 ? nser[i] - i : a.length-i;
            int area = a[i] * (left + right -1);
            maxArea = Math.max(area, maxArea);
        }
        System.out.println("Max Are: "+ maxArea);
        return maxArea;
    }

    public static int kadanesAlgorithm(int[] array) {
        int sumEndingHere = 0, maxSumSoFar = Integer.MIN_VALUE;
        for(int i = 0; i<array.length; i++) {
            sumEndingHere += array[i];
            if(maxSumSoFar < sumEndingHere) {
                maxSumSoFar = sumEndingHere;
            }
            if(sumEndingHere < 0) {
                sumEndingHere = 0;
            }
        }
        return maxSumSoFar;
    }

    public static void main(String[] args) {
        boolean[][] land = new boolean[][] {
                {true, true, false, true, false},
                {false, true, false, false, false},
                {true, false, false, false, false},
                {false, true, true, false, true}
        };
        int expected = 6;
        int actual = new LongestPark().largestPark(land);
        System.out.println(actual);
        System.out.println(expected == actual);
    }
}
