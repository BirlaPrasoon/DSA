package leetcode.problems.google.hard.graphs.dijkstra;

import leetcode.Pair;

import java.util.*;

public class DesignGraphWithShortestPathCalculator {

    /**
     * There is a directed weighted graph that consists of n nodes numbered from 0 to n - 1. The edges of the graph are initially represented by the given array edges where edges[i] = [fromi, toi, edgeCosti] meaning that there is an edge from fromi to toi with the cost edgeCosti.
     * </br>
     * Implement the Graph class:</br>
     * </br>
     * Graph(int n, int[][] edges) initializes the object with n nodes and the given edges.</br>
     * addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to, edgeCost]. It is guaranteed that there is no edge between the two nodes before adding this one.</br>
     * int shortestPath(int node1, int node2) returns the minimum cost of a path from node1 to node2. If no path exists, return -1. The cost of a path is the sum of the costs of the edges in the path.
     * </br></br>
     * <p>
     * Example 1:</br>
     * <p>
     * Input</br>
     * ["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
     * [[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]</br>
     * Output</br>
     * [null, 6, -1, null, 6]</br>
     * <p>
     * Explanation</br>
     * Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);</br>
     * g.shortestPath(3, 2); // return 6. The shortest path from 3 to 2 in the first diagram above is 3 -> 0 -> 1 -> 2 with a total cost of 3 + 2 + 1 = 6.</br>
     * g.shortestPath(0, 3); // return -1. There is no path from 0 to 3.</br>
     * g.addEdge([1, 3, 4]); // We add an edge from node 1 to node 3, and we get the second diagram above.</br>
     * g.shortestPath(0, 3); // return 6. The shortest path from 0 to 3 now is 0 -> 1 -> 3 with a total cost of 2 + 4 = 6.</br>
     * </br>
     *
     * <b>Constraints:</b>
     * </br>
     * <ul>
     * <li>1 <= n <= 100</br></li>
     * <li>0 <= edges.length <= n * (n - 1)</br></li>
     * <li>edges[i].length == edge.length == 3</br></li>
     * <li>0 <= fromi, toi, from, to, node1, node2 <= n - 1</br></li>
     * <li>1 <= edgeCosti, edgeCost <= 10<sup>6</sup></br></li>
     * <li>There are no repeated edges and no self-loops in the graph at any point.</br></li>
     * <li>At most 100 calls will be made for addEdge.</br></li>
     * <li>At most 100 calls will be made for shortestPath.</br></li>
     * </ul>
     */

    class Graph {
        List<List<Pair<Integer, Integer>>> graph;

        public Graph(int n, int[][] edges) {
            graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] e : edges) {
                addEdge(e);
            }
        }

        public void addEdge(int[] edge) {
            graph.get(edge[0]).add(new Pair<>(edge[1], edge[2]));
        }

        public int shortestPath(int node1, int node2) {
            int n = graph.size();
            var pq = new PriorityQueue<List<Integer>>(Comparator.comparingInt(list -> list.get(0)));
            int[] costForNode = new int[n];
            Arrays.fill(costForNode, Integer.MAX_VALUE);
            costForNode[node1] = 0;
            pq.offer(Arrays.asList(0, node1));

            while (!pq.isEmpty()) {
                List<Integer> curr = pq.poll();
                int currCost = curr.get(0);
                int currNode = curr.get(1);

                if (currCost > costForNode[currNode]) {
                    continue;
                }
                if (currNode == node2) {
                    return currCost;
                }
                for (Pair<Integer, Integer> neighbor : graph.get(currNode)) {
                    int neighborNode = neighbor.getKey();
                    int cost = neighbor.getValue();
                    int newCost = currCost + cost;

                    if (newCost < costForNode[neighborNode]) {
                        costForNode[neighborNode] = newCost;
                        pq.offer(Arrays.asList(newCost, neighborNode));
                    }
                }
            }

            return -1;
        }

        public int shortestPath2(int node1, int node2) {
            int n = graph.size();
            int[] cost = new int[n];
            Arrays.fill(cost, Integer.MAX_VALUE);
            cost[node1] = 0;
            var pq = new PriorityQueue<int[]>((a,b) -> a[1]- b[1]);
//            boolean[] visited = new boolean[n];
            pq.add(new int[]{node1, 0});
            while(!pq.isEmpty()) {
                int[] cur = pq.poll();
                int curNode = cur[0];
                int curWt = cur[1];
                if(curNode == node2){
                    return curWt;
                }
                for(Pair<Integer, Integer> nei: graph.get(curNode)) {
                    // We do not use visited set with Dijkstra..., same node may come shorter via some other path.
//                    if(visited[nei.getKey()]) continue;
                    int newWt = curWt + nei.getValue();
                    int newNode = nei.getKey();
                    if(newWt < cost[newNode]) {
//                        visited[newNode] = true;
                        cost[nei.getKey()] = newWt;
                        pq.add(new int[]{nei.getKey(), newWt});
                    }
                }
            }
            return -1;
        }

    }
}
