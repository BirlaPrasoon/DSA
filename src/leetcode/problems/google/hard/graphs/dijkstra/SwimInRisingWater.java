package leetcode.problems.google.hard.graphs.dijkstra;

import java.util.*;

public class SwimInRisingWater {
    /**
     * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).</br>
     * The rain starts to fall. At time t, the depth of the water everywhere is t.</br> You can swim from a square to another 4-directionally adjacent square
     * if and only if the elevation of both squares individually are at most t. </br></br> You can swim infinite distances in zero time.
     * <p>
     * Of course, you must stay within the boundaries of the grid during your swim.</br>
     * <p>
     * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).</br>
     *
     * </br>
     * Input: grid = [[0,2],[1,3]]</br>
     * Output: 3</br></br>
     * Explanation:</br>
     * At time 0, you are in grid location (0, 0).</br>
     * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.</br>
     * You cannot reach point (1, 1) until time 3.</br>
     * When the depth of water is 3, we can swim anywhere inside the grid.</br>
     * </br></br>
     * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]</br>
     * Output: 16</br></br>
     * Explanation: The final route is shown.</br>
     * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.</br>
     *
     *
     * <i>The question is essentially asking us to find a path where the maximum height is minimized.</i>
     */


    class SolutionPriorityQueueAndBFS {
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        record Point(int x, int y) {
        }

        /**
         * Why not explore all paths and find the minimum of max heights ? That's exponential...</br>
         * <p>
         * This solution using Modified dijkstra is O(n<sup>2</sup>log(n<sup>2</sup>)) -> O(n<sup>2</sup>log(n))
         */
        public int swimInWater(int[][] grid) {
            int n = grid.length;
            Queue<Point> q = new PriorityQueue<>(Comparator.comparingInt(a -> grid[a.x][a.y]));
            Set<Point> visited = new HashSet<>();
            Point start = new Point(0, 0);
            Point end = new Point(n - 1, n - 1);
            q.offer(start);
            visited.add(start);
            int level = grid[0][0];
            while (!q.isEmpty()) {
                Point curr = q.poll();
                level = Math.max(level, grid[curr.x][curr.y]);
                if (curr.equals(end)) {
                    return level;
                }
                for (var dir : direction) {
                    int x = curr.x + dir[0];
                    int y = curr.y + dir[1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        Point next = new Point(x, y);
                        if (visited.contains(next)) continue;
                        visited.add(next);
                        q.offer(next);
                    }
                }
            }
            return -1;
        }
    }

    class Solution {

        static int[][] arr;
        static int n;
        static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        static boolean helper(int x) {
            if (arr[0][0] > x) return false;
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{0, 0});
            int[][] visited = new int[n][n];
            visited[0][0] = 1;

            while (!q.isEmpty()) {
                int[] a = q.remove();
                int r = a[0];
                int c = a[1];
                if (r == n - 1 && c == n - 1) return true;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dir[i][0];
                    int nc = c + dir[i][1];
                    if (nr >= 0 && nc >= 0 && nr < n && nc < n && visited[nr][nc] != 1 && arr[nr][nc] <= x) {
                        q.add(new int[]{nr, nc});
                        visited[nr][nc] = 1;
                    }
                }
            }
            return false;
        }

        public int swimInWater(int[][] grid) {
            arr = grid;
            n = arr.length;
            int st = 0;
            int end = 2505;
            int ans = -1;
            while (st <= end) {
                int mid = st + (end - st) / 2;
                if (helper(mid)) {
                    ans = mid;
                    end = mid - 1;
                } else st = mid + 1;
            }
            return ans;
        }
    }
}
