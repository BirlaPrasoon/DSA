package leetcode.problems.google.hard.graphs.diff;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class FindEdgesInShortestPath {

    class Pair{
        int distance;
        int node;
        Pair(int node, int distance){
            this.distance= distance;
            this.node= node;
        }
    }
    class Solution {
        public boolean[] findAnswer(int n, int[][] edges) {

            ArrayList<ArrayList<Pair>>adj = new ArrayList<>();

            for(int i=0; i<n; i++){
                adj.add(new ArrayList<>());
            }


            for(int[] edge: edges){
                adj.get(edge[0]).add(new Pair(edge[1], edge[2]));
                adj.get(edge[1]).add(new Pair(edge[0], edge[2]));

            }

            PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a, b)-> a.distance - b.distance);
            long disFront[] = new long[n];
            long disBack[] = new long[n];

            for(int i=0; i<n; i++){
                disFront[i]= Integer.MAX_VALUE;
                disBack[i]=Integer.MAX_VALUE;
            }
            disFront[0]=0;
            disBack[n-1]=0;

            pq.add(new Pair(0,0));
            while(!pq.isEmpty()){
                Pair it = pq.poll();
                int node = it.node;
                int dist = it.distance;

                for(Pair i: adj.get((int)node)){
                    int adjNode = i.node;
                    int adjDist =i.distance;
                    if(dist+adjDist<disFront[adjNode]){
                        disFront[adjNode]=dist+adjDist;
                        pq.add(new Pair(adjNode, dist+adjDist));
                    }

                }
            }

            pq.add(new Pair(n-1,0));

            while(!pq.isEmpty()){
                Pair it = pq.poll();
                int node = it.node;
                int dist = it.distance;

                for(Pair i: adj.get((int)node)){
                    int adjNode = i.node;
                    int adjDist =i.distance;
                    if(dist+adjDist<disBack[adjNode]){
                        disBack[adjNode]=dist+adjDist;
                        pq.add(new Pair(adjNode, dist+adjDist));
                    }

                }
            }
            long shortestPath = disFront[n-1];

            boolean [] res = new boolean[edges.length];
            for(int i=0; i<edges.length; i++){
                res[i]= disFront[edges[i][0]] + edges[i][2] + disBack[edges[i][1]] == shortestPath ||
                        disFront[edges[i][1]] + edges[i][2] + disBack[edges[i][0]] == shortestPath;
            }
            return res;

        }
    }
}
