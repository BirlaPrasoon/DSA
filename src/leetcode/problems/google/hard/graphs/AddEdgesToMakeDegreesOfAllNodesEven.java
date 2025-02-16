package leetcode.problems.google.hard.graphs;

import java.util.*;

public class AddEdgesToMakeDegreesOfAllNodesEven {

    /*
    * Intuition
So here first find out all the vertices whose edges are odd.

Approach
Now find out if the number of odd vertices are 2 or 4, if it is 2 than it should not be connected together
* by any vertex so that we can establish connection between them using 1 vertex, and if it is 4 than it should be
* connected by one another, so that it should return true and we can connect them using atmost 2 vertices.
Note: Here the maximum number of edges we can add are 2.
    * */
    class Solution {
        HashSet<Integer>[] graph;

        public boolean isPossible(int n, List<List<Integer>> edges) {
            makeUndirectedGraph(edges);
            Set<Integer> set = getOddDegreeVertices(n);

            if (set.isEmpty()) return true;

            if (set.size() % 2 == 1 || set.size() > 4) return false;
            // it is not possible to connect odd number of odd vertices.

            if (set.size() == 4) return four(set);
            return two(set);
        }

        private Set<Integer> getOddDegreeVertices(int n) {
            graph = new HashSet[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new HashSet<>();
            }

            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= n; i++)
                if (graph[i].size() % 2 == 1) set.add(i);
            return set;
        }

        private void makeUndirectedGraph(List<List<Integer>> edges) {
            for (List<Integer> list : edges) {
                graph[list.get(0)].add(list.get(1));
                graph[list.get(1)].add(list.get(0));
            }
        }

        boolean four(Set<Integer> set) {
            Iterator<Integer> iterator = set.iterator();

            int p = iterator.next();
            int q = iterator.next();
            int r = iterator.next();
            int s = iterator.next();

            // We need to try and make edges in between them only... if they're not already connected..
            // (pq, rs) || (pr,qs) || (ps, qr)

            return ((notConnected(p, q) && notConnected(r, s)) ||
                    (notConnected(p, r)) && notConnected(q, s)) ||
                    (notConnected(p, s) && notConnected(q, r));
        }

        private boolean notConnected(int x, int y) {
            return !graph[x].contains(y);
        }

        boolean two(Set<Integer> set) {
            Iterator<Integer> iterator = set.iterator();
            int x = iterator.next();
            int y = iterator.next();

            // Check if these are not already connected, connect them.
            if (notConnected(x,y)) return true;
            return checkForThirdVertexWhichIsNotConnectedToEither(x, y);
        }

        private boolean checkForThirdVertexWhichIsNotConnectedToEither(int x, int y) {
            for (int i = 1; i < graph.length; i++)
                if (i != x && i != y && notConnected(i, x) && notConnected(i, y))
                    return true;
            return false;
        }
    }
}
