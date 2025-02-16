package leetcode.problems.google.hard.binarySearch.most_asked;

import java.util.*;

public class PartitionArrayToMinimizeSumDifference {

    // Array Size is always even, this is critical...

    public int minimumDifference(int[] nums) {

        int n = nums.length;

        Map<Integer, List<Integer>> fPartition = subsetSums(nums, 0, n / 2, false);

        Map<Integer, List<Integer>> sPartition = subsetSums(nums, n / 2, n, true);
        //Store total array sum.
        int total = 0;
        for (int ele : nums) total += ele;

        //Take biggest integer possible as the initial result.
        int result = Integer.MAX_VALUE;

        //Iterate over all sizes from 0 to n/2 inclusive.
        for (int i = 0; i <= n / 2; i++) {

            //Subset sums of first partition of size i
            List<Integer> fSums = fPartition.get(i);

            //Subset sums of second partition of size n/2 - i (remaining part)
            List<Integer> sSums = sPartition.get(n / 2 - i);

            //Number of subset sums of size i,
            int size = sSums.size();

            //Find the suitable subset sum of the second partition for every subset sub of first partition using binary search.
            for (int fSum : fSums) {
                int low = 0;
                int high = size - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;

                    //Sum of the partiton of size i and n/2-i together should constitute to total/2 (total - 2 * (x+y) -> min).
                    int target = 2 * (fSum + sSums.get(mid));

                    //If they are equal then return 0 because lowest absolute difference cannot be less than 0.
                    if (total == target) return 0;
                    else if (total > target) low = mid + 1;
                    else high = mid - 1;
                }

                //Now check the element at low and high and compare which one is giving lowest absolute difference and update result.
                if (low < size) {
                    result = Math.min(result, Math.abs(total - 2 * (fSum + sSums.get(low))));
                }
                if (high >= 0) {
                    result = Math.min(result, Math.abs(total - 2 * (fSum + sSums.get(high))));
                }
            }
        }
        return result;
    }

    static Map<Integer, List<Integer>> subsetSums(int[] arr, int start, int exclusiveEnd, boolean shouldSort) {

        // There are total 2^n subsets
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = exclusiveEnd - start;
        int total = 1 << n;

        // Consider all numbers from 0 to 2^n - 1
        for (int i = 0; i < total; i++) {
            int sum = 0;

            int count = countSetBits(i);
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += arr[start + j];
                }
            }
            map.computeIfAbsent(count, key -> new ArrayList<>());
            map.get(count).add(sum);
        }
        if (shouldSort)
            map.forEach((k, v) -> Collections.sort(v));
        return map;
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


    public static void main(String[] args) {
        PartitionArrayToMinimizeSumDifference ob = new PartitionArrayToMinimizeSumDifference();
        System.out.println(ob.minimumDifference(new int[]{20, 3, 9, 7}));
    }
}
