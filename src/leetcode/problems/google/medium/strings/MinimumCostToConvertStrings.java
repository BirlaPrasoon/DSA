package leetcode.problems.google.medium.strings;

import java.util.*;

public class MinimumCostToConvertStrings {

    // Dijkstra TLE
    public static long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int CHARS = 26;
        long[][] dist = new long[CHARS][CHARS];
        for (long[] d : dist) Arrays.fill(d, Long.MAX_VALUE);
        for (int i = 0; i < CHARS; i++) {
            dist[i][i] = 0;
        }
        ArrayList<Pair>[] adj = new ArrayList[CHARS];
        HashMap<String, Integer> edges = new HashMap<>();
        for (var i = 0; i < original.length; i++) {
            String s = original[i] +":"+ changed[i];
            int co = Integer.min(cost[i], edges.getOrDefault(s, Integer.MAX_VALUE));
            edges.put(s, co);
        }
        for (var i = 0; i < CHARS; i++)
            adj[i] = new ArrayList<>();
        for (var i = 0; i < original.length; i++) {
            adj[original[i] - 'a'].add(new Pair(changed[i] - 'a', cost[i]));
        }
        for(Map.Entry<String, Integer> entry: edges.entrySet()) {
            String srcdst = entry.getKey();
            int co = entry.getValue();
            char src = srcdst.charAt(0);
            char de = srcdst.charAt(2);
            dist[src-'a'][de-'a'] = co;
        }

        for (int i = 0; i < CHARS; i++) {
            boolean visited[] = new boolean[CHARS];
            var pq = new PriorityQueue<Pair>((a, b) -> (int) (a.cost - b.cost));
            long[] distMat = dist[i];
            pq.add(new Pair(i, distMat[i]));
            while (!pq.isEmpty()) {
                var node = pq.poll();
                var cur = node.c;
                visited[cur] = true;
                for (var nei : adj[cur]) {
                    if (!visited[nei.c]) {
                        if (distMat[nei.c] > distMat[cur] + nei.cost) distMat[nei.c] = distMat[cur] + nei.cost;
                        pq.add(new Pair(nei.c, distMat[nei.c]));
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < source.length(); i++) {
            int or = source.charAt(i) - 'a';
            int ch = target.charAt(i) - 'a';
            long distance = dist[or][ch];
            if (distance == Long.MAX_VALUE) return -1;
            ans += distance;
        }
        return ans;
    }

    // Floyd Warshal
    public static long minimumCostFW(String source, String target, char[] original, char[] changed, int[] cost) {
        int CHARS = 26;
        long[][] dist = new long[CHARS][CHARS];
        long INF = (long) 10e12;
        for (long[] d : dist) Arrays.fill(d, INF);
        for (int i = 0; i < CHARS; i++) {
            dist[i][i] = 0;
        }
        for (var i = 0; i < original.length; i++) {
            dist[original[i] - 'a'][changed[i] - 'a'] = Math.min(dist[original[i] - 'a'][changed[i] - 'a'],cost[i]);
        }

        for (int k = 0; k < CHARS; k++) {
            for (int i = 0; i < CHARS; i++) {
                if(dist[i][k] < INF) {
                    for (int j = 0; j < CHARS; j++) {
                        if(dist[k][j] < INF) {
                            if (dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < source.length(); i++) {
            int or = source.charAt(i) - 'a';
            int ch = target.charAt(i) - 'a';
            long distance = dist[or][ch];
            System.out.println("or: " + source.charAt(i) +"-> ta: " + target.charAt(i)+ " distance: " + distance);
            if (distance == INF) return -1;
            ans += distance;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minimumCost("caabcdadad", "bbbcbcdabb", "ddbaacac".toCharArray(), "bcdcbadd".toCharArray(), new int[]{1, 5, 2, 3, 5, 7, 11, 7}));
    }

    static final class Pair {
        private final int c;
        private final long cost;

        Pair(int c, long cost) {
            this.c = c;
            this.cost = cost;
        }

        public int c() {
            return c;
        }

        public long cost() {
            return cost;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Pair) obj;
            return this.c == that.c && this.cost == that.cost;
        }

        @Override
        public int hashCode() {
            return Objects.hash(c, cost);
        }

        @Override
        public String toString() {
            return "Pair[" + "c=" + c + ", " + "cost=" + cost + ']';
        }
    }
}
