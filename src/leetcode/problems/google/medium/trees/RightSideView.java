package leetcode.problems.google.medium.trees;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RightSideView {

    class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            Map<Integer, Integer> map = new HashMap<>();
            helper(root, 0, map);
            List<Integer> list = new ArrayList<>();
            int i = 0;
            while (map.containsKey(i)) {
                list.add(map.get(i));
                i++;
            }
            return list;
        }

        void helper(TreeNode root, int depth, Map<Integer, Integer> map) {
            if(root == null ) return;
            helper(root.left, depth+1, map);
            map.put(depth, root.val);
            helper(root.right, depth+1, map);
        }
    }

}
