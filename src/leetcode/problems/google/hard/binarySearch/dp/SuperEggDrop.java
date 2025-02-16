package leetcode.problems.google.hard.binarySearch.dp;

import java.util.Arrays;

public class SuperEggDrop {
    public static int[][] dp;

    public int superEggDrop(int k, int n) {
        dp = new int[k + 1][n + 1];
        for (int[] i : dp) Arrays.fill(i, -1);
        return solve(k, n);
    }

    public static int solve(int e, int f) {
        if (f == 0 || f == 1 || e == 1) return f;
        if (dp[e][f] != -1) return dp[e][f];
        int ans = Integer.MAX_VALUE, l = 1, h = f;
        // use binary search insted of linear search
        while (l <= h) {
            int mid = (h + l) / 2;
            int left = solve(e - 1, mid - 1);
            int right = solve(e, f - mid);
            int temp = 1 + Math.max(left, right);
            if (left < right) l = mid + 1;
            else h = mid - 1;
            ans = Math.min(ans, temp);
        }
        return dp[e][f] = ans;
    }

    public int superEggDropBottomUp(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        // i represents the number of moves we've taken,
        // at max we may end up taking n moves, so i goes from 1 to n.
        for (int i = 1; i <= n; i++) {
            // j represents the number of eggs we have.
            for (int j = 1; j <= k; j++) {
                dp[j][i] = dp[j - 1][i - 1] + dp[j][i - 1] + 1;
                if (dp[j][i] >= n) return i; // if I reach a case where I can find floor that is greater than n, I've reached my n.
            }
        }
        return -1;
    }

    Integer[][] dp2;

    public int superEggDropTopDown(int k, int n) {
        dp2 = new Integer[k + 1][n + 1];
        return sol(k, n);
    }

    public int sol(int k, int n) {
        if (k == 1 || n == 0 || n == 1) return n;
        if (dp2[k][n] != null) return dp2[k][n];
        int l = 1, h = n;
        int ans = Integer.MAX_VALUE;
        while (l <= h) {
            int mid = (l + h) / 2;
            int k1 = sol(k - 1, mid - 1);
            int k2 = sol(k, n - mid);
            int temp = 1 + Math.max(k1, k2);
            if (k1 < k2) l = mid + 1;
            else h = mid - 1;
            ans = Math.min(temp, ans);
        }
        return dp2[k][n] = ans;
    }
}
