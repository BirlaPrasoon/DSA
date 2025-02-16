package leetcode.problems.google.hard.binarySearch.dp.lisOrPatienceSorting;

import java.util.Arrays;

public class RussianDolls {
    class Solution {

        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int len = 0;
            for (int num : nums) {
                int i = Arrays.binarySearch(dp, 0, len, num);
                if (i < 0) {
                    i = -(i + 1);
                }
                dp[i] = num;
                if (i == len) {
                    len++;
                }
            }
            return len;
        }

        public int maxEnvelopes(int[][] envelopes) {
            // sort on increasing in first dimension and decreasing in second
            Arrays.sort(envelopes, (row1, row2) -> {
                if (row1[0] == row2[0]) {
                    return row2[1] - row1[1];
                }
                return row1[0] - row2[0];
            });
            // extract the second dimension and run LIS
            int[] secondDim = new int[envelopes.length];
            for (int i = 0; i < envelopes.length; ++i) secondDim[i] = envelopes[i][1];
            return lengthOfLIS(secondDim);
        }
    }

}
