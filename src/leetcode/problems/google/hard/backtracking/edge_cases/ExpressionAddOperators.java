package leetcode.problems.google.hard.backtracking.edge_cases;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    /**
     * <h1>Problem Statement</h1>
     * Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
     * <p>
     * Note that operands in the returned expressions should not contain leading zeros.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: num = "123", target = 6
     * Output: ["1*2*3","1+2+3"]
     * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
     * Example 2:
     * <p>
     * Input: num = "232", target = 8
     * Output: ["2*3+2","2+3*2"]
     * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
     * Example 3:
     * <p>
     * Input: num = "3456237490", target = 9191
     * Output: []
     * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= num.length <= 10
     * num consists of only digits.
     * -2<sup>31</sup> <= target <= 2<sup>31</sup> - 1
     */

    static class Solution {

        public ArrayList<String> answer;
        public String digits;
        public long target;

        public void recurse(int index, long previousOperand, long currentOperand, long value, ArrayList<String> ops) {
            String nums = this.digits;

            // Done processing all the digits in num
            if (index == nums.length()) {

                // If the final value == target expected AND
                // no operand is left unprocessed
                if (value == this.target && currentOperand == 0) {
                    StringBuilder sb = new StringBuilder();
                    ops.subList(1, ops.size()).forEach(v -> sb.append(v));
                    this.answer.add(sb.toString());
                }
                return;
            }

            // Extending the current operand by one digit
            currentOperand = currentOperand * 10 + Character.getNumericValue(nums.charAt(index));
            String current_val_rep = Long.toString(currentOperand);
            int length = nums.length();

            // To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
            // valid operand. Hence this check
            if (currentOperand > 0) {

                // NO OP recursion
                recurse(index + 1, previousOperand, currentOperand, value, ops);
            }

            // ADDITION
            ops.add("+");
            ops.add(current_val_rep);
            recurse(index + 1, currentOperand, 0, value + currentOperand, ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);

            if (ops.size() > 0) {

                // SUBTRACTION
                ops.add("-");
                ops.add(current_val_rep);
                recurse(index + 1, -currentOperand, 0, value - currentOperand, ops);
                ops.remove(ops.size() - 1);
                ops.remove(ops.size() - 1);

                // MULTIPLICATION
                ops.add("*");
                ops.add(current_val_rep);
                // we need to reverse the previous operation in case of multiplication. Ex expression is (1+5) and now if we want to multiply with 3,
                // we'll get 18 while answer should be 1 + (5*3) = 16. So (1+5-5) + 5*3 gives us the answer.
                var newValue = value - previousOperand + (currentOperand * previousOperand);
                recurse(index + 1, currentOperand * previousOperand, 0, newValue, ops);
                ops.remove(ops.size() - 1);
                ops.remove(ops.size() - 1);
            }
        }

        public List<String> addOperators(String num, int target) {

            if (num.isEmpty()) {
                return new ArrayList<String>();
            }

            this.target = target;
            this.digits = num;
            this.answer = new ArrayList<String>();
            this.recurse(0, 0, 0, 0, new ArrayList<String>());
            return this.answer;
        }
    }

    public static void main(String[] args) {
        ExpressionAddOperators.Solution expressionAddOperators = new ExpressionAddOperators.Solution();
        System.out.println(expressionAddOperators.addOperators("1005", 500));
    }

}
