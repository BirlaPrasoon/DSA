package leetcode.problems.google.medium.trees;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LargestValueInEachRow {
    class SolutionBFS {
        public List<Integer> largestValues(TreeNode root) {
            if (root == null) {
                return new ArrayList<Integer>();
            }

            List<Integer> ans = new ArrayList<Integer>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                int currentLength = queue.size();
                int currMax = Integer.MIN_VALUE;

                for (int i = 0; i < currentLength; i++) {
                    TreeNode node = queue.remove();
                    currMax = Math.max(currMax, node.val);

                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }

                ans.add(currMax);
            }

            return ans;
        }
    }
    class SolutionDFS {
        List<Integer> ans;

        public List<Integer> largestValues(TreeNode root) {
            ans = new ArrayList<Integer>();
            dfs(root, 0);
            return ans;
        }

        public void dfs(TreeNode node, int depth) {
            if (node == null) {
                return;
            }

            if (depth == ans.size()) {
                ans.add(node.val);
            } else {
                ans.set(depth, Math.max(ans.get(depth), node.val));
            }

            dfs(node.left, depth + 1);
            dfs(node.right, depth + 1);
        }
    }
}
