package leetcode.problems.google.medium.breadth_first_search;

import leetcode.Pair;

import java.util.*;

public class AsFarFromLandAsPossible {

    /**
     * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land,
     * find a water cell such that its distance to the nearest land cell is maximized, and return the distance.
     * If no land or water exists in the grid, return -1.
     * <p>
     * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1)
     * is |x0 - x1| + |y0 - y1|.
     * <p>
     * Example 1:
     * Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
     * Output: 2
     * Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
     * <p>
     * Example 2:
     * Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
     * Output: 4
     * Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
     */

    class SolutionBFS {
        // Four directions: Up, Down, Left and Right.
        int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int maxDistance(int[][] grid) {
            // A copy matrix of the grid to mark water cells as land once visited.
            int[][] visited = new int[grid.length][grid[0].length];

            // Insert all the land cells in the queue.
            Queue<Pair<Integer, Integer>> q = new LinkedList<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    // Copy grid to the visited matrix.
                    visited[i][j] = grid[i][j];
                    if (grid[i][j] == 1) {
                        q.offer(new Pair(i, j));
                    }
                }
            }

            int distance = -1;
            while (!q.isEmpty()) {
                int qSize = q.size();

                // Iterate over all the current cells in the queue.
                while (qSize-- > 0) {
                    Pair<Integer, Integer> landCell = q.poll();
                    assert landCell != null;

                    // From the current land cell, traverse to all the four directions
                    // and check if it is a water cell. If yes, convert it to land
                    // and add it to the queue.
                    for (int[] dir : direction) {
                        int x = landCell.key + dir[0];
                        int y = landCell.value + dir[1];

                        if (insideBoundaryAndNotVisited(grid, x, y, visited)) {
                            // Marking as 1 to avoid re-iterating it.
                            visited[x][y] = 1;
                            q.offer(new Pair<>(x, y));
                        }
                    }
                }

                // After each iteration of queue elements, increment distance
                // as we covered 1 unit distance from all cells in every direction.
                distance++;
            }

            // If the distance is 0, there is no water cell.
            return distance == 0 ? -1 : distance;
        }

        private static boolean insideBoundaryAndNotVisited(int[][] grid, int x, int y, int[][] visited) {
            return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && visited[x][y] == 0;
        }
    }

    class SolutionDP {
        public int maxDistance(int[][] grid) {
            int rows = grid.length;
            // Although it's a square matrix, using different variable for readability.
            int cols = grid[0].length;

            // Maximum manhattan distance possible + 1.
            final int MAX_DISTANCE = rows + cols + 1;

            int[][] dist = new int[rows][cols];
            for (int[] arr : dist)
                Arrays.fill(arr, MAX_DISTANCE);

            // First pass: check for left and top neighbours
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // Distance of land cells will be 0.
                    if (grid[i][j] == 1) {
                        dist[i][j] = 0;
                    } else {
                        // Check left and top cell distances if they exist and update the current cell distance.
                        dist[i][j] = Math.min(
                                dist[i][j],
                                Math.min(
                                        i > 0 ? dist[i - 1][j] + 1 : MAX_DISTANCE,
                                        j > 0 ? dist[i][j - 1] + 1 : MAX_DISTANCE
                                ));
                    }
                }
            }

            // Second pass: check for the bottom and right neighbours.
            int ans = Integer.MIN_VALUE;
            for (int i = rows - 1; i >= 0; i--) {
                for (int j = cols - 1; j >= 0; j--) {
                    // Check the right and bottom cell distances if they exist and update the current cell distance.
                    dist[i][j] = Math.min(
                            dist[i][j],
                            Math.min(
                                    i < rows - 1 ? dist[i + 1][j] + 1 : MAX_DISTANCE,
                                    j < cols - 1 ? dist[i][j + 1] + 1 : MAX_DISTANCE
                            ));

                    ans = Math.max(ans, dist[i][j]);
                }
            }

            // If ans is 0, it means there is no water cell,
            // If ans is MAX_DISTANCE, it implies no land cell.
            return ans == 0 || ans == MAX_DISTANCE ? -1 : ans;
        }
    }

    ;
}
