package leetcode.problems.google.medium.topologicalSort;

import java.util.*;

public class LoudAndRich {

    /**
    There is a group of n people labeled from 0 to n - 1 where each person has a different amount of money and a different level of quietness.<br><br>

    You are given an array richer where richer[i] = [ai, bi] indicates that ai has more money than bi and an integer array quiet where quiet[i] is the quietness of the ith person. All the given data in richer are logically correct (i.e., the data will not lead you to a situation where x is richer than y and y is richer than x at the same time).<br></br><br></br>

    Return an integer array answer where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]) among all people who definitely have equal to or more money than the person x.</br></br>

    Example 1:<br>
    Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]</br>
    Output: [5,5,2,5,4,5,6,7]</br></br>
    Explanation:</br>
    answer[0] = 5.</br>
    Person 5 has more money than 3, which has more money than 1, which has more money than 0.</br>
    The only person who is quieter (has lower quiet[x]) is person 7, but it is not clear if they have more money than person 0.</br>
    answer[7] = 7.</br>
    Among all people that definitely have equal to or more money than person 7 (which could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has lower quiet[x]) is person 7.</br>
    The other answers can be filled out with similar reasoning.</br>
 </br>
    Example 2:</br>
    Input: richer = [], quiet = [0]</br>
    Output: [0]</br>
 </br>
    Constraints:</br>
    <code>n == quiet.length</code></br>
    <code>1 <= n <= 500</code></br>
    <code>0 <= quiet[i] < n</code></br>
    All the values of quiet are unique.</br>
     <code>0 <= richer.length <= n * (n - 1) / 2<code></br>
    <code>0 <= ai, bi < n</code></br>
    <code>ai != bi</code></br>
    All the pairs of richer are unique.</br>
    The observations in richer are all logically consistent.</br>
    */

    // Look in notes, its an easy one,
        // MINi = Min(Qi, MIN(allNeighbors of I))


        /**
        *
        * Time Complexity: O(N<sup>2</sup>), where N is the number of people.
        We are iterating here over array richer. It could contain up to
        <code>1+...+N−1=N(N−1)/2 </code>elements, for example, in the situation
        when each new person is richer than the previous one.

        Space Complexity: O(N<sup>2</sup>), to keep the graph with N<sup>2</sup> edges.
        * */
    class Solution {
        ArrayList<Integer>[] graph;
        int[] answer;
        int[] quiet;

        public int[] loudAndRich(int[][] richer, int[] quiet) {
            int N = quiet.length;
            graph = new ArrayList[N];
            answer = new int[N];
            this.quiet = quiet;

            for (int node = 0; node < N; ++node)
                graph[node] = new ArrayList<Integer>();

            for (int[] edge: richer)
                graph[edge[1]].add(edge[0]);

            Arrays.fill(answer, -1);

            for (int node = 0; node < N; ++node)
                dfs(node);
            return answer;
        }

        // DFS with DP.
        public int dfs(int node) {
            if (answer[node] == -1) {
                answer[node] = node; // Qi is the quiteness we assume to be minimum at first.
                for (int child: graph[node]) {
                    int cand = dfs(child);
                    if (quiet[cand] < quiet[answer[node]])
                        answer[node] = cand;
                }
            }
            return answer[node];
        }
    }
}
