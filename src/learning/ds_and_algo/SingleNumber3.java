package learning.ds_and_algo;

public class SingleNumber3 {
    // All numbers twice except 2 numbers which once.

    class Solution {
        public int[] singleNumber(int[] nums) {
            // difference between two numbers (x and y) which were seen only once
            int bitmask = 0;
            for (int num : nums)
                bitmask ^= num;

            // rightmost 1-bit diff between x and y
            int diff = bitmask & (-bitmask);

            int x = 0;
            // bitmask which will contain only x
            for (int num : nums)
                if ((num & diff) != 0) x ^= num;

            return new int[]{x, bitmask^x};
        }
    }
}
