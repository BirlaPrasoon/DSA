package leetcode.problems.google.hard.arrays;

import java.util.*;
import java.util.function.Function;

public class IterativeSubSetSums {

    public static void main(String[] args) {
        int[] nums = {-36,36};
        int ans = minimumDifference(nums);
        System.out.println(ans);
    }

    private static int minimumDifference(int[] nums) {
        int total = 0;
        for(int x: nums) total+=x;

        int mid = nums.length/2;
        Map<Integer, List<Integer>> fMap = subsetSums(nums, 0, mid, false);
        Map<Integer, List<Integer>> sMap = subsetSums(nums, mid, nums.length, true);
        int maxDiff = Integer.MIN_VALUE;
        for(int i = 0; i<=mid; i++) {
            List<Integer> first = fMap.get(i);
            List<Integer> second = sMap.get(mid-i);
            for(int ff: first) {
                int target = total/2 - ff;
                int pos = lower_bound.apply(Collections.binarySearch(second, target));
                if (pos >= 0) {
                    int ele = second.get(pos);
                    int possibleSum = ele + ff;
                    if( possibleSum<= total/2) {
                        maxDiff = Math.max(maxDiff, possibleSum);
                    }
                }
            }
        }
        int second = total - maxDiff;
        return (Math.abs(maxDiff - second));
    }

    private static final Function<Integer, Integer> lower_bound = x -> x<0 ? ~x - 1 : x;

    private List<Integer> subsetSums(int[] nums) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for (int num : nums) {
            int size = result.size();
            for (int j = 0; j < size; j++) {
                result.add(result.get(j) + num);
            }
        }
//        Collections.sort(result);  // Optional: sort the result
        return result;
    }
    static Map<Integer, List<Integer>> subsetSums(int[] arr, int start, int inclusiveEnd, boolean shouldSort) {

        // There are total 2^n subsets
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = inclusiveEnd - start;
        int total = 1 << n;

        // Consider all numbers from 0 to 2^n - 1
        for (int i = 0; i < total; i++) {
            int sum = 0;

            int count = countSetBits(i);
            for (int k = 0; k < n; k++){
                if (isKthBitSetInclusive(i, k)) {
                    sum += arr[start +k];
                }
            }
            map.computeIfAbsent(count, key -> new ArrayList<>());
            map.get(count).add(sum);
        }
        if(shouldSort)
            map.forEach((k, v) -> Collections.sort(v));
        return map;
    }

    private static boolean isKthBitSetInclusive(int i, int j) {
        return (i & (1 << j)) != 0;
    }

    // number of set bits in an integer
    static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}

