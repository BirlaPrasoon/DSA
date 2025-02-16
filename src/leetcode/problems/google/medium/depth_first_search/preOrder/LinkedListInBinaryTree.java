package leetcode.problems.google.medium.depth_first_search.preOrder;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LinkedListInBinaryTree {

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

    // O(n*m) time, O(n+m) space.
    class Solution {

        public boolean isSubPath(ListNode head, TreeNode root) {
            if (root == null) return false;
            return checkPath(root, head);
        }

        private boolean checkPath(TreeNode node, ListNode head) {
            if (node == null) return false;
            if (dfs(node, head)) return true; // If a matching path is found
            // Recursively check left and right subtrees
            return checkPath(node.left, head) || checkPath(node.right, head);
        }

        private boolean dfs(TreeNode node, ListNode head) {
            if (head == null) return true; // All nodes in the list matched
            if (node == null) return false; // Reached end of tree without matching all nodes
            if (node.val != head.val) return false; // Value mismatch
            return dfs(node.left, head.next) || dfs(node.right, head.next);
        }
    }

    // KMP - O((2^k-1).m)
    class SolutionKMP {

        public boolean isSubPath(ListNode head, TreeNode root) {
            // Build the pattern and prefix table from the linked list
            List<Integer> pattern = new ArrayList<>();
            List<Integer> prefixTable = new ArrayList<>();
            pattern.add(head.val);
            prefixTable.add(0);
            int patternIndex = 0;
            head = head.next;

            while (head != null) {
                while (patternIndex > 0 && head.val != pattern.get(patternIndex)) {
                    patternIndex = prefixTable.get(patternIndex - 1);
                }
                patternIndex += head.val == pattern.get(patternIndex) ? 1 : 0;
                pattern.add(head.val);
                prefixTable.add(patternIndex);
                head = head.next;
            }

            // Perform DFS to search for the pattern in the tree
            return searchInTree(root, 0, pattern, prefixTable);
        }

        private boolean searchInTree(TreeNode node, int patternIndex, List<Integer> pattern, List<Integer> prefixTable) {
            if (node == null) return false;

            while (patternIndex > 0 && node.val != pattern.get(patternIndex)) {
                patternIndex = prefixTable.get(patternIndex - 1);
            }
            patternIndex += node.val == pattern.get(patternIndex) ? 1 : 0;

            // Check if the pattern is fully matched
            if (patternIndex == pattern.size()) return true;

            // Recursively search left and right subtrees
            return (searchInTree(node.left, patternIndex, pattern, prefixTable) ||
                    searchInTree(node.right, patternIndex, pattern, prefixTable));
        }
    }
}
