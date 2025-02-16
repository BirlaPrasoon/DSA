package leetcode.problems.google.hard.backtracking;

import leetcode.Pair;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner {


    /*
    * Complexity Analysis

Time complexity : O(N−M), where N is a number of cells in the room and M is a number of obstacles.

We visit each non-obstacle cell once and only once.
At each visit, we will check 4 directions around the cell. Therefore, the total number of operations would be 4⋅(N−M).
Space complexity : O(N−M), where N is a number of cells in the room and M is a number of obstacles.

We employed a hashtable to keep track of whether a non-obstacle cell is visited or not.
    *
    * */

    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        public boolean move();

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        public void turnLeft();

        public void turnRight();

        // Clean the current cell.
        public void clean();
    }

    class Solution {
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
        int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        Robot robot;

        public void goOneStepBackAndTurnToSameDirection() {
            robot.turnRight();
            robot.turnRight();
            robot.move();
            robot.turnRight();
            robot.turnRight();
        }

        public void backtrack(int row, int col, int current_direction) {
            visited.add(new Pair<>(row, col));
            robot.clean();
            // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
            for (int i = 0; i < 4; ++i) {
                int newD = (current_direction + i) % 4;
                int newRow = row + DIRECTIONS[newD][0];
                int newCol = col + DIRECTIONS[newD][1];

                if (!visited.contains(new Pair<>(newRow, newCol)) && robot.move()) {
                    backtrack(newRow, newCol, newD);
                    goOneStepBackAndTurnToSameDirection();
                }
                // turn the robot following chosen direction : clockwise
                robot.turnRight();
            }
        }

        public void cleanRoom(Robot robot) {
            this.robot = robot;
            backtrack(0, 0, 0);
        }
    }


}
