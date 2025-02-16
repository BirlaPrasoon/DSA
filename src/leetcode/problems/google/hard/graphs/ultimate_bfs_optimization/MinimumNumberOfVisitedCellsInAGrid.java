package leetcode.problems.google.hard.graphs.ultimate_bfs_optimization;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class MinimumNumberOfVisitedCellsInAGrid {
    /**
    *
    *
        You are given a 0-indexed m x n integer matrix grid. Your initial position is at the top-left cell (0, 0).<br><br>

        Starting from the cell (i, j), you can move to one of the following cells:<br><br>

        Cells (i, k) with j < k <= grid[i][j] + j (rightward movement), or<br>
        Cells (k, j) with i < k <= grid[i][j] + i (downward movement).<br>

        Return the minimum number of cells you need to visit to reach the bottom-right cell (m - 1, n - 1). If there is no valid path, return -1.<br>

    *
    * */

    static class Solution {
        record Pair(int x, int y) { }
        public int minimumVisitedCells(int[][] grid) {
            int n = grid.length, m = grid[0].length, level = 0;
            TreeSet<Integer> rows[] = new TreeSet[n], cols[] = new TreeSet[m];
            for(int i = 0; i < n; i++){
                rows[i] = new TreeSet<>();
                for(int j = 0; j < m; j++) rows[i].add(j);
            }
            for(int j = 0; j < m; j++){
                cols[j] = new TreeSet<>();
                for(int i = 0; i < n; i++) cols[j].add(i);
            }
            Queue<Pair> que = new LinkedList<>();
            que.add(new Pair(0, 0));
            while(!que.isEmpty()){
                for(int p = que.size()-1; p >= 0; p--){
                    Pair cur = que.poll();
                    int x = cur.x, y = cur.y;
                    if(x == n - 1 && y == m - 1) return level + 1;
                    int curVal = grid[x][y];

                    System.out.println("CELL:[" + x + "," + y+"]");
                    var rSet = rows[x].subSet(y + 1, Math.min(m, y + curVal + 1));
                    System.out.println("Marked visited: row: " + x + " columns: " + rSet);
                    var cSet = cols[y].subSet(x + 1, Math.min(n, x + curVal + 1));
                    System.out.println("Marked visited: column: " + x + " rows: " + rSet);

                    rSet.forEach(k -> que.add(new Pair(x, k)));
                    cSet.forEach(k -> que.add(new Pair(k, y)));
                    rSet.clear();
                    cSet.clear();
                }
                level ++;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{3,4,2,1},{4,2,3,1},{2,1,0,0},{2,4,0,0}};
        Solution solution = new Solution();
        System.out.println(solution.minimumVisitedCells(grid));
    }
}
