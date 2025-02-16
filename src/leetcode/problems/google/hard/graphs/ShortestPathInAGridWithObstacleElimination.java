package leetcode.problems.google.hard.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInAGridWithObstacleElimination {
    record State(int steps, int row, int col, int k){
        @Override
        public int hashCode() {
            return (this.row + 1) * (this.col + 1) * this.k;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof State)) {
                return false;
            }
            State newState = (State) other;
            return (this.row == newState.row) && (this.col == newState.col) && (this.k == newState.k);
        }
    }

    class Solution {
        private static int[][] DIRS = {{1,0}, {0,1}, {-1,0}, {0,-1}};
        public int shortestPath(int[][] grid, int k) {
            int rows = grid.length, cols = grid[0].length;
            int[] target = {rows - 1, cols - 1};

            // if we have sufficient quotas to eliminate the obstacles in the worst case,
            // then the shortest distance is the Manhattan distance.
            if (k >= rows + cols - 2) {
                return rows + cols - 2;
            }

            Queue<State> queue = new LinkedList<>();
            HashSet<State> visited = new HashSet<>();

            // (steps, row, col, remaining quota to eliminate obstacles)
            State start = new State(0, 0, 0, k);
            queue.add(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                State curr = queue.poll();

                // we reach the target here
                if (target[0] == curr.row && target[1] == curr.col) {
                    return curr.steps;
                }

                for(int[] dir : DIRS) {
                    int x = curr.row + dir[0];
                    int y = curr.col + dir[1];
                    if(insideBoundary(x, y, grid)) {
                        int nextElimination = curr.k - grid[x][y];
                        State newState = new State(curr.steps + 1, x, y, nextElimination);
                        if (nextElimination >= 0 && !visited.contains(newState)) {
                            visited.add(newState);
                            queue.add(newState);
                        }
                    }
                }
            }

            // did not reach the target
            return -1;
        }

        private boolean insideBoundary(int x, int y, int [][]grid){
            return x>=0 && y>=0 && x<grid.length && y<grid[0].length;

        }
    }
}
