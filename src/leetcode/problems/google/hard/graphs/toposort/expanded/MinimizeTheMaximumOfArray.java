package leetcode.problems.google.hard.graphs.toposort.expanded;

public class MinimizeTheMaximumOfArray {

/**    You are given a 0-indexed array nums comprising of n non-negative integers.</br></br>

    In one operation, you must:</br>

    Choose an integer i such that 1 <= i < n and nums[i] > 0.</br>
    Decrease nums[i] by 1.</br>
    Increase nums[i - 1] by 1.</br>
    Return the minimum possible value of the maximum integer of nums after performing any number of operations.</br></br>



            Example 1:</br>

    Input: nums = [3,7,1,6]</br>
    Output: 5</br>
    Explanation:</br>
    One set of optimal operations is as follows:</br>
            1. Choose i = 1, and nums becomes [4,6,1,6].</br>
            2. Choose i = 3, and nums becomes [4,6,2,5].</br>
            3. Choose i = 1, and nums becomes [5,5,2,5].</br>
    The maximum integer of nums is 5. It can be shown that the maximum number cannot be less than 5.</br>
    Therefore, we return 5.</br></br>
    Example 2:</br>

    Input: nums = [10,1]</br>
    Output: 10</br>
    Explanation:</br>
    It is optimal to leave nums as is, and since 10 is the maximum value, we return 10.</br></br>

    Constraints:</br>

    n == nums.length</br>
2 <= n <= 10<sup>5</sup></br>
            0 <= nums[i] <= 10<sup>9<sup></br>
    */

    // Logic is in notes, still intution is, first element is the one we can't decrease.. So moving forward we can only
    // increase the max value not decrease it.
    // Total/n is the minimum value we can get across n elements, hence running (sum / i+1) is the minimum maximum value for i values.

    class Solution {
        public int minimizeArrayValue(int[] nums) {
            long sum = 0, res = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                res = Math.max(res, (sum + i) / (i + 1));
            }
            return (int)res;
        }
    }

}
