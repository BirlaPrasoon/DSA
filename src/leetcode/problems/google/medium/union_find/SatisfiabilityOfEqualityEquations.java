package leetcode.problems.google.medium.union_find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SatisfiabilityOfEqualityEquations {

    /*
    *
    * You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.



Example 1:

Input: equations = ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
There is no way to assign the variables to satisfy both equations.
Example 2:

Input: equations = ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
    *
    * */

    class SolutionDFS {
        public boolean equationsPossible(String[] equations) {
            List<Integer>[] graph = new ArrayList[26];
            for (int i = 0; i < 26; ++i)
                graph[i] = new ArrayList();

            for (String eqn : equations) {
                if (eqn.charAt(1) == '=') {
                    int x = eqn.charAt(0) - 'a';
                    int y = eqn.charAt(3) - 'a';
                    graph[x].add(y);
                    graph[y].add(x);
                }
            }

            int[] color = new int[26];
            Arrays.fill(color, -1);

            for (int i = 0; i < 26; i++) {
                if (color[i] == -1) {
                    dfs(i, i, color, graph);
                }
            }

            for (String eqn : equations) {
                if (eqn.charAt(1) == '!') {
                    int x = eqn.charAt(0) - 'a';
                    int y = eqn.charAt(3) - 'a';
                    if (color[x] == color[y])
                        return false;
                }
            }

            return true;
        }

        // mark the color of `node` as `c`
        private static void dfs(int node, int c, int[] color, List<Integer>[] graph) {
            if (color[node] == -1) {
                color[node] = c;
                for (int nei : graph[node])
                    dfs(nei, c, color, graph);
            }
        }
    }

    class SolutionUF {
        public boolean equationsPossible(String[] equations) {
            int[] root = new int[26];
            for (int i = 0; i < 26; i++) {
                root[i] = i;
            }

            for (String eqn : equations) {
                if (eqn.charAt(1) == '=') {
                    int x = eqn.charAt(0) - 'a';
                    int y = eqn.charAt(3) - 'a';
                    union(root, x, y);
                }
            }

            for (String eqn : equations) {
                if (eqn.charAt(1) == '!') {
                    int x = eqn.charAt(0) - 'a';
                    int y = eqn.charAt(3) - 'a';
                    if (find(root, x) == find(root, y))
                        return false;
                }
            }

            return true;
        }

        private static int find(int[] root, int x) {
            if (root[x] != x)
                root[x] = find(root, root[x]);
            return root[x];
        }

        private static void union(int[] root, int x, int y) {
            x = find(root, x);
            y = find(root, y);
            if (x != y)
                root[x] = y;
        }
    }

}
