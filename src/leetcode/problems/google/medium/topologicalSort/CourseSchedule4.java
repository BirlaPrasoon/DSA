package leetcode.problems.google.medium.topologicalSort;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule4 {
/**

    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.

    </br>For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.</br>
    Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.

    </br></br>You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.

 </br></br>
    Return a boolean array answer, where answer[j] is the answer to the jth query.

 </br>
 <b>Constraints:</b>
 </br>
<code>
    2 <= numCourses <= 100
    0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
    prerequisites[i].length == 2
    0 <= ai, bi <= numCourses - 1
    ai != bi
    All the pairs [ai, bi] are unique.
    The prerequisites graph has no cycles.
    1 <= queries.length <= 104
    0 <= ui, vi <= numCourses - 1
    ui != vi
    </code>
*/

    class Solution {
        public List<Boolean> checkIfPrerequisite(final int n, final int[][] prerequisites, final int[][] queries) {
            final boolean[][] dependencies = new boolean[n][n];

            for(final int[] prerequisite : prerequisites)
                dependencies[prerequisite[0]][prerequisite[1]] = true;

            for(int i = 0; i < n; ++i)
                for(int j = 0; j < n; ++j)
                    for(int k = 0; k < n; ++k)
                        if(!dependencies[j][k])
                            dependencies[j][k] = dependencies[j][i] && dependencies[i][k];

            final List<Boolean> result = new ArrayList<>();

            for(final int[] query : queries)
                result.add(dependencies[query[0]][query[1]]);

            return result;
        }
    }

}
