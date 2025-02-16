package leetcode.problems.google.medium.trees;


import leetcode.ListNode;
import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class SortedListToBinarySearchTree {
    /*
    * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a
height-balanced
 binary search tree.
    * */

    /*
    * Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
Example 2:

Input: head = []
Output: []

    * */
    /**
     * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode(int
     * x) { val = x; } }
     */
    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
     * right; TreeNode(int x) { val = x; } }
     */
    class Solution {
        private List<Integer> values;

        public Solution() {
            this.values = new ArrayList<Integer>();
        }

        private void mapListToValues(ListNode head) {
            while (head != null) {
                this.values.add(head.val);
                head = head.next;
            }
        }

        private TreeNode convertListToBST(int left, int right) {
            // Invalid case
            if (left > right) {
                return null;
            }

            // Middle element forms the root.
            int mid = (left + right) / 2;
            TreeNode node = new TreeNode(this.values.get(mid));

            // Base case for when there is only one element left in the array
            if (left == right) {
                return node;
            }

            // Recursively form BST on the two halves
            node.left = convertListToBST(left, mid - 1);
            node.right = convertListToBST(mid + 1, right);
            return node;
        }

        public TreeNode sortedListToBST(ListNode head) {
            // Form an array out of the given linked list and then
            // use the array to form the BST.
            this.mapListToValues(head);

            // Convert the array to
            return convertListToBST(0, this.values.size() - 1);
        }
    }
    /**
     * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode(int
     * x) { val = x; } }
     */
    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
     * right; TreeNode(int x) { val = x; } }
     */
    class SolutionInOrderSimulation {
        private ListNode head;

        private int findSize(ListNode head) {
            ListNode ptr = head;
            int c = 0;
            while (ptr != null) {
                ptr = ptr.next;
                c += 1;
            }
            return c;
        }

        private TreeNode convertListToBST(int l, int r) {
            // Invalid case
            if (l > r) {
                return null;
            }

            int mid = (l + r) / 2;

            // First step of simulated inorder traversal. Recursively form
            // the left half
            TreeNode left = this.convertListToBST(l, mid - 1);

            // Once left half is traversed, process the current node
            TreeNode node = new TreeNode(this.head.val);
            node.left = left;

            // Maintain the invariance mentioned in the algorithm
            this.head = this.head.next;

            // Recurse on the right hand side and form BST out of them
            node.right = this.convertListToBST(mid + 1, r);
            return node;
        }

        public TreeNode sortedListToBST(ListNode head) {
            // Get the size of the linked list first
            int size = this.findSize(head);

            this.head = head;

            // Form the BST now that we know the size
            return convertListToBST(0, size - 1);
        }
    }
}
