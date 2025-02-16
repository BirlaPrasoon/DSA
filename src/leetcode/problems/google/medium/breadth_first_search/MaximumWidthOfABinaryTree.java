package leetcode.problems.google.medium.breadth_first_search;

import leetcode.Pair;
import leetcode.TreeNode;

import java.util.LinkedList;

public class MaximumWidthOfABinaryTree {

/*
    Given the root of a binary tree, return the maximum width of the given tree.

    The maximum width of a tree is the maximum width among all levels.

    The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
    where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level
    are also counted into the length calculation.

    It is guaranteed that the answer will in the range of a 32-bit signed integer.
*/

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public int widthOfBinaryTree(TreeNode root) {
            if (root == null)
                return 0;

            // The queue of elements [(node, col_index)]
            LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
            Integer maxWidth = 0;

            queue.addLast(new Pair<>(root, 0));
            while (queue.size() > 0) {
                Pair<TreeNode, Integer> head = queue.getFirst();

                // Iterate through the current level
                Integer currLevelSize = queue.size();
                Pair<TreeNode, Integer> elem = null;
                for (int i = 0; i < currLevelSize; ++i) {
                    elem = queue.removeFirst();
                    TreeNode node = elem.getKey();
                    if (node.left != null)
                        queue.addLast(new Pair<>(node.left, 2 * elem.getValue()));
                    if (node.right != null)
                        queue.addLast(new Pair<>(node.right, 2 * elem.getValue() + 1));
                }

                // Calculate the length of the current level,
                // by comparing the first and last col_index.
                maxWidth = Math.max(maxWidth, elem.getValue() - head.getValue() + 1);
            }

            return maxWidth;
        }
    }



}
