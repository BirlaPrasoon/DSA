package leetcode.problems.google.easy.arrays;

import java.util.*;

public class KthLargestInAStream {

    class KthLargest {

        PriorityQueue<Integer> set = new PriorityQueue<>();
        int k;
        public KthLargest(int k, int[] nums) {
            this.k = k;
            for(int x: nums){
                set.add(x);
                if(set.size() > k) {
                    set.poll();
                }
            }
        }

        public int add(int val) {
            set.add(val);
            if(set.size() < this.k) {
                return -1;
            }
            if(set.size()> k){
                set.poll();
            }
            return set.peek();
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
}
