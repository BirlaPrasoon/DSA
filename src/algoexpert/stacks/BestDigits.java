package algoexpert.stacks;

import java.util.Stack;

public class BestDigits {

    // Prepare all possible test cases beforehand. I missed all decreasing number sequence, all 0, and 100000003
    public String bestDigits(String number, int numDigits) {
        int x = numDigits;
        if(numDigits == 0) return number;
        Stack<Integer> stack = new Stack<>();
        for(char c: number.toCharArray()) {
            int n = c - '0';
            // This should be while loop not if, we need to remove all smaller elements on the left
            while(!stack.isEmpty() && stack.peek() <= n && numDigits>0) {
                stack.pop();
                numDigits--;
            }
            stack.push(n);
        }
        // Write your code here.
        int targetDigitCount = number.length() - x;
        while (stack.size() > targetDigitCount) stack.pop();
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "10000000002";
        BestDigits bd = new BestDigits();
        System.out.println(bd.bestDigits(s, 10));
    }
}
