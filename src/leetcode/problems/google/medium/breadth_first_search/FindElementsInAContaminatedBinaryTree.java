package leetcode.problems.google.medium.breadth_first_search;

import leetcode.TreeNode;
import java.util.*;

public class FindElementsInAContaminatedBinaryTree {

/*
    Given a binary tree with the following rules:

    root.val == 0
    If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
    If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
    Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.

    Implement the FindElements class:

    FindElements(TreeNode* root) Initializes the object with a contaminated binary tree and recovers it.
    bool find(int target) Returns true if the target value exists in the recovered binary tree.
*/



    class FindElements {
        private final Set<Integer> values;

        public FindElements(final TreeNode root) {
            this.values = new HashSet<>();
            this.values.add(0);

            root.val = 0;

            this.recover(root);
        }

        public boolean find(final int target) {
            return this.values.contains(target);
        }

        private void recover(final TreeNode root) {
            if(root == null)
                return;

            this.values.add(root.val);

            if(root.left != null)
                root.left.val = root.val * 2 + 1;

            if(root.right != null)
                root.right.val = root.val * 2 + 2;

            this.recover(root.left);
            this.recover(root.right);
        }
    }
}
