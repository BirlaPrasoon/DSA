package leetcode.problems.google.hard.greedy;

import java.util.Arrays;

public class MaximumSumOfNodeValues {

    /**
     * Problem Statement:
     * There exists an undirected tree with n nodes numbered from 0 to n - 1. You are given a 0-indexed integer array nums of length n, where nums[i] represents the value of the ith node. You are also given a 2D integer array edges of length n - 1, where edges[i] = [u_i, v_i] indicates that there is an edge between nodes u_i and v_i in the tree.
     *
     * You are allowed to perform the following operation any number of times:
     * - Choose two adjacent nodes and flip the values of both nodes. In other words, swap the values of the two nodes.
     *
     * The score of the tree is defined as the sum of the values of all nodes.
     *
     * Return the maximum possible score after performing any number of operations.
     *
     * Example 1:
     * Input: nums = [1,2,3], edges = [[0,1],[1,2]]
     * Output: 6
     * Explanation:
     * - No operations are needed since the tree already has the maximum sum of node values.
     *
     * Example 2:
     * Input: nums = [1,2,1], edges = [[0,1],[1,2]]
     * Output: 4
     * Explanation:
     * - Swap the values of nodes 1 and 2. The tree becomes [1,1,2].
     * - The sum of node values is now 1 + 1 + 2 = 4, which is the maximum possible.
     *
     * Constraints:
     * - 1 <= n <= 2 * 10^4
     * - nums.length == n
     * - 1 <= nums[i] <= 10^4
     * - edges.length == n - 1
     * - edges[i].length == 2
     * - 0 <= u_i, v_i < n
     * - The input is a valid tree.
     */

    //(NLogN)
    class SolutionGreedy {
        public long maximumValueSum(int[] nums, int k, int[][] edges) {
            int n = nums.length;
            int[] netChange = new int[n];
            long nodeSum = 0;

            for (int i = 0; i < n; i++) {
                netChange[i] = (nums[i] ^ k) - nums[i];
                nodeSum += nums[i];
            }

            Arrays.sort(netChange);
            // Reverse the sorted array
            for (int i = 0; i < n / 2; i++) {
                int temp = netChange[i];
                netChange[i] = netChange[n - 1 - i];
                netChange[n - 1 - i] = temp;
            }

            for (int i = 0; i < n; i += 2) {
                // If netChange contains odd number of elements break the loop
                if (i + 1 == n) {
                    break;
                }
                long pairSum = netChange[i] + netChange[i + 1];
                // Include in nodeSum if pairSum is positive
                if (pairSum > 0) {
                    nodeSum += pairSum;
                }
            }
            return nodeSum;
        }
    }

    //O(n)
    class SolutionDP {
        public long maximumValueSum(int[] nums, int k, int[][] edges) {
            long[][] memo = new long[nums.length][2];
            for (long[] row : memo) {
                Arrays.fill(row, -1);
            }
            return maxSumOfNodes(0, 1, nums, k, memo);
        }

        private long maxSumOfNodes(int index, int isEven, int[] nums, int k,
                                   long[][] memo) {
            if (index == nums.length) {
                // If the operation is performed on an odd number of elements return
                // INT_MIN
                return isEven == 1 ? 0 : Integer.MIN_VALUE;
            }
            if (memo[index][isEven] != -1) {
                return memo[index][isEven];
            }
            // No operation performed on the element
            long noXorDone = nums[index] + maxSumOfNodes(index + 1, isEven, nums, k, memo);
            // XOR operation is performed on the element
            long xorDone = (nums[index] ^ k) +
                    maxSumOfNodes(index + 1, isEven ^ 1, nums, k, memo);

            // Memoize and return the result
            return memo[index][isEven] = Math.max(xorDone, noXorDone);
        }
    }

}
