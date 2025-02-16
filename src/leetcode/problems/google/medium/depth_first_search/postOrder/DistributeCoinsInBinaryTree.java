package leetcode.problems.google.medium.depth_first_search.postOrder;

import leetcode.TreeNode;

public class DistributeCoinsInBinaryTree {

/*
    You are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are n coins in total throughout the whole tree.

    In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from parent to child, or from child to parent.

    Return the minimum number of moves required to make every node have exactly one coin.

*/

    class Solution {

        private int moves;

        public int distributeCoins(TreeNode root) {
            moves = 0;
            dfs(root);
            return moves;
        }

        private int dfs(TreeNode current) {
            if (current == null) return 0;

            // Calculate the coins each subtree has available to exchange
            int leftCoins = dfs(current.left);
            int rightCoins = dfs(current.right);

            // Add the total number of exchanges to moves
            moves += Math.abs(leftCoins) + Math.abs(rightCoins);

            // The number of coins current has available to exchange
            return (current.val - 1) + leftCoins + rightCoins;
        }
    }

}
