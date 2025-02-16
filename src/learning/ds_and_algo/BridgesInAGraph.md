# Bridges in a Graph

## Overview

A **Bridge** (or Cut-Edge) in a graph is an edge which, when removed, increases the number of connected components of the graph. Identifying bridges is important for network design and reliability analysis, as these edges represent critical connections that could disrupt connectivity if removed.

## Key Concepts

- **Bridge**: An edge that, when removed, results in an increase in the number of connected components.
- **DFS (Depth-First Search)**: A traversal method used to discover bridges.

## Complexities

- **Time Complexity**: `O(V + E)`, where `V` is the number of vertices and `E` is the number of edges.
- **Space Complexity**: `O(V)` for storing the graph and auxiliary data structures.

## Algorithm Steps

### 1. Initialization

1. **Data Structures**:
   - `discovery[]`: Stores discovery times of visited vertices.
   - `low[]`: Stores the lowest discovery time reachable from a vertex.
   - `parent[]`: Stores the parent of each vertex in the DFS tree.
   - `bridges[]`: To store the bridges found.

2. **Start DFS Traversal**:
   - Initialize all vertices as unvisited.
   - Call DFS utility for each unvisited vertex.

### 2. DFS Utility for Bridges

1. **Visit Nodes**:
   - Update discovery and low values for each visited vertex.
   - Check for bridge conditions based on discovery and low values.

2. **Conditions**:
   - If `low[v] > discovery[u]`, then the edge `(u, v)` is a bridge.

### Example

Consider the following undirected graph:

  0
 /|\
1 2 3
|/|
4 5
 |
 6


### Finding Bridges

1. **Start DFS from Node 0**:
    - Discovery times and low values are updated during traversal.

2. **Identify Bridges**:
    - For each edge `(u, v)`, check if `low[v] > discovery[u]`.

### Code Example (Java)

Here is a Java implementation to find bridges in an undirected graph using DFS:

```java
import java.util.*;

public class BridgesInGraph {
    private static int time = 0;

    public static void main(String[] args) {
        int V = 7; // Number of vertices
        List<List<Integer>> adj = new ArrayList<>();
        
        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 0, 3);
        addEdge(adj, 1, 4);
        addEdge(adj, 2, 5);
        addEdge(adj, 4, 5);
        addEdge(adj, 4, 6);
        addEdge(adj, 5, 6);

        findBridges(V, adj);
    }

    private static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    private static void findBridges(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        int[] tin = new int[V];
        int[] low = new int[V];
        int[] parent = new int[V];
        List<int[]> bridges = new ArrayList<>();

        Arrays.fill(parent, -1);
        Arrays.fill(tin, -1);
        Arrays.fill(low, -1);

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, tin, low, parent, bridges, adj);
            }
        }

        System.out.println("Bridges:");
        for (int[] bridge : bridges) {
            System.out.println(Arrays.toString(bridge));
        }
    }

    private static void dfs(int u, boolean[] visited, int[] discovery, int[] low, int[] parent, List<int[]> bridges, List<List<Integer>> adj) {
        visited[u] = true;
        discovery[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                parent[v] = u;
                dfs(v, visited, discovery, low, parent, bridges, adj);

                low[u] = Math.min(low[u], low[v]);

                if (low[v] > discovery[u]) {
                    bridges.add(new int[] {u, v});
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], discovery[v]);
            }
        }
    }
}
```
