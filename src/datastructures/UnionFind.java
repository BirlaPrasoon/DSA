package datastructures;

public class UnionFind {

    private final int[] parent;
    private final int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // O(logN), below implementation is with path compression so O(1) amortized
    public int find(int a) {
        if(parent[a] != a) {
            parent[a] = find(parent[a]);
        }

        return parent[a];
    }

    // O(logN), below implementation is with path compression so
    public boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot == yRoot) return false;
        if(rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if(rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[xRoot] = yRoot;
            rank[xRoot]++;
        }
        return true;
    }
}
