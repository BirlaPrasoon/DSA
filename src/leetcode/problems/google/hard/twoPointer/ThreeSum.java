package leetcode.problems.google.hard.twoPointer;

import java.util.*;

public class ThreeSum {
    static class Solution {

        //// Solution 1 without sorting
        public List<List<Integer>> threeSum(int[] nums) {
            Set<List<Integer>> res = new HashSet<>();
            Set<Integer> dups = new HashSet<>();
            Map<Integer, Integer> seen = new HashMap<>();
            for (int i = 0; i < nums.length; ++i) {
                if (dups.add(nums[i])) {
                    for (int j = i + 1; j < nums.length; ++j) {
                        int complement = -nums[i] - nums[j];
                        // Very nice trick...
                        if (seen.containsKey(complement) && seen.get(complement) == i) {
                            List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                            Collections.sort(triplet);
                            res.add(triplet);
                        }
                        seen.put(nums[j], i);
                    }
                }
            }
            return new ArrayList<>(res);
        }
    }

    //// solution 2 with sorting

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) { // don't wanna use the same value at the first and second index.
                twoSum(nums, i, res);
            }
        return res;
    }

    void twoSum(int[] nums, int i, List<List<Integer>> res) {
        var seen = new HashSet<Integer>();
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (j + 1 < nums.length && nums[j] == nums[j + 1]) ++j;
            }
            seen.add(nums[j]);
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution().threeSum(new int[]{0, 0, 0, 2, -1, -4}));
    }
}
