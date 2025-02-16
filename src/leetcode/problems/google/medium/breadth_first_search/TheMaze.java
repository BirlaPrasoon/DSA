package leetcode.problems.google.medium.breadth_first_search;

import java.util.LinkedList;
import java.util.Queue;

public class TheMaze {

    /*
    * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.

You may assume that the borders of the maze are all walls (see examples).
    * */

    class Solution {
        public boolean hasPath(int[][] maze, int[] start, int[] destination) {
            int m = maze.length;
            int n = maze[0].length;
            boolean[][] visit = new boolean[m][n];
            int[] dirX = {0, 1, 0, -1};
            int[] dirY = {-1, 0, 1, 0};

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(start);
            visit[start[0]][start[1]] = true;

            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                if (curr[0] == destination[0] && curr[1] == destination[1]) {
                    return true;
                }
                for (int i = 0; i < 4; i++) {
                    int r = curr[0];
                    int c = curr[1];
                    // Move the ball in the chosen direction until it can.
                    while (r >= 0 && r < m && c >= 0 && c < n && maze[r][c] == 0) {
                        r += dirX[i];
                        c += dirY[i];
                    }
                    // Revert the last move to get the cell to which the ball rolls.
                    r -= dirX[i];
                    c -= dirY[i];
                    if (!visit[r][c]) {
                        queue.offer(new int[]{r, c});
                        visit[r][c] = true;
                    }
                }
            }
            return false;
        }
    }
}
