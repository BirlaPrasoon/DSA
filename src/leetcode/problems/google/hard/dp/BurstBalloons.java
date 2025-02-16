package leetcode.problems.google.hard.dp;

public class BurstBalloons {

    public static void main(String[] args) {
        BurstBalloons b = new BurstBalloons();
        int[] nums = new int[]{1,2,3,4};
        System.out.println(b.maxCoins(nums));
    }

    int[][] dp;
    public int maxCoins(int[] nums) {
        int n = nums.length;
        dp = new int[n+2][n+2];
        int[] newNums = new int[n+2];
        newNums[0] = 1;
        newNums[n+1] = 1;
        for(int i = 1; i<=n; i++) {
            newNums[i] = nums[i-1];
        }
        return maxCoins(newNums, 1, n);
    }

    public int maxCoins(int[] nums, int left, int right) {
        if(dp[left][right] > 0) return dp[left][right];
        if(left == right) {
            dp[left][right] = nums[left-1] * nums[left] * nums[left+1];
            return dp[left][right];
        }
        int max = 0;
        for(int i = left; i <= right; i++){
            int leftMax = maxCoins(nums, left, i-1);
            int rightMax = maxCoins(nums, i+1, right);
            int current = nums[left-1] * nums[i] * nums[right+1] + leftMax + rightMax;
            max = Math.max(max, current);
        }
        dp[left][right] = max;
        return max;
    }
}
