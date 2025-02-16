package leetcode.problems.google.medium.depth_first_search.graph;

import java.util.*;

public class AllAncestorsOfANodeInDirectedGraph {
/*
    You are given a positive integer n representing the number of nodes of a Directed Acyclic Graph (DAG).
    The nodes are numbered from 0 to n - 1 (inclusive).

    You are also given a 2D integer array edges, where edges[i] = [fromi, toi] denotes that there is a
    unidirectional edge from fromi to toi in the graph.

    Return a list answer, where answer[i] is the list of ancestors of the ith node, sorted in ascending order.

    A node u is an ancestor of another node v if u can reach v via a set of edges.

    Constraints:

    1 <= n <= 1000
    0 <= edges.length <= min(2000, n * (n - 1) / 2)
    edges[i].length == 2
    0 <= fromi, toi <= n - 1
    fromi != toi
    There are no duplicate edges.
    The graph is directed and acyclic.

    */


    // DFS on reversed graph
    // Time complexity: O(n^2 +n⋅m)
    class Solution {

        public List<List<Integer>> getAncestors(int n, int[][] edges) {
            // Initialize adjacency list for the graph
            List<Integer>[] adjacencyList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjacencyList[i] = new ArrayList<>();
            }

            // Populate the adjacency list with reversed edges
            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                adjacencyList[to].add(from);
            }

            List<List<Integer>> ancestorsList = new ArrayList<>();

            // For each node, find all its ancestors (children in reversed graph)
            for (int i = 0; i < n; i++) {

                Set<Integer> visited = new HashSet<>();
                findChildren(i, adjacencyList, visited);

                // Add visited nodes to the current nodes' ancestor list
                List<Integer> ancestors = new ArrayList<>();
                for (int node = 0; node < n; node++) {
                    if (node == i) continue;
                    if (visited.contains(node)) ancestors.add(node);
                }
                ancestorsList.add(ancestors);
            }

            return ancestorsList;
        }

        // Helper method to perform DFS and find all children of a given node
        private void findChildren(int currentNode, List<Integer>[] adjacencyList, Set<Integer> visitedNodes) {
            // Mark current node as visited
            visitedNodes.add(currentNode);

            // Recursively traverse all neighbors
            for (int neighbour : adjacencyList[currentNode]) {
                if (!visitedNodes.contains(neighbour)) {
                    findChildren(neighbour, adjacencyList, visitedNodes);
                }
            }
        }
    }

    // Topological Sort
    /*

    Time complexity: O(n^2+m)
    Space complexity: O(n^2+m)

    * The problem revolves around the nature of the graph as a Directed Acyclic Graph (DAG).
    * In a DAG, cycles are absent, and each path progresses clearly from a starting point to an endpoint.
    * This characteristic implies that by processing nodes in a specific order, we can systematically determine each node's ancestors.
    * The key to identifying this optimal processing order lies in topological sorting.
    * In a DAG, topological sorting arranges nodes such that for every directed edge from node u to node v, u precedes v in the ordering.
    * This arrangement is crucial because it ensures that when we process a node v, we have already considered all its potential ancestors.
    * To achieve this ordering, we will use Kahn's algorithm.
    *
    * Kahn's algorithm is a method for topologically sorting a directed acyclic graph.
    * It starts by identifying all nodes without incoming edges and placing them in a queue.
    * At each step, it removes a node from this queue, adds it to the sorted list, and eliminates its outgoing edges from the graph.
    * This process may create new nodes without incoming edges, which are then added to the queue.
    * The algorithm continues until the queue is empty. The resulting list provides a valid topological ordering of the graph.
    * For a more detailed explanation of Kahn's algorithm and its implementation, refer to this Explore Card.
    *
    * After establishing the topological order, we process each node sequentially.
    * For each node, we iterate through its neighbors, designating both the node itself and its ancestors as ancestors of the neighbor.
    * To efficiently track each node's ancestors, we use a list of sets. Sets, unlike lists, maintain unique elements,
    * ensuring each ancestor appears only once in a node's ancestor set.
    *
    * In the final step, we'll convert these sets of ancestors into lists, as required by the problem statement.
    *
    * */
    class SolutionTopo {

        public List<List<Integer>> getAncestors(int n, int[][] edges) {
            // Create adjacency list
            List<Integer>[] adjacencyList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjacencyList[i] = new ArrayList<>();
            }

            // Fill the adjacency list and indegree array based on the edges
            int[] indegree = new int[n];
            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                adjacencyList[from].add(to);
                indegree[to]++;
            }

            // Queue for nodes with no incoming edges (starting points for topological sort)
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < indegree.length; i++) {
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }

            // List to store the topological order of nodes
            List<Integer> topologicalOrder = new ArrayList<>();
            while (!queue.isEmpty()) {
                int currentNode = queue.poll();
                topologicalOrder.add(currentNode);

                // Reduce indegree of neighboring nodes and add them to the queue
                // if they have no more incoming edges
                for (int neighbor : adjacencyList[currentNode]) {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }

            // Initialize the result list and set list for storing ancestors
            List<List<Integer>> ancestorsList = new ArrayList<>();
            List<Set<Integer>> ancestorsSetList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                ancestorsList.add(new ArrayList<>());
                ancestorsSetList.add(new HashSet<>());
            }

            // Fill the set list with ancestors using the topological order
            for (int node : topologicalOrder) {
                for (int neighbor : adjacencyList[node]) {
                    // Add immediate parent, and other ancestors.
                    ancestorsSetList.get(neighbor).add(node);
                    ancestorsSetList.get(neighbor).addAll(ancestorsSetList.get(node));
                }
            }

            // Convert sets to lists
            for (int i = 0; i < n; i++) {
                for (int node = 0; node < n; node++) {
                    if (node == i) continue;
                    if (ancestorsSetList.get(i).contains(node)) {
                        ancestorsList.get(i).add(node);
                    }
                }
            }

            return ancestorsList;
        }
    }


}
