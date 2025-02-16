package leetcode.problems.google.medium.breadth_first_search;

import java.util.*;

public class MostProfitablePathInATree {

/*
    There is an undirected tree with n nodes labeled from 0 to n - 1, rooted at node 0.
    You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge
    between nodes ai and bi in the tree.

    At every node i, there is a gate. You are also given an array of even integers amount, where amount[i] represents:

    the price needed to open the gate at node i, if amount[i] is negative, or,
    the cash reward obtained on opening the gate at node i, otherwise.
    The game goes on as follows:

    Initially, Alice is at node 0 and Bob is at node bob.
    At every second, Alice and Bob each move to an adjacent node. Alice moves towards some leaf node, while Bob moves towards node 0.
    For every node along their path, Alice and Bob either spend money to open the gate at that node, or accept the reward. Note that:
    If the gate is already open, no price will be required, nor will there be any cash reward.
    If Alice and Bob reach the node simultaneously, they share the price/reward for opening the gate there. In other words,
    if the price to open the gate is c, then both Alice and Bob pay c / 2 each. Similarly, if the reward at the gate is c,
    both of them receive c / 2 each.

    If Alice reaches a leaf node, she stops moving. Similarly, if Bob reaches node 0, he stops moving.
    Note that these events are independent of each other.
    Return the maximum net income Alice can have if she travels towards the optimal leaf node.
*/

    class Solution {
        HashMap<Integer, Integer> bobPath = new HashMap<>();
        boolean[] visited;

        public boolean dfs(List<List<Integer>> graph, int src, int dest, HashMap<Integer, Integer> pPath, int time) {

            if (dest == src) {
                return true;
            }
            visited[src] = true;

            for (int nei : graph.get(src)) {
                if (!visited[nei]) {
                    if (dfs(graph, nei, dest, pPath, time + 1)) {
                        pPath.put(nei, time);
                        return true;
                    }
                }
            }
            visited[src] = false;
            return false;
        }

        public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
            List<List<Integer>> graph = new ArrayList<>();
            visited = new boolean[amount.length];
            int[] inDegree = new int[amount.length];
            for (int i = 0; i < amount.length; i++) {
                graph.add(new ArrayList<>());
            }

            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
                inDegree[edge[0]]++;
                inDegree[edge[1]]++;
            }


            dfs(graph, bob, 0, bobPath, 1);
            bobPath.put(bob, 0);

            Arrays.fill(visited, false);

            int maxAmt = Integer.MIN_VALUE;
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{0, amount[0]});
            visited[0] = true;
            int aliceTime = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                aliceTime++;
                for (int i = 0; i < size; i++) {
                    int[] nodeWithAmt = queue.remove();
                    for (int curNode : graph.get(nodeWithAmt[0])) {
                        if (visited[curNode]) {
                            continue;
                        }
                        int amt = nodeWithAmt[1];
                        // case 1:  bob hasn't visited this node or his time was greater than mine.
                        if (!bobPath.containsKey(curNode) || aliceTime < bobPath.get(curNode)) {
                            amt += amount[curNode];
                        } else if (aliceTime == bobPath.get(curNode)) {
                            amt += amount[curNode] / 2;
                        }
                        if (inDegree[curNode] == 1) {
                            maxAmt = Math.max(maxAmt, amt);
                        }
                        visited[curNode] = true;
                        queue.add(new int[]{curNode, amt});
                    }
                }
            }


            return maxAmt;
        }
    }

}
