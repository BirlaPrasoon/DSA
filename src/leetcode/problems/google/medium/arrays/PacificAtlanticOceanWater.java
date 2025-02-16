package leetcode.problems.google.medium.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticOceanWater {
    public static void main(String[] args) {
        PacificAtlanticOceanWater p = new PacificAtlanticOceanWater();
        System.out.println(p.pacificAtlantic(new int[][]
                {{1, 2}}));
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        var rows = heights.length;
        var cols = heights[0].length;
        var pac = new boolean[rows][cols];
        var atl = new boolean[rows][cols];
        // Pacific Ocean
        // do a DFS for every column from row 0 to check which cells are reachable from Pacific ocean
        for (var col = 0; col < cols; col++) {
            dfs(0, col, rows, cols, pac, heights[0][col], heights);
        }
        // do a DFS for every row from column 0 to check which cells are reachable from Pacific ocean
        for (var row = 0; row < rows; row++) {
            dfs(row, 0, rows, cols, pac, heights[row][0], heights);
        }
        // Atlantic Ocean
        // do a dfs for every column from last row to check which cells are reachable from Atlantic Ocean
        for (var col = 0; col < cols; col++) {
            dfs(rows - 1, col, rows, cols, atl, heights[rows - 1][col], heights);
        }
        // do a dfs for every row from last column to check which cells are reachable from Atlantic Ocean
        for (int row = 0; row < rows; row++) {
            dfs(row, cols - 1, rows, cols, atl, heights[row][cols - 1], heights);
        }
        var result = new ArrayList<List<Integer>>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (pac[i][j] && atl[i][j])
                    result.add(Arrays.asList(i, j));
            }
        return result;
    }

    private void dfs(int row, int col, int rows, int cols, boolean[][] visited, int prevHeight, int[][] heights) {
        if (isOutOfBounds(row, col, rows, cols)) return;
        if (visited[row][col] || prevHeight > heights[row][col])
            return;
        visited[row][col] = true;
        dfs(row + 1, col, rows, cols, visited, heights[row][col], heights);
        dfs(row - 1, col, rows, cols, visited, heights[row][col], heights);
        dfs(row, col + 1, rows, cols, visited, heights[row][col], heights);
        dfs(row, col - 1, rows, cols, visited, heights[row][col], heights);
    }

    private static boolean isOutOfBounds(int row, int col, int rows, int cols) {
        return row < 0 || row >= rows || col < 0 || col >= cols;
    }
}
