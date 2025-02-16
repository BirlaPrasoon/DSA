package leetcode.problems.google.medium.depth_first_search.inorder;

import leetcode.TreeNode;

public class ConvertBSTtoGreaterTree {

    class Solution {
        private int sum = 0;

        public TreeNode convertBST(TreeNode root) {
            if (root != null) {
                convertBST(root.right);
                sum += root.val;
                root.val = sum;
                convertBST(root.left);
            }
            return root;
        }
    }

}
