package algoexpert.binarytrees;

public class HeightBalancedBinaryTree {

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;
        public int height = 0;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public boolean heightBalancedBinaryTree(BinaryTree tree) {
        if(tree == null) return true;
//        height(tree);
        int l = height(tree.left);
        int r = height(tree.right);
        // System.out.println("Node: " + tree.value + " l: " + l + " r:" + r);
        if(Math.abs(l - r) >1) return false;
        return heightBalancedBinaryTree(tree.left) && heightBalancedBinaryTree(tree.right);
    }

    public int height(BinaryTree root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) {
            System.out.println("Node: " + root.value + " height: " + root.height);
            // this is the difference in the height function between BinaryTreeDiameter & HeightBalancedBinaryTree
            return 1;
        }
        if(root.height != 0) return root.height;

        int leftHeight = 0, rightHeight = 0;
        if(root.left != null) {
            leftHeight = height(root.left);
        }
        if(root.right != null) {
            rightHeight =  height(root.right);
        }
        root.height = Math.max(leftHeight, rightHeight) + 1;

        System.out.println("Node: " + root.value + " height: " + root.height);
        return root.height;
    }
}
