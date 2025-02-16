package leetcode.problems.google.hard.binarySearch;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomPickWithBlackList {

    class Solution {
        Map<Integer, Integer> map;
        Random rand = new Random();
        int size;

        // Map the blacklisted elements with starting elements / ending elements.
        public Solution(int n, int[] blacklist) {
            map = new HashMap<>();
            size = n - blacklist.length;
            int last = n - 1;
            for (int b : blacklist) {
                map.put(b, -1);
            }
            for (int b : blacklist) {
                if (b >= size) {
                    continue;
                }
                while (map.containsKey(last)) {
                    last--;
                }
                map.put(b, last);
                last--;
            }
        }

        public int pick() {
            int idx = rand.nextInt(size);
            if (map.containsKey(idx)) {
                return map.get(idx);
            }
            return idx;
        }
    }

}
