package leetcode.problems.google.easy.string;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class RepeatedSubstringPattern {

    static class Solution {
        static LinkedList<Integer> getDivisors(int n) {
            // Vector to store half of the divisors
            LinkedList<Integer> v = new LinkedList<>();
            LinkedList<Integer> secondHalf = new LinkedList<>();
            for (int i = 1; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    // check if divisors are equal
                    if (n / i == i) v.add(i);
                    else {
                        v.add(i);
                        secondHalf.addFirst(n / i);
                    }
                }
            }
            v.addAll(secondHalf);
            return v;
        }

        public boolean repeatedSubstringPattern(String s) {
            int n = s.length();
            LinkedList<Integer> list = getDivisors(n);
            list.remove(list.size() - 1);
            for (int a : list) {
                String sub = s.substring(0, a);
                int q = n / a;
                StringBuilder sb = new StringBuilder();
                while (q-- > 0) {
                    sb.append(sub);
                }
                String t = sb.toString();
                if (t.equals(s)) return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.repeatedSubstringPattern("abcabcabcabc"));
    }

}
