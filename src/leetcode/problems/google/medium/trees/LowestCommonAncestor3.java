package leetcode.problems.google.medium.trees;


import java.util.HashSet;

public class LowestCommonAncestor3 {

// Definition for a TreeNode.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};


    class Solution {
        public Node lowestCommonAncestor(Node p, Node q) {
            if(p == null || q == null) return null;

            HashSet<Node> ansestors = new HashSet<Node>();

            while(p != null) {
                ansestors.add(p);
                p = p.parent;
            }

            while(!ansestors.contains(q)) {
                q = q.parent;
            }

            return q;
        }
    }
}
