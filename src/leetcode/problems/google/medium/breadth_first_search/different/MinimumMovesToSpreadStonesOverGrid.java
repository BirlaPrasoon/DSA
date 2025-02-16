package leetcode.problems.google.medium.breadth_first_search.different;
import java.util.ArrayList;
import java.util.List;

public class MinimumMovesToSpreadStonesOverGrid {

/*
    You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing the number of stones in each cell.
    The grid contains exactly 9 stones, and there can be multiple stones in a single cell.

    In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.

    Return the minimum number of moves required to place one stone in each cell.
    */


    class Solution {
        private int minimumMoves(int[][] grid) {
            if (grid[0][0] == 9 || grid[0][2] == 9 || grid[2][0] == 9 || grid[2][2] == 9)
                return 18;
            if (grid[0][1] == 9 || grid[1][0] == 9 || grid[1][2] == 9 || grid[2][1] == 9)
                return 15;
            if (grid[1][1] == 9)
                return 12;

            List<int[]> z = new ArrayList<>();
            List<int[]> o = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == 0) {
                        z.add(new int[]{i, j});
                    }
                    while (grid[i][j] > 1) {
                        grid[i][j]--;
                        o.add(new int[]{i, j});
                    }
                }
            }

            return f(z, o, 0);
        }

        private int f(List<int[]> z, List<int[]> o, int ind) {
            int n = z.size();
            if (n==0) {
                return 0;
            }

            int ans = Integer.MAX_VALUE;
            for (int j = 0; j < o.size(); j++) {
                int[] curZ = z.get(ind);
                int[] curO = o.get(j);

                int cur = Math.abs(curZ[0] - curO[0]) + Math.abs(curZ[1] - curO[1]);
                o.set(j, new int[]{-1, -1});

                ans = Math.min(ans, cur + f(z, o, ind + 1));
                o.set(j, curO);
            }

            return ans;
        }
    }
}
