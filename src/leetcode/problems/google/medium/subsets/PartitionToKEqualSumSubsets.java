package leetcode.problems.google.medium.subsets;

public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % k != 0) {
            return false;
        }

        int subsetSum = total / k;
        boolean[] visited = new boolean[nums.length];
        return canPartition(nums, visited, 0, k, 0, subsetSum);
    }

    private boolean canPartition(int[] nums, boolean[] visited, int start, int k, int curSum, int subsetSum) {
        if (k == 0) {
            return true;
        }
        if (curSum == subsetSum) {
            return canPartition(nums, visited, 0, k - 1, 0, subsetSum);
        }
        for (int i = start; i < nums.length; i++) {
            if (visited[i] || curSum + nums[i] > subsetSum) {
                continue;
            }
            visited[i] = true;
            if (canPartition(nums, visited, i + 1, k, curSum + nums[i], subsetSum)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }
}
