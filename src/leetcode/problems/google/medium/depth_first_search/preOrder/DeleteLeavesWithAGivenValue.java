package leetcode.problems.google.medium.depth_first_search.preOrder;

import leetcode.TreeNode;

public class DeleteLeavesWithAGivenValue {

/*
    Given a binary tree root and an integer target, delete all the leaf nodes with value target.

    Note that once you delete a leaf node with value target, if its parent node becomes a leaf node
    and has the value target, it should also be deleted (you need to continue doing that until you cannot).
*/

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null)
            return null;
        TreeNode left = removeLeafNodes(root.left, target);
        TreeNode right = removeLeafNodes(root.right, target);

        if (isLeafWithMatchingValue(left, target)) root.left = null;
        else root.left = left;

        if (isLeafWithMatchingValue(right, target)) root.right = null;
        else root.right = right;

        if (isLeafWithMatchingValue(root, target))
            return null;
        return root;
    }

    private boolean isLeafWithMatchingValue(TreeNode root, int target) {
        if (root == null)
            return false;
        return root.val == target && root.left == null && root.right == null;
    }

}
