package leetcode.problems.google.easy.recursion;

import java.util.Stack;

public class SplitBST {
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }

    public TreeNode[] splitBST(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode[2];
        }
        if (root.val > target) {
            TreeNode[] left = splitBST(root.left, target);
            root.left = left[1];
            return new TreeNode[] { left[0], root };
        } else {
            TreeNode[] right = splitBST(root.right, target);
            root.right = right[0];
            return new TreeNode[] { root, right[1] };
        }
    }

    public TreeNode[] splitBSTIterative(TreeNode root, int target) {
        // Array to store the two split trees
        TreeNode[] ans = new TreeNode[2];

        // If the root is null, return the empty array
        if (root == null) {
            return ans;
        }

        // Stack to traverse the tree and find the split point
        Stack<TreeNode> stack = new Stack<>();

        // Find the node with the value closest to the target
        while (root != null) {
            stack.push(root);
            if (root.val > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        // Process nodes in reverse order from the stack to perform the split
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.val > target) {
                // Assign current node's left child to the subtree
                // containing nodes greater than the target
                curr.left = ans[1];
                // current node becomes the new root of this subtree
                ans[1] = curr;
            } else {
                // Assign current node's right child to the subtree
                // containing nodes smaller than the target
                curr.right = ans[0];
                // current node becomes the new root of this subtree
                ans[0] = curr;
            }
        }

        return ans;
    }
}
