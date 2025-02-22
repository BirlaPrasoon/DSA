package leetcode.problems.google.medium.breadth_first_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PerfectSquares {

/*
    Given an integer n, return the least number of perfect square numbers that sum to n.

    A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.



    Example 1:

    Input: n = 12
    Output: 3
    Explanation: 12 = 4 + 4 + 4.
    Example 2:

    Input: n = 13
    Output: 2
    Explanation: 13 = 4 + 9.
*/

    class SolutionGreedyBFS {
        public int numSquares(int n) {

            ArrayList<Integer> square_nums = new ArrayList<Integer>();
            for (int i = 1; i * i <= n; ++i) {
                square_nums.add(i * i);
            }

            Set<Integer> queue = new HashSet<Integer>();
            queue.add(n);

            int level = 0;
            while (queue.size() > 0) {
                level += 1;
                Set<Integer> next_queue = new HashSet<Integer>();

                for (Integer remainder : queue) {
                    for (Integer square : square_nums) {
                        if (remainder.equals(square)) {
                            return level;
                        } else if (remainder < square) {
                            break;
                        } else {
                            next_queue.add(remainder - square);
                        }
                    }
                }
                queue = next_queue;
            }
            return level;
        }
    }

    class Solution {

        public int numSquares(int n) {
            int dp[] = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            // bottom case
            dp[0] = 0;

            // pre-calculate the square numbers.
            int max_square_index = (int) Math.sqrt(n) + 1;
            int square_nums[] = new int[max_square_index];
            for (int i = 1; i < max_square_index; ++i) {
                square_nums[i] = i * i;
            }

            for (int i = 1; i <= n; ++i) {
                for (int s = 1; s < max_square_index; ++s) {
                    if (i < square_nums[s])
                        break;
                    dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
                }
            }
            return dp[n];
        }
    }
}
