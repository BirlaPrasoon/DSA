package leetcode.problems.google.medium.union_find;

import datastructures.UnionFind;

import java.util.*;

public class CountUnreachablePairsOfNodesInAnUndirectedGraph {

/*    You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.

    Return the number of pairs of different nodes that are unreachable from each other.*/

    class SolutionDFS {
        public int dfs(int node, Map<Integer, List<Integer>> adj, boolean[] visit) {
            int count = 1;
            visit[node] = true;
            if (!adj.containsKey(node)) {
                return count;
            }
            for (int neighbor : adj.get(node)) {
                if (!visit[neighbor]) {
                    count += dfs(neighbor, adj, visit);
                }
            }
            return count;
        }

        public long countPairs(int n, int[][] edges) {
            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (int[] edge : edges) {
                adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
                adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
            }

            long numberOfPairs = 0;
            long sizeOfComponent = 0;
            long remainingNodes = n;
            boolean[] visit = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!visit[i]) {
                    sizeOfComponent = dfs(i, adj, visit);
                    numberOfPairs += sizeOfComponent * (remainingNodes - sizeOfComponent);
                    remainingNodes -= sizeOfComponent;
                }
            }
            return numberOfPairs;
        }
    }

    class SolutionBFS {
        public int bfs(int node, Map<Integer, List<Integer>> adj, boolean[] visit) {
            Queue<Integer> q = new LinkedList<>();
            q.offer(node);
            int count = 1;
            visit[node] = true;

            while (!q.isEmpty()) {
                node = q.poll();
                if (!adj.containsKey(node)) {
                    continue;
                }
                for (int neighbor : adj.get(node)) {
                    if (!visit[neighbor]) {
                        visit[neighbor] = true;
                        count++;
                        q.offer(neighbor);
                    }
                }
            }
            return count;
        }

        public long countPairs(int n, int[][] edges) {
            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (int[] edge : edges) {
                adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
                adj.computeIfAbsent(edge[1], k -> new ArrayList<Integer>()).add(edge[0]);
            }

            long numberOfPairs = 0;
            long sizeOfComponent = 0;
            long remainingNodes = n;
            boolean[] visit = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!visit[i]) {
                    sizeOfComponent = bfs(i, adj, visit);
                    numberOfPairs += sizeOfComponent * (remainingNodes - sizeOfComponent);
                    remainingNodes -= sizeOfComponent;
                }
            }
            return numberOfPairs;
        }
    }

    class SolutionUF {
        public long countPairs(int n, int[][] edges) {
            UnionFind dsu = new UnionFind(n);
            for(int[] edge: edges) {
                dsu.union(edge[0], edge[1]);
            }

            Map<Integer, Integer> componentSize = new HashMap<>();
            for(int i= 0; i<n; i++) {
                int parent = dsu.find(i);
                if(componentSize.containsKey(parent)) {
                    componentSize.put(parent, componentSize.get(parent) + 1);
                } else {
                    componentSize.put(parent, 1);
                }
            }

            long numberOfPaths = 0;
            long remainingNodes = n;
            for (int size : componentSize.values()) {
                numberOfPaths += size * (remainingNodes - size);
                remainingNodes -= size;
            }
            return numberOfPaths;
        }
    }
}
