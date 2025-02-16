package leetcode.problems.google.medium.breadth_first_search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TheMaze2 {

/*    There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

    Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return the shortest distance for the ball to stop at the destination. If the ball cannot stop at destination, return -1.

    The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).

    You may assume that the borders of the maze are all walls (see examples).*/

    public class Solution {
        public int shortestDistance(int[][] maze, int[] start, int[] dest) {
            int[][] distance = new int[maze.length][maze[0].length];
            for (int[] row: distance)
                Arrays.fill(row, Integer.MAX_VALUE);
            distance[start[0]][start[1]] = 0;
            int[][] dirs={{0, 1} ,{0, -1}, {-1, 0}, {1, 0}};
            Queue< int[] > queue = new LinkedList< >();
            queue.add(start);
            while (!queue.isEmpty()) {
                int[] s = queue.remove();
                for (int[] dir: dirs) {
                    int x = s[0] + dir[0];
                    int y = s[1] + dir[1];
                    int count = 0;
                    while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                        x += dir[0];
                        y += dir[1];
                        count++;
                    }
                    if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                        distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                        queue.add(new int[] {x - dir[0], y - dir[1]});
                    }
                }
            }
            return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
        }
    }
}
