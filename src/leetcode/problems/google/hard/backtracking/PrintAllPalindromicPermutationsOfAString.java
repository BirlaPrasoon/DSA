package leetcode.problems.google.hard.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PrintAllPalindromicPermutationsOfAString {

    /**
     * Given a string, we need to print all possible palindromes that can be generated using letters of that string. </br></br>
     * Examples:</br>
     * <p>
     * Input:  str = "aabcb"</br>
     * Output: abcba bacab</br>
     * <p>
     * Input:  str = "aabbcadad"</br>
     * Output: aabdcdbaa aadbcbdaa abadcdaba</br>
     * abdacadba adabcbada adbacabda</br>
     * baadcdaab badacadab bdaacaadb</br>
     * daabcbaad dabacabad dbaacaabd</br>
     */


    public class GFG {
        public static boolean isPalin(String s, HashMap<Character, Integer> freq) {
            freq.clear();
            for (int i = 0; i < 26; i++) {
                freq.put((char) ('a' + i), 0);
            }
            int l = s.length();

            // Update frequency map according to given string
            for (int i = 0; i < l; i++) {
                freq.put(s.charAt(i), freq.get(s.charAt(i)) + 1);
            }
            int odd = 0;

            // Loop to count total letters with odd frequency
            for (int i = 0; i < 26; i++) {
                if (freq.get((char) ('a' + i)) % 2 == 1) {
                    odd += 1;
                }
            }


            // Palindrome condition :
            // if length is odd then only one letter's frequency
            // must be odd if length is even no letter should
            // have odd frequency
            return (l % 2 == 1 && odd == 1) || (l % 2 == 0 && odd == 0);
        }

        // Function to reverse a string
        public static String reverse(String s) {
            char[] arr = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = arr.length - 1; i >= 0; i--) {
                sb.append(arr[i]);
            }

            return sb.toString();
        }

        // Function to print all possible palindromes by letter
// of given string
        public static void printAllPossiblePalindromes(String s) {
            HashMap<Character, Integer> freq = new HashMap<Character, Integer>();

            // Checking whether the input string can make a
            // palindrome or not
            if (!isPalin(s, freq)) {
                return;
            }
            int l = s.length();
            StringBuilder half = new StringBuilder();
            char oddC = '\0';


            // Create a string with half of the letters of the
            // palindrome and keep track of the letter with an
            // odd frequency (if any)
            for (int i = 0; i < 26; i++) {
                if (freq.get((char) ('a' + i)) % 2 == 1) {
                    oddC = (char) ('a' + i);
                }
                half.append(new String(new char[(int) (freq.get((char) ('a' + i)) / 2)]).replace('\0', (char) ('a' + i)));
            }
            String palin = "";
            HashSet<String> xd = new HashSet<String>();

            // Loop through all permutations of half and add the
            // reverse of the permutation to the end If the
            // length is odd, add the odd letter in the middle
            // as well
            for (char[] p : permute(half.toString().toCharArray())) {
                palin = new String(p);
                if (l % 2 == 1) {
                    palin += oddC;
                }
                palin += reverse(new String(p));
                if (!xd.contains(palin)) {
                    System.out.println(palin);
                    xd.add(palin);
                }
            }
        }

        // Function to generate all permutations of an array
        public static List<char[]> permute(char[] arr) {
            List<char[]> result = new ArrayList<char[]>();
            if (arr.length == 1) {
                result.add(arr);
            } else {
                for (int i = 0; i < arr.length; i++) {
                    char current = arr[i];
                    char[] remaining = new char[arr.length - 1];
                    int index = 0;
                    for (int j = 0; j < arr.length; j++) {
                        if (j != i) {
                            remaining[index++] = arr[j];
                        }
                    }
                    List<char[]> remainingPermuted = permute(remaining);
                    for (char[] chars : remainingPermuted) {
                        char[] temp = new char[1 + chars.length];
                        temp[0] = current;
                        for (int k = 0; k < chars.length; k++) {
                            temp[k + 1] = chars[k];
                        }
                        result.add(temp);
                    }
                }
            }
            return result;
        }

        // Driver code
        public static void main(String[] args) {
            String s = "aabbcadad";
            System.out.println("All palindrome permutations of " + s);

            // Function call
            printAllPossiblePalindromes(s);
        }
    }


}
