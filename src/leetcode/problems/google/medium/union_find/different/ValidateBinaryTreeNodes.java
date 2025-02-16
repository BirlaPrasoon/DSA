package leetcode.problems.google.medium.union_find.different;

import java.util.*;

public class ValidateBinaryTreeNodes {

    /*
     *
     * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i],
     * return true if and only if all the given nodes form exactly one valid binary tree.
     *
     * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
     *
     * Note that the nodes have no values and that we only use the node numbers in this problem.
     *
     *
     * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
     * Output: true
     *
     * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
     * Output: false
     * */

    class SolutionDFS {
        public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
            int root = findRoot(n, leftChild, rightChild);
            if (root == -1) {
                return false;
            }

            Set<Integer> seen = new HashSet<>();
            Stack<Integer> stack = new Stack<>();
            seen.add(root);
            stack.push(root);

            while (!stack.isEmpty()) {
                int node = stack.pop();
                int[] children = {leftChild[node], rightChild[node]};

                for (int child : children) {
                    if (child != -1) {
                        if (seen.contains(child)) {
                            return false;
                        }

                        stack.push(child);
                        seen.add(child);
                    }
                }

            }

            return seen.size() == n;
        }

        public int findRoot(int n, int[] left, int[] right) {
            Set<Integer> children = new HashSet<>();
            for (int node : left) {
                children.add(node);
            }

            for (int node : right) {
                children.add(node);
            }

            for (int i = 0; i < n; i++) {
                if (!children.contains(i)) {
                    return i;
                }
            }

            return -1;
        }
    }

    class SolutionBFS {
        public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
            int root = findRoot(n, leftChild, rightChild);
            if (root == -1) {
                return false;
            }

            Set<Integer> seen = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            seen.add(root);
            queue.add(root);

            while (!queue.isEmpty()) {
                int node = queue.remove();
                int[] children = {leftChild[node], rightChild[node]};

                for (int child : children) {
                    if (child != -1) {
                        if (seen.contains(child)) {
                            return false;
                        }

                        queue.add(child);
                        seen.add(child);
                    }
                }

            }

            return seen.size() == n;
        }

        public int findRoot(int n, int[] left, int[] right) {
            Set<Integer> children = new HashSet<>();
            for (int node : left) {
                children.add(node);
            }

            for (int node : right) {
                children.add(node);
            }

            for (int i = 0; i < n; i++) {
                if (!children.contains(i)) {
                    return i;
                }
            }

            return -1;
        }
    }

    class UnionFind {
        private final int n;
        private final int[] parents;
        public int components;

        UnionFind(int n) {
            this.n = n;
            parents = new int[n];
            components = n;

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public boolean union(int parent, int child) {
            int parentParent = find(parent);
            int childParent = find(child);

            if (childParent != child || parentParent == childParent) {
                return false;
            }

            components--;
            parents[childParent] = parentParent;

            return true;
        }

        private int find(int node) {
            if (parents[node] != node) {
                parents[node] = find(parents[node]);
            }

            return parents[node];
        }
    }

    class Solution {
        public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
            UnionFind uf = new UnionFind(n);
            for (int node = 0; node < n; node++) {
                int[] children = { leftChild[node], rightChild[node] };
                for (int child : children) {
                    if (child == -1) {
                        continue;
                    }

                    if (!uf.union(node, child)) {
                        return false;
                    }
                }
            }

            return uf.components == 1;
        }
    }
}
