package algoexpert.binarytrees;

import java.util.ArrayList;

public class ReconstructBST {

    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    public BST reconstructBst(ArrayList<Integer> preOrderTraversalValues) {
        if(preOrderTraversalValues.size() == 0) return null;
        int cur = preOrderTraversalValues.get(0);
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for(int i= 1; i<preOrderTraversalValues.size(); i++) {
            if(i <= cur){
                left.add(i);
            }
            if(i > cur) {
                right.add(i);
            }
        }
        BST bt = new BST(cur);
        bt.left = reconstructBst(left);
        bt.right = reconstructBst(right);
        return bt;
    }
}
