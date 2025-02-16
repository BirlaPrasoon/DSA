package algoexpert.binarytrees;

public class SplitBinaryTree {

    // This is an input class. Do not edit.
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;
        public int sum;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    private int helper(BinaryTree tree, int sum) {
        if(tree == null) return 0;
        if(tree.left == null && tree.right == null) return 0;
        if(tree.left != null && tree.right != null) {
            int leftSumWithRoot = tree.left.sum + tree.value + sum;
            int rightSumWithRoot = tree.right.sum + tree.value + sum;
            int leftSumWithoutRoot = tree.left.sum;
            int rightSumWithoutRoot = tree.right.sum;
            if(leftSumWithRoot == rightSumWithoutRoot) {
                return leftSumWithRoot;
            }
            if(leftSumWithoutRoot == rightSumWithRoot){
                return rightSumWithRoot;
            }
            // else we need to recurse for left tree & right tree;
            // whichever returns non 0, return that else return 0;
            int leftSum = helper(tree.left, tree.value + sum + tree.right.sum);
            if(leftSum > 0) return leftSum;
            return helper(tree.right, tree.value + sum + tree.left.sum);
        }
        if(tree.right == null) {
            int curSum = sum + tree.value;
            if(tree.left.sum == curSum) return curSum;
            return helper(tree.left, curSum);
        }

        int curSum = sum + tree.value;
        if(tree.right.sum == curSum) return curSum;
        return helper(tree.right, curSum);
    }

    public int splitBinaryTree(BinaryTree tree) {
        sum(tree);
        return helper(tree, 0);
    }

    public int sum(BinaryTree tree) {
        if(tree == null) return 0;
        tree.sum = tree.value + sum(tree.left) + sum(tree.right);
        return tree.sum;
    }

    public static void main(String[] args) {

    }
}
