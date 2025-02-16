package algoexpert.stacks;

import java.util.Stack;

public class MinMaxStackConstruction {

    // This can be done using one single stack as well.
    // Stack<int[]> int[0] = value, int[1] = min, int[2] = max;
//    static class MinMaxStack {
//        Stack<Integer> minStack = new Stack<>();
//        Stack<Integer> stack = new Stack<>();
//        Stack<Integer> maxStack = new Stack<>();
//        public int peek() {
//            return stack.peek();
//        }
//
//        public int pop() {
//            int popped = stack.pop();
//            if(minStack.peek() == popped) {
//                minStack.pop();
//            }
//            if(maxStack.peek() == popped)
//                maxStack.pop();
//            return popped;
//        }
//
//        public void push(Integer number) {
//            if(minStack.isEmpty() || number <= minStack.peek()) {
//                minStack.push(number);
//            }
//            if(maxStack.isEmpty() || number >= maxStack.peek()) {
//                maxStack.push(number);
//            }
//            stack.push(number);
//        }
//
//        public int getMin() {
//            return minStack.peek();
//        }
//
//        public int getMax() {
//            // Write your code here.
//            return maxStack.peek();
//        }
//    }

    static class MinMaxStack {
        Stack<int[]> stack = new Stack<>();
        public int peek() {
            return stack.peek()[0];
        }

        public int pop() {
            return stack.pop()[0];
        }

        public void push(Integer number) {
            if(stack.isEmpty()) {
                stack.push(new int[]{number, number, number});
            } else {
                int min = stack.peek()[1];
                int max = stack.peek()[2];
                stack.push(new int[]{number, Math.min(min, number), Math.max(max, number)});
            }
        }

        public int getMin() {
            return stack.peek()[1];
        }

        public int getMax() {
            // Write your code here.
            return stack.peek()[2];
        }
    }
}
