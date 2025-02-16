package leetcode.problems.google.medium.depth_first_search.inorder;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ClosestNodeQueriesInBinarySearchTree {

    class Solution {
        public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {

            // first create a tree set and add all node values to this set
            TreeSet<Integer> set = new TreeSet<>();

            // perform any tree traversal
            inOrder(root,set);

            // create a result array
            List<List<Integer>> result = new ArrayList<>(queries.size());

            // go through each query
            for (Integer query : queries) {

                result.add(new ArrayList<>());
                int largest = -1;

                if (set.floor(query) != null) {
                    largest = set.floor(query);
                }

                int smallest = -1;
                if (set.ceiling(query) != null) {
                    smallest = set.ceiling(query);
                }

                result.get(result.size() - 1).add(largest);
                result.get(result.size() - 1).add(smallest);
            }
            return result;

        }

        private void inOrder(TreeNode root, TreeSet set){
            if(root==null) return;
            inOrder(root.left, set);
            set.add(root.val);
            inOrder(root.right, set);
        }
    }
}
