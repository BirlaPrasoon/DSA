package leetcode.problems.google.hard.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class PizzaWith3NSlices {
    /**
     * Problem Statement:
     * There is a pizza with 3n slices of varying sizes. You and your friends will take slices as follows:
     * - You will pick any pizza slice.
     * - Your friend Alice will pick the next slice in the clockwise direction.
     * - Your friend Bob will pick the next slice in the counterclockwise direction.
     * - Repeat until there are no more slices left.
     *
     * Given an integer array slices that represents the sizes of the pizza slices in clockwise order,
     * return the maximum possible sum of slice sizes you can pick.
     *
     * Example 1:
     * Input: slices = [1,2,3,4,5,6]
     * Output: 10
     * Explanation:
     * - Pick slice 6 (size 6).
     * - Alice picks slice 5 (size 5).
     * - Bob picks slice 1 (size 1).
     * - You pick slice 2 (size 2).
     * - Alice picks slice 3 (size 3).
     * - Bob picks slice 4 (size 4).
     * - Total sum = 6 + 2 = 8.
     * Alternatively, you can pick slices 4 and 2 for a total sum of 10.
     *
     * Example 2:
     * Input: slices = [8,9,8,6,1,1]
     * Output: 16
     * Explanation:
     * - Pick slice 8 (size 8).
     * - Alice picks slice 9 (size 9).
     * - Bob picks slice 1 (size 1).
     * - You pick slice 8 (size 8).
     * - Alice picks slice 6 (size 6).
     * - Bob picks slice 1 (size 1).
     * - Total sum = 8 + 8 = 16.
     *
     * Constraints:
     * - 3n == slices.length
     * - 1 <= slices.length <= 500
     * - 1 <= slices[i] <= 1000
     */
    public int maxSizeSlices(int[] slices) {
        int m = slices.length, n = m / 3;
        int[] slices1 = Arrays.copyOfRange(slices, 0, m-1);
        int[] slices2 = Arrays.copyOfRange(slices, 1, m);
        return Math.max(maxSum(slices1, n), maxSum(slices2, n));
    }

    int maxSum(int[] arr, int n) { // max sum when pick `n` non-adjacent elements from `arr`
        int m = arr.length;
        int[][] dp = new int[m+1][n+1]; // dp[i][j] is maximum sum which we pick `j` elements from linear array `i` elements
        // Case j = 0 (pick 0 elements): dp[i][0] = 0
        // Case i = 0 (array is empty): dp[0][j] = 0
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i == 1) { // array has only 1 element
                    dp[i][j] = arr[0]; // pick that element
                } else {
                    dp[i][j] = Math.max(
                            dp[i-1][j],             // don't pick element `ith`
                            dp[i-2][j-1] + arr[i-1] // pick element `ith` -> dp[i-2][j-1] means choose `j-1` elements from array `i-2` elements
                            //   because we exclude adjacent element `(i-1)th`
                    );
                }
            }
        }
        return dp[m][n];
    }

    /*
    * Greedy
brilliant idea from leedcode-cn, credit to kira1928
idea is to use priority queue to hold current optimal result, and give us a chance to revoke our pick for a better option that is discovered later in the process
[8,9,8,1,2,3] as an example
9 + 3 < 8 + 8
when picking 9, remove nodes 8, 9, 8, BUT add a new node 7(8+8-9) into the list
so that when later 7 is picked, it's equivalent of picking 8 + 8 instead 9
    * */

    class SolutionGreedy {
        static class Node {
            public Node prev = null;
            public Node next = null;
            public int value = 0;
            public Node(int value) {
                this.value = value;
            }
        }
        public int maxSizeSlices(int[] slices) {
            int n = slices.length;
            int res = 0;

            // build up the doubly linked list and priority queue
            Node head = null, curr = null;
            Queue<Node> pq = new PriorityQueue<>((x, y) -> y.value - x.value);
            for (int value : slices) {
                Node node = new Node(value);
                if (head == null) {
                    head = node;
                }
                if (curr == null) {
                    curr = node;
                } else {
                    curr.next = node;
                    node.prev = curr;
                    curr = node;
                }
                pq.add(node);
            }
            // create cycle
            head.prev = curr;
            curr.next = head;


            for (int i = 0; i < n / 3; ++i) {
                Node node = pq.poll();
                res += node.value;
                Node newNode = new Node(node.prev.value + node.next.value - node.value);
                node.prev.prev.next = newNode;
                newNode.prev = node.prev.prev;
                node.next.next.prev = newNode;
                newNode.next = node.next.next;
                pq.remove(node.prev);
                pq.remove(node.next);
                pq.add(newNode);
            }
            return res;
        }
    }
}
