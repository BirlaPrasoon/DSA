package leetcode.problems.google.easy.hashing;

import java.util.Arrays;
import java.util.HashMap;

public class LongestSubStringWithEvenVowelCount {
    boolean isVowel(char c) {
        switch (c) {
            case 'a':
            case 'i':
            case 'e':
            case 'o':
            case 'u':
                return true;
            default:return false;
        }
    }

    int getMaskNumber(char c) {
        switch (c) {
            case 'a': return 2;
            case 'e': return 4;
            case 'i': return 8;
            case 'o': return 16;
            case 'u': return 32;
            default:  return -1;
        }
    }
    public int findTheLongestSubstring(String s) {
        int arr[] = new int[35];
        Arrays.fill(arr, -2);
        int val = 0;
        arr[val] = -1;
        int maxLen = 0;

        for(int i = 0; i<s.length(); i++) {
            if(isVowel(s.charAt(i))) {
                val ^= getMaskNumber(s.charAt(i));
            }
            if(arr[val] >= -1) {
                int index = arr[val];
                int len = i - index;
                if(len > maxLen) {
                    maxLen = len;
                }
            } else {
                arr[val] = i;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubStringWithEvenVowelCount l = new LongestSubStringWithEvenVowelCount();
        System.out.println(l.findTheLongestSubstring("eapppz"));
    }
}
