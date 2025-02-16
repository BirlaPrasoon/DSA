package leetcode.problems.google.medium.greedy.non_intuitive;

import java.util.Arrays;

public class MinimumIncrementToMakeArrayUnique {
/**
    You are given an integer array nums. In one move, you can pick an index i where 0 <= i < nums.length and increment nums[i] by 1.</br></br>

    Return the minimum number of moves to make every value in nums unique.</br></br>

    The test cases are generated so that the answer fits in a 32-bit integer.</br></br>



    Example 1:</br>
    Input: nums = [1,2,2]</br>
    Output: 1</br>
    Explanation: After 1 move, the array could be [1, 2, 3].</br></br>

    Example 2:</br>
    Input: nums = [3,2,1,2,1,7]</br>
    Output: 6</br>
    Explanation: After 6 moves, the array could be [3, 4, 1, 2, 5, 7].</br></br>

    It can be shown that it is impossible for the array to have all unique values with 5 or less moves.</br></br>

    Constraints:</br>
    1 <= nums.length <= 10<sup>5</sup></br>
    0 <= nums[i] <= 10<sup>5</sup></br>

    */


    class Solution {

        public int minIncrementForUnique(int[] nums) {
            int minIncrements = 0;

            Arrays.sort(nums);

            for (int i = 1; i < nums.length; i++) {
                // Ensure each element is greater than its previous
                if (nums[i] <= nums[i - 1]) {
                    // Add the required increment to minIncrements
                    int increment = nums[i - 1] + 1 - nums[i];
                    minIncrements += increment;

                    // Set the element to be greater than its previous
                    nums[i] = nums[i - 1] + 1;
                }
            }

            return minIncrements;
        }
    }

    class SolutionOofnTime {

        public int minIncrementForUnique(int[] nums) {
            int n = nums.length;
            int max = 0;
            int minIncrements = 0;

            // Find maximum value in array to determine range of frequencyCount array
            for (int val : nums) {
                max = Math.max(max, val);
            }

            // Create a frequencyCount array to store the frequency of each value in nums
            int[] frequencyCount = new int[n + max];

            // Populate frequencyCount array with the frequency of each value in nums
            for (int val : nums) {
                frequencyCount[val]++;
            }

            // Iterate over the frequencyCount array to make all values unique
            for (int i = 0; i < frequencyCount.length; i++) {
                if (frequencyCount[i] <= 1) continue;

                // Determine excess occurrences, carry them over to the next value,
                // ensure single occurrence for current value, and update minIncrements.
                int duplicates = frequencyCount[i] - 1;
                frequencyCount[i + 1] += duplicates;
                frequencyCount[i] = 1;
                minIncrements += duplicates;
            }

            return minIncrements;
        }
    }

}
