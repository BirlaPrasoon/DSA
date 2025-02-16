# Articulation Point in a Graph

## Overview

An **Articulation Point** (or Cut Vertex) in a graph is a vertex which, when removed along with its incident edges, increases the number of connected components of the graph. Identifying articulation points is useful in network design and reliability analysis, as they represent critical nodes that could disrupt connectivity if removed.

## Key Concepts

- **Articulation Point**: A vertex that, when removed, results in an increase in the number of connected components of the graph.
- **DFS (Depth-First Search)**: A traversal method used to discover articulation points.

## Complexities

- **Time Complexity**: `O(V + E)`, where `V` is the number of vertices and `E` is the number of edges.
- **Space Complexity**: `O(V)` for storing the graph and auxiliary data structures.

## Algorithm Steps

### 1. Initialization

1. **Data Structures**:
    - `discovery[]`: Stores discovery times of visited vertices.
    - `low[]`: Stores the lowest discovery time reachable from a vertex.
    - `parent[]`: Stores the parent of each vertex in the DFS tree.
    - `articulationPoints[]`: To store the articulation points.

2. **Start DFS Traversal**:
    - Initialize all vertices as unvisited.
    - Call DFS utility for each unvisited vertex.

### 2. DFS Utility for Articulation Points

1. **Visit Nodes**:
    - Update discovery and low values for each visited vertex.
    - Check for articulation point conditions based on discovery and low values.

2. **Conditions**:
    - **Root Condition**: If the root of the DFS tree has two or more children.
    - **Non-root Condition**: If a vertex `u` is not a root, it is an articulation point if there exists a child `v` such that `low[v] >= discovery[u]`.

### Example

Consider the following undirected graph:

      0
     /|\
    1 2 3
    |/|
    4 5
     |
     6


### Finding Articulation Points

1. **Start DFS from Node 0**:
    - Discovery times and low values are updated during traversal.

2. **Identify Articulation Points**:
    - For the root node (0), check the number of children.
    - For non-root nodes, check the `low` values of children.

### Code Example (Java)

Here is a Java implementation of finding articulation points in an undirected graph using DFS:

```java
import java.util.*;

public class ArticulationPoints {
    private static int time = 0;

    public static void main(String[] args) {
        int V = 5; // Number of vertices
        List<List<Integer>> adj = new ArrayList<>();
        
        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 2, 3);
        addEdge(adj, 2, 4);

        findArticulationPoints(V, adj);
    }

    private static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    private static void findArticulationPoints(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        int[] tin = new int[V];
        int[] low = new int[V];
        int[] parent = new int[V];
        boolean[] ap = new boolean[V]; // Articulation Points
        
        Arrays.fill(parent, -1);
        Arrays.fill(tin, -1);
        Arrays.fill(low, -1);

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, tin, low, parent, ap, adj);
            }
        }

        System.out.println("Articulation Points:");
        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.print(i + " ");
            }
        }
    }

    private static void dfs(int u, boolean[] visited, int[] discovery, int[] low, int[] parent, boolean[] ap, List<List<Integer>> adj) {
        visited[u] = true;
        discovery[u] = low[u] = ++time;
        int children = 0;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                parent[v] = u;
                children++;
                dfs(v, visited, discovery, low, parent, ap, adj);

                low[u] = Math.min(low[u], low[v]);

                if (parent[u] == -1 && children > 1) {
                    ap[u] = true;
                }

                if (parent[u] != -1 && low[v] >= discovery[u]) {
                    ap[u] = true;
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], discovery[v]);
            }
        }
    }
}
```
