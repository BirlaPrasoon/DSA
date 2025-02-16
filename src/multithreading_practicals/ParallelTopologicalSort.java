package multithreading_practicals;

import java.util.*;
import java.util.concurrent.*;

public class ParallelTopologicalSort {
    private int V;
    private List<List<Integer>> adj;
    private int[] inDegree;

    public ParallelTopologicalSort(int v) {
        V = v;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        inDegree = new int[V];
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        inDegree[w]++;
    }

    public List<List<Integer>> topologicalSort() {
        List<List<Integer>> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // Add all vertices with 0 in-degree to the queue
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>(queue);
            result.add(currentLevel);
            queue.clear();

            ExecutorService executor = Executors.newFixedThreadPool(currentLevel.size());
            List<CompletableFuture<Void>> futures = new ArrayList<>();

            for (int node : currentLevel) {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    processNode(node);
                    for (int neighbor : adj.get(node)) {
                        if (--inDegree[neighbor] == 0) {
                            synchronized (queue) {
                                queue.offer(neighbor);
                            }
                        }
                    }
                }, executor);
                futures.add(future);
            }

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            executor.shutdown();
        }

        return result;
    }

    private void processNode(int node) {
        System.out.println("Processing node " + node + " on thread " + Thread.currentThread().getName());
        // Simulate some work
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ParallelTopologicalSort graph = new ParallelTopologicalSort(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        List<List<Integer>> result = graph.topologicalSort();

        System.out.println("Topological Sort (Levels for Parallel Execution):");
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Level " + i + ": " + result.get(i));
        }
    }
}