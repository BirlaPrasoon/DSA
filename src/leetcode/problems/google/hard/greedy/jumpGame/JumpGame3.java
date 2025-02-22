package leetcode.problems.google.hard.greedy.jumpGame;

import java.util.LinkedList;
import java.util.Queue;

public class JumpGame3 {


    /*
    *
    * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.

Notice that you can not jump outside of the array at any time.



Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation:
All possible ways to reach at index 3 with value 0 are:
index 5 -> index 4 -> index 1 -> index 3
index 5 -> index 6 -> index 4 -> index 1 -> index 3
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true
Explanation:
One possible way to reach at index 3 with value 0 is:
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.

    * */

    class SolutionBFS {

        public boolean canReach(int[] arr, int start) {
            int n = arr.length;

            Queue<Integer> q = new LinkedList<>();
            q.add(start);

            while (!q.isEmpty()) {
                int node = q.poll();
                // check if reach zero
                if (arr[node] == 0) {
                    return true;
                }
                if (arr[node] < 0) {
                    continue;
                }

                // check available next steps
                if (node + arr[node] < n) {
                    q.offer(node + arr[node]);
                }
                if (node - arr[node] >= 0) {
                    q.offer(node - arr[node]);
                }
                // mark as visited
                arr[node] = -arr[node];
            }
            return false;
        }
    }

    class SolutionDFS {

        public boolean canReach(int[] arr, int start) {
            if (start >= 0 && start < arr.length && arr[start] >= 0) {
                if (arr[start] == 0) {
                    return true;
                }
                arr[start] = -arr[start];
                return (canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]));
            }
            return false;
        }
    }
}
