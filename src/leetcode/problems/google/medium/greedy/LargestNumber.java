package leetcode.problems.google.medium.greedy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class LargestNumber {

    // Trick is to use String concatenation comparison.
    // Edge cases - [0,0], string will turn out to be "00". Need to take care of this. Remove beginning zero, if string is empty, return "0";

    class Solution {
        public String largestNumber(int[] nums) {
            if(allZeros(nums)) {
                return "0";
            }
            ArrayList<String> ns = new ArrayList<>();
            for (int n : nums) {
                ns.add(Integer.toString(n));
            }
            ns.sort((a, b) -> {
                String s1 = a + b;
                String s2 = b + a;
                return s1.compareTo(s2);
            });
            StringBuilder sb = new StringBuilder();
            for (int i = nums.length - 1; i >= 0; i--) {
                nums[i] = Integer.parseInt(ns.get(i));
                sb.append(ns.get(i));
            }
            return sb.toString();
        }

        private boolean allZeros(int[] nums) {
            for(int x: nums) if(x!=0) return false;
            return true;
        }
    }

}
