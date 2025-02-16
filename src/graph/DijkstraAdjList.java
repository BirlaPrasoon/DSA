package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DijkstraAdjList {

    static class AdjListNode{
        private final int weight;
        private final int vertex;
        AdjListNode(int v, int w){
            this.weight = w;
            this.vertex = v;
        }

        public int getWeight() {
            return weight;
        }

        public int getVertex() {
            return vertex;
        }
    }

    public static int[] dijkstra(int V, ArrayList<ArrayList<AdjListNode>> gph, int src) {
        int [] dis = new int[V];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[src] = 0;
        PriorityQueue<AdjListNode> pq = new PriorityQueue<>(Comparator.comparingInt(AdjListNode::getWeight));
        pq.add(new AdjListNode(src, 0));
        while (!pq.isEmpty()) {
            AdjListNode cur = pq.poll();
            for(AdjListNode n: gph.get(cur.vertex)) {
                if(dis[n.getVertex()] > dis[cur.getVertex()] + n.getWeight()) {
                    dis[n.getVertex()] = dis[cur.getVertex()] + n.getWeight();
                    pq.add(new AdjListNode(n.getVertex(), dis[n.getVertex()]));
                }
            }
        }
        return dis;
    }

    public static void main(String[] args) {
        int V = 9;
        ArrayList<ArrayList<AdjListNode> > graph
                = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        int source = 0;
        graph.get(0).add(new AdjListNode(1, 4));
        graph.get(0).add(new AdjListNode(7, 8));
        graph.get(1).add(new AdjListNode(2, 8));
        graph.get(1).add(new AdjListNode(7, 11));
        graph.get(1).add(new AdjListNode(0, 7));
        graph.get(2).add(new AdjListNode(1, 8));
        graph.get(2).add(new AdjListNode(3, 7));
        graph.get(2).add(new AdjListNode(8, 2));
        graph.get(2).add(new AdjListNode(5, 4));
        graph.get(3).add(new AdjListNode(2, 7));
        graph.get(3).add(new AdjListNode(4, 9));
        graph.get(3).add(new AdjListNode(5, 14));
        graph.get(4).add(new AdjListNode(3, 9));
        graph.get(4).add(new AdjListNode(5, 10));
        graph.get(5).add(new AdjListNode(4, 10));
        graph.get(5).add(new AdjListNode(6, 2));
        graph.get(6).add(new AdjListNode(5, 2));
        graph.get(6).add(new AdjListNode(7, 1));
        graph.get(6).add(new AdjListNode(8, 6));
        graph.get(7).add(new AdjListNode(0, 8));
        graph.get(7).add(new AdjListNode(1, 11));
        graph.get(7).add(new AdjListNode(6, 1));
        graph.get(7).add(new AdjListNode(8, 7));
        graph.get(8).add(new AdjListNode(2, 2));
        graph.get(8).add(new AdjListNode(6, 6));
        graph.get(8).add(new AdjListNode(7, 1));

        int[] distance = dijkstra(V, graph, source);
        // Printing the Output
        System.out.println("Vertex  "
                + "  Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "             "
                    + distance[i]);
        }
    }


}
