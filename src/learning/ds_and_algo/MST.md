# Minimum Spanning Trees (MSTs) in a Graph

## Overview

A **Minimum Spanning Tree (MST)** of a connected, undirected graph is a subset of edges that forms a tree including all vertices, with the total weight of the edges minimized. MSTs are crucial for network design, clustering, and various optimization problems.

## Key Concepts

- **Minimum Spanning Tree (MST)**: A spanning tree of a graph with the minimum possible total edge weight.
- **Algorithms**: Two common algorithms to find MSTs are Kruskal's Algorithm and Prim's Algorithm.

## Complexities

- **Kruskal's Algorithm**:
    - **Time Complexity**: `O(E log E)` or `O(E log V)`, where `E` is the number of edges and `V` is the number of vertices.
    - **Space Complexity**: `O(V + E)`.

- **Prim's Algorithm**:
    - **Time Complexity**: `O(E + V log V)` with a priority queue, where `E` is the number of edges and `V` is the number of vertices.
    - **Space Complexity**: `O(V)`.

## Algorithms

### 1. Kruskal's Algorithm

Kruskal's Algorithm works by sorting all edges of the graph by their weight and adding them one by one to the MST, ensuring no cycles are formed using a Union-Find data structure.

**Steps**:
1. **Sort all edges**: Sort edges based on their weight.
2. **Initialize Union-Find**: Use a Union-Find structure to keep track of connected components.
3. **Process Edges**: Add edges to the MST if they do not form a cycle (using Union-Find to check).

### 2. Prim's Algorithm

Prim's Algorithm grows the MST by starting from an arbitrary vertex and adding the smallest edge that connects a vertex in the MST to a vertex outside it.

**Steps**:
1. **Initialize**: Start with an arbitrary vertex and mark it as included in the MST.
2. **Priority Queue**: Use a priority queue to get the minimum weight edge connecting the MST to a non-MST vertex.
3. **Update MST**: Add the selected edge to the MST and update the priority queue with edges connected to the newly added vertex.

## Example

Consider the following undirected graph with weights:

            4
        (0)---(1)
        /|\   /|
       / | \ / |
      1  |  2  | 3
    /    | /   |
  (3)---(2)---(4)
           6



### Kruskal's Algorithm Example

1. **Edges Sorted by Weight**: `(0, 1) - 1`, `(1, 2) - 2`, `(3, 2) - 3`, `(0, 3) - 4`.
2. **Process Edges**:
    - Add `(0, 1)`.
    - Add `(1, 2)`.
    - Add `(3, 2)` (does not form a cycle).
    - Skip `(0, 3)` (would form a cycle).

**MST Edges**: `(0, 1)`, `(1, 2)`, `(3, 2)`.

### Code Example (Java) for Kruskal's Algorithm

```java
import java.util.*;

public class KruskalMST {
    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    private static int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        return find(parent, parent[i]);
    }

    private static void union(int[] parent, int[] rank, int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);

        if (xroot != yroot) {
            if (rank[xroot] < rank[yroot]) {
                parent[xroot] = yroot;
            } else if (rank[xroot] > rank[yroot]) {
                parent[yroot] = xroot;
            } else {
                parent[yroot] = xroot;
                rank[xroot]++;
            }
        }
    }

    public static void kruskalMST(int V, List<Edge> edges) {
        Collections.sort(edges);

        int[] parent = new int[V];
        int[] rank = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            int x = find(parent, edge.u);
            int y = find(parent, edge.v);

            if (x != y) {
                mst.add(edge);
                union(parent, rank, x, y);
            }
        }

        System.out.println("Edges in MST:");
        int totalWeight = 0;
        for (Edge edge : mst) {
            System.out.println(edge.u + " - " + edge.v + ": " + edge.weight);
            totalWeight += edge.weight;
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    public static void main(String[] args) {
        int V = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(2, 3, 3));
        edges.add(new Edge(0, 3, 4));

        kruskalMST(V, edges);
    }
}
```

### Prim's Algorithms Example 

