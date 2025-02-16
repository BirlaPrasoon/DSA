package leetcode.problems.google.hard.calculator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class BasicCalculator3 {
    /**
     * Implement a basic calculator to evaluate a simple expression string.</br></br>
     * The expression string contains only non-negative integers, <code>'+', '-', '*', '/'</code> operators, and open <code>'('</code> and closing parentheses <code>')'</code>. The integer division should truncate toward zero.</br></br>
     * You may assume that the given expression is always valid. All intermediate results will be in the range of <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>.</br></br>
     * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().</br></br>
     * Example 1:</br>
     * Input: <code>s = "1+1"</code></br>
     * Output: 2</br></br>
     * Example 2:</br>
     * Input: <code>s = "6-4/2"</code></br>
     * Output: 4</br></br>
     * Example 3:</br>
     * Input: <code>s = "2*(5+5*2)/3+(6/2+8)"</code></br>
     * Output: 21</br></br>
     * Constraints:</br>
     * 1 <= s <= 10<sup>4</sup></br>
     * s consists of digits, <code>'+', '-', '*', '/', '(', and ')'</code></br>
     * s is a valid expression.</br>
     */

    class Solution {
        private String evaluate(char operator, String first, String second) {
            int x = Integer.parseInt(first);
            int y = Integer.parseInt(second);
            int res = 0;

            if (operator == '+') {
                res = x;
            } else if (operator == '-') {
                res = -x;
            } else if (operator == '*') {
                res = x * y;
            } else {
                res = x / y;
            }

            return Integer.toString(res);
        }

        public int calculate(String s) {
            Stack<String> stack = new Stack<>();
            String curr = "";
            char previousOperator = '+';
            s += "@";
            Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));

            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    curr += c;
                } else if (c == '(') {
                    stack.push("" + previousOperator); // convert char to string before pushing
                    previousOperator = '+';
                } else {
                    if (previousOperator == '*' || previousOperator == '/') {
                        stack.push(evaluate(previousOperator, stack.pop(), curr));
                    } else {
                        stack.push(evaluate(previousOperator, curr, "0"));
                    }

                    curr = "";
                    previousOperator = c;
                    if (c == ')') {
                        int currentTerm = 0;
                        while (!operators.contains(stack.peek())) {
                            currentTerm += Integer.parseInt(stack.pop());
                        }

                        curr = Integer.toString(currentTerm);
                        previousOperator = stack.pop().charAt(0); // convert string from stack back to char
                    }
                }
            }

            int ans = 0;
            for (String num : stack) {
                ans += Integer.parseInt(num);
            }

            return ans;
        }
    }
}
