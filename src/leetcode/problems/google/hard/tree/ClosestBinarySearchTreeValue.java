package leetcode.problems.google.hard.tree;


import leetcode.TreeNode;

import java.util.*;

public class ClosestBinarySearchTreeValue {

    /**
     * Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are closest to the target. You may return the answer in any order.
     * <p>
     * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
     * <p>
     * Input: root = [4,2,5,1,3], target = 3.714286, k = 2
     * Output: [4,3]
     * Example 2:
     * <p>
     * Input: root = [1], target = 0.000000, k = 1
     * Output: [1]
     */

    class SolutionBruteForce { // nlogn
        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            List<Integer> arr = new ArrayList<>();
            dfs(root, arr);

            Collections.sort(arr, (o1, o2) -> Math.abs(o1 - target) <= Math.abs(o2 - target) ? -1 : 1);

            return arr.subList(0, k);

        }

        public void dfs(TreeNode node, List<Integer> arr) {
            if (node == null) {
                return;
            }

            arr.add(node.val);
            dfs(node.left, arr);
            dfs(node.right, arr);
        }
    }

    class SolutionWithHeap { // nlogk
        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            Queue<Integer> heap = new PriorityQueue<>((a, b) -> Math.abs(a - target) > Math.abs(b - target) ? -1 : 1);
            dfs(root, heap, k);

            return new ArrayList<>(heap);
        }

        public void dfs(TreeNode node, Queue<Integer> heap, int k) {
            if (node == null) {
                return;
            }

            heap.add(node.val);
            if (heap.size() > k) {
                heap.remove();
            }

            dfs(node.left, heap, k);
            dfs(node.right, heap, k);
        }
    }

    class SolutionSlidingWindowPlusInOrder { // O(n+k)
        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            List<Integer> arr = new ArrayList<>();
            dfs(root, arr);

            int start = 0;
            double minDiff = Double.MAX_VALUE;

            for (int i = 0; i < arr.size(); i++) {
                if (Math.abs(arr.get(i) - target) < minDiff) {
                    minDiff = Math.abs(arr.get(i) - target);
                    start = i;
                }
            }

            int left = start;
            int right = start + 1;

            while (right - left - 1 < k) {
                // Be careful to not go out of bounds
                if (left < 0) {
                    right += 1;
                    continue;
                }

                if (right == arr.size() || Math.abs(arr.get(left) - target) <= Math.abs(arr.get(right) - target)) {
                    left -= 1;
                } else {
                    right += 1;
                }
            }

            return arr.subList(left + 1, right);
        }

        public void dfs(TreeNode node, List<Integer> arr) {
            if (node == null) {
                return;
            }

            dfs(node.left, arr);
            arr.add(node.val);
            dfs(node.right, arr);
        }
    }
}
