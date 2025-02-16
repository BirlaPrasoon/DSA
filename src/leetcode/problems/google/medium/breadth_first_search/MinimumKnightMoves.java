package leetcode.problems.google.medium.breadth_first_search;

import java.util.*;

public class MinimumKnightMoves {

/*
    In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

    A knight has 8 possible moves it can make, as illustrated below.
    Each move is two squares in a cardinal direction, then one square in an orthogonal direction.


    Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.



    Example 1:

    Input: x = 2, y = 1
    Output: 1
    Explanation: [0, 0] → [2, 1]
    Example 2:

    Input: x = 5, y = 5
    Output: 4
    Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]


    Constraints:

            -300 <= x, y <= 300
            0 <= |x| + |y| <= 300

    */

    class SolutionBFS {
        public int minKnightMoves(int x, int y) {
            // the offsets in the eight directions
            int[][] offsets = {{1, 2}, {2, 1}, {2, -1}, {1, -2},
                    {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

            // - Rather than using the inefficient HashSet, we use the bitmap
            //     otherwise we would run out of time for the test cases.
            // - We create a bitmap that is sufficient to cover all the possible
            //     inputs, according to the description of the problem.
            boolean[][] visited = new boolean[607][607];

            Deque<int[]> queue = new LinkedList<>();
            queue.addLast(new int[]{0, 0});
            int steps = 0;

            while (!queue.isEmpty()) {
                int currLevelSize = queue.size();
                // iterate through the current level
                for (int i = 0; i < currLevelSize; i++) {
                    int[] curr = queue.removeFirst();
                    if (curr[0] == x && curr[1] == y) {
                        return steps;
                    }

                    for (int[] offset : offsets) {
                        int[] next = new int[]{curr[0] + offset[0], curr[1] + offset[1]};
                        // align the coordinate to the bitmap
                        if (!visited[next[0] + 302][next[1] + 302]) {
                            visited[next[0] + 302][next[1] + 302] = true;
                            queue.addLast(next);
                        }
                    }
                }
                steps++;
            }
            // move on to the next level
            return steps;
        }
    }

    class Solution {
        private Map<String, Integer> memo = new HashMap<>();

        private int dfs(int x, int y) {
            String key = x + "," + y;
            if (memo.containsKey(key)) {
                return memo.get(key);
            }

            if (x + y == 0) {
                return 0;
            } else if (x + y == 2) {
                return 2;
            } else {
                Integer ret = Math.min(dfs(Math.abs(x - 1), Math.abs(y - 2)),
                        dfs(Math.abs(x - 2), Math.abs(y - 1))) + 1;
                memo.put(key, ret);
                return ret;
            }
        }

        public int minKnightMoves(int x, int y) {
            return dfs(Math.abs(x), Math.abs(y));
        }
    }
}
