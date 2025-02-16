package leetcode.problems.google.hard.topoSort;

import java.util.*;

public class ParallelCourses3 {

    /**
     * You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given a 2D integer array relations where relations[j] = [prevCoursej, nextCoursej] denotes that course prevCoursej has to be completed before course nextCoursej (prerequisite relationship). Furthermore, you are given a 0-indexed integer array time where time[i] denotes how many months it takes to complete the (i+1)th course.
     * <p>
     * You must find the minimum number of months needed to complete all the courses following these rules:
     * <p>
     * You may start taking a course at any time if the prerequisites are met.
     * Any number of courses can be taken at the same time.
     * Return the minimum number of months needed to complete all the courses.
     * <p>
     * Note: The test cases are generated such that it is possible to complete every course (i.e., the graph is a directed acyclic graph).
     * <p>
     * Example 1:
     * Input: n = 3, relations = [[1,3],[2,3]], time = [3,2,5]
     * Output: 8
     * Explanation: The figure above represents the given graph and the time required to complete each course.
     * We start course 1 and course 2 simultaneously at month 0.
     * Course 1 takes 3 months and course 2 takes 2 months to complete respectively.
     * Thus, the earliest time we can start course 3 is at month 3, and the total time required is 3 + 5 = 8 months.
     * Example 2:
     * <p>
     * <p>
     * Input: n = 5, relations = [[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
     * Output: 12
     * Explanation: The figure above represents the given graph and the time required to complete each course.
     * You can start courses 1, 2, and 3 at month 0.
     * You can complete them after 1, 2, and 3 months respectively.
     * Course 4 can be taken only after course 3 is completed, i.e., after 3 months. It is completed after 3 + 4 = 7 months.
     * Course 5 can be taken only after courses 1, 2, 3, and 4 have been completed, i.e., after max(1,2,3,7) = 7 months.
     * Thus, the minimum time needed to complete all the courses is 7 + 5 = 12 months.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= n <= 5 * 104
     * 0 <= relations.length <= min(n * (n - 1) / 2, 5 * 104)
     * relations[j].length == 2
     * 1 <= prevCoursej, nextCoursej <= n
     * prevCoursej != nextCoursej
     * All the pairs [prevCoursej, nextCoursej] are unique.
     * time.length == n
     * 1 <= time[i] <= 104
     * The given graph is a directed acyclic graph.
     */

    class Solution {

        /**
         * <b>Algorithm:</b>
         * <ol>
         * <li>Initialize the following data structures:</li>
         * <ul>
         * <li>A graph from relations. For convenience, we will change the nodes to be 0-indexed.</li>
         * <li>An array indegree of length n, representing the indegree of each node.</li>
         * <li>A queue to perform Kahn's algorithm.</li>
         * <li>An array maxTime of length n, representing the maximum value of all paths ending at certain nodes.</li>
         * </ul>
         *
         * <li>For all nodes with indegree[node] = 0, push them to the queue and initialize maxTime[node] = time[node].</li>
         * <li>While queue is not empty:</li>
         * <ul>
         * <li>Pop a node.</li>
         * <li>Iterate over graph[node]. For each neighbor:</li>
         * <ul>
         * <li>Update maxTime[neighbor] with maxTime[node] + time[neighbor] if it is larger.</li>
         * <li>Decrement indegree[neighbor].</li>
         * <li>If indegree[neighbor] == 0, push neighbor to queue.</li>
         * </ul>
         * </ul>
         * <li>Return max(maxTime).</li>
         * </ol>
         */

        public int minimumTime(int n, int[][] relations, int[] time) {
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int i = 0; i < n; i++) {
                graph.put(i, new ArrayList<>());
            }

            int[] indegree = new int[n];
            for (int[] edge : relations) {
                int x = edge[0] - 1;
                int y = edge[1] - 1;
                graph.get(x).add(y);
                indegree[y]++;
            }

            Queue<Integer> queue = new LinkedList<>();
            int[] maxTime = new int[n];

            for (int node = 0; node < n; node++) {
                if (indegree[node] == 0) {
                    queue.add(node);
                    maxTime[node] = time[node];
                }
            }

            while (!queue.isEmpty()) {
                int node = queue.remove();
                for (int neighbor : graph.get(node)) {
                    maxTime[neighbor] = Math.max(maxTime[neighbor], maxTime[node] + time[neighbor]);
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }

            int ans = 0;
            for (int node = 0; node < n; node++) {
                ans = Math.max(ans, maxTime[node]);
            }

            return ans;
        }

    }

}
