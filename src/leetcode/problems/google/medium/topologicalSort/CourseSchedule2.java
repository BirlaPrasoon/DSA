package leetcode.problems.google.medium.topologicalSort;

import java.util.*;

public class CourseSchedule2 {

/*
    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
*/


    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            boolean isPossible = true;
            Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
            int[] indegree = new int[numCourses];
            int[] topologicalOrder = new int[numCourses];

            // Create the adjacency list representation of the graph
            for (int[] prerequisite : prerequisites) {
                int dest = prerequisite[0];
                int src = prerequisite[1];
                adjList.putIfAbsent(src, new ArrayList<Integer>());
                adjList.get(src).add(dest);

                // Record in-degree of each vertex
                indegree[dest] += 1;
            }

            // Add all vertices with 0 in-degree to the queue
            Queue<Integer> q = new LinkedList<Integer>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }

            int i = 0;
            // Process until the Q becomes empty
            while (!q.isEmpty()) {
                int node = q.remove();
                topologicalOrder[i++] = node;

                // Reduce the in-degree of each neighbor by 1
                if (adjList.containsKey(node)) {
                    for (Integer neighbor : adjList.get(node)) {
                        indegree[neighbor]--;

                        // If in-degree of a neighbor becomes 0, add it to the Q
                        if (indegree[neighbor] == 0) {
                            q.add(neighbor);
                        }
                    }
                }
            }

            // Check to see if topological sort is possible or not.
            if (i == numCourses) {
                return topologicalOrder;
            }

            return new int[0];
        }
    }
}
