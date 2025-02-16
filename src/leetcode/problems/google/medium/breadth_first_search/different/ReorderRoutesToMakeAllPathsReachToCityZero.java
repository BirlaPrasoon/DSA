package leetcode.problems.google.medium.breadth_first_search.different;
import java.util.*;
public class ReorderRoutesToMakeAllPathsReachToCityZero {

/*    There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

    Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

    This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

    Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

            It's guaranteed that each city can reach city 0 after reorder.*/

    class SolutionBFS {
        int count = 0;

        public void bfs(int node, int n, Map<Integer, List<List<Integer>>> adj) {
            Queue<Integer> q = new LinkedList<>();
            boolean[] visit = new boolean[n];
            q.offer(node);
            visit[node] = true;

            while (!q.isEmpty()) {
                node = q.poll();
                if (!adj.containsKey(node)) {
                    continue;
                }
                for (List<Integer> nei : adj.get(node)) {
                    int neighbor = nei.get(0);
                    int sign = nei.get(1);
                    if (!visit[neighbor]) {
                        count += sign;
                        visit[neighbor] = true;
                        q.offer(neighbor);
                    }
                }
            }
        }

        public int minReorder(int n, int[][] connections) {
            Map<Integer, List<List<Integer>>> adj = new HashMap<>();
            for (int[] connection : connections) {
                adj.putIfAbsent(connection[0], new ArrayList<>());
                adj.putIfAbsent(connection[1], new ArrayList<>());
                // reverse edge
                adj.get(connection[0]).add(Arrays.asList(connection[1], 1));
                // don't reverse the edge
                adj.get(connection[1]).add(Arrays.asList(connection[0], 0));
            }
            bfs(0, n, adj);
            return count;
        }
    }

}
