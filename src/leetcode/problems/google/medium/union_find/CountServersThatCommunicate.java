package leetcode.problems.google.medium.union_find;

public class CountServersThatCommunicate {
    /*
     *
     * You are given a map of a server center, represented as a m * n integer matrix grid,
     * where 1 means that on that cell there is a server and 0 means that it is no server.
     *
     * Two servers are said to communicate if they are on the same row or on the same column.
     *
     * Return the number of servers that communicate with any other server.


     *
     * */

    class Solution {
        public int countServers(int[][] grid) {
            int[] row = new int[grid.length];
            int[] col = new int[grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    row[i] += grid[i][j];
                    col[j] += grid[i][j];
                }
            }
            int ans = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1 && (row[i] > 1 || col[j] > 1)) {
                        ans++;
                    }
                }
            }
            return ans;
        }
    }
}
