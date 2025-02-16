package leetcode.problems.google.medium.monotonic_stack;

import leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NextGreaterElementInALinkedList {

    /*
    * You are given the head of a linked list with n nodes.

For each node in the list, find the value of the next greater node. That is, for each node, find the value of the first node that is next to it and has a strictly larger value than it.

Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed). If the ith node does not have a next greater node, set answer[i] = 0.
    * */


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    class Solution {
        public int[] nextLargerNodes(ListNode head) {
            List<Integer> values = new ArrayList<>();
            while (head != null) {
                values.add(head.val);
                head = head.next;
            }

            int n = values.size();
            int[] answer = new int[n];
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < n; ++i) {
                while (!stack.isEmpty() && values.get(stack.peek()) < values.get(i)) {
                    answer[stack.peek()] = values.get(i);
                    stack.pop();
                }
                stack.add(i);
            }
            return answer;
        }
    }
}
