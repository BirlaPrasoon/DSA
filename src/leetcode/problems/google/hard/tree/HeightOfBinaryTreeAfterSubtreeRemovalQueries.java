package leetcode.problems.google.hard.tree;

import java.util.*;

public class HeightOfBinaryTreeAfterSubtreeRemovalQueries {
    static class TreeNode {
        int val;
        public TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }
    int dfs(TreeNode root, Map<Integer, int[]> heights, int level) {
        if (root == null) return 0;
        heights.put(root.val, new int[]{
                1 + Math.max(dfs(root.left, heights, level + 1), dfs(root.right, heights, level + 1)), // height of tree
                level // current level
        });
        return heights.get(root.val)[0];
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        // map for storing height and level of each node
        Map<Integer, int[]> heights = new HashMap<>();
        // map for storing heights at each level
        Map<Integer, PriorityQueue<Integer>> levelOrderHeights = new HashMap<>();

        // dfs to calculate the height and level of each node
        dfs(root, heights, 0);

        for (int i = 0; i < heights.get(root.val)[0]; ++i)
            levelOrderHeights.put(i, new PriorityQueue<>((a, b) -> Integer.compare(b, a)));
        System.out.println(levelOrderHeights);
        // BFS to calculate heights and each level
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i<size; i++){
                TreeNode node = q.poll();
                assert node != null;
                levelOrderHeights.get(level).add(heights.get(node.val)[0]);
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            level++;
        }
        System.out.println(levelOrderHeights);
        int[] ans = new int[queries.length];
        int i = 0;
        int rootHeight = heights.get(root.val)[0];
        for (int query : queries) {
            int height = heights.get(query)[0];
            int curlevel = heights.get(query)[1];
            PriorityQueue<Integer> curheights = levelOrderHeights.get(curlevel);

            // if height of the deleted node is not the maximum at that level, then it will have no impact on the root height
            if (height != curheights.peek()) ans[i++] = rootHeight - 1;

                // if it is the only node at that level, means height of the root will get reduced by height of the node subtree
            else if (curheights.size() == 1) ans[i++] = rootHeight - height - 1;

                // if deleted node has the max height at that level then the height of the root get reduced by the difference of max and second max height at that level
            else {
                int curHeight = curheights.poll(); // best height taken
                int res = rootHeight - curHeight + (curheights.peek() /* second-best height */) - 1;
                curheights.add(curHeight); // best height added back as it shouldn't affect the next query
                ans[i++] = res;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        HeightOfBinaryTreeAfterSubtreeRemovalQueries solution = new HeightOfBinaryTreeAfterSubtreeRemovalQueries();

        // Example tree:
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        TreeNode nine = new TreeNode(9);

        five.left = eight;
        five.right = nine;
        nine.left = three;
        nine.right = seven;
        eight.left = two;
        eight.right = one;
        two.left = four;
        two.right = six;

        // Example queries:
        int[] queries = {3,2,4,8};

        // Run the solution
        int[] results = solution.treeQueries(five, queries);

        // Output results
        System.out.println(Arrays.toString(results)); // Expected: [2, 3, 3]
    }
}
