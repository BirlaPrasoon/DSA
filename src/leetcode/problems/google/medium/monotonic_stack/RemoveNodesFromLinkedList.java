package leetcode.problems.google.medium.monotonic_stack;

import leetcode.ListNode;

import java.util.Stack;

public class RemoveNodesFromLinkedList {

        /*
        *
        You are given the head of a linked list.

        Remove every node which has a node with a greater value anywhere to the right side of it.

        Return the head of the modified linked list.

        * */

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public ListNode removeNodes(ListNode head) {
            Stack<ListNode> stack = new Stack<>();
            ListNode current = head;

            // Add nodes to the stack
            while (current != null) {
                stack.push(current);
                current = current.next;
            }

            current = stack.pop();
            int maximum = current.val;
            ListNode resultList = new ListNode(maximum);

            // Remove nodes from the stack and add to result
            while (!stack.isEmpty()) {
                current = stack.pop();
                // Current should not be added to the result
                if (current.val < maximum) {
                    continue;
                }
                // Add new node with current's value to front of the result
                else {
                    ListNode newNode = new ListNode(current.val);
                    newNode.next = resultList;
                    resultList = newNode;
                    maximum = current.val;
                }
            }

            return resultList;
        }
    }

    class SolutionRecursive {
        public ListNode removeNodes(ListNode head) {
            // Base case, reached end of the list
            if (head == null || head.next == null) {
                return head;
            }

            // Recursive call
            ListNode nextNode = removeNodes(head.next);

            // If the next node has greater value than head, delete the head
            // Return next node, which removes the current head and makes next the new head
            if (head.val < nextNode.val) {
                return nextNode;
            }

            // Keep the head
            head.next = nextNode;
            return head;
        }
    }

}
