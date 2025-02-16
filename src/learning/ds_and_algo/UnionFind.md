# Union-Find Data Structure

## Overview

The **Union-Find** data structure (also known as **Disjoint Set Union** or **DSU**) is used to manage and merge disjoint sets efficiently. It supports two main operations:

- **Find**: Determine the root or representative of the set containing a particular element.
- **Union**: Merge two sets into one.

## Key Concepts

- **Path Compression**: During the `find` operation, we make nodes point directly to the root, which flattens the structure and speeds up future operations.
- **Union by Rank**: During the `union` operation, we attach the smaller tree under the root of the larger tree to keep the tree shallow.

## Complexities

- **Find**: `O(α(n))`
    - Where `α(n)` is the Inverse Ackermann function, which grows very slowly. In practice, it is almost constant time.

- **Union**: `O(α(n))`
    - The same complexity as the `find` operation.

- **Space Complexity**: `O(n)`
    - The space required to store the parent and rank arrays.

## Implementation

Here's a Java implementation of the Union-Find data structure with path compression and union by rank:

```java
public class UnionFind {

    private int[] parent;
    private int[] rank;

    // Initialize the Union-Find data structure
    // Complexity: O(n)
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find the root of the set containing 'x'
    // Complexity: O(α(n))
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union the sets containing 'x' and 'y'
    // Complexity: O(α(n))
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            // Union by rank
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // Check if 'x' and 'y' are in the same set
    // Complexity: O(α(n))
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    // Main method for demonstration
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);

        // Union operations
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(4, 5);

        // Find operations
        System.out.println("1 and 3 connected: " + uf.connected(1, 3)); // True
        System.out.println("1 and 5 connected: " + uf.connected(1, 5)); // False

        // Union more sets
        uf.union(3, 5);
        System.out.println("1 and 5 connected: " + uf.connected(1, 5)); // True
    }
}
