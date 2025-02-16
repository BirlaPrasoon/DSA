package leetcode.problems.google.medium.greedy;

import java.util.HashMap;

public class OptimalPartitioningOfAString {

    class Solution {
        public int partitionString(String s) {
            HashMap<Character, Integer> map = new HashMap<>();
            int count =0;
            for(int i = 0; i<s.length(); i++) {
                if(map.containsKey(s.charAt(i))) {
                    count++;
                    map.clear();
                }
                map.put(s.charAt(i), 1);
            }
            // This is crucial... dry run will find this out.
            if(!map.isEmpty()) count++;
            return count;
        }
    }

}
