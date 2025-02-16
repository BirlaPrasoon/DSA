package leetcode.problems.google.medium.depth_first_search.backtracking;

public class PartitionIntoBeautifulStrings {
    public static void main(String[] args) {
        System.out.println(new Solution().minimumBeautifulSubstrings("0"));
    }

    static class Solution {
        int min = Integer.MAX_VALUE;
        String[] powers = {"1", "101", "11001", "1111101", "1001110001", "110000110101"};
        public int minimumBeautifulSubstrings(String s) {
            solve(s, 0);
            return min == Integer.MAX_VALUE ? -1 : min;
        }

        private void solve(String s, int count) {
            if(s.length() == 0) {
                if(count < min) {
                    min = count;
                }
                return;
            }
            for(String t: powers) {
                if(s.startsWith(t)) {
                    String remaining = s.substring(t.length());
                    solve(remaining, count+1);
                }
            }
        }

        private boolean startsWith(String s, String t, int index) {
            if(index+t.length() > s.length()) return false;
            for (int i = index; i < index + t.length(); i++) {
                if (s.charAt(i) != t.charAt(i - index)) {
                    return false;
                }
            }
            return true;
        }
    }
}
