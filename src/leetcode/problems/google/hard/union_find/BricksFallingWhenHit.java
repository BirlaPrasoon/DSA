package leetcode.problems.google.hard.union_find;

import java.util.Arrays;

public class BricksFallingWhenHit {


    /**
     * You are given an m x n binary grid, where each 1 represents a brick and 0 represents an empty space.<br><br>
     * A brick is stable if:<br>
     * <p>
     * It is directly connected to the top of the grid, or<br>
     * At least one other brick in its four adjacent cells is stable.<br>
     * You are also given an array hits, which is a sequence of erasures we want to apply.<br>
     * Each time we want to erase the brick at the location hits[i] = (rowi, coli).<br>
     * The brick on that location (if it exists) will disappear. Some other bricks may no longer be stable<br>
     * because of that erasure and will fall. Once a brick falls, it is immediately erased from the grid (i.e., it does not land on other stable bricks).<br>
     * <br>
     * Return an array result, where each result[i] is the number of bricks that will fall after the ith erasure is applied.<br>
     * <br>
     * Note that an erasure may refer to a location with no brick, and if it does, no bricks drop.<br>
     *
     * <br>
     * Input: grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]<br>
     * Output: [2]<br>
     * Explanation: Starting with the grid:<br>
     * [[1,0,0,0],<br>
     * [1,1,1,0]]<br>
     * We erase the underlined brick at (1,0), resulting in the grid:<br>
     * [[1,0,0,0],<br>
     * [0,1,1,0]]<br>
     * The two underlined bricks are no longer stable as they are no longer connected to the top nor adjacent to another stable brick, so they will fall. The resulting grid is:<br>
     * [[1,0,0,0],<br>
     * [0,0,0,0]]<br>
     * Hence the result is [2].<br>
     */

    class Solution {
        public int[] hitBricks(int[][] grid, int[][] hits) {
            int R = grid.length, C = grid[0].length;
            int[] dr = {1, 0, -1, 0};
            int[] dc = {0, 1, 0, -1};

            int[][] A = new int[R][C];
            for (int r = 0; r < R; ++r)
                A[r] = grid[r].clone();
            for (int[] hit : hits)
                A[hit[0]][hit[1]] = 0;

            DSU dsu = new DSU(R * C + 1);
            for (int r = 0; r < R; ++r) {
                for (int c = 0; c < C; ++c) {
                    if (A[r][c] == 1) {
                        int i = r * C + c;
                        if (r == 0)
                            dsu.union(i, R * C);
                        if (r > 0 && A[r - 1][c] == 1)
                            dsu.union(i, (r - 1) * C + c);
                        if (c > 0 && A[r][c - 1] == 1)
                            dsu.union(i, r * C + c - 1);
                    }
                }
            }
            int t = hits.length;
            int[] ans = new int[t--];

            while (t >= 0) {
                int r = hits[t][0];
                int c = hits[t][1];
                int preRoof = dsu.top();
                if (grid[r][c] == 0) {
                    t--;
                } else {
                    int i = r * C + c;
                    for (int k = 0; k < 4; ++k) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        if (0 <= nr && nr < R && 0 <= nc && nc < C && A[nr][nc] == 1)
                            dsu.union(i, nr * C + nc);
                    }
                    if (r == 0)
                        dsu.union(i, R * C);
                    A[r][c] = 1;
                    ans[t--] = Math.max(0, dsu.top() - preRoof - 1);
                }
            }

            return ans;
        }
    }

    class DSU {
        int[] parent;
        int[] rank;
        int[] sz;

        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; ++i)
                parent[i] = i;
            rank = new int[N];
            sz = new int[N];
            Arrays.fill(sz, 1);
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int xr = find(x), yr = find(y);
            if (xr == yr) return;

            if (rank[xr] < rank[yr]) {
                int tmp = yr;
                yr = xr;
                xr = tmp;
            }
            if (rank[xr] == rank[yr])
                rank[xr]++;

            parent[yr] = xr;
            // This is the modification.
            sz[xr] += sz[yr];
        }

        public int size(int x) {
            return sz[find(x)];
        }

        public int top() {
            return size(sz.length - 1) - 1;
        }
    }
}
