package leetcode.problems.google.medium.breadth_first_search;
import java.util.*;
public class CheapestFlightWithinKStops {

/*
    There are n cities connected by some number of flights.
    You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight
    from city fromi to city toi with cost pricei.

    You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
    If there is no such route, return -1.
*/


    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<int[]>> adj = new HashMap<>();
            for (int[] i : flights)
                adj.computeIfAbsent(i[0], value -> new ArrayList<>())
                        .add(new int[] { i[1], i[2] });

            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);

            Queue<int[]> q = new LinkedList<>();
            dist[src] = 0;
            q.offer(new int[] { src, 0 });
            int stops = 0;

            while (stops <= k && !q.isEmpty()) {
                // Iterate on current level.
                for(int i = q.size()-1; i>=0; i--){
                    int[] temp = q.poll();
                    int node = temp[0];
                    int distance = temp[1];

                    if (!adj.containsKey(node))
                        continue;
                    // Loop over neighbors of popped node.
                    for (int[] e : adj.get(node)) {
                        int neighbour = e[0];
                        int price = e[1];
                        if (price + distance < dist[neighbour]) {
                            dist[neighbour] = price + distance;
                            q.offer(new int[]{neighbour, dist[neighbour]});
                        }
                    }
                }
                stops++;
            }
            return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
        }
    }
}
