package leetcode.problems.google.hard.arrays;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class LargestAreaInAHistogram {
    static int[] nextSmaller(int[] hist) {
        int n = hist.length;
        int[] nextS = new int[n];
        for (int i = 0; i < n; i++) nextS[i] = n;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && hist[i] < hist[st.peek()]) nextS[st.pop()] = i;
            st.push(i);
        }
        return nextS;
    }

    static int[] prevSmaller(int[] hist) {
        int n = hist.length;
        int[] prevS = new int[n];
        for (int i = 0; i < n; i++) prevS[i] = -1;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && hist[i] < hist[st.peek()]) st.pop();
            if (!st.isEmpty()) prevS[i] = st.peek();
            st.push(i);
        }
        return prevS;
    }

    static int getMaxArea(int[] hist) {
        int[] prevS = prevSmaller(hist);
        int[] nextS = nextSmaller(hist);
        int maxArea = 0;

        for (int i = 0; i < hist.length; i++) {
            int width = nextS[i] - prevS[i] - 1; // current element is counted twice.
            int area = hist[i] * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] hist = {60, 20, 50, 40, 10, 50, 60};
        System.out.println(getMaxArea(hist));
    }
}
