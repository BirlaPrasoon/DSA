package leetcode.problems.google.medium.greedy;

import java.util.PriorityQueue;

public class ReorganizeString {
/*
    Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

    Return any possible rearrangement of s or return "" if not possible.



    Example 1:

    Input: s = "aab"
    Output: "aba"
    Example 2:

    Input: s = "aaab"
    Output: ""


    Constraints:

            1 <= s.length <= 500
    s consists of lowercase English letters.
    */

    class Solution {
        public String reorganizeString(String s) {
            int[] freq = new int[26];
            for(char c: s.toCharArray()) {
                freq[c-'a']++;
            }
            // Remember this is reverse ordering, we need to compare with opposite of natural sorting.
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.freq - a.freq);
            for(int i = 0; i<26; i++) {
                if(freq[i]>0){
                    pq.add(new Pair((char)(i+'a'), freq[i]));
                }
            }
            // a=3, b=1
            StringBuilder ans = new StringBuilder();
            // ans = aba
            while(!pq.isEmpty()) {
                if(pq.size() == 1) {
                    // make sure ans is not null.
                    if(!ans.isEmpty() && pq.peek().c == ans.charAt(ans.length()-1)) return "";
                    // break only if this character's frequency is not 1, otherwise its a valid answer.
                    if(pq.peek().freq > 1) return "";
                    ans.append(pq.poll().c);
                    continue;
                }
                var first = pq.poll();
                var second = pq.poll();
                ans.append(first.c);
                ans.append(second.c);
                Pair p = new Pair(first.c, first.freq-1);
                Pair q = new Pair(second.c, second.freq-1);
                if(p.freq > 0) {
                    pq.add(p);
                }
                if(q.freq > 0) {
                    pq.add(q);
                }
            }
            return ans.toString();
        }

        record Pair(char c, int freq) { }
    }
}
