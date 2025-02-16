package leetcode.problems.google.medium.union_find;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfOperationsToMakeNetworkConnected {


    /*
    * There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network
    * where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any
    * other computer directly or indirectly through the network.
    *
    * You are given an initial computer network connections. You can extract certain cables between two directly connected
    * computers, and place them between any pair of disconnected computers to make them directly connected.
    *
    * Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.
    *
    * */

    class SolutionDFS {
        public void dfs(int node, Map<Integer, List<Integer>> adj, boolean[] visit) {
            visit[node] = true;
            if (!adj.containsKey(node)) {
                return;
            }
            for (int neighbor : adj.get(node)) {
                if (!visit[neighbor]) {
                    visit[neighbor] = true;
                    dfs(neighbor, adj, visit);
                }
            }
        }

        public int makeConnected(int n, int[][] connections) {
            if (connections.length < n - 1) {
                return -1;
            }

            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (int[] connection : connections) {
                adj.computeIfAbsent(connection[0], k -> new ArrayList<Integer>()).add(connection[1]);
                adj.computeIfAbsent(connection[1], k -> new ArrayList<Integer>()).add(connection[0]);
            }

            int numberOfConnectedComponents = 0;
            boolean[] visit = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!visit[i]) {
                    numberOfConnectedComponents++;
                    dfs(i, adj, visit);
                }
            }

            return numberOfConnectedComponents - 1;
        }
    }
    class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++)
                parent[i] = i;
            rank = new int[size];
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union_set(int x, int y) {
            int xset = find(x), yset = find(y);
            if (xset == yset) {
                return;
            } else if (rank[xset] < rank[yset]) {
                parent[xset] = yset;
            } else if (rank[xset] > rank[yset]) {
                parent[yset] = xset;
            } else {
                parent[yset] = xset;
                rank[xset]++;
            }
        }
    }

    class Solution {
        public int makeConnected(int n, int[][] connections) {
            if(connections.length < n-1) {
                return -1;
            }

            UnionFind dsu = new UnionFind(n);
            int numberOfConnectedComponents = n;
            for (int[] connection : connections) {
                if (dsu.find(connection[0]) != dsu.find(connection[1])) {
                    numberOfConnectedComponents--;
                    dsu.union_set(connection[0], connection[1]);
                }
            }

            return numberOfConnectedComponents - 1;
        }
    }
}
