package leetcode.problems.google.medium.graph.self_loop_minimum_distance;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class OptimizeWaterSupply {

    class Solution {
        public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
            List<List<Edge>> graph = new ArrayList<>();
            for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
            for(int[] pipe : pipes) {
                int u = pipe[0];
                int v = pipe[1];
                int cost = pipe[2];
                graph.get(u).add(new Edge(v, cost));
                graph.get(v).add(new Edge(u, cost));
            }
            for(int i = 1; i<=n; i++) {
                graph.get(0).add(new Edge(i, wells[i-1]));
            }
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(0, 0));
            boolean[] visited = new boolean[n+1];
            int cost = 0;
            while(!pq.isEmpty()) {
                Edge e = pq.poll();
                if(visited[e.v]) continue;
                visited[e.v] = true;
                cost += e.w;
                for(Edge nei: graph.get(e.v)) {
                    if(!visited[e.v]) {
                        pq.add(nei);
                    }
                }
            }
            return cost;
        }

        static class Edge implements Comparable<Edge> {
            int v,w;
            Edge(int v, int w) {
                this.v = v;
                this.w = w;
            }

            @Override
            public int compareTo(Edge edge) {
                return this.w - edge.w;
            }

            @Override
            public String toString() {
                return "Edge{" +
                        "v=" + v +
                        ", w=" + w +
                        '}';
            }
        }


    }
}
