package leetcode.problems.google.hard.greedy.jumpGame;

import java.util.LinkedList;
import java.util.Queue;

public class JumpGame7 {
    /**
    *
    * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:

i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.
Return true if you can reach index s.length - 1 in s, or false otherwise.



Example 1:

Input: s = "011010", minJump = 2, maxJump = 3
Output: true
Explanation:
In the first step, move from index 0 to index 3.
In the second step, move from index 3 to index 5.
Example 2:

Input: s = "01101110", minJump = 2, maxJump = 3
Output: false


Constraints:

2 <= s.length <= 10<sup>5</sup>
s[i] is either '0' or '1'.
s[0] == '0'
1 <= minJump <= maxJump < s.length
    * */

    class Solution {
        public boolean canReach(String s, int minJump, int maxJump) {
            if(s.charAt(s.length() - 1) != '0')
                return false;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);

            // This variable tells us till which index we have processed
            int maxReach = 0;

            while(!queue.isEmpty()){
                int idx = queue.remove();
                // If we reached the last index
                if(idx == s.length() - 1)
                    return true;

                // start the loop from max of [current maximum (idx + minJump), maximum processed index (maxReach)]
                for(int j = Math.max(idx + minJump, maxReach); j <= Math.min(idx + maxJump, s.length() - 1); j++){
                    if(s.charAt(j) == '0')
                        queue.add(j);
                }

                // since we have processed till idx + maxJump so update maxReach to next index
                maxReach = Math.min(idx + maxJump + 1, s.length() - 1);
            }

            return false;
        }
    }
}
