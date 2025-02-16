package algoexpert.binarytrees;

import java.util.*;

public class NodesAtKDistance {

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public ArrayList<Integer> findNodesDistanceK(
            BinaryTree tree, int target, int k
    ) {
        Queue<BinaryTree> q = new LinkedList<>();
        HashMap<BinaryTree, BinaryTree> parent = new HashMap<>();
        q.add(tree);
        parent.put(tree, null);
        int c = 0;
        BinaryTree need = null;
        while(!q.isEmpty()) {
            int s = q.size();
            for(int i = 0; i<s; i++) {
                BinaryTree cur = q.poll();
                if(cur.value == target) {
                    need = cur;
                }
                if(cur.left != null) {
                    q.offer(cur.left);
                    parent.put(cur.left, cur);
                }
                if(cur.right != null) {
                    q.offer(cur.right);
                    parent.put(cur.right, cur);
                }
            }
            c++;
        }
        c = 0;
        HashSet<BinaryTree> set = new HashSet<>();
        ArrayList<Integer> ans = new ArrayList<>();
        q.offer(need);
        while(!q.isEmpty()) {
            int s = q.size();
            for(int i = 0;i<s; i++) {
                BinaryTree cur = q.poll();
                set.add(cur);
                if(c == k) {
                    ans.add(cur.value);
                    continue;
                }
                // left
                if(cur.left != null &&!set.contains(cur.left)) {
                    q.add(cur.left);
                }
                // right
                if(cur.right!= null && !set.contains(cur.right)) {
                    q.add(cur.right);
                }
                // parent
                if(parent.get(cur) != null && !set.contains(parent.get(cur))) {
                    q.add(parent.get(cur));
                }
            }
            c++;
            if(c > k) {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(20);
        root.left = new BinaryTree(8);
        root.right = new BinaryTree(22);
        root.left.left = new BinaryTree(4);
        root.left.right = new BinaryTree(12);
        root.left.right.left = new BinaryTree(10);
        root.left.right.right = new BinaryTree(4);
        BinaryTree target = root.left.right;
        NodesAtKDistance k = new NodesAtKDistance();
        List<Integer> result = k.findNodesDistanceK(root, target.value, 2);
        System.out.print("[");
        for (int i = 0; i < result.size() - 1; i++) {
            System.out.print(result.get(i) + ", ");
        }
        System.out.println(result.get(result.size() - 1) + "]");
    }
}
