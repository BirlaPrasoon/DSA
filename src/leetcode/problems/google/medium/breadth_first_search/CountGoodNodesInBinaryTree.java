package leetcode.problems.google.medium.breadth_first_search;

import leetcode.TreeNode;
import java.util.*;

public class CountGoodNodesInBinaryTree {

    /*
    *
    * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with
    * a value greater than X.
    * Return the number of good nodes in the binary tree.
    *
    * */

    class SolutionDFS {
        private int numGoodNodes = 0;

        public int goodNodes(TreeNode root) {
            dfs(root, Integer.MIN_VALUE);
            return numGoodNodes;
        }

        private void dfs(TreeNode node, int maxSoFar) {
            if (maxSoFar <= node.val) {
                numGoodNodes++;
            }

            if (node.right != null) {
                dfs(node.right, Math.max(node.val, maxSoFar));
            }

            if (node.left != null) {
                dfs(node.left, Math.max(node.val, maxSoFar));
            }
        }
    }

    class Pair {
        public TreeNode node;
        public int maxSoFar;

        public Pair(TreeNode node, int maxSoFar) {
            this.node = node;
            this.maxSoFar = maxSoFar;
        }
    }

    class Solution {
        public int goodNodes(TreeNode root) {
            int numGoodNodes = 0;
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(new Pair(root, Integer.MIN_VALUE));

            while (!queue.isEmpty()) {
                Pair curr = queue.poll();
                if (curr.maxSoFar <= curr.node.val) {
                    numGoodNodes++;
                }

                if (curr.node.right != null) {
                    queue.offer(new Pair(curr.node.right, Math.max(curr.node.val, curr.maxSoFar)));
                }

                if (curr.node.left != null) {
                    queue.offer(new Pair(curr.node.left, Math.max(curr.node.val, curr.maxSoFar)));
                }
            }

            return numGoodNodes;
        }
    }
}
