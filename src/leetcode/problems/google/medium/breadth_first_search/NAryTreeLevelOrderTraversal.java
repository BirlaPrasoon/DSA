package leetcode.problems.google.medium.breadth_first_search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NAryTreeLevelOrderTraversal {

    // This code is a modified version of the code posted by
// #zzzliu on the discussion forums.
    class Solution {

        class Node {
            public int val;
            public List<Node> children;

            public Node() {}

            public Node(int _val) {
                val = _val;
            }

            public Node(int _val, List<Node> _children) {
                val = _val;
                children = _children;
            }
        };

        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node node = queue.poll();
                    level.add(node.val);
                    queue.addAll(node.children);
                }
                result.add(level);
            }
            return result;
        }
    }
}
