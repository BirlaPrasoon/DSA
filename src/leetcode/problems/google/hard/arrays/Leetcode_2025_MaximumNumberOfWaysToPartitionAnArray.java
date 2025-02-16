package leetcode.problems.google.hard.arrays;

import java.util.Arrays;
import java.util.HashMap;

public class Leetcode_2025_MaximumNumberOfWaysToPartitionAnArray {
    /**
     * Have I seen this before kinda question, very good, spend some time in reviewing this.
     * <p>
     * You are given a 0-indexed integer array nums of length n.
     * The number of ways to partition nums is the number of pivot indices that satisfy both conditions:</br>
     * 1 <= pivot < n</br>
     * nums[0] + nums[1] + ... + nums[pivot - 1] == nums[pivot] + nums[pivot + 1] + ... + nums[n - 1]</br>
     * You are also given an integer k. You can choose to change the value of one element of nums to k, or to leave the array unchanged.</br>
     * Return the maximum possible number of ways to partition nums to satisfy both conditions after changing at most one element.</br>
     * <p>
     * Example 1:</br>
     * Input: nums = [2,-1,2], k = 3</br>
     * Output: 1</br>
     * Explanation: One optimal approach is to change nums[0] to k. The array becomes [3,-1,2].</br>
     * There is one way to partition the array:</br>
     * - For pivot = 2, we have the partition [3,-1 | 2]: 3 + -1 == 2.</br></br>
     * Example 2:</br>
     * <p>
     * Input: nums = [0,0,0], k = 1</br>
     * Output: 2</br>
     * Explanation: The optimal approach is to leave the array unchanged.</br>
     * There are two ways to partition the array:</br>
     * - For pivot = 1, we have the partition [0 | 0,0]: 0 == 0 + 0.</br>
     * - For pivot = 2, we have the partition [0,0 | 0]: 0 + 0 == 0.</br>
     * Example 3:</br>
     * <p>
     * Input: nums = [22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14], k = -33</br>
     * Output: 4</br>
     * Explanation: One optimal approach is to change nums[2] to k. The array becomes [22,4,-33,-20,-15,15,-16,7,19,-10,0,-13,-14].</br>
     * There are four ways to partition the array.</br>
     */

    public int waysToPartition(int[] nums, int k) {

        System.out.println("Nums: " + Arrays.toString(nums) + " k:" + k);
        int n = nums.length;
        long[] prefix = new long[n + 1];
        long[] suffix = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + nums[i];
        }

        System.out.println("pref: " + Arrays.toString(prefix));
        System.out.println("suff: " + Arrays.toString(suffix));
        int count = 0;
        int max = 0;

        HashMap<Long, Integer> frontMap = new HashMap<>();
        HashMap<Long, Integer> mapFromEnd = new HashMap<>();


        count = letsCountHowManyCanWePartitionWithoutUsingTheReplacement(n, prefix, suffix, count, frontMap);
        System.out.println("Front Map: " + frontMap);
        System.out.println("End Map: " + mapFromEnd);
        max = count;
        System.out.println("MAX: " + max);

        System.out.println();
        System.out.println("Starting for loop");
        for (int i = n - 1; i >= 0; i--) {
            long diff = k - nums[i];
            int c = 0;
            System.out.println();
            System.out.println("----------");
            System.out.println("k: " + k);
            System.out.println("nums[i] " + nums[i]);
            System.out.println("for i: " + i);
            System.out.println();
            System.out.println("diff:" + diff);
            System.out.println("initial front map: " + frontMap);
            System.out.println("initial front mapFromEnd: " + mapFromEnd);

            c = doestThisReplacementMakeSumZeroWithElementsInLeftOrRight(frontMap, mapFromEnd, diff, c);

            // Lets put this diff in endMap and remove from front map

            letsRemoveThisDiffFromFrontMapAndPutInEndMap(prefix, suffix, frontMap, mapFromEnd, i);
            System.out.println();
            System.out.println("ending front map: " + frontMap);
            System.out.println("ending front mapFromEnd: " + mapFromEnd);
            System.out.println();

            max = Math.max(max, c);
            System.out.println("MAX: " + max + " internal count: " + c);
        }
        return max;
    }

    private static int letsCountHowManyCanWePartitionWithoutUsingTheReplacement(int n, long[] prefix, long[] suffix, int count, HashMap<Long, Integer> frontMap) {
        for (int i = 1; i < n; i++) {
            if (prefix[i] == suffix[i]) {
                // we already have the partition here so add to count;
                count++;
            } else {
                // diff with cur included in prefix sum
                long diff = prefix[i] - suffix[i];
                frontMap.put(diff, frontMap.getOrDefault(diff, 0) + 1);
            }
        }
        return count;
    }

    private static void letsRemoveThisDiffFromFrontMapAndPutInEndMap(long[] prefix, long[] suffix, HashMap<Long, Integer> frontMap, HashMap<Long, Integer> mapFromEnd, int i) {
        long dd = prefix[i] - suffix[i];
        System.out.println("dd: " + dd);

        if (frontMap.containsKey(dd)) {
            frontMap.put(dd, frontMap.get(dd) - 1);
            if (frontMap.get(dd) == 0) {
                frontMap.remove(dd);
            }
        }


        if (mapFromEnd.containsKey(dd)) {
            mapFromEnd.put(dd, mapFromEnd.get(dd) + 1);
        } else {
            mapFromEnd.put(dd, 1);
        }
    }

    private static int doestThisReplacementMakeSumZeroWithElementsInLeftOrRight(HashMap<Long, Integer> frontMap, HashMap<Long, Integer> mapFromEnd, long diff, int c) {
        if (frontMap.containsKey(diff)) {
            // if I have seen this diff before in frontMap that means from that index to this index, sum is 0
            // and that index can become partition
            c += frontMap.get(diff);
        }

        if (mapFromEnd.containsKey(-diff)) {
            // if I have seen this diff before in last subarray,
            // that means from this index to that index sum is 0
            // and this is a valid partition
            c += mapFromEnd.get(-diff);
        }
        return c;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, -1, 2};
        int[] b = new int[]{0, 0, 0};
        int[] c = new int[]{22, 4, -25, -20, -15, 15, -16, 7, 19, -10, 0, -13, -14};
        int k = -33;
        System.out.println(new Leetcode_2025_MaximumNumberOfWaysToPartitionAnArray().waysToPartition(b, k));
    }

}
