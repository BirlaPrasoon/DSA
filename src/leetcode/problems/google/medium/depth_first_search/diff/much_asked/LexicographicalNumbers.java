package leetcode.problems.google.medium.depth_first_search.diff.much_asked;

import java.util.ArrayList;
import java.util.List;

public class LexicographicalNumbers {

/**
    Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 </br></br>
    You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 </br>
    </br>
    Example 1:
 </br>
    Input: n = 13
 </br>
    Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
    </br>
    Example 2:
 </br>
    Input: n = 2
 </br>
    Output: [1,2]

    </br>
    Constraints:
    <br></br>
    <math>1<=n<= 5*10<sup>4</sup></math>

*/


    // O(n), O(1) space, Difficult to understand.
    class SolutionIterative {

        public List<Integer> lexicalOrder(int n) {
            List<Integer> lexicographicalNumbers = new ArrayList<>();
            int currentNumber = 1;

            // Generate numbers from 1 to n
            for (int i = 0; i < n; ++i) {
                lexicographicalNumbers.add(currentNumber);

                // If multiplying the current number by 10 is within the limit, do it
                if (currentNumber * 10 <= n) {
                    currentNumber *= 10;
                } else {
                    // Adjust the current number by moving up one digit
                    while (currentNumber % 10 == 9 || currentNumber >= n) {
                        currentNumber /= 10; // Remove the last digit
                    }
                    currentNumber += 1; // Increment the number
                }
            }

            return lexicographicalNumbers;
        }
    }

    //  time: O(n) space: O(log10(n))
    public class Solution {

        public List<Integer> lexicalOrder(int n) {
            List<Integer> lexicographicalNumbers = new ArrayList<>();
            // Start generating numbers from 1 to 9
            for (int start = 1; start <= 9; ++start) {
                generateLexicalNumbers(start, n, lexicographicalNumbers);
            }
            return lexicographicalNumbers;
        }

        private void generateLexicalNumbers(int currentNumber, int limit, List<Integer> result) {
            // If the current number exceeds the limit, stop recursion
            if (currentNumber > limit) return;

            // Add the current number to the result
            result.add(currentNumber);

            // Try to append digits from 0 to 9 to the current number
            for (int nextDigit = 0; nextDigit <= 9; ++nextDigit) {
                int nextNumber = currentNumber * 10 + nextDigit;
                // If the next number is within the limit, continue recursion
                if (nextNumber <= limit) {
                    generateLexicalNumbers(nextNumber, limit, result);
                } else {
                    break; // No need to continue if nextNumber exceeds limit
                }
            }
        }
    }

}
