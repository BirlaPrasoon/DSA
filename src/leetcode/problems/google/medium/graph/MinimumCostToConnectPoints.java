package leetcode.problems.google.medium.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumCostToConnectPoints {
    public static void main(String[] args) {
        int a[][] = {{0,0},{1,1},{1,0},{-1,1}};
        MinimumCostToConnectPoints m = new MinimumCostToConnectPoints();
        System.out.println(m.minCostConnectPoints(a));
    }
    static class Edge implements Comparable<Edge>{
        int u, wt;
        Edge(int u, int wt) {
            this.u = u;
            this.wt = wt;
        }

        @Override
        public int compareTo(Edge o) {
            return this.wt - o.wt;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "u=" + u +
                    ", wt=" + wt +
                    '}';
        }
    }
    private int primMST(int[][] points) {
        ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
        for(int i = 0; i<points.length; i++) {
            edges.add(new ArrayList<>());
        }
        for(int i=0; i<points.length; i++) {
            for(int j = 0; j<points.length; j++) {
                if(i == j) continue;
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.get(i).add(new Edge(j, dist));
            }
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        boolean[] inMST = new boolean[points.length];
        int val = 0;
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            int u = e.u;
            if(inMST[u]) continue;
            val += e.wt;
            inMST[u] = true;
            for(Edge v: edges.get(u)) {
                if(!inMST[v.u])
                    pq.add(v);
            }
        }
        return val;
    }

    public int minCostConnectPoints(int[][] points) {
        return primMST(points);
    }

    private int kruskal(int [][] points) {
        ArrayList<Point> pointList = new ArrayList<>();
        for(int i=0; i<points.length; i++) {
            for(int j = i+1; j<points.length; j++) {
                if(i == j) continue;
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                pointList.add(new Point(i, j, dist));
            }
        }

        Collections.sort(pointList);
        int min = 0;

        UnionFind unionFind = new UnionFind(points.length);
        for(Point p: pointList){
            if(!unionFind.belongTogether(p.u, p.v)){
                unionFind.union(p.u, p.v);
                min += p.wt;
            }
        }
        return min;
    }

    class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n){
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY) return;
            if(rank[rootX]< rank[rootY]) {
                parent[rootX] = rootY;
            } else if(rank[rootY] < rank[rootX]) {
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootY;
                rank[rootY]++;
            }
        }

        public boolean belongTogether(int x, int y) {
            return find(x) == find(y);
        }
    }

    static class Point implements Comparable<Point>{
        int u, v, wt;
        Point(int u, int v, int wt) {
            this.u = u;
            this.v = v;
            this.wt = wt;
        }

        @Override
        public int compareTo(Point p) {

            return this.wt - p.wt;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "u=" + u +
                    ", v=" + v +
                    ", wt=" + wt +
                    '}';
        }
    }
}
