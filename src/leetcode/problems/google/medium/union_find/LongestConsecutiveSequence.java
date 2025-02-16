package leetcode.problems.google.medium.union_find;

import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSequence {
    /*
    *
        Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
        You must write an algorithm that runs in O(n) time.
    *
    * Example 1:

        Input: nums = [100,4,200,1,3,2]
        Output: 4
        Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
        Example 2:

        Input: nums = [0,3,7,2,5,8,4,6,0,1]
        Output: 9

    * */

    // O(N)
    class SolutionNormal {
        public int longestConsecutive(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for(int n: nums) {
                set.add(n);
            }
            int max = 0;
            for(int n: nums) {
                if(set.contains(n)) {
                    int len = 1;
                    int x = n;
                    while(set.contains(n+1)) {
                        len++;
                        n++;
                        set.remove(n);
                    }
                    while(set.contains(x-1)) {
                        len++;
                        x--;
                        set.remove(x);
                    }
                    if(len > max) max = len;
                }
            }
            return max;
        }
    }
    // Sorting O(NlogN)
    class Solution {
        public int longestConsecutive(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            Arrays.sort(nums);

            int longestStreak = 1;
            int currentStreak = 1;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i - 1]) {
                    if (nums[i] == nums[i - 1] + 1) {
                        currentStreak += 1;
                    } else {
                        longestStreak = Math.max(longestStreak, currentStreak);
                        currentStreak = 1;
                    }
                }
            }

            return Math.max(longestStreak, currentStreak);
        }
    }


}
