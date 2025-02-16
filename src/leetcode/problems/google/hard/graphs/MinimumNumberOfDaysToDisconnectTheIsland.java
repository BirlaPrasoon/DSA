package leetcode.problems.google.hard.graphs;

import java.util.Arrays;

public class MinimumNumberOfDaysToDisconnectTheIsland {
    // count number of islands, if greater than 1, return 0;
    // now check if removing any one cell disconnects the island, if yes, return 1.
    // its a matrix, pick the left most cell with value 1, now this cell can be connected to at most 2 cells.
    // so we have to delete at most 2 cells for this one cell to be disconnected from the remaining island.


/**    You are given an m x n binary grid grid where 1 represents land and 0 represents water. An island is a maximal 4-directionally (horizontal or vertical) connected group of 1's.</br></br>

    The grid is said to be connected if we have exactly one island, otherwise is said disconnected.</br></br>

    In one day, we are allowed to change any single land cell (1) into a water cell (0).</br></br>

    Return the minimum number of days to disconnect the grid.</br></br>


    Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]</br>

    Output: 2</br>
    Explanation: We need at least 2 days to get a disconnected grid.</br>
    Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.</br></br>


    Input: grid = [[1,1]]</br>
    Output: 2</br>
    Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.</br></br>


            Constraints:</br></br>

    m == grid.length</br>
    n == grid[i].length</br>
    1 <= m, n <= 30</br>
    grid[i][j] is either 0 or 1.</br>
    */

    class Solution {

        // Directions for adjacent cells: right, left, down, up
        private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minDays(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;

            // Count initial islands
            int initialIslandCount = countIslands(grid);

            // Already disconnected or no land
            if (initialIslandCount != 1) {
                return 0;
            }

            // Try removing each land cell
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (grid[row][col] == 0) continue; // Skip water

                    // Temporarily change to water
                    grid[row][col] = 0;
                    int newIslandCount = countIslands(grid);

                    // Check if disconnected
                    if (newIslandCount != 1) return 1;

                    // Revert change
                    grid[row][col] = 1;
                }
            }

            return 2;
        }

        private int countIslands(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            boolean[][] visited = new boolean[rows][cols];
            int islandCount = 0;

            // Iterate through all cells
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    // Found new island
                    if (!visited[row][col] && grid[row][col] == 1) {
                        exploreIsland(grid, row, col, visited);
                        islandCount++;
                    }
                }
            }
            return islandCount;
        }

        // Helper method to explore all cells of an island
        private void exploreIsland(int[][] grid, int row, int col, boolean[][] visited) {
            visited[row][col] = true;

            // Check all adjacent cells
            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                // Explore if valid land cell
                if (isValidLandCell(grid, newRow, newCol, visited)) {
                    exploreIsland(grid, newRow, newCol, visited);
                }
            }
        }

        private boolean isValidLandCell(int[][] grid, int row, int col, boolean[][] visited) {
            int rows = grid.length;
            int cols = grid[0].length;
            // Check bounds, land, and not visited
            return (row >= 0 && col >= 0 && row < rows && col < cols && grid[row][col] == 1 && !visited[row][col]);
        }
    }

    // Articulation point way...
    // Time complexity: O(m⋅n)
    class SolutionArticulationPoint {

        // Directions for adjacent cells: right, down, left, up
        private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0},};

        public int minDays(int[][] grid) {
            int rows = grid.length, cols = grid[0].length;
            ArticulationPointInfo apInfo = new ArticulationPointInfo(false, 0);
            int landCells = 0, islandCount = 0;

            // Arrays to store information for each cell
            int[][] discoveryTime = new int[rows][cols]; // Time when a cell is first discovered
            int[][] lowestReachable = new int[rows][cols]; // Lowest discovery time reachable from the subtree rooted at
            // this cell
            int[][] parentCell = new int[rows][cols]; // Parent of each cell in DFS tree

            // Initialize arrays with default values
            for (int i = 0; i < rows; i++) {
                Arrays.fill(discoveryTime[i], -1);
                Arrays.fill(lowestReachable[i], -1);
                Arrays.fill(parentCell[i], -1);
            }

            // Traverse the grid to find islands and articulation points
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        landCells++;
                        if (discoveryTime[i][j] == -1) { // If not yet visited
                            // Start DFS for a new island
                            findArticulationPoints(grid, i, j, discoveryTime, lowestReachable, parentCell, apInfo);
                            islandCount++;
                        }
                    }
                }
            }

            // Determine the minimum number of days to disconnect the grid
            if (islandCount == 0 || islandCount >= 2) return 0; // Already disconnected or no land
            if (landCells == 1) return 1; // Only one land cell
            if (apInfo.hasArticulationPoint) return 1; // An articulation point exists
            return 2; // Need to remove any two land cells
        }

        private void findArticulationPoints(int[][] grid, int row, int col, int[][] discoveryTime, int[][] lowestReachable, int[][] parentCell, ArticulationPointInfo apInfo) {
            int rows = grid.length, cols = grid[0].length;
            discoveryTime[row][col] = apInfo.time;
            apInfo.time++;
            lowestReachable[row][col] = discoveryTime[row][col];
            int children = 0;

            // Explore all adjacent cells
            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if (isValidLandCell(grid, newRow, newCol)) {
                    if (discoveryTime[newRow][newCol] == -1) {
                        children++;
                        parentCell[newRow][newCol] = row * cols + col; // Set parent
                        findArticulationPoints(grid, newRow, newCol, discoveryTime, lowestReachable, parentCell, apInfo);

                        // Update lowest reachable time
                        lowestReachable[row][col] = Math.min(lowestReachable[row][col], lowestReachable[newRow][newCol]);

                        // Check for articulation point condition
                        if (lowestReachable[newRow][newCol] >= discoveryTime[row][col] && parentCell[row][col] != -1) {
                            apInfo.hasArticulationPoint = true;
                        }
                    } else if (newRow * cols + newCol != parentCell[row][col]) {
                        // Update lowest reachable time for back edge
                        lowestReachable[row][col] = Math.min(lowestReachable[row][col], discoveryTime[newRow][newCol]);
                    }
                }
            }

            // Root of DFS tree is an articulation point if it has more than one child
            if (parentCell[row][col] == -1 && children > 1) {
                apInfo.hasArticulationPoint = true;
            }
        }

        // Check if the given cell is a valid land cell
        private boolean isValidLandCell(int[][] grid, int row, int col) {
            int rows = grid.length, cols = grid[0].length;
            return (row >= 0 && col >= 0 && row < rows && col < cols && grid[row][col] == 1);
        }

        private class ArticulationPointInfo {

            boolean hasArticulationPoint;
            int time;

            ArticulationPointInfo(boolean hasArticulationPoint, int time) {
                this.hasArticulationPoint = hasArticulationPoint;
                this.time = time;
            }
        }
    }
}
