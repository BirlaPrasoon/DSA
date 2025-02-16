package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class KosarajuScc {

    static class Graph {
        int V;
        ArrayList<ArrayList<Integer>> gph;
        Graph(int V) {
            this.V = V;
            gph = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                gph.add(new ArrayList<>());
            }
        }
        public void addEdge(int x, int y) {
            gph.get(x).add(y);
        }

        public void dfs(int src, ArrayList<ArrayList<Integer>> gph, boolean visited[], Stack<Integer> st) {
            visited[src] = true;
            for(int n: gph.get(src)) {
                if(!visited[n]) {
                    dfs(n, gph, visited, st);
                }
            }
            st.add(src);
        }

        public ArrayList<ArrayList<Integer>> getTranspose() {
            ArrayList<ArrayList<Integer>> gph2 = new ArrayList<>();
            for(int i = 0; i<this.gph.size(); i++) {
                gph2.add(new ArrayList<>());
            }
            for(int i= 0; i<gph.size(); i++) {
                for(int x: gph.get(i)) {
                    gph2.get(x).add(i);
                }
            }
            return gph2;
        }

        public int numberOfScc() {
            boolean[] visited = new boolean[gph.size()];
            Stack<Integer> finishingOrder = new Stack<>();
            dfs(0, gph, visited, finishingOrder);
            ArrayList<ArrayList<Integer>> gph2 = getTranspose();
            Arrays.fill(visited, false);
            System.out.println("Finishing order: " + finishingOrder);
            int numberOfScc = 0;
            for(int x:finishingOrder) {
                if(!visited[x]) {
                    numberOfScc++;
                    Stack<Integer> sc = new Stack<>();
                    dfs(x, gph2, visited, sc);
                    System.out.println("Scc: " + sc);
                }
            }
            return numberOfScc;
        }
    }

    public static void main(String args[])
    {
        // Create graphs given in the above diagrams
        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 0);
        g1.addEdge(2, 4);
//        g1.addEdge(4, 2);
        System.out.println(g1.numberOfScc());

        Graph g2 = new Graph(4);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);

    }
}
