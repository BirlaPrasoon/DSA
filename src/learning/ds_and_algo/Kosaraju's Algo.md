# Kosaraju's Algorithm

## Overview

**Kosaraju's Algorithm** is used to find all strongly connected components (SCCs) in a directed graph. A strongly connected component is a maximal subgraph where every vertex is reachable from every other vertex within the same component.

## Key Concepts

- **Strongly Connected Component (SCC)**: A maximal subgraph where every vertex can reach every other vertex.
- **Algorithm**: Kosaraju's Algorithm uses two main steps: Depth-First Search (DFS) to determine the finishing times of nodes and another DFS on the transposed graph in the order of decreasing finishing times to find SCCs.

## Complexity

- **Time Complexity**: `O(V + E)`, where `V` is the number of vertices and `E` is the number of edges.
- **Space Complexity**: `O(V + E)` for storing graph representations and recursion stack.

## Steps of Kosaraju's Algorithm

1. **First DFS**: Perform a DFS on the original graph to get the finishing times of vertices.
2. **Transpose Graph**: Reverse the direction of all edges in the graph.
3. **Second DFS**: Perform DFS on the transposed graph in the order of decreasing finishing times obtained from the first DFS.

## Example

Consider the following directed graph:

    1 → 2 → 3
    ↓    ↑
    4 → 5


- **Graph Representation**:
    - `1 → 2`
    - `2 → 3`
    - `3 → 1`
    - `4 → 5`
    - `5 → 4`

### Kosaraju's Algorithm Execution

1. **First DFS**:
    - Start DFS from vertex 1: finishing order `[3, 2, 1]` (vertex 4 and 5 are separate).

2. **Transpose Graph**:
    - Reverse the edges to get:
        - `2 → 1`
        - `3 → 2`
        - `1 → 3`
        - `5 → 4`
        - `4 → 5`

3. **Second DFS**:
    - Perform DFS in order of finishing times `[3, 2, 1]` on the transposed graph:
        - SCC 1: `{1, 2, 3}`
        - SCC 2: `{4, 5}`

## Code Example (Java)

```java
import java.util.*;

public class KosarajuSCC {
    static class Graph {
        private final int V;
        private final List<List<Integer>> adjList;

        Graph(int V) {
            this.V = V;
            adjList = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            adjList.get(u).add(v);
        }

        List<List<Integer>> getAdjList() {
            return adjList;
        }

        Graph getTranspose() {
            Graph transpose = new Graph(V);
            for (int u = 0; u < V; u++) {
                for (int v : adjList.get(u)) {
                    transpose.addEdge(v, u);
                }
            }
            return transpose;
        }
    }

    private static void fillOrder(int v, boolean[] visited, Stack<Integer> stack, Graph graph) {
        visited[v] = true;
        for (int i : graph.getAdjList().get(v)) {
            if (!visited[i]) {
                fillOrder(i, visited, stack, graph);
            }
        }
        stack.push(v);
    }

    private static void dfs(int v, boolean[] visited, Graph graph) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int i : graph.getAdjList().get(v)) {
            if (!visited[i]) {
                dfs(i, visited, graph);
            }
        }
    }

    public static void kosarajuSCC(Graph graph) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.V];
        
        // Step 1: Fill vertices in stack according to their finishing times
        for (int i = 0; i < graph.V; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack, graph);
            }
        }

        // Step 2: Get the transpose of the graph
        Graph transpose = graph.getTranspose();

        // Step 3: Do DFS in the order defined by stack
        Arrays.fill(visited, false);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                System.out.print("SCC: ");
                dfs(v, visited, transpose);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 5);
        graph.addEdge(5, 4);

        System.out.println("Strongly Connected Components:");
        kosarajuSCC(graph);
    }
}
```
