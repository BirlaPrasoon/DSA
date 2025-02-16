package leetcode.problems.google.medium.depth_first_search.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSetWithoutDuplicates {

    public static void main(String[] args) {
        System.out.println(new PowerSetWithoutDuplicates().subsetsWithDup(new int[]{1,1,3}));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> set = new ArrayList<>();
        Arrays.sort(nums);

        backtrack(nums, n, ans, set, 0);
        return ans;
    }

    private void backtrack(int[] nums, int n, List<List<Integer>> ans, List<Integer> set, int idx) {
        if (idx >= n) {
            ans.add(new ArrayList<>(set));
            return;
        }

        // all subsets that include nums[i]
        set.add(nums[idx]);
        backtrack(nums, n, ans, set, idx + 1);
        set.remove(set.size() - 1);

        // all subsets that don't include nums[i]
        while (idx + 1 < n && nums[idx] == nums[idx + 1]) {
            idx++;
        }
        backtrack(nums, n, ans, set, idx+1);
    }
}
