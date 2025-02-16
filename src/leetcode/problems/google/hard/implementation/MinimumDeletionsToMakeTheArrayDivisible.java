package leetcode.problems.google.hard.implementation;

import java.util.*;

public class MinimumDeletionsToMakeTheArrayDivisible {
    /*
    *
    * You are given two positive integer arrays nums and numsDivide. You can delete any number of elements from nums.
      Return the minimum number of deletions such that the smallest element in nums divides all the elements of numsDivide. If this is not possible, return -1.
      Note that an integer x divides y if y % x == 0.
    * */

    public int minOperationsUsingGcd(int[] nums, int[] numsDivide) {
        // sort nums and then if the first is not able to divide;
        Arrays.sort(nums);
        // gcd of numsDivied, so basically that is what we need to divide in order;
        int gc = numsDivide[0];
        for(int i=1;i<numsDivide.length;i++) {
            gc = gcd(gc, numsDivide[i]);
        }

        for(int i=0;i<nums.length;i++) {
            if(gc%nums[i]==0) return i;
        }

        return -1;
    }

    public int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }

    public int minOperations(int[] nums, int[] numsDivide) {
        int count = 0;
        int counts[] = new int[100000+5];
        for (int j : nums) {
            counts[j]++;
        }
        for (int i = 0; i < counts.length; i++) {
            if(counts[i] <=0) continue;
            boolean isDivisible = true;
            if(i == 0) count+=counts[i];
            else {
                for (int j : numsDivide) {
                    if (j % i != 0) {
                        isDivisible = false;
                        break;
                    }
                }
            }
            if (isDivisible) return count;
            else count += counts[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        MinimumDeletionsToMakeTheArrayDivisible m = new MinimumDeletionsToMakeTheArrayDivisible();
        System.out.println(m.minOperations(new int[]{4,3,6}, new int[]{8,2,6,10}));
    }
}
