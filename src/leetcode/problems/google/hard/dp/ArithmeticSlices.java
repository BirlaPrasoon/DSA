package leetcode.problems.google.hard.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArithmeticSlices {

    /**
     * Given an integer array nums, return the number of all the arithmetic subsequences of nums.<br></br>
     * <p>
     * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.<br><br>
     * <p>
     * For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.<br>
     * For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.<br>
     * A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.<br>
     * <p>
     * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].<br>
     * The test cases are generated so that the answer fits in 32-bit integer.<br>
     * <br>
     * Example 1:<br>
     * <p>
     * Input: nums = [2,4,6,8,10]<br>
     * Output: 7<br>
     * Explanation: All arithmetic subsequence slices are:<br>
     * [2,4,6]<br>
     * [4,6,8]<br>
     * [6,8,10]<br>
     * [2,4,6,8]<br>
     * [4,6,8,10]<br>
     * [2,4,6,8,10]<br>
     * [2,6,10]<br><br>
     * Example 2:<br>
     * <p>
     * Input: nums = [7,7,7,7,7]<br>
     * Output: 16<br>
     * Explanation: Any subsequence of this array is arithmetic.<br>
     * <p>
     * Constraints:<br>
     * <br>
     * 1  <= nums.length <= 1000<br>
     * -2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1<br>
     */


    class Solution {
        public int numberOfArithmeticSlices(int[] A) {
            int n = A.length;
            long ans = 0;
            // this map represents for index i, for common difference delta, how many APs end here.
            Map<Integer, Integer>[] cnt = new Map[n];
            for (int i = 0; i < n; i++) {
                cnt[i] = new HashMap<>(i);
                for (int j = 0; j < i; j++) {
                    long delta = (long) A[i] - (long) A[j];
                    // Edge cases...
                    if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                        continue;
                    }
                    int diff = (int) delta;
                    int countOfAPsEndingAtIndexJWithDiff = cnt[j].getOrDefault(diff, 0);
                    // How many are ending at current index.
                    // There can be duplicates that's why we need to keep into account we calculated for some older j in current ith iteration.
                    int origin = cnt[i].getOrDefault(diff, 0);
                    cnt[i].put(diff, origin + countOfAPsEndingAtIndexJWithDiff + 1);
                    ans += countOfAPsEndingAtIndexJWithDiff;
                }
            }
            return (int) ans;
        }
    }


    class SolutionRecursiveTLE {
        private int n;
        private int ans;

        private void dfs(int dep, int[] A, List<Long> cur) {
            if (dep == n) {
                if (cur.size() < 3) {
                    return;
                }
                long diff = cur.get(1) - cur.get(0);
                for (int i = 1; i < cur.size(); i++) {
                    if (cur.get(i) - cur.get(i - 1) != diff) {
                        return;
                    }
                }
                ans++;
                return;
            }
            dfs(dep + 1, A, cur);
            cur.add((long) A[dep]);
            dfs(dep + 1, A, cur);
            cur.remove((long) A[dep]);
        }

        public int numberOfArithmeticSlices(int[] A) {
            n = A.length;
            ans = 0;
            List<Long> cur = new ArrayList<Long>();
            dfs(0, A, cur);
            return (int) ans;
        }
    }
}
