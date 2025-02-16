package leetcode.problems.google.hard.intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberOfFlowersInFullBloom {
    /**
     * <h1>Problem Statement</h1>
     * You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array people of size n, where people[i] is the time that the ith person will arrive to see the flowers.
     * <p>
     * Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.
     * <p>
     * <p>
     *     <h2>Examples</h2>
     * <h4>Example 1:</h4>
     * <p>
     * <p>
     * <b>Input:</b> flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]</br>
     * <b>Output:</b> [1,2,2,2]</br>
     * <b>Explanation:</b> The figure above shows the times when the flowers are in full bloom and when the people arrive.
     * For each person, we return the number of flowers in full bloom during their arrival.
     * <h4>Example 2:</h4>
     * <b>Input:</b> flowers = [[1,10],[3,3]], people = [3,3,2]</br>
     * <b>Output:</b> [2,2,1]</br>
     * <b>Explanation:</b> The figure above shows the times when the flowers are in full bloom and when the people arrive.
     * For each person, we return the number of flowers in full bloom during their arrival.
     * <p>
     * <h3>Constraints:</h3>
     * <p>
     * 1 <= flowers.length <= 5 * 10<sup>4</sup></br>
     * flowers[i].length == 2</br>
     * 1 <= start<sub>i</sub> <= end<sub>i</sub> <= 10<sup>9</sup></br>
     * 1 <= people.length <= 5 * 10<sup>4</sup></br>
     * 1 <= people[i] <= 10<sup>9</sup></br>
     */

    class Solution {
        public int[] fullBloomFlowers(int[][] flowers, int[] people) {
            List<Integer> starts = new ArrayList();
            List<Integer> ends = new ArrayList();

            for (int[] flower : flowers) {
                starts.add(flower[0]);
                // This is the trick to handle equality check...
                ends.add(flower[1] + 1);
            }

            Collections.sort(starts);
            Collections.sort(ends);
            int[] ans = new int[people.length];

            for (int index = 0; index < people.length; index++) {
                int person = people[index];
                int i = binarySearch(starts, person);
                int j = binarySearch(ends, person);
                ans[index] = i - j;
            }

            return ans;
        }

        public int binarySearch(List<Integer> arr, int target) {
            int left = 0;
            int right = arr.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (target < arr.get(mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }
}
