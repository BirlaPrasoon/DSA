package leetcode.problems.google.medium.dynamicprogramming;

public class CheckIfThereIsAPathWithEqualNumberOfZerosAndOnes {

    class Solution {
        Boolean[][][] memo;
        int n, m, k;
        public boolean isThereAPath(int[][] grid) {
            n = grid.length;
            m = grid[0].length;
            if ((n+m) % 2 == 0) return false;
            k = (n+m) / 2; // Target sum
            // Memo up to k, no need for after k since we know it'll be False
            memo = new Boolean[n][m][k+1];
            return isThereAPath(grid, 0, 0, 0);
        }

        public boolean isThereAPath(int[][] grid, int x, int y, int sum) {
            if (x >= n || y >= m) return false;
            sum += grid[x][y];
            // Sum shouldn't be more than k.
            // Also, n+m is max possible sum and x+y is what we've seen so far. Their difference
            // is the remaining cells, which must be at least k-sum (otherwise we won't get to k == sum)
            if (sum > k || n+m-x-y < k-sum) return false; // Optimization/Optional
            if (x == n-1 && y == m-1) return memo[x][y][sum] = sum == k;
            if (memo[x][y][sum] != null) return memo[x][y][sum];
            return memo[x][y][sum] = isThereAPath(grid, x+1, y, sum) || isThereAPath(grid, x, y+1, sum);
        }
    }
}
