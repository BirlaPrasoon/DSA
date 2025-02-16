package leetcode.problems.google.medium.greedy.most_asked;

public class MinimumNumberOfSwapsToBalanceParenthesis {
/**
    You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.</br>

    A string is called balanced if and only if:</br></br>

    It is the empty string, or</br>
    It can be written as AB, where both A and B are balanced strings, or</br>
    It can be written as [C], where C is a balanced string.</br>
    You may swap the brackets at any two indices any number of times.</br></br>

    Return the minimum number of swaps to make s balanced.</br></br>

    Example 1:</br>

    Input: s = "][]["</br>
    Output: 1</br>
    Explanation: You can make the string balanced by swapping index 0 with index 3.</br>
    The resulting string is "[[]]".</br></br>
    Example 2:</br>

    Input: s = "]]][[["</br>
    Output: 2</br>
    Explanation: You can do the following to make the string balanced:</br>
            - Swap index 0 with index 4. s = "[]][][".</br>
            - Swap index 1 with index 5. s = "[[][]]".</br>
    The resulting string is "[[][]]".</br></br>
    Example 3:</br>

    Input: s = "[]"</br>
    Output: 0</br>
    Explanation: The string is already balanced.</br></br>


            Constraints:</br>

    n == s.length</br>
2 <= n <= 10<sup>6</sup></br>
    n is even.</br>
            s[i] is either '[' or ']'.</br>
    The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.</br>
    */

    class Solution {

        // trick
        public int minSwaps(String s) {
            int leftOpen = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                // If character is opening bracket, increment the stack size.
                if (ch == '[') leftOpen++;
                else {
                    // If the character is closing bracket, and we have an opening bracket, decrease
                    // the stack size.
                    if (leftOpen > 0) leftOpen--;
                }
            }
            return (leftOpen + 1) / 2;
        }
    }
}
