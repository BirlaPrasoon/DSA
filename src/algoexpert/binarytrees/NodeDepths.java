package algoexpert.binarytrees;

// Even in this simple question, I made a mistake of what should be the value of sumDepth.
public class NodeDepths {

    public static int nodeDepths(BinaryTree root) {
        return helper(root, 0);
    }

    public static int helper(BinaryTree root, int depth) {
        int sumDepth = depth;
        if(root.left != null) {
            sumDepth += helper(root.left, depth+1);
        }
        if(root.right != null) {
            sumDepth += helper(root.right, depth+1);
        }
        return sumDepth;
    }

    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.left.left = new BinaryTree(4);
        root.left.left.left = new BinaryTree(8);
        root.left.left.right = new BinaryTree(9);
        root.left.right = new BinaryTree(5);
        root.right = new BinaryTree(3);
        root.right.left = new BinaryTree(6);
        root.right.right = new BinaryTree(7);
        int actual = nodeDepths(root);
        System.out.println(actual);
    }
}
