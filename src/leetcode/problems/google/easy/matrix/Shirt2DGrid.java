package leetcode.problems.google.easy.matrix;

import java.util.ArrayList;
import java.util.List;

public class Shirt2DGrid {

/*    Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.

    In one shift operation:

    Element at grid[i][j] moves to grid[i][j + 1].
    Element at grid[i][n - 1] moves to grid[i + 1][0].
    Element at grid[m - 1][n - 1] moves to grid[0][0].
    Return the 2D grid after applying shift operation k times.
    */

    class Solution {
        public List<List<Integer>> shiftGrid(int[][] grid, int k) {
            List<Integer> list = new ArrayList<>();
            for (int[] ints : grid) {
                for (int j = 0; j < grid[0].length; j++) {
                    list.add(ints[j]);
                }
            }
            List<List<Integer>> ans = new ArrayList<>();
            int n = grid.length;
            int m = grid[0].length;
            int total = n*m;
            k = k%total;
            int starting = (total - k)%(total);
            for(int i = 0; i<n; i++) {
                ans.add(new ArrayList<>());
                for(int j = 0; j<m; j++) {
                    ans.get(i).add(list.get((starting)%(total)));
                    starting++;
                }
            }
            return ans;
        }
    }

}
