package learning.ds_and_algo;

import java.util.Arrays;

public class DigitDp {
    private int[][] dp;
    private String num;

    public int countDigitOne(int n) {
        num = String.valueOf(n);
        int len = num.length();
        dp = new int[len][10];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, true);
    }

    private int dfs(int pos, int count, boolean tight) {

        if (pos == num.length()) {
            return count;
        }

        if (!tight && dp[pos][count] != -1) {
            return dp[pos][count];
        }

        int limit = tight ? num.charAt(pos) - '0' : 9;
        int result = 0;

        for (int digit = 0; digit <= limit; digit++) {
            boolean newTight = tight && (digit == limit);
            int newCount = count + (digit == 1 ? 1 : 0);
            result += dfs(pos + 1, newCount, newTight);
        }

        if (!tight) {
            dp[pos][count] = result;
        }

        return result;
    }

    public static void main(String[] args) {
        DigitDp digitDp = new DigitDp();
        System.out.println(digitDp.countDigitOne(13));
    }
}
