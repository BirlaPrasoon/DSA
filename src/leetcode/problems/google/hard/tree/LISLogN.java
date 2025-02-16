package leetcode.problems.google.hard.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class LISLogN {

    private static final Function<Integer, Integer> lower_bound = (pos) -> pos >= 0 ? pos : ~pos;
    // Notes
    /*
    Binary Search (Collections.binarySearch): In Java, the method Collections.binarySearch() is used to find the position
    where an element should be inserted to maintain sorted order. If the element is not found, it returns -(insertion point) - 1.
    So, if the return value is negative, we use -(lb + 1) to find the correct insertion point.
    * */
    public static int longestSubsequence( int[] nums) {
        List<Integer> lis = new ArrayList<>();
        for (int num : nums) {
            int lb = lower_bound.apply(Collections.binarySearch(lis, num));
            if (lb == lis.size()) lis.add(num);
            else lis.set(lb, num);
            System.out.println(lis);
        }
        return lis.size();
    }

    public static void main(String[] args) {
        int[] a = {0,1,8,8,2,3};
        System.out.println(longestSubsequence(a));
        System.out.println(lower_bound.apply(0));
    }
}
