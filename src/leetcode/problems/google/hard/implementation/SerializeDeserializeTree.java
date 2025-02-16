package leetcode.problems.google.hard.implementation;


import leetcode.TreeNode;

import java.util.*;

public class SerializeDeserializeTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    // Iterative
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root==null) return "";
            StringBuilder sb =new StringBuilder();
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while(!q.isEmpty()){
                TreeNode curr = q.poll();
                if(curr == null){
                    sb.append("n ");
                    continue;
                }
                sb.append(curr.val+" ");
                q.offer(curr.left);
                q.offer(curr.right);
            }
            return sb.toString();

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(Objects.equals(data, "")) return null;
            String[] vals = data.split(" ");
            TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            for(int i=1;i<vals.length;i++){
                TreeNode parent = q.poll();
                if(!vals[i].equals("n")){
                    TreeNode left = new TreeNode(Integer.parseInt(vals[i]));
                    parent.left = left;
                    q.offer(left);
                }
                if(!vals[++i].equals("n")){
                    TreeNode right = new TreeNode(Integer.parseInt(vals[i]));
                    parent.right=right;
                    q.offer(right);
                }
            }
            return root;
        }
    }


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Codecdfs {

        // Encodes a tree to a single string.
        public String rserialize(TreeNode root, String str) {
            // Recursive serialization.
            if (root == null) {
                str += "null,";
            } else {
                str += root.val + ",";
                str = rserialize(root.left, str);
                str = rserialize(root.right, str);
            }
            return str;
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return rserialize(root, "");
        }

        // Decodes your encoded data to tree.
        public TreeNode rdeserialize(List<String> l) {
            // Recursive deserialization.
            if (l.get(0).equals("null")) {
                l.remove(0);
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
            l.remove(0);
            root.left = rdeserialize(l);
            root.right = rdeserialize(l);

            return root;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] data_array = data.split(",");
            List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
            return rdeserialize(data_list);
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
}
