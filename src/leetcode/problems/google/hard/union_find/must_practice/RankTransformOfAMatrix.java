package leetcode.problems.google.hard.union_find.must_practice;

import java.util.*;

public class RankTransformOfAMatrix {

    /*
    *

        Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].

        The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:

        The rank is an integer starting from 1.
        If two elements p and q are in the same row or column, then:
            If p < q then rank(p) < rank(q)
            If p == q then rank(p) == rank(q)
            If p > q then rank(p) > rank(q)
        The rank should be as small as possible.
        The test cases are generated so that answer is unique under the given rules.


        Input: matrix = [[1,2],[3,4]]
        Output: [[1,2],[2,3]]

        Explanation:

        The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
        The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
        The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
        The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0],
        and both matrix[0][1] and matrix[1][0] are rank 2.

*
* */

    class Solution {
        public int[][] matrixRankTransform(int[][] m) {
            int M = m.length, N = m[0].length;
            int[][] a = new int[M][N];
            int[] maxRankRow = new int[M];
            int[] maxRankCol = new int[N];
            TreeMap<Integer, List<int[]>> map = new TreeMap<>();
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    map.putIfAbsent(m[i][j], new ArrayList<>());
                    map.get(m[i][j]).add(new int[] { i, j });
                }
            }

            // go from the lowest value key to the highest
            for (int k : map.keySet()) {
                // repeat for each value until we used all cells with the same number
                // on each step we find cells connected by row/column and calculate their rank
                while (map.get(k).size() > 0) {
                    Set<Integer> rowsUsed = new HashSet<>();
                    Set<Integer> colsUsed = new HashSet<>();
                    List<int[]> points = map.get(k);

                    // get the first cell as the root and find all connected cells
                    int[] root = points.get(0);
                    rowsUsed.add(root[0]);
                    colsUsed.add(root[1]);
                    boolean[] used = new boolean[points.size()];
                    used[0] = true;
                    // continue until we found all connected
                    while (true) {
                        int added = 0;
                        for (int i = 1; i < points.size(); i++) {
                            int[] n = points.get(i);
                            if (used[i]) continue;
                            // if the cell is in the same row or column with the root or any one that is already connected with the root
                            if (rowsUsed.contains(n[0]) || colsUsed.contains(n[1])) {
                                rowsUsed.add(n[0]);
                                colsUsed.add(n[1]);
                                used[i] = true;
                                added++;
                            }
                        }
                        if (added == 0) break;
                    }
                    List<int[]> connected = new ArrayList<>();
                    List<int[]> left = new ArrayList<>();
                    for (int i = 0; i < points.size(); i++) {
                        if (used[i]) connected.add(points.get(i));
                        else left.add(points.get(i));
                    }
                    // put all that are not connected back to the map
                    map.put(k, left);

                    int rank = Integer.MIN_VALUE;

                    // calculate the maximum rank of all connected cells
                    for (int[] n : connected) {
                        rank = Math.max(rank, Math.max(maxRankRow[n[0]], maxRankCol[n[1]]) + 1);
                    }
                    // update maxRank for all cols and rows and set the rank as answer for each connected cell
                    for (int[] n : connected) {
                        maxRankRow[n[0]] = maxRankCol[n[1]] = rank;
                        a[n[0]][n[1]] = rank;
                    }
                }
            }
            return a;
        }
    }

    class SolutionBFS {
        public int[][] matrixRankTransform(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            // link row to col, and link col to row
            Map<Integer, Map<Integer, List<Integer>>> graphs = new HashMap<>();
            // graphs.get(v): the connection graph of value v
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int v = matrix[i][j];
                    // if not initialized, initial it
                    if (!graphs.containsKey(v)) {
                        graphs.put(v, new HashMap<Integer, List<Integer>>());
                    }
                    Map<Integer, List<Integer>> graph = graphs.get(v);
                    if (!graph.containsKey(i)) {
                        graph.put(i, new ArrayList<Integer>());
                    }
                    if (!graph.containsKey(~j)) {
                        graph.put(~j, new ArrayList<Integer>());
                    }
                    // link i to j, and link j to i
                    graph.get(i).add(~j);
                    graph.get(~j).add(i);
                }
            }

            // put points into `value2index` dict, grouped by connection
            // use TreeMap to help us sort the key automatically
            SortedMap<Integer, List<List<int[]>>> value2index = new TreeMap<>();
            boolean[][] seen = new boolean[m][n]; // mark whether put into `value2index` or not
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (seen[i][j]) {
                        continue;
                    }
                    seen[i][j] = true;
                    int v = matrix[i][j];
                    Map<Integer, List<Integer>> graph = graphs.get(v);
                    // store visited row and col
                    Set<Integer> rowcols = new HashSet<Integer>();
                    rowcols.add(i);
                    rowcols.add(~j);
                    // start bfs
                    Queue<Integer> q = new LinkedList<>();
                    q.offer(i);
                    q.offer(~j);
                    while (!q.isEmpty()) {
                        int node = q.poll();
                        for (int rowcol : graph.get(node)) {
                            if (!rowcols.contains(rowcol)) {
                                rowcols.add(rowcol);
                                q.offer(rowcol);
                            }
                        }
                    }
                    // transform rowcols into points
                    List<int[]> points = new ArrayList<>();
                    for (int rowcol : rowcols) {
                        for (int k : graph.get(rowcol)) {
                            if (k >= 0) {
                                points.add(new int[] { k, ~rowcol });
                                seen[k][~rowcol] = true;
                            } else {
                                points.add(new int[] { rowcol, ~k });
                                seen[rowcol][~k] = true;
                            }
                        }
                    }
                    if (!value2index.containsKey(v)) {
                        value2index.put(v, new ArrayList<List<int[]>>());
                    }
                    value2index.get(v).add(points);
                }
            }
            int[][] answer = new int[m][n]; // the required rank matrix
            int[] rowMax = new int[m]; // rowMax[i]: the max rank in i row
            int[] colMax = new int[n]; // colMax[j]: the max rank in j col
            for (int v : value2index.keySet()) {
                // update by connected points with same value
                for (List<int[]> points : value2index.get(v)) {
                    int rank = 1;
                    for (int[] point : points) {
                        rank = Math.max(rank, Math.max(rowMax[point[0]], colMax[point[1]]) + 1);
                    }
                    for (int[] point : points) {
                        answer[point[0]][point[1]] = rank;
                        // update rowMax and colMax
                        rowMax[point[0]] = Math.max(rowMax[point[0]], rank);
                        colMax[point[1]] = Math.max(colMax[point[1]], rank);
                    }
                }
            }
            return answer;
        }
    }

    class SolutionDFS {
        public void dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> rowcols) {
            // the dfs function to find connected parts
            rowcols.add(node);
            for (int rowcol : graph.get(node)) {
                if (!rowcols.contains(rowcol)) {
                    dfs(rowcol, graph, rowcols);
                }
            }
        }

        public int[][] matrixRankTransform(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            // link row to col, and link col to row
            Map<Integer, Map<Integer, List<Integer>>> graphs = new HashMap<>();
            // graphs.get(v): the connection graph of value v
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int v = matrix[i][j];
                    // if not initialized, initial it
                    if (!graphs.containsKey(v)) {
                        graphs.put(v, new HashMap<Integer, List<Integer>>());
                    }
                    Map<Integer, List<Integer>> graph = graphs.get(v);
                    if (!graph.containsKey(i)) {
                        graph.put(i, new ArrayList<Integer>());
                    }
                    if (!graph.containsKey(~j)) {
                        graph.put(~j, new ArrayList<Integer>());
                    }
                    // link i to j, and link j to i
                    graph.get(i).add(~j);
                    graph.get(~j).add(i);
                }
            }

            // put points into `value2index` dict, grouped by connection
            // use TreeMap to help us sort the key automatically
            SortedMap<Integer, List<List<int[]>>> value2index = new TreeMap<>();
            boolean[][] seen = new boolean[m][n]; // mark whether put into `value2index` or not
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (seen[i][j]) {
                        continue;
                    }
                    seen[i][j] = true;
                    int v = matrix[i][j];
                    Map<Integer, List<Integer>> graph = graphs.get(v);
                    // use dfs to find the connected parts
                    Set<Integer> rowcols = new HashSet<Integer>();
                    dfs(i, graph, rowcols);
                    dfs(~j, graph, rowcols);
                    // transform rowcols into points
                    List<int[]> points = new ArrayList<>();
                    for (int rowcol : rowcols) {
                        for (int k : graph.get(rowcol)) {
                            if (k >= 0) {
                                points.add(new int[] { k, ~rowcol });
                                seen[k][~rowcol] = true;
                            } else {
                                points.add(new int[] { rowcol, ~k });
                                seen[rowcol][~k] = true;
                            }
                        }
                    }
                    if (!value2index.containsKey(v)) {
                        value2index.put(v, new ArrayList<List<int[]>>());
                    }
                    value2index.get(v).add(points);
                }
            }
            int[][] answer = new int[m][n]; // the required rank matrix
            int[] rowMax = new int[m]; // rowMax[i]: the max rank in i row
            int[] colMax = new int[n]; // colMax[j]: the max rank in j col
            for (int v : value2index.keySet()) {
                // update by connected points with same value
                for (List<int[]> points : value2index.get(v)) {
                    int rank = 1;
                    for (int[] point : points) {
                        rank = Math.max(rank, Math.max(rowMax[point[0]], colMax[point[1]]) + 1);
                    }
                    for (int[] point : points) {
                        answer[point[0]][point[1]] = rank;
                        // update rowMax and colMax
                        rowMax[point[0]] = Math.max(rowMax[point[0]], rank);
                        colMax[point[1]] = Math.max(colMax[point[1]], rank);
                    }
                }
            }
            return answer;
        }
    }

    //https://leetcode.com/problems/rank-transform-of-a-matrix/solutions/1391380/c-python-hashmap-sorting-union-find-clean-concise/
    //  O(NMlog(NM))
    // Watch this, this implementation is much-much simpler. https://www.youtube.com/watch?v=KM2Pi9ARGqQ
    class SolutionUF {
        // implement find and union
        public int find(Map<Integer, Integer> UF, int x) {
            if (x != UF.get(x)) {
                UF.put(x, find(UF, UF.get(x)));
            }
            return UF.get(x);
        }

        public void union(Map<Integer, Integer> UF, int x, int y) {
            if (!UF.containsKey(x)) {
                UF.put(x, x);
            }
            if (!UF.containsKey(y)) {
                UF.put(y, y);
            }
            UF.put(find(UF, x), find(UF, y));
        }

        public int[][] matrixRankTransform(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            // link row and col together
            Map<Integer, Map<Integer, Integer>> UFs = new HashMap<>();
            // UFs.get(v): the Union-Find of value v
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int v = matrix[i][j];
                    if (!UFs.containsKey(v)) {
                        UFs.put(v, new HashMap<Integer, Integer>());
                    }
                    // union i to j
                    union(UFs.get(v), i, ~j); // ~j simply means (n+ j)th index for simplicity.
                }
            }

            // put points into `value2index` dict, grouped by connection
            // use TreeMap to help us sort the key automatically
            // value : [(groupId: [element_location1, element_location2], groupId2: [element_location1, element_location2...])]
            // [III]GroupId : is the representative of the group. We'll not use this, but this is just to separate groups.
            // Above thinking[III] is wrong, rank of an element should be same across matrix, so we can consider individual element positions together.
            // Now each group itself is going to have one rank, even though the element is different, groups can have different ranks as they have nothing in common.
            SortedMap<Integer, Map<Integer, List<int[]>>> groups = new TreeMap<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int v = matrix[i][j];
                    if (!groups.containsKey(v)) {
                        groups.put(v, new HashMap<Integer, List<int[]>>());
                    }
                    Map<Integer, List<int[]>> indexes = groups.get(v);
                    int f = find(UFs.get(v), i);
                    if (!indexes.containsKey(f)) {
                        indexes.put(f, new ArrayList<int[]>());
                    }
                    indexes.get(f).add(new int[] { i, j });
                }
            }

            int[][] answer = new int[m][n]; // the required rank matrix
            int[] rowMax = new int[m]; // rowMax[i]: the max rank in i row
            int[] colMax = new int[n]; // colMax[j]: the max rank in j col
            for (int v : groups.keySet()) {
                // update by connected points with same value
                for (List<int[]> group : groups.get(v).values()) {
                    int rank = 1;
                    for (int[] point : group) {
                        rank = Math.max(rank, Math.max(rowMax[point[0]], colMax[point[1]]) + 1);
                    }
                    for (int[] point : group) {
                        answer[point[0]][point[1]] = rank;
                        // update rowMax and colMax
                        rowMax[point[0]] = Math.max(rowMax[point[0]], rank);
                        colMax[point[1]] = Math.max(colMax[point[1]], rank);
                    }
                }
            }
            return answer;
        }
    }

}
