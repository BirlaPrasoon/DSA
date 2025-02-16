package leetcode.problems.google.medium.depth_first_search.diff;

public class ChangeTheRootOfTheBinaryTree {
/*

    Given the root of a binary tree and a leaf node, reroot the tree so that the leaf is the new root.

    You can reroot the tree with the following steps for each node cur on the path starting from the leaf up to the root excluding the root:

    If cur has a left child, then that child becomes cur's right child.
    cur's original parent becomes cur's left child. Note that in this process the original parent's pointer to cur becomes null,
    making it have at most one child.

    Return the new root of the rerooted tree.

    Note: Ensure that your solution sets the Node.parent pointers correctly after rerooting or you will receive "Wrong Answer".

*/

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

    Node original_root;

    public Node flipBinaryTree(Node root, Node leaf) {
        original_root = root;
        return helper(leaf, null); // the new parent of the leaf node must be null
    }

    public Node helper(Node node, Node new_parent) {
        Node old_parent = node.parent;
        node.parent = new_parent;

        if (node.left == new_parent) node.left = null;
        if (node.right == new_parent) node.right = null;

        if (node == original_root) return node;

        if (node.left != null) node.right = node.left;
        node.left = helper(old_parent, node);

        return node;
    }


}
