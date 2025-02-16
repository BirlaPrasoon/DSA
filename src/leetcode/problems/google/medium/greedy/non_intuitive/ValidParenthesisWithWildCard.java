package leetcode.problems.google.medium.greedy.non_intuitive;

import java.util.Arrays;

public class ValidParenthesisWithWildCard {

    class SolutionOpenMinOpenMaxRange {
        public boolean checkValidString(String s) {
            int leftMin = 0, leftMax = 0;

            for(char c: s.toCharArray()) {
                if(c == '(') {
                    leftMin++;leftMax++;
                } else if(c == ')') {
                    leftMin--; leftMax--;
                } else { // *
                    leftMin--; leftMax++;
                }
                if(leftMax<0) return false;
                if(leftMin<0) leftMin=0;
            }
            return leftMin==0;
        }
    }

    class SolutionOpenCloseCount {
        public boolean checkValidString(String s) {
            int openCount = 0;
            int closeCount = 0;
            int length = s.length() - 1;

            // Traverse the string from both ends simultaneously
            for (int i = 0; i <= length; i++) {
                // Count open parentheses or asterisks
                if (s.charAt(i) == '(' || s.charAt(i) == '*') {
                    openCount++;
                } else {
                    openCount--;
                }

                // Count close parentheses or asterisks
                if (s.charAt(length - i) == ')' || s.charAt(length - i) == '*') {
                    closeCount++;
                } else {
                    closeCount--;
                }

                // If at any point open count or close count goes negative, the string is invalid
                if (openCount < 0 || closeCount < 0) {
                    return false;
                }
            }

            // If open count and close count are both non-negative, the string is valid
            return true;
        }
    }

    // O(n^2)
    class SolutionDP {
        public boolean checkValidString(String s) {
            int n = s.length();
            int[][] memo = new int[n][n];
            for (int[] row: memo) {
                Arrays.fill(row, -1);
            }
            return isValidString(0, 0, s, memo);
        }

        private boolean isValidString(int index, int leftOpenCount, String str, int[][] memo) {
            // If reached end of the string, check if all brackets are balanced
            if (index == str.length()) {
                return (leftOpenCount == 0);
            }
            // If already computed, return memoized result
            if (memo[index][leftOpenCount] != -1) {
                return memo[index][leftOpenCount] == 1;
            }
            boolean isValid = false;
            // If encountering '*', try all possibilities
            if (str.charAt(index) == '*') {
                isValid |= isValidString(index + 1, leftOpenCount + 1, str, memo); // Treat '*' as '('
                if (leftOpenCount > 0) {
                    isValid |= isValidString(index + 1, leftOpenCount - 1, str, memo); // Treat '*' as ')'
                }
                isValid |= isValidString(index + 1, leftOpenCount, str, memo); // Treat '*' as empty
            } else {
                // Handle '(' and ')'
                if (str.charAt(index) == '(') {
                    isValid = isValidString(index + 1, leftOpenCount + 1, str, memo); // Increment count for '('
                } else if (leftOpenCount > 0) {
                    isValid = isValidString(index + 1, leftOpenCount - 1, str, memo); // Decrement count for ')'
                }
            }

            // Memoize and return the result
            memo[index][leftOpenCount] = isValid ? 1 : 0;
            return isValid;
        }
    }

}
