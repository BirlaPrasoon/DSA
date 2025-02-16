package leetcode.problems.google.medium.monotonic_stack.surprising;

import leetcode.TreeNode;

public class BinarySearchTreeFromPreorder {

/*    Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.

    It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.

    A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.

    A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.
    */

/*    Input: preorder = [8,5,1,7,10,12]
    Output: [8,5,10,1,7,null,12]
    Example 2:

    Input: preorder = [1,3]
    Output: [1,null,3]*/

    class Solution {
        int idx = 0;
        int[] preorder;
        int n;

        public TreeNode helper(int lower, int upper) {
            //If all elements from preorder are used
            // then the tree is constructed
            if (idx == n) return null;

            int val = preorder[idx];
            //If the current element
            // couldn't be placed here to meet BST requirements
            if (val < lower || val > upper) return null;

            //Place the current element
            // and recursively construct subtrees
            idx++;
            TreeNode root = new TreeNode(val);
            root.left = helper(lower, val);
            root.right = helper(val, upper);
            return root;
        }

        public TreeNode bstFromPreorder(int[] preorder) {
            this.preorder = preorder;
            n = preorder.length;
            return helper(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }
}
