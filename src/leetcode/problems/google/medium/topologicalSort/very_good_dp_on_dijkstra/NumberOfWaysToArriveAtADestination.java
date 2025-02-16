package leetcode.problems.google.medium.topologicalSort.very_good_dp_on_dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class NumberOfWaysToArriveAtADestination {
/**
    You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
    </br></br>
    You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
    </br></br>
    Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
    */



    class Solution {
        public int countPaths(int n, int[][] roads) {
            PriorityQueue<Pair> q = new PriorityQueue<>((x, y) -> Long.compare(x.a, y.a));
            List<List<Pair>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int[] ar : roads) {
                adj.get(ar[0]).add(new Pair(ar[1], ar[2])); // node wt
                adj.get(ar[1]).add(new Pair(ar[0], ar[2]));
            }
            long[] dis = new long[n];
            int[] ways = new int[n];

            Arrays.fill(dis, Long.MAX_VALUE / 2);
            dis[0] = 0;
            ways[0] = 1;
            q.add(new Pair(0, 0));

            while (!q.isEmpty()) {
                long pd = q.peek().a;
                long node = q.peek().b;
                q.remove();
                for (Pair i : adj.get((int) node)) {
                    long edgewt = i.b;
                    long adjnode = i.a;
                    if (edgewt + pd < dis[(int) adjnode]) {
                        dis[(int) adjnode] = edgewt + pd;
                        q.add(new Pair(dis[(int) adjnode], adjnode));
                        ways[(int) adjnode] = ways[(int) node];
                    } else if (edgewt + pd == dis[(int) adjnode]) {
                        ways[(int) adjnode] = (ways[(int) adjnode] + ways[(int) node]) % ((int) 1e9 + 7); // Corrected ways count
                    }
                }
            }

            return ways[n - 1] % ((int) 1e9 + 7);
        }
    }

    class Pair {
        long a; // distance
        long b; // node

        Pair(long a, long b) {
            this.a = a;
            this.b = b;
        }
    }

}
