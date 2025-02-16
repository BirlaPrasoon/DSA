package leetcode.problems.google.hard.strings;

import java.util.Arrays;

public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        int length = s.length();
        String reversedString = new StringBuilder(s).reverse().toString();

        // Iterate through the string to find the longest palindromic prefix
        for (int i = 0; i < length; i++) {
            String originalSubtring = s.substring(0, length - i);
            String reversedSubstring = reversedString.substring(i);

            if (originalSubtring.equals(reversedSubstring)) {
                return reversedString.substring(0, i) + s;
            }
        }
        return "";
    }

    // Version 2
    /*
     * s = babbcb
     * srev = bcbbab
     * s' = s+'*' + srev
     * s' = babbcb*bcbbab
     * lps[] = [0,0,1,1,0,1,0,1,0,1,1,2,3]
     * ans = s.substring(0,n- lps[2*s.length()]) + s
     * */
    public String shortestPalindromeON(String s) {
        String reversedString = new StringBuilder(s).reverse().toString();
        String combinedString = s + "#" + reversedString;
        int[] prefixTable = buildPrefixTable(combinedString);

        int palindromeLength = prefixTable[combinedString.length() - 1];
        StringBuilder suffix = new StringBuilder(s.substring(palindromeLength)).reverse();
        return suffix.append(s).toString();
    }

    private int[] buildPrefixTable(String s) {
        int[] prefixTable = new int[s.length()];
        int length = 0;
        for (int i = 1; i < s.length(); i++) {
            while (length > 0 && s.charAt(i) != s.charAt(length)) {
                length = prefixTable[length - 1];
            }
            if (s.charAt(i) == s.charAt(length)) {
                length++;
            }
            prefixTable[i] = length;
        }
        return prefixTable;
    }


    public static void main(String[] args) {
        String s = "abcd";
        ShortestPalindrome sp = new ShortestPalindrome();
        System.out.println(sp.shortestPalindrome(s));
    }
}
