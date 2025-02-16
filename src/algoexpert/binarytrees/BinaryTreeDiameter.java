package algoexpert.binarytrees;

public class BinaryTreeDiameter {

    // This is an input class. Do not edit.
    int maxDiameter = 0;
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;
        public int height = 0;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public int binaryTreeDiameter(BinaryTree tree) {
        // Write your code here.
        height(tree);

        return maxDiameter;
    }

    public int height(BinaryTree root) {
        if(root.left == null && root.right == null) {
            System.out.println("Node: " + root.value + " height: " + root.height);
            return 0;
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
        if(root.left != null && root.right != null) {
            if(leftHeight + rightHeight + 2 > maxDiameter) {
                maxDiameter = leftHeight + rightHeight + 2;
            }
        } else if(root.left != null ) {
            if(leftHeight + 1 > maxDiameter) {
                maxDiameter = leftHeight + 1;
            }
        } else if(root.right != null) {
            if(rightHeight + 1 > maxDiameter) {
                maxDiameter = rightHeight + 1;
            }
        }

        System.out.println("Node: " + root.value + " height: " + root.height);
        return root.height;
    }
    public static void main(String[] args) {

    }
}
