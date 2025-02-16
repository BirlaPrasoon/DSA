package leetcode.problems.google.medium.topologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ParallelCourses {


/*
    You are given an integer n, which indicates that there are n courses labeled from 1 to n.
    You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.

    In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.

    Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.



    Example 1:


    Input: n = 3, relations = [[1,3],[2,3]]
    Output: 2
    Explanation: The figure above represents the given graph.
    In the first semester, you can take courses 1 and 2.
    In the second semester, you can take course 3.
    Example 2:


    Input: n = 3, relations = [[1,2],[2,3],[3,1]]
    Output: -1
    Explanation: No course can be studied because they are prerequisites of each other.


    Constraints:

            1 <= n <= 5000
            1 <= relations.length <= 5000
    relations[i].length == 2
            1 <= prevCoursei, nextCoursei <= n
    prevCoursei != nextCoursei
    All the pairs [prevCoursei, nextCoursei] are unique.
    */

    class Solution {
        public int minimumSemesters(int numCourses, int[][] prerequisites) {
            List<Integer> graph[] = new ArrayList[numCourses];
            for(int i= 0; i<numCourses; i++) {
                graph[i] = new ArrayList<>();
            }
            int []indegrees = new int[numCourses];
            int []topoOrder = new int[numCourses];
            for(int []edge: prerequisites) {
                int dest = edge[0]-1;
                int src = edge[1]-1;
                graph[src].add(dest);
                indegrees[dest]++;
            }
            Queue<Integer> q = new LinkedList<>();
            for(int i=0; i<numCourses; i++) {
                if(indegrees[i] == 0) q.add(i);
            }
            boolean visited[] = new boolean[numCourses];
            int index = 0;
            int count = 0;
            while(!q.isEmpty()) {
                int size = q.size();
                for(int i= 0; i<size; i++){
                    int node = q.poll();
                    if(visited[node]) continue;
                    topoOrder[index++] = node;
                    visited[node] = true;
                    for(int nei: graph[node]) {
                        indegrees[nei]--;
                        if(indegrees[nei] == 0) {
                            q.add(nei);
                        }
                    }
                }
                count++;
            }
            if(index == numCourses) return count;
            return -1;
        }
    }
}
