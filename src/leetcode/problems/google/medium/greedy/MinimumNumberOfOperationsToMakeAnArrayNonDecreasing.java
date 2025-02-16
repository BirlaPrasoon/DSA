package leetcode.problems.google.medium.greedy;

public class MinimumNumberOfOperationsToMakeAnArrayNonDecreasing {

    /*
    *
    * You are given an integer array nums.

Any positive divisor of a natural number x that is strictly less than x is called a proper divisor of x. For example, 2 is a proper divisor of 4, while 6 is not a proper divisor of 6.

You are allowed to perform an operation any number of times on nums, where in each operation you select any one element from nums and divide it by its greatest proper divisor.

Return the minimum number of operations required to make the array non-decreasing.

If it is not possible to make the array non-decreasing using any number of operations, return -1.



Example 1:

Input: nums = [25,7]

Output: 1

Explanation:

Using a single operation, 25 gets divided by 5 and nums becomes [5, 7].

Example 2:

Input: nums = [7,7,6]

Output: -1

Example 3:

Input: nums = [1,1,1,1]

Output: 0



Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 106
    * */


    class Solution {
        public int minOperations(int[] nums) {
            int n = nums.length;
            int operations = 0;

            for (int i = n - 2; i >= 0; i--) {
                if (nums[i] <= nums[i + 1]) {
                    continue;
                }

                int current = nums[i];
                int next = nums[i + 1];
                int local_operations = 0;
                int divisor = greatestProperDivisor(current);

                if (divisor <= 1) {
                    return -1;
                }

                local_operations++;
                nums[i] = divisor;
                if (nums[i] > next) {
                    return -1;
                }
                operations += local_operations;
            }

            return operations;
        }

        private int greatestProperDivisor(int x) {
            if (x <= 1) {
                return 0;
            }
            for (int i = 2; i <= Math.sqrt(x); i++) {
                if (x % i == 0) {
                    if (i < x) {
                        return i;
                    }
                    if (x / i < x) {
                        return x / i;
                    }
                }
            }
            return 0;
        }
    }

}
