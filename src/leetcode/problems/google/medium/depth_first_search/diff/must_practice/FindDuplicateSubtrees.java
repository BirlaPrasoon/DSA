package leetcode.problems.google.medium.depth_first_search.diff.must_practice;

import leetcode.TreeNode;

import java.util.*;

public class FindDuplicateSubtrees {
/*

    Given the root of a binary tree, return all duplicate subtrees.

    For each kind of duplicate subtrees, you only need to return the root node of any one of them.

    Two trees are duplicate if they have the same structure with the same node values.

*/

    // Approach 1: String representation approach

    // O(n^2)
    class Solution {
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            List<TreeNode> res = new LinkedList<>();
            traverse(root, new HashMap<>(), res);
            return res;
        }

        public String traverse(TreeNode node, Map<String, Integer> cnt, List<TreeNode> res) {
            if (node == null) {
                return "";
            }
            String representation = "(" + traverse(node.left, cnt, res) + ")" + node.val + "(" + traverse(node.right, cnt, res) + ")";
            cnt.put(representation, cnt.getOrDefault(representation, 0) + 1);
            if (cnt.get(representation) == 2) {
                res.add(node);
            }
            return representation;
        }
    }


    // O(n) - Assign IDs.
    class SolutionOptimizedStringMatching {
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            List<TreeNode> res = new LinkedList<>();
            traverse(root, new HashMap<>(), new HashMap<>(), res);
            return res;
        }

        public int traverse(TreeNode node, Map<String, Integer> tripletToID, Map<Integer, Integer> cnt, List<TreeNode> res) {
            if (node == null) {
                return 0;
            }
            String triplet = traverse(node.left, tripletToID, cnt, res) + "," + node.val + "," + traverse(node.right, tripletToID, cnt, res);
            if (!tripletToID.containsKey(triplet)) {
                tripletToID.put(triplet, tripletToID.size() + 1);
            }
            int id = tripletToID.get(triplet);
            cnt.put(id, cnt.getOrDefault(id, 0) + 1);
            if (cnt.get(id) == 2) {
                res.add(node);
            }
            return id;
        }
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class SolutionAnotherVersionOfHasing {
        /** Approach 2
         1. Creating a my own version of TreeNode that has the attributes - val, left, right and hash
         a. override hashCode() method to return hash, checks if there is a collision with the same hash.
         b. override equals() method - if there is a collision, checks for hash, val and left and right subtrees
         TC - O(N), SC - O(N) */
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            List<TreeNode> result = new ArrayList<>();
            if(root == null) return result;

            Map<MyTreeNode, Integer> hm = new HashMap<>();
            // prime number 31 because its used in hash computation in hashmap java, for more details refer to algo patterns notes
            recursion(root, result, hm, 1, 31);
            return result;
        }

        public MyTreeNode recursion(TreeNode node, List<TreeNode> result, Map<MyTreeNode, Integer> hm, int hash, int prime) {
            if(node == null) return null;
            MyTreeNode left = recursion(node.left, result, hm, hash, prime);
            MyTreeNode right = recursion(node.right, result, hm, hash, prime);

            hash = hash * prime + node.val;

            if(left != null) hash = hash + left.hash;
            if(right != null) hash = hash + right.hash;

            MyTreeNode cur = new MyTreeNode(node.val, hash, left, right);

            int count = hm.getOrDefault(cur, 0) + 1;
            hm.put(cur, count);

            if(count == 2) {
                result.add(node);
            }
            return cur;
        }

        class MyTreeNode {
            int val;
            int hash;
            MyTreeNode left;
            MyTreeNode right;

            public MyTreeNode(int val, int hash, MyTreeNode left, MyTreeNode right) {
                this.val = val;
                this.hash = hash;
                this.left = left;
                this.right = right;
            }

            public int hashCode() {
                return hash;
            }

            public boolean equals(Object o) {
                MyTreeNode node = (MyTreeNode) o;
                return node.val == this.val && node.hash == this.hash && equals(this.left, node.left) && equals(this.right, node.right);
            }

            public boolean equals(MyTreeNode a, MyTreeNode b) {
                if(a == null && b == null) return true;
                if(a == null || b == null) return false;
                return a.equals(b);
            }
        }
    }


}
