package leetcode.problems.google.hard.graphs.dpOnGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathVisitingAllNodes {
/*
    You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.

    Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

    Example 1:


Input: graph = [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]

Example 2:


Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]

Constraints:

n == graph.length
1 <= n <= 12
0 <= graph[i].length < n
graph[i] does not contain i.
If graph[a] contains b, then graph[b] contains a.
The input graph is always connected.

        */

    class Solution {

        /*
        *
        * Intuition

The previous approach is comparatively slow but works because the bounds are small. The main problem is that we are working backwards and need a DFS starting from every node. The optimal path may end at node 0, but we still need to check all other nodes to make sure. As with any problem that asks us to find the shortest path, it may be more intuitive to approach this problem using BFS.

Because BFS guarantees the shortest path in an unweighted graph, as soon as we find an answer, we know it is the optimal one, unlike in the previous solution where we needed to explore all reachable states to make sure.

This approach is similar to the previous one in terms of logic. However, instead of using top-down dynamic programming in the form of DFS + memoization, we will perform BFS by implementing a queue. Instead of starting at endingMask, we will start at the base case masks - only having one bit set to 1, and then work our way towards endingMask.

Because we were working backwards in the previous approach, for all neighbors, we needed to check (neighbor, mask) and (neighbor, mask ^ (1 << node)). Now, since we're moving forwards, the state we should consider next from each (node, mask) is different. If we are at node A and move to node B, it doesn't matter if we go B -> A -> B or A -> B - in both cases, upon arriving at B, we want our mask to have the bit corresponding to node B set as 1. This is a nice improvement on the previous approach as for each neighbor, we only need to consider 1 possibility instead of 2. Since we always want the bit to be set to 1, we will use an OR operation with 1 << neighbor to make sure the bit is set to 1.

More formally, for any given state (node, mask), we can traverse to (neighbor, mask | (1 << neighbor)) for all neighbors in graph[node]. We will still need to use some space to ensure that we don't revisit states and create an infinite cycle, but this time we don't need to associate the states with any values, just a flag to indicate if it has been visited yet or not.

Algorithm

If graph only contains one node, then return 0 as we can start at node 0 and complete the problem without taking any steps.

Initialize some variables:

n, as the length of graph.
endingMask = (1 << n) - 1, a bitmask that represents all nodes being visited.
seen, a data structure that will be used to indicate if we have visited a state to prevent cycles.
queue, a data structure that implements an abstract queue used for our BFS.
steps, an integer that keeps track of which step we are on. Since BFS gaurantees a shortest path, as soon as we encounter endingMask, we can return steps.
Populate queue and seen with the base cases (starting at all nodes with the mask set to having only visited the given node). This is (i, 1 << i) for all i from 0 to n - 1.

Perform a BFS:

Initialize nextQueue, which will replace queue at the end of the current step.
Loop through the current queue. For each state (node, mask), loop through graph[node]. For each neighbor, declare a new state (neighbor, nextMask), where nextMask = mask | (1 << neighbor). If nextMask == endingMask, then that means taking one more step to the neighbor will complete visiting all nodes, so return 1 + steps. Otherwise, if this new state has not yet been visited, then add it nextQueue and seen.
After looping through the current queue, increment steps by 1 and replace queue with nextQueue.
The constraints state that the input graph is always connected, therefore there will always be an answer. The return statement in the BFS will always trigger, and we don't need to worry about other cases.


        * */


        // Time: 2^n.n^2
        // Space: 2^n.n
        public int shortestPathLength(int[][] graph) {
            if (graph.length == 1) {
                return 0;
            }
            int n = graph.length;
            int endingMask = (1 << n) - 1;
//            System.out.println("Ending mask: " + endingMask); // 111111
            boolean[][] seen = new boolean[n][endingMask];
            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                queue.add(new int[]{i, 1 << i});
                seen[i][1 << i] = true;
            }
            int steps = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] currentPair = queue.poll();
                    int node = currentPair[0];
                    int mask = currentPair[1];
                    for (int neighbor : graph[node]) {
                        int nextMask = mask | (1 << neighbor);
                        if (nextMask == endingMask) {
                            return 1 + steps;
                        }
                        if (!seen[neighbor][nextMask]) {
                            seen[neighbor][nextMask] = true;
                            queue.add(new int[]{neighbor, nextMask});
                        }
                    }
                }
                steps++;
            }
            return -1;
        }
    }

    class SolutionMine {
        public int shortestPathLength(int[][] adj) {
            int n = adj.length;
            int END_MASK = (1<<n) -1;
            HashSet<State> visited = new HashSet<>();
            Queue<State> q = new LinkedList<>();
            for(int i = 0; i<n; i++) {
                int curMask = 1<<i;
                State initial = new State(i, curMask, 0);
                q.add(initial);
                visited.add(initial);
            }
            while(!q.isEmpty()) {
                int size= q.size();
                for (int i = 0; i < size; i++) {
                    State state = q.poll();
                    if(state.mask == END_MASK) return state.steps;
                    for(int nei: adj[state.node]) {
                        int newMask = state.mask | 1<<nei;
                        State newState = new State(nei, newMask, state.steps+1);
                        if(visited.contains(newState)) continue;
                        visited.add(newState);
                        q.add(newState);
                    }
                }
            }

            return -1;
        }
    }

    record State(int node, int mask, int steps){
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State state)) return false;

            return node == state.node && mask == state.mask;
        }

        public int hashCode() {
            int result = node;
            result = 31 * result + mask;
            return result;
        }
    }


}
