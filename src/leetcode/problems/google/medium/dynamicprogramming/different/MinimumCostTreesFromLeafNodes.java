package leetcode.problems.google.medium.dynamicprogramming.different;

import leetcode.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimumCostTreesFromLeafNodes {


/*    Given an array arr of positive integers, consider all binary trees such that:

    Each node has either 0 or 2 children;
    The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
    The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree, respectively.
    Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node. It is guaranteed this sum fits into a 32-bit integer.

    A node is a leaf if and only if it has zero children.
    */

/*
    Input: arr = [6,2,4]
    Output: 32
    Explanation: There are two possible trees shown.
    The first has a non-leaf node sum 36, and the second has non-leaf node sum 32.
*/
    class Solution {
        public int mctFromLeafValues(int[] arr) {
            int n = arr.length;
            // To Store maximum value between the range
            Map<Pair<Integer, Integer>, Integer> max = new HashMap<>();
            for (int i = 0; i < n; i++) {
                max.put(new Pair<>(i, i), arr[i]);
                for (int j = i + 1; j < n; j++) {
                    max.put(new Pair<>(i, j), Math.max(arr[j], max.get(new Pair<>(i, j - 1))));
                }
            }

            // return helper(arr, max, 0, n - 1);

            int[][] dp = new int[n + 1][n + 1];
            for (int[] row : dp) Arrays.fill(row, -1);
            return helperMem(arr, max, 0, n - 1, dp);
        }

        public int helper(int[] arr, Map<Pair<Integer, Integer>, Integer> max, int left, int right) {
            if (left == right) return 0;
            int ans = Integer.MAX_VALUE;

            for (int i = left; i < right; i++) {
                ans = Math.min(ans,
                        max.get(new Pair<>(left, i)) * max.get(new Pair<>(i + 1, right))
                                + helper(arr, max, left, i) + helper(arr, max, i + 1, right)
                );
            }
            return ans;
        }

        public int helperMem(int[] arr, Map<Pair<Integer, Integer>, Integer> max, int left, int right, int[][] dp) {
            if (left == right) return 0;
            if (dp[left][right] != -1) return dp[left][right];
            int ans = Integer.MAX_VALUE;

            for (int i = left; i < right; i++) {
                ans = Math.min(ans,
                        max.get(new Pair(left, i)) * max.get(new Pair(i + 1, right))
                                + helperMem(arr, max, left, i, dp) + helperMem(arr, max, i + 1, right, dp)
                );
            }
            return dp[left][right] = ans;
        }
    }

}
