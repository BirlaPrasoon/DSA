package leetcode.problems.google.hard.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParenthesis {
    /**
     * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
     * <p>
     * Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "()())()"
     * Output: ["(())()","()()()"]
     * Example 2:
     * <p>
     * Input: s = "(a)())()"
     * Output: ["(a())()","(a)()()"]
     * Example 3:
     * <p>
     * Input: s = ")("
     * Output: [""]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 25
     * s consists of lowercase English letters and parentheses '(' and ')'.
     * There will be at most 20 parentheses in s.
     */

    class Solution {
        private Set<String> result = new HashSet<>();
        private int minRemovals;

        public List<String> removeInvalidParentheses(String s) {
            minRemovals = calculateMinRemovals(s);
            backtrack(s, 0, 0, 0, new StringBuilder(), 0);
            return new ArrayList<>(result);
        }

        private void backtrack(String s, int index, int leftCount, int rightCount, StringBuilder path, int removedCount) {
            if (index == s.length()) {
                if (leftCount == rightCount && removedCount == minRemovals) {
                    result.add(path.toString());
                }
                return;
            }

            char ch = s.charAt(index);
            int length = path.length();

            // Option to exclude the current character
            if (ch == '(' || ch == ')') {
                backtrack(s, index + 1, leftCount, rightCount, path, removedCount + 1);
            }

            // Option to include the current character
            path.append(ch);

            if (ch == '(') {
                backtrack(s, index + 1, leftCount + 1, rightCount, path, removedCount);
            } else if (ch == ')') {
                if (rightCount < leftCount) {
                    backtrack(s, index + 1, leftCount, rightCount + 1, path, removedCount);
                }
            } else {
                backtrack(s, index + 1, leftCount, rightCount, path, removedCount);
            }

            // Backtrack by removing the current character
            path.deleteCharAt(length);
        }

        private int calculateMinRemovals(String s) {
            int leftExtra = 0, rightExtra = 0;
            for (char ch : s.toCharArray()) {
                if (ch == '(') {
                    leftExtra++;
                } else if (ch == ')') {
                    if (leftExtra > 0) {
                        leftExtra--;
                    } else {
                        rightExtra++;
                    }
                }
            }
            return leftExtra + rightExtra;
        }
    }


}
