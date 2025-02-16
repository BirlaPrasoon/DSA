package leetcode.problems.google.hard.intervals;

import java.util.*;

public class MinimumIntervalToIncludeEachQuery {
    /**
     * <h1>Problem Statement</h1>
     * You are given a 2D integer array intervals, where intervals[i] = [left<sub>i</sub>, right<sub>i</sub>] describes the ith interval starting at lefti and ending at righti (inclusive). The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.
     * <p>
     * You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such that left<sub>i</sub> <= queries[j] <= right<sub>i</sub>. If no such interval exists, the answer is -1.
     * <p>
     * <h2>ASK</h2>
     * Return an array containing the answers to the queries.
     * <br>
     * <h2>Examples</h2><br>
     * <b>Example 1:</b></br>
     * Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]</br>
     * Output: [3,3,1,4]</br>
     * Explanation: The queries are processed as follows:</br>
     * - Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.</br>
     * - Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.</br>
     * - Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.</br>
     * - Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.</br>
     * <p>
     * <b>Example 2:</b></br>
     * Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]</br>
     * Output: [2,-1,4,6]</br>
     * Explanation: The queries are processed as follows:</br>
     * - Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.</br>
     * - Query = 19: None of the intervals contain 19. The answer is -1.</br>
     * - Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.</br>
     * - Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 =6</br>
     * <p>
     * <p>
     * <h2>Constraints:</h2>
     * <p>
     * 1 <= intervals.length <= 10<sup>5</sup>
     * <p>
     * 1 <= queries.length <= 10<sup>5</sup>
     * <p>
     * intervals[i].length == 2
     * <p>
     * 1 <= left<sub>i</sub> <= right<sub>i</sub> <= 10<sup>7</sup>
     * <p>
     * 1 <= queries[j] <= 10<sup>7</sup>
     */


    class Solution {
        public int[] minInterval(int[][] inters, int[] quers) {
            int numQuery = quers.length;
            Interval[] intervals = getIntervals(inters, numQuery);
            Query[] queries = getQueries(quers);

            Arrays.sort(intervals, Comparator.comparingInt(i -> i.start));
            Arrays.sort(queries, Comparator.comparingInt(q -> q.query));

            //sort interval in increasing order of size
            PriorityQueue<Interval> minHeap = new PriorityQueue<>(Comparator.comparingInt(i -> (i.end - i.start)));

            int[] result = new int[numQuery];

            int j = 0;
            for (Query query : queries) {
                //add all the interval which start is less or equal than current query value
                while (j < intervals.length && intervals[j].start <= query.query) {
                    minHeap.add(intervals[j]);
                    j++;
                }

                //remove all the smallest size interval which end val is less than current query value
                while (!minHeap.isEmpty() && minHeap.peek().end < query.query) {
                    minHeap.remove();
                }
                //now if heap is empty it means there is no interval which satisfy query val
                result[query.index] = minHeap.isEmpty() ? -1 : (minHeap.peek().end - minHeap.peek().start + 1);
            }

            return result;
        }

        record Query(int query, int index) { }

        private Query[] getQueries(int[] quers) {
            Query[] queries = new Query[quers.length];
            for (int i = 0; i < quers.length; i++) {
                queries[i] = new Query(quers[i], i);
            }
            return queries;
        }

        record Interval(int start, int end) { }

        private Interval[] getIntervals(int[][] inters, int numQuery) {
            Interval[] intervals = new Interval[numQuery];
            for (int i = 0; i < inters.length; i++) {
                intervals[i] = new Interval(inters[i][0], inters[i][1]);
            }
            return intervals;
        }
    }

}
