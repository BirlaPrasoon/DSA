package leetcode.problems.google.medium.topologicalSort;

import java.util.*;

public class SequenceReconstruction {
    /**
     * You are given an integer array nums of length n where nums is a permutation of the integers in the range [1, n]. <br>You are also given a 2D integer array sequences where sequences[i] is a subsequence of nums.<br>
     * <p>
     * Check if nums is the shortest possible and the only supersequence.<br>
     * The shortest supersequence is a sequence with the shortest length and has all sequences[i] as subsequences. <br>
     * There could be multiple valid supersequences for the given array sequences.<br>
     * <p>
     * For example, for sequences = [[1,2],[1,3]], there are two shortest supersequences, [1,2,3] and [1,3,2].<br>
     * While for sequences = [[1,2],[1,3],[1,2,3]], the only shortest supersequence possible is [1,2,3]. [1,2,3,4] is a possible supersequence but not the shortest.<br>
     * Return true if nums is the only shortest supersequence for sequences, or false otherwise.
     * <p>
     * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,2,3], sequences = [[1,2],[1,3]]
     * Output: false
     * Explanation: There are two possible supersequences: [1,2,3] and [1,3,2].
     * The sequence [1,2] is a subsequence of both: [1,2,3] and [1,3,2].
     * The sequence [1,3] is a subsequence of both: [1,2,3] and [1,3,2].
     * Since nums is not the only shortest supersequence, we return false.
     * Example 2:
     * <p>
     * Input: nums = [1,2,3], sequences = [[1,2]]
     * Output: false
     * Explanation: The shortest possible supersequence is [1,2].
     * The sequence [1,2] is a subsequence of it: [1,2].
     * Since nums is not the shortest supersequence, we return false.
     * Example 3:
     * <p>
     * Input: nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
     * Output: true
     * Explanation: The shortest possible supersequence is [1,2,3].
     * The sequence [1,2] is a subsequence of it: [1,2,3].
     * The sequence [1,3] is a subsequence of it: [1,2,3].
     * The sequence [2,3] is a subsequence of it: [1,2,3].
     * Since nums is the only shortest supersequence, we return true.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * n == nums.length
     * 1 <= n <= 104
     * nums is a permutation of all the integers in the range [1, n].
     * 1 <= sequences.length <= 10^4
     * 1 <= sequences[i].length <= 10^4
     * 1 <= sum(sequences[i].length) <= 10^5
     * 1 <= sequences[i][j] <= n
     * All the arrays of sequences are unique.
     * sequences[i] is a subsequence of nums.
     */

    class Solution {
        public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
            int n = nums.length;

            int[] inDegree = new int[n + 1];
            List<List<Integer>> graph = new ArrayList<>();
            for(int listSize = 0; listSize <= n; listSize++) {
                graph.add(new ArrayList<>());
            }

            for(List<Integer> seq : sequences) {
                for(int index = 1; index < seq.size(); index++) {
                    int nodeA = seq.get(index - 1);
                    int nodeB = seq.get(index);

                    if(nodeA < 1 || nodeA > n || nodeB < 1 || nodeB > n) return false;

                    graph.get(nodeA).add(nodeB);
                    inDegree[nodeB]++;
                }
            }

            Queue<Integer> topSort = new LinkedList<>();
            for(int node = 1; node <= n; node++) {
                if(inDegree[node] == 0) {
                    topSort.add(node);
                }
            }

            for (int num : nums) {
                // There can be only one possible sequence if there is only one element to be processed from the queue,
                // as all elements in the queue haave exactly same processing probability based on how the indegree was
                // calculated or how the graph was constructed.
                if (topSort.size() > 1 || num != topSort.peek()) return false;

                int curr = topSort.poll();
                for (int next : graph.get(curr)) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) topSort.offer(next);
                }
            }

            return true;
        }
    }

}
