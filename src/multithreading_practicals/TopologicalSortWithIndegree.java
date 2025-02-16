package multithreading_practicals;

import java.util.*;

public class TopologicalSortWithIndegree {
    private int V;
    private List<List<Integer>> adj;
    private int[] originalIndegree;

    public TopologicalSortWithIndegree(int v) {
        V = v;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        originalIndegree = new int[V];
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        originalIndegree[w]++;
    }

    public List<List<Integer>> topologicalSort() {
        List<List<Integer>> result = new ArrayList<>();
        int[] indegree = Arrays.copyOf(originalIndegree, V);
        Queue<Integer> queue = new LinkedList<>();

        // Add all vertices with 0 in-degree to the queue
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>(queue);
            result.add(currentLevel);
            queue.clear();

            for (int node : currentLevel) {
                for (int neighbor : adj.get(node)) {
                    if (--indegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return result;
    }

    public void printTopologicalOrder() {
        List<List<Integer>> order = topologicalSort();
        System.out.println("Topological Sort (Levels for Parallel Execution):");
        for (int i = 0; i < order.size(); i++) {
            System.out.print("Level " + i + ": ");
            for (int node : order.get(i)) {
                System.out.print("(" + node + ", " + originalIndegree[node] + ") ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TopologicalSortWithIndegree graph = new TopologicalSortWithIndegree(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        graph.printTopologicalOrder();
    }
}