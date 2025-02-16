package leetcode.problems.google.medium.union_find;

import java.util.*;

public class ReachableNodesWithRestrictions {
    /* *
    *
    * There is an undirected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
    * You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that
    * there is an edge between nodes ai and bi in the tree. You are also given an integer array restricted which represents restricted nodes.
    *
    * Return the maximum number of nodes you can reach from node 0 without visiting a restricted node.
    *
    * Note that node 0 will not be a restricted node.
    *
    * */

    class Solution {
        public int reachableNodes(int n, int[][] edges, int[] restricted) {
            //Store all edges in 'neighbors'.
            Map<Integer, List<Integer>> neighbors = new HashMap<>();
            for (int[] edge : edges) {
                int a = edge[0], b = edge[1];
                neighbors.computeIfAbsent(a, value -> new ArrayList<Integer>()).add(b);
                neighbors.computeIfAbsent(b, value -> new ArrayList<Integer>()).add(a);
            }

            // Mark the nodes in 'restricted' as visited.
            Set<Integer> seen = new HashSet<>();
            for (int node : restricted) {
                seen.add(node);
            }

            // Store all the nodes to be visited in 'queue'.
            int ans = 0;
            seen.add(0);
            Queue<Integer> queue = new LinkedList<>(Arrays.asList(0));

            while (!queue.isEmpty()) {
                int currNode = queue.poll();
                ans++;

                // For all the neighbors of the current node, if we haven't visit it before,
                // add it to 'queue' and mark it as visited.
                for (int nextNode : neighbors.get(currNode)) {
                    if (!seen.contains(nextNode)) {
                        seen.add(nextNode);
                        queue.offer(nextNode);
                    }
                }
            }

            return ans;
        }
    }

    class UnionFind {
        private int[] root;
        private int[] rank;
        public UnionFind(int n) {
            this.root = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; ++i) {
                this.root[i] = i;
                this.rank[i] = 1;
            }
        }
        public int find(int x) {
            if (this.root[x] != x) {
                this.root[x] = find(this.root[x]);
            }
            return this.root[x];
        }
        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX != rootY) {
                if (this.rank[rootX] > this.rank[rootY]) {
                    int tmp = rootX;
                    rootX = rootY;
                    rootY = tmp;
                }
                this.root[rootX] = rootY;
                this.rank[rootY] += this.rank[rootX];
            }
        }
        public int getSize(int x) {
            return this.rank[find(x)];
        }
    }

    class SolutionUF {
        public int reachableNodes(int n, int[][] edges, int[] restricted) {
            UnionFind uf = new UnionFind(n);
            Set<Integer> restSet = new HashSet<>();

            for(int node : restricted){
                restSet.add(node);
            }

            for (int[] edge : edges) {
                int a = edge[0], b = edge[1];
                if (!restSet.contains(a) && !restSet.contains(b)) {
                    uf.union(a, b);
                }
            }
            return uf.getSize(0);
        }
    }
}
