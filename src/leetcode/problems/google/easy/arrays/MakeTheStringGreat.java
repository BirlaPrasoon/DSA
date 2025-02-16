package leetcode.problems.google.easy.arrays;

import java.util.Stack;

public class MakeTheStringGreat {
    /*
    * Given a string s of lower and upper case English letters.

A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:

0 <= i <= s.length - 2
s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.

Return the string after making it good. The answer is guaranteed to be unique under the given constraints.

Notice that an empty string is also good.
    * */

    public String makeGood(String s) {
        // Use stack to store the visited characters.
        Stack<Character> stack = new Stack<>();

        // Iterate over 's'.
        for (char currChar : s.toCharArray()) {
            // If the current character make a pair with the last character in the stack,
            // remove both of them. Otherwise, we the add current character to stack.
            if (!stack.isEmpty() && Math.abs(stack.lastElement() - currChar) == 32)
                stack.pop();
            else
                stack.add(currChar);
        }

        // Returns the string concatenated by all characters left in the stack.
        StringBuilder ans = new StringBuilder();
        for (char currChar : stack)
            ans.append(currChar);
        return ans.toString();
    }
}
