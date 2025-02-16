package leetcode.problems.google.medium.union_find;

import java.util.*;

public class FindIfAPathExists {
    /*
    * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
    * */

    // DFS and BFS will give us the answer in O(N) but UF is a good touch.
    class SolutionIterativeDFS {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            // Store all edges according to nodes in 'graph'.
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int[] edge : edges) {
                int a = edge[0], b = edge[1];
                graph.computeIfAbsent(a, val -> new ArrayList<Integer>()).add(b);
                graph.computeIfAbsent(b, val -> new ArrayList<Integer>()).add(a);
            }

            // Start from source node, add it to stack.
            boolean[] seen = new boolean[n];
            seen[source] = true;
            Stack<Integer> stack = new Stack<>();
            stack.push(source);

            while (!stack.isEmpty()) {
                int currNode = stack.pop();
                if (currNode == destination) {
                    return true;
                }
                // Add all unvisited neighbors of the current node to stack'
                // and mark it as visited.
                for (int nextNode : graph.get(currNode)) {
                    if (!seen[nextNode]) {
                        seen[nextNode] = true;
                        stack.push(nextNode);
                    }
                }
            }

            return false;
        }
    }
    class SolutionBFS {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            //Store all edges in 'graph'.
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int[] edge : edges) {
                int a = edge[0], b = edge[1];
                graph.computeIfAbsent(a, val -> new ArrayList<Integer>()).add(b);
                graph.computeIfAbsent(b, val -> new ArrayList<Integer>()).add(a);
            }

            // Store all the nodes to be visited in 'queue'.
            boolean[] seen = new boolean[n];
            seen[source] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);

            while (!queue.isEmpty()) {
                int currNode = queue.poll();
                if (currNode == destination) {
                    return true;
                }

                // For all the neighbors of the current node, if we haven't visit it before,
                // add it to 'queue' and mark it as visited.
                for (int nextNode : graph.get(currNode)) {
                    if (!seen[nextNode]) {
                        seen[nextNode] = true;
                        queue.offer(nextNode);
                    }
                }
            }

            return false;
        }
    }


    class SolutionUF {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            UnionFind uf = new UnionFind(n);

            for (int[] edge : edges) {
                uf.union(edge[0], edge[1]);
            }

            return uf.find(source) == uf.find(destination);
        }
        private static class UnionFind {
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
                    // Modify the root of the smaller group as the root of the
                    // larger group, also increment the size of the larger group.
                    this.root[rootX] = rootY;
                    this.rank[rootY] += this.rank[rootX];
                }
            }
        }
    }
}
