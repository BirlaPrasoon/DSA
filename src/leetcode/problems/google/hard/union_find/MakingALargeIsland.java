package leetcode.problems.google.hard.union_find;

import java.util.*;

public class MakingALargeIsland {

    /**
     * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.<br>
     * <br>
     * Return the size of the largest island in grid after applying this operation.<br>
     * <br>
     * An island is a 4-directionally connected group of 1s.<br>
     *
     * <br><br>
     * Example 1:<br>
     * <p>
     * Input: grid = [[1,0],[0,1]]<br>
     * Output: 3<br>
     * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.<br><br>
     * Example 2:<br>
     * <p>
     * Input: grid = [[1,1],[1,0]]<br>
     * Output: 4<br>
     * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.<br>
     * Example 3:<br>
     * <p>
     * Input: grid = [[1,1],[1,1]]<br>
     * Output: 4<br>
     * Explanation: Can't change any 0 to 1, only one island with area = 4.<br>
     */

    // N4
    class Solution {
        int[] dr = new int[]{-1, 0, 1, 0};
        int[] dc = new int[]{0, -1, 0, 1};

        public int largestIsland(int[][] grid) {
            int N = grid.length;

            int ans = 0;
            boolean hasZero = false;
            for (int r = 0; r < N; ++r)
                for (int c = 0; c < N; ++c)
                    if (grid[r][c] == 0) {
                        hasZero = true;
                        grid[r][c] = 1;
                        ans = Math.max(ans, check(grid, r, c));
                        grid[r][c] = 0;
                    }

            return hasZero ? ans : N * N;
        }

        public int check(int[][] grid, int r0, int c0) {
            int N = grid.length;
            Stack<Integer> stack = new Stack<>();
            Set<Integer> seen = new HashSet<>();
            stack.push(r0 * N + c0);
            seen.add(r0 * N + c0);

            while (!stack.isEmpty()) {
                int code = stack.pop();
                int r = code / N, c = code % N;
                for (int k = 0; k < 4; ++k) {
                    int nr = r + dr[k], nc = c + dc[k];
                    if (!seen.contains(nr * N + nc) && 0 <= nr && nr < N &&
                            0 <= nc && nc < N && grid[nr][nc] == 1) {
                        stack.push(nr * N + nc);
                        seen.add(nr * N + nc);
                    }
                }
            }

            return seen.size();
        }
    }

    // N2
    class SolutionOptimizedUF {
        int[] dr = new int[]{-1, 0, 1, 0};
        int[] dc = new int[]{0, -1, 0, 1};
        int[][] grid;
        int N;

        public int largestIsland(int[][] grid) {
            this.grid = grid;
            N = grid.length;

            int index = 2;
            int[] area = new int[N * N + 2];
            for (int r = 0; r < N; ++r)
                for (int c = 0; c < N; ++c)
                    if (grid[r][c] == 1)
                        area[index] = dfs(r, c, index++);

            int ans = 0;
            for (int x : area) ans = Math.max(ans, x);
            for (int r = 0; r < N; ++r)
                for (int c = 0; c < N; ++c)
                    if (grid[r][c] == 0) {
                        Set<Integer> seen = new HashSet<>();
                        for (Integer move : neighbors(r, c))
                            if (grid[move / N][move % N] > 1)
                                seen.add(grid[move / N][move % N]);

                        int bns = 1;
                        for (int i : seen) bns += area[i];
                        ans = Math.max(ans, bns);
                    }

            return ans;
        }

        public int dfs(int r, int c, int index) {
            int ans = 1;
            grid[r][c] = index;
            for (Integer move : neighbors(r, c)) {
                if (grid[move / N][move % N] == 1) {
                    grid[move / N][move % N] = index;
                    ans += dfs(move / N, move % N, index);
                }
            }

            return ans;
        }

        public List<Integer> neighbors(int r, int c) {
            List<Integer> ans = new ArrayList<>();
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (0 <= nr && nr < N && 0 <= nc && nc < N)
                    ans.add(nr * N + nc);
            }

            return ans;
        }
    }


}
