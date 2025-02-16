package leetcode.problems.google.medium.depth_first_search.inorder;

import leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RecoverBinarySearchTree {

    /*

    You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake.
    Recover the tree without changing its structure.

    Input: root = [1,3,null,null,2]
    Output: [3,1,null,null,2]
    Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

    Input: root = [3,1,4,null,null,2]
    Output: [2,1,4,null,null,3]
    Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.

    The number of nodes in the tree is in the range [2, 1000].
    -2^31 <= Node.val <= 2^31 - 1

    Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
    */

    // Cases -
    /*
     * Case 1 - 2 Nodes are not at their right position ()
     * Case 1 - Only one node violates the BST property (cur >= prev), in this case the two problamatic nodes are prev and cur (adjacent nodes).
     * */

    // O(n) space, O(n) time
    class SolutionInorderWithSpace {
        public void inorder(TreeNode root, List<Integer> nums) {
            if (root == null) return;
            inorder(root.left, nums);
            nums.add(root.val);
            inorder(root.right, nums);
        }

        public int[] findTwoSwapped(List<Integer> nums) {
            int n = nums.size();
            int x = -1, y = -1;
            boolean swapped_first_occurrence = false;

            for (int i = 0; i < n - 1; ++i) {
                if (nums.get(i + 1) < nums.get(i)) {
                    y = nums.get(i + 1);
                    if (!swapped_first_occurrence) {
                        // The first swap occurrence
                        x = nums.get(i);
                        swapped_first_occurrence = true;
                    } else {
                        // The second swap occurrence
                        break;
                    }
                }
            }
            return new int[]{x, y};
        }

        public void recover(TreeNode r, int count, int x, int y) {
            if (r != null) {
                if (r.val == x || r.val == y) {
                    if (r.val == x) {
                        r.val = y;
                    } else {
                        r.val = x;
                    }
                    if (--count == 0) return;
                }
                recover(r.left, count, x, y);
                recover(r.right, count, x, y);
            }
        }

        public void recoverTree(TreeNode root) {
            List<Integer> nums = new ArrayList();
            inorder(root, nums);
            int[] swapped = findTwoSwapped(nums);
            recover(root, 2, swapped[0], swapped[1]);
        }
    }

    class SolutionIterativeInOrder {
        public void swap(TreeNode a, TreeNode b) {
            int tmp = a.val;
            a.val = b.val;
            b.val = tmp;
        }

        public void recoverTree(TreeNode root) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode x = null, y = null, pred = null;

            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.add(root);
                    root = root.left;
                }
                root = stack.removeLast();
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null) x = pred;
                    else break;
                }
                pred = root;
                root = root.right;
            }

            swap(x, y);
        }
    }

    class SolutionMorrisTraversal {
        public void swap(TreeNode a, TreeNode b) {
            int tmp = a.val;
            a.val = b.val;
            b.val = tmp;
        }

        public void recoverTree(TreeNode root) {
            // predecessor is a Morris predecessor.
            // In the 'loop' cases it could be equal to the node itself predecessor == root.
            // pred is a 'true' predecessor,
            // the previous node in the inorder traversal.
            TreeNode x = null, y = null, pred = null, predecessor = null;

            while (root != null) {
                // If there is a left child
                // then compute the predecessor.
                // If there is no link predecessor.right = root --> set it.
                // If there is a link predecessor.right = root --> break it.
                if (root.left != null) {
                    // Predecessor node is one step left
                    // and then right till you can.
                    predecessor = root.left;
                    while (predecessor.right != null && predecessor.right != root) predecessor = predecessor.right;

                    // Set the link predecessor.right = root
                    // and go to explore left subtree
                    if (predecessor.right == null) {
                        predecessor.right = root;
                        root = root.left;
                    }
                    // Break the link predecessor.right = root
                    // link is broken : time to change subtree and go right
                    else {
                        // Check for the swapped nodes
                        if (pred != null && root.val < pred.val) {
                            y = root;
                            if (x == null) x = pred;
                        }
                        pred = root;

                        predecessor.right = null;
                        root = root.right;
                    }
                }
                // If there is no left child
                // then just go right.
                else {
                    // Check for the swapped nodes
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) x = pred;
                    }
                    pred = root;

                    root = root.right;
                }
            }
            swap(x, y);
        }
    }


}
