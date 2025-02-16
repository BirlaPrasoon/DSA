package leetcode.problems.google.hard.graphs.dijkstra;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SafestPathInAGrid {

    /**
     * You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:
     * <p>
     * A cell containing a thief if grid[r][c] = 1
     * An empty cell if grid[r][c] = 0
     * You are initially positioned at cell (0, 0).
     * In one move, you can move to any adjacent cell in the grid, including cells containing thieves.
     * <p>
     * The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.
     * <p>
     * Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).
     * <p>
     * An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.
     * <p>
     * The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.
     */

    class Solution {

        // Directions for moving to neighboring cells: right, left, down, up
        final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        /**
         * Time complexity: O(n<sup>2</sup>⋅logn).
         * */
        public int maximumSafenessFactor(List<List<Integer>> grid) {
            int n = grid.size();
            int[][] mat = new int[n][n];
            Queue<int[]> multiSourceQueue = new LinkedList<>();

            // To make modifications and navigation easier, the grid is converted into a 2-d array.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid.get(i).get(j) == 1) {
                        // Push thief coordinates to the queue
                        multiSourceQueue.add(new int[]{i, j});
                        // Mark thief cell with 0
                        mat[i][j] = 0;
                    } else {
                        // Mark empty cell with -1
                        mat[i][j] = -1;
                    }
                }
            }

            // Calculate safeness factor for each cell using BFS
            while (!multiSourceQueue.isEmpty()) {
                int size = multiSourceQueue.size();
                while (size-- > 0) {
                    int[] curr = multiSourceQueue.poll();
                    // Check neighboring cells
                    for (int[] d : dir) {
                        int di = curr[0] + d[0];
                        int dj = curr[1] + d[1];
                        int val = mat[curr[0]][curr[1]];
                        // Check if the neighboring cell is valid and unvisited
                        if (isValidCell(mat, di, dj) && mat[di][dj] == -1) {
                            // Update safeness factor and push to the queue
                            mat[di][dj] = val + 1;
                            multiSourceQueue.add(new int[]{di, dj});
                        }
                    }
                }
            }

            // Binary search for maximum safeness factor
            int start = 0;
            int end = 0;
            int res = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Set end as the maximum safeness factor possible
                    end = Math.max(end, mat[i][j]);
                }
            }

            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (isValidSafeness(mat, mid)) {
                    // Store valid safeness and search for larger ones
                    res = mid;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            return res;
        }

        // Check if a path exists with given minimum safeness value
        private boolean isValidSafeness(int[][] grid, int minSafeness) {
            int n = grid.length;

            // Check if the source and destination cells satisfy minimum safeness
            if (grid[0][0] < minSafeness || grid[n - 1][n - 1] < minSafeness) {
                return false;
            }

            Queue<int[]> traversalQueue = new LinkedList<>();
            traversalQueue.add(new int[]{0, 0});
            boolean[][] visited = new boolean[n][n];
            visited[0][0] = true;

            // Breadth-first search to find a valid path
            while (!traversalQueue.isEmpty()) {
                int[] curr = traversalQueue.poll();
                if (curr[0] == n - 1 && curr[1] == n - 1) {
                    return true; // Valid path found
                }
                // Check neighboring cells
                for (int[] d : dir) {
                    int di = curr[0] + d[0];
                    int dj = curr[1] + d[1];
                    // Check if the neighboring cell is valid, unvisited and satisfying minimum safeness
                    if (isValidCell(grid, di, dj) && !visited[di][dj] && grid[di][dj] >= minSafeness) {
                        visited[di][dj] = true;
                        traversalQueue.add(new int[]{di, dj});
                    }
                }
            }

            return false; // No valid path found
        }

        // Check if a given cell lies within the grid
        private boolean isValidCell(int[][] mat, int i, int j) {
            int n = mat.length;
            return i >= 0 && j >= 0 && i < n && j < n;
        }
    }

    /**
    * Time complexity: O(n<sup>2</sup>⋅logn)</br>
     * Modified Dijkstra
    * */
    class SolutionBFSAndGreedy {

        // Directions for moving to neighboring cells: right, left, down, up
        final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int maximumSafenessFactor(List<List<Integer>> grid) {
            int n = grid.size();
            int[][] mat = new int[n][n];
            Queue<int[]> multiSourceQueue = new LinkedList<>();

            // To make modifications and navigation easier, the grid is converted into a 2-d array
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid.get(i).get(j) == 1) {
                        // Push thief coordinates to the queue
                        multiSourceQueue.add(new int[]{i, j});
                        // Mark thief cell with 0
                        mat[i][j] = 0;
                    } else {
                        // Mark empty cell with -1
                        mat[i][j] = -1;
                    }
                }
            }

            // Calculate safeness factor for each cell using BFS
            while (!multiSourceQueue.isEmpty()) {
                for(int x = multiSourceQueue.size(); x>0; x--) {
                    int[] curr = multiSourceQueue.poll();
                    // Check neighboring cells
                    for (int[] d : dir) {
                        int di = curr[0] + d[0];
                        int dj = curr[1] + d[1];
                        int val = mat[curr[0]][curr[1]];
                        // Check if the neighboring cell is valid and unvisited
                        if (isValidCell(mat, di, dj) && mat[di][dj] == -1) {
                            // Update safeness factor and push to the queue
                            mat[di][dj] = val + 1;
                            multiSourceQueue.add(new int[]{di, dj});
                        }
                    }
                }
            }

            // Priority queue to prioritize cells with higher safeness factor
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
            // Push starting cell to the priority queue
            pq.add(new int[]{0, 0, mat[0][0]}); // [x-coordinate, y-coordinate, maximum_safeness_till_now]
            mat[0][0] = -1; // Mark the source cell as visited

            // BFS to find the path with maximum safeness factor
            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                // If reached the destination, return safeness factor
                if (curr[0] == n - 1 && curr[1] == n - 1) {
                    return curr[2];
                }
                // Explore neighboring cells
                for (int[] d : dir) {
                    int di = d[0] + curr[0];
                    int dj = d[1] + curr[1];
                    if (isValidCell(mat, di, dj) && mat[di][dj] != -1) {
                        // Update safeness factor for the path and mark the cell as visited
                        pq.add(new int[]{di, dj, Math.min(curr[2], mat[di][dj])});
                        mat[di][dj] = -1;
                    }
                }
            }

            return -1; // No valid path found
        }

        // Check if a given cell lies within the grid
        private boolean isValidCell(int[][] mat, int i, int j) {
            int n = mat.length;
            return i >= 0 && j >= 0 && i < n && j < n;
        }
    }
}
