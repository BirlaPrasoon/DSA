package leetcode.problems.google.medium.trees;

import leetcode.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BinaryTreeFromDescription {
    /*
    *
    * You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

If isLefti == 1, then childi is the left child of parenti.
If isLefti == 0, then childi is the right child of parenti.
Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.
    *
    * */

    /*
    * Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]
Explanation: The root node is the node with value 50 since it has no parent.
The resulting binary tree is shown in the diagram.
    * */

    /*
    * Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]
Explanation: The root node is the node with value 1 since it has no parent.
The resulting binary tree is shown in the diagram.
    * */

    class Solution {

        public TreeNode createBinaryTree(int[][] descriptions) {
            // Maps values to TreeNode pointers
            Map<Integer, TreeNode> nodeMap = new HashMap<>();

            // Stores values which are children in the descriptions
            Set<Integer> children = new HashSet<>();

            // Iterate through descriptions to create nodes and set up tree structure
            for (int[] description : descriptions) {
                // Extract parent value, child value, and whether it is a
                // left child (1) or right child (0)
                int parentValue = description[0];
                int childValue = description[1];
                boolean isLeft = description[2] == 1;

                // Create parent and child nodes if not already created
                if (!nodeMap.containsKey(parentValue)) {
                    nodeMap.put(parentValue, new TreeNode(parentValue));
                }
                if (!nodeMap.containsKey(childValue)) {
                    nodeMap.put(childValue, new TreeNode(childValue));
                }

                // Attach child node to parent's left or right branch
                if (isLeft) {
                    nodeMap.get(parentValue).left = nodeMap.get(childValue);
                } else {
                    nodeMap.get(parentValue).right = nodeMap.get(childValue);
                }

                // Mark child as a child in the set
                children.add(childValue);
            }

            // Find and return the root node
            for (TreeNode node : nodeMap.values()) {
                if (!children.contains(node.val)) {
                    return node; // Root node found
                }
            }

            return null; // Should not occur according to problem statement
        }
    }
}
