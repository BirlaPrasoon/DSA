package leetcode.problems.google.medium.greedy;

public class MaximumNumberOfOperationsToMoveOnesToEnd {


    class Solution {
        public int maxOperations(String s) {
            int count = 0;
            int oc = 0;
            // 1010100
            // oc = 3
            // cnt = 3
            for(int i= 0; i<s.length(); i++) {
                if(i+1 == s.length()) {
                    if(s.charAt(i) == '0')
                        count += oc;
                } else if(s.charAt(i) == '0' && s.charAt(i+1) == '1')  {
                    count += oc;
                }
                if(s.charAt(i) == '1') oc++;
            }
            return count;
        }
    }

}
