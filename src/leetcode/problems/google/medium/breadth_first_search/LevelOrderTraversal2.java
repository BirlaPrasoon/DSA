package leetcode.problems.google.medium.breadth_first_search;

import leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelOrderTraversal2 {


    /**
     * Given the root of a binary tree,
     * return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[15,7],[9,20],[3]]
     * Example 2:
     * <p>
     * Input: root = [1]
     * Output: [[1]]
     * Example 3:
     * <p>
     * Input: root = []
     * Output: []
     */

    class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> levels = new ArrayList<List<Integer>>();
            if (root == null) return levels;

            ArrayDeque<TreeNode> nextLevel = new ArrayDeque<>() {
                {
                    offer(root);
                }
            };
            ArrayDeque<TreeNode> currLevel = new ArrayDeque();

            while (!nextLevel.isEmpty()) {
                currLevel = nextLevel.clone();
                nextLevel.clear();
                levels.add(new ArrayList<Integer>());

                for (TreeNode node : currLevel) {
                    // append the current node value
                    levels.get(levels.size() - 1).add(node.val);

                    // process child nodes for the next level
                    if (node.left != null) nextLevel.offer(node.left);
                    if (node.right != null) nextLevel.offer(node.right);
                }
            }

            Collections.reverse(levels);
            return levels;
        }
    }
}
