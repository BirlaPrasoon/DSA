package leetcode.problems.google.medium.arrays.easy_but_tricky;

public class ShuffleTheArray {
/*
    Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].

    Return the array in the form [x1,y1,x2,y2,...,xn,yn].

    Example 1:

    Input: nums = [2,5,1,3,4,7], n = 3
    Output: [2,3,5,4,1,7]
    Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].
    Example 2:

    Input: nums = [1,2,3,4,4,3,2,1], n = 4
    Output: [1,4,2,3,3,2,4,1]
    Example 3:

    Input: nums = [1,1,2,2], n = 2
    Output: [1,2,1,2]


    Constraints:

            1 <= n <= 500
    nums.length == 2n
1 <= nums[i] <= 10^3
    */

    // Storing two numbers in one array.
    // But this won't scale for numbers that don't fit in 2nd halfs.
    class Solution {
        public int[] shuffle(int[] nums, int n) {
            // Store each y(i) with respective x(i).
            for (int i = n; i < 2 * n; ++i) {
                int secondNum = nums[i] << 10;
                nums[i - n] |= secondNum;
            }

            // '0000000000 1111111111' in decimal.
            int allOnes = (int) Math.pow(2, 10) - 1;

            // We will start putting all numbers from the end,
            // as they are empty places.
            for (int i = n - 1; i >= 0; --i) {
                // Fetch both the numbers from the current index.
                int secondNum = nums[i] >> 10;
                int firstNum = nums[i] & allOnes;
                nums[2 * i + 1] = secondNum;
                nums[2 * i] = firstNum;
            }
            return nums;
        }
    }


    class SolutionExtraSpace {
        public int[] shuffle(int[] nums, int n) {
            int[] result = new int[2 * n];
            for (int i = 0; i < n; ++i) {
                result[2 * i] = nums[i];
                result[2 * i + 1] = nums[n + i];
            }
            return result;
        }
    }

}
