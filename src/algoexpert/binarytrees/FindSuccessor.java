package algoexpert.binarytrees;

public class FindSuccessor {

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;
        public BinaryTree parent = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
        if(node.right != null) return minimumTree(node.right);
        BinaryTree y = node.parent;
        while(y!= null && node == y.right) {
            node = y;
            y = y.parent;
        }
        return y;
    }

    public BinaryTree minimumTree(BinaryTree tree) {
        while(tree.left != null) tree = tree.left;
        return tree;
    }

    public static void main(String[] args) {

    }
}
