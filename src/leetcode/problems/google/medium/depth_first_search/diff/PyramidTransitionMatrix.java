package leetcode.problems.google.medium.depth_first_search.diff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PyramidTransitionMatrix {
/*

    You are stacking blocks to form a pyramid. Each block has a color, which is represented by a single letter.
    Each row of blocks contains one less block than the row beneath it and is centered on top.

    To make the pyramid aesthetically pleasing, there are only specific triangular patterns that are allowed.
    A triangular pattern consists of a single block stacked on top of two blocks.
    The patterns are given as a list of three-letter strings allowed, where the first two characters of a pattern
    represent the left and right bottom blocks respectively, and the third character is the top block.

    For example, "ABC" represents a triangular pattern with a 'C' block stacked on top of an 'A' (left) and 'B' (right) block.
    Note that this is different from "BAC" where 'B' is on the left bottom and 'A' is on the right bottom.
    You start with a bottom row of blocks bottom, given as a single string, that you must use as the base of the pyramid.

    Given bottom and allowed, return true if you can build the pyramid all the way to the top such that
    every triangular pattern in the pyramid is in allowed, or false otherwise.

    */

    class Solution {
        public boolean pyramidTransition(String bottom, List<String> allowed) {
            HashMap<String, List<Character>> map = new HashMap<>();
            HashSet<String> invalidBottoms = new HashSet<>();
            for (String s : allowed) {
                String b = s.substring(0, 2);
                char up = s.charAt(2);
                if (!map.containsKey(b))
                    map.put(b, new ArrayList<>());
                map.get(b).add(up);
            }

            return pyramidHelper(bottom, map, invalidBottoms);
        }

        private boolean pyramidHelper(String bottom, HashMap<String, List<Character>> map, HashSet<String> invalidBottoms) {
            if (bottom.length() == 2) {
                return map.containsKey(bottom);
            }

            if (invalidBottoms.contains(bottom))
                return false;

            List<String> potentialBottoms = new ArrayList<>();
            getPotentialBottoms("", bottom, 0, map, potentialBottoms);
            for (String b : potentialBottoms) {
                boolean res = pyramidHelper(b, map, invalidBottoms);
                if (res)
                    return res;
            }

            invalidBottoms.add(bottom);
            return false;
        }

        private void getPotentialBottoms(String bsf, String bottom, int idx, HashMap<String, List<Character>> map,
                                         List<String> potentialBottoms) {
            if (idx == bottom.length() - 2) {
                String s = bottom.substring(idx, bottom.length());
                if (!map.containsKey(s)) {
                    return;
                }

                List<Character> list = map.get(s);
                for (char suffix : list) {
                    potentialBottoms.add(bsf + suffix);
                }

                return;
            }

            String s = bottom.substring(idx, idx + 2);
            if (!map.containsKey(s))
                return;
            for (char suffix : map.get(s)) {
                getPotentialBottoms(bsf + suffix, bottom, idx + 1, map, potentialBottoms);
            }
        }
    }

}