```java
import java.util.*;

public class PrimMST {
    static class Edge implements Comparable<Edge> {
        int v, weight;

        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void primMST(int V, List<List<Edge>> adj) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] inMST = new boolean[V];
        int totalWeight = 0;

        pq.add(new Edge(0, 0)); // Start with vertex 0

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.v;

            if (inMST[u]) continue;

            inMST[u] = true;
            totalWeight += edge.weight;

            for (Edge e : adj.get(u)) {
                if (!inMST[e.v]) {
                    pq.add(e);
                }
            }
        }

        System.out.println("Total weight of MST: " + totalWeight);
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Edge>> adj = new ArrayList<>();
        
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Edge(1, 1));
        adj.get(1).add(new Edge(0, 1));
        adj.get(1).add(new Edge(2, 2));
        adj.get(2).add(new Edge(1, 2));
        adj.get(2).add(new Edge(3, 3));
        adj.get(3).add(new Edge(2, 3));
        adj.get(0).add(new Edge(3, 4));
        adj.get(3).add(new Edge(0, 4));

        primMST(V, adj);
    }
}
```

# Differences Between Prim's and Kruskal's Algorithms

Both Prim's and Kruskal's algorithms are popular methods for finding the Minimum Spanning Tree (MST) of a graph. While they achieve the same goal, they have different approaches and characteristics. Here's a detailed comparison:

## Overview

- **Prim's Algorithm**: Grows the MST from a starting vertex, adding the smallest edge that connects the MST to a new vertex.
- **Kruskal's Algorithm**: Builds the MST by sorting all edges and adding them one by one, ensuring no cycles are formed.

## Comparison Table

| Feature                  | Prim's Algorithm                          | Kruskal's Algorithm                           |
|--------------------------|-------------------------------------------|----------------------------------------------|
| **Approach**             | Greedy approach from a single vertex      | Greedy approach from the edges               |
| **Starting Point**       | Starts with an arbitrary vertex           | Starts with sorting all edges                |
| **Data Structures Used** | Priority Queue (Min-Heap)                 | Union-Find (Disjoint Set)                    |
| **Edge Selection**       | Adds edges connecting the MST to new vertices | Adds edges in increasing order of weight     |
| **Complexity (Time)**    | `O(E + V log V)` with priority queue       | `O(E log E)` or `O(E log V)` with Union-Find |
| **Complexity (Space)**   | `O(V)` for storing priority queue and MST | `O(V + E)` for storing edges and Union-Find  |
| **Graph Representation** | Works with adjacency list or matrix       | Works with edge list                          |
| **Cycle Detection**      | Implicitly avoids cycles during MST growth| Explicitly checks for cycles using Union-Find|
| **Best For**             | Dense graphs where edges are many          | Sparse graphs where edges are few            |

## Detailed Differences

### 1. Approach

- **Prim's Algorithm**:
    - Starts from a single vertex and grows the MST by adding the smallest edge connecting a vertex inside the MST to a vertex outside the MST.
    - Efficient for dense graphs where edge weights are known.

- **Kruskal's Algorithm**:
    - Processes edges in sorted order of weight and adds each edge to the MST, provided it doesn’t form a cycle (checked using Union-Find).
    - Efficient for sparse graphs with fewer edges.

### 2. Data Structures

- **Prim's Algorithm**:
    - Uses a priority queue (min-heap) to efficiently get the next minimum edge.

- **Kruskal's Algorithm**:
    - Uses Union-Find (disjoint set) to manage and merge disjoint sets and detect cycles.

### 3. Complexity

- **Prim's Algorithm**:
    - **Time Complexity**: `O(E + V log V)` if implemented with a priority queue.
    - **Space Complexity**: `O(V)` for storing the priority queue and MST.

- **Kruskal's Algorithm**:
    - **Time Complexity**: `O(E log E)` or `O(E log V)` due to sorting edges and Union-Find operations.
    - **Space Complexity**: `O(V + E)` for storing edges and Union-Find structure.

### 4. Cycle Detection

- **Prim's Algorithm**:
    - Cycle detection is implicitly handled as it grows the MST from a single connected component.

- **Kruskal's Algorithm**:
    - Explicitly uses Union-Find to detect and avoid cycles by ensuring that adding an edge doesn’t form a cycle.

### 5. Best Use Cases

- **Prim's Algorithm**:
    - Ideal for dense graphs where edges are numerous, as it focuses on expanding the MST efficiently.

- **Kruskal's Algorithm**:
    - Ideal for sparse graphs where the number of edges is relatively small, as it processes edges in sorted order and handles cycles efficiently.

## Example Code

### Kruskal's Algorithm (Java)

```java
// Kruskal's Algorithm code
