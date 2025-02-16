package leetcode.problems.google.hard.graphs.diff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RedundantConnections2 {
    class Solution {

        public class DisjointSet {
            int[] size;
            int[] parent;

            DisjointSet(int nodeCount) {
                parent = new int[nodeCount];
                size = new int[nodeCount];

                for (int i = 0; i < nodeCount; i++) {
                    parent[i] = i;
                    size[i] = 1;
                }
            }

            public int findUparent(int node) {
                if (node == parent[node])
                    return node;

                return parent[node] = findUparent(parent[node]);
            }

            public void unionBySize(int a, int b) {
                int upa = findUparent(a);
                int upb = findUparent(b);

                if (upa == upb)
                    return;

                int upa_size = size[upa];
                int upb_size = size[upb];

                if (upa_size < upb_size) {
                    parent[upa] = upb;
                    size[upb] += upa_size;
                } else {
                    parent[upb] = upa;
                    size[upa] += upb_size;
                }
            }
        }

        public int[] findRedundantDirectedConnection(int[][] edges) {

            int n = edges.length;
            int indegree[] = new int[n + 1];

            Arrays.fill(indegree, -1);

            int candidate1 = -1;
            int candidate2 = -1;

            /** Identify double parent node : Where indegree becomes 2 */
            for (int i = 0; i < n; i++) {
                int to = edges[i][1];

                if (indegree[to] == -1) {
                    indegree[to] = i;
                } else {
                    /* Both edges are candidate for redundant connection */
                    candidate1 = i;
                    candidate2 = indegree[to];
                    break;
                }
            }

            /** If candidate1 & candidate2 = -1, Means double parent doesn't exist
             for any node, but it will have cycle */
            boolean doubleParent = (candidate1 != -1 && candidate2 != -1);

            DisjointSet ds = new DisjointSet(n + 1);

            /** Check if there exists a cycle after removing the candidate1 */
            for (int i = 0; i < n; i++) {

                /** Skip candidate1 : so that n-1 edges are processed */
                if (i == candidate1)
                    continue;

                int from = edges[i][0];
                int to = edges[i][1];

                if (ds.findUparent(from) != ds.findUparent(to)) {
                    ds.unionBySize(from, to);
                } else {
                    /** If double parent doesn't exist means this edge is forming
                     a cycle, Hence return the processing edge */
                    if (!doubleParent)
                        return edges[i];
                    else {
                        /** Cycle is being formed even after removing candidate1 Hence
                         candidate1 can't be the redundant connection; return candidate2
                         in this case */
                        return edges[candidate2];
                    }

                }
            }

            /** Cycle could not be formed after removing candidate1, Hence return candidate1 */
            return edges[candidate1];
        }
    }


}
