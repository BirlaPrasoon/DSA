package leetcode.problems.google.medium.union_find;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfDifferentIslands {
    /*
    * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Return the number of distinct islands.
    * */

    /*
    This is interesting example.
    * Input: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
        Output: 3
    * */

    // O(M2⋅N2)
    class SolutionBruteForce {

        private List<List<int[]>> uniqueIslands = new ArrayList<>(); // All known unique islands.
        private List<int[]> currentIsland = new ArrayList<>(); // Current Island
        private int[][] grid; // Input grid
        private boolean[][] seen; // Cells that have been explored.

        public int numDistinctIslands(int[][] grid) {
            this.grid = grid;
            this.seen = new boolean[grid.length][grid[0].length];
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    dfs(row, col);
                    if (currentIsland.isEmpty()) {
                        continue;
                    }
                    // Translate the island we just found to the top left.
                    int minCol = grid[0].length - 1;
                    for (int i = 0; i < currentIsland.size(); i++) {
                        minCol = Math.min(minCol, currentIsland.get(i)[1]);
                    }
                    for (int[] cell : currentIsland) {
                        cell[0] -= row;
                        cell[1] -= minCol;
                    }
                    // If this island is unique, add it to the list.
                    if (currentIslandUnique()) {
                        uniqueIslands.add(currentIsland);
                    }
                    currentIsland = new ArrayList<>();
                }
            }
            return uniqueIslands.size();
        }

        private void dfs(int row, int col) {
            if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) return;
            if (seen[row][col] || grid[row][col] == 0) return;
            seen[row][col] = true;
            currentIsland.add(new int[]{row, col});
            dfs(row + 1, col);
            dfs(row - 1, col);
            dfs(row, col + 1);
            dfs(row, col - 1);
        }

        private boolean currentIslandUnique() {
            for (List<int[]> otherIsland : uniqueIslands) {
                if (currentIsland.size() != otherIsland.size()) {
                    continue;
                }
                if (equalIslands(currentIsland, otherIsland)) {
                    return false;
                }
            }
            return true;
        }

        private boolean equalIslands(List<int[]> island1, List<int[]> island2) {
            for (int i = 0; i < island1.size(); i++) {
                if (island1.get(i)[0] != island2.get(i)[0] || island1.get(i)[1] != island2.get(i)[1]) {
                    return false;
                }
            }
            return true;
        }
    }
    //  O(M⋅N) - Much better, just commented because of Java version issue.
    // Interesting read - Set of Set is also comparable.
/*    class SolutionHashByLocalCoordinates {

        private int[][] grid;
        private boolean[][] seen;
        private Set<Pair<Integer, Integer>> currentIsland;
        private int currRowOrigin;
        private int currColOrigin;

        private void dfs(int row, int col, Set<Pair<Integer, Integer>> currentIsland) {
            if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
                return;
            }
            if (grid[row][col] == 0 || seen[row][col]) {
                return;
            }
            seen[row][col] = true;
            currentIsland.add(new Pair<>(row - currRowOrigin, col - currColOrigin));
            dfs(row + 1, col);
            dfs(row - 1, col);
            dfs(row, col + 1);
            dfs(row, col - 1);
        }

        public int numDistinctIslands(int[][] grid) {
            this.grid = grid;
            this.seen = new boolean[grid.length][grid[0].length];
            Set<Set<Pair<Integer, Integer>>> islands = new HashSet<>();
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    this.currentIsland = new HashSet<>();
                    this.currRowOrigin = row;
                    this.currColOrigin = col;
                    dfs(row, col);
                    if (!currentIsland.isEmpty()) {
                        islands.add(currentIsland);
                    }
                }
            }
            return islands.size();
        }
    }
    */

    // Hash By Path Signature O(M⋅N)
    class Solution {
        private int[][] grid;
        private boolean[][] visited;
        private StringBuffer currentIsland;

        public int numDistinctIslands(int[][] grid) {
            this.grid = grid;
            this.visited = new boolean[grid.length][grid[0].length];
            Set<String> islands = new HashSet<>();
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    currentIsland = new StringBuffer();
                    dfs(row, col, '0');
                    if (currentIsland.isEmpty()) {
                        continue;
                    }
                    islands.add(currentIsland.toString());
                }
            }
            return islands.size();
        }

        private void dfs(int row, int col, char dir) {
            if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
                return;
            }
            if (visited[row][col] || grid[row][col] == 0) {
                return;
            }
            visited[row][col] = true;
            currentIsland.append(dir);
            dfs(row + 1, col, 'D');
            dfs(row - 1, col, 'U');
            dfs(row, col + 1, 'R');
            dfs(row, col - 1, 'L');
            currentIsland.append('0');
        }
    }
}
