package leetcode.problems.google.hard.palindromes;

import java.util.ArrayList;
import java.util.List;

public class ClosestPalindrome {

    // O(nlog(m))
    class SolutionBinarySearch {

        // Convert to palindrome keeping first half constant.
        private long convert(long num) {
            String s = Long.toString(num);
            int n = s.length();
            int l = (n - 1) / 2, r = n / 2;
            char[] sArray = s.toCharArray();
            while (l >= 0) {
                sArray[r++] = sArray[l--];
            }
            return Long.parseLong(new String(sArray));
        }

        // Find the previous palindrome, just smaller than n.
        private long previousPalindrome(long num) {
            long left = 0, right = num;
            long ans = Long.MIN_VALUE;
            while (left <= right) {
                long mid = (right - left) / 2 + left;
                long palin = convert(mid);
                if (palin < num) {
                    ans = palin;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return ans;
        }

        // Find the next palindrome, just greater than n.
        private long nextPalindrome(long num) {
            long left = num, right = (long) 1e18;
            long ans = Long.MIN_VALUE;
            while (left <= right) {
                long mid = (right - left) / 2 + left;
                long palin = convert(mid);
                if (palin > num) {
                    ans = palin;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }

        public String nearestPalindromic(String n) {
            long num = Long.parseLong(n);
            long a = previousPalindrome(num);
            long b = nextPalindrome(num);
            if (Math.abs(a - num) <= Math.abs(b - num)) {
                return Long.toString(a);
            }
            return Long.toString(b);
        }
    }

    // O(n)
    static class Solution {

        public String nearestPalindromic(String n) {
            int len = n.length();
            int i = len % 2 == 0 ? len / 2 - 1 : len / 2;
            List<Long> possibilities = getPossibleCandidates(n, i, len);
//            System.out.println("Possibilities:" + possibilities);

            // Find the palindrome with minimum difference, and minimum value.
            long diff = Long.MAX_VALUE, res = 0, nl = Long.parseLong(n);
            for (long cand : possibilities) {
                if (cand == nl) continue;
                if (Math.abs(cand - nl) < diff) {
                    diff = Math.abs(cand - nl);
                    res = cand;
                } else if (Math.abs(cand - nl) == diff) {
                    res = Math.min(res, cand);
                }
            }

            return String.valueOf(res);
        }

        private List<Long> getPossibleCandidates(String n, int i, int len) {
            long firstHalf = Long.parseLong(n.substring(0, i + 1));
//            System.out.println("First Half: " + firstHalf);
            /*

            Generate possible palindromic candidates:
            1. Create a palindrome by mirroring the first half.
            2. Create a palindrome by mirroring the first half incremented by 1.
            3. Create a palindrome by mirroring the first half decremented by 1.
            4. Handle edge cases by considering palindromes of the form 999...
               and 100...001 (smallest and largest n-digit palindromes).

            */
            List<Long> possibilities = new ArrayList<>();

            possibilities.add(halfToPalindrome(firstHalf, len % 2 == 0));
            possibilities.add(halfToPalindrome(firstHalf + 1, len % 2 == 0));
            possibilities.add(halfToPalindrome(firstHalf - 1, len % 2 == 0));
            possibilities.add((long) Math.pow(10, len - 1) - 1);
            possibilities.add((long) Math.pow(10, len) + 1);
            return possibilities;
        }

        private long halfToPalindrome(long left, boolean even) {
            // Convert the given half to palindrome.
//            System.out.println("LEFT: " + left + " even: " + even);
            long res = left;
            if (!even) left = left / 10;
            while (left > 0) {
                res = res * 10 + (left % 10);
                left /= 10;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ClosestPalindrome.Solution().nearestPalindromic("19295"));
    }
}
