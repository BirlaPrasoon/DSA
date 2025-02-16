package leetcode.problems.google.hard.palindromes;

public class SuperPalindromes {

    /**
     * Let's say a positive integer is a super-palindrome if it is a palindrome, and it is also the square of a palindrome.
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: left = "4", right = "1000"
     * Output: 4
     * Explanation: 4, 9, 121, and 484 are superpalindromes.
     * Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
     * Example 2:
     * <p>
     * Input: left = "1", right = "2"
     * Output: 1
     */

    private static boolean isPalindrome(long n) {
        String s = Long.toString(n);
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    public static int superpalindromesInRange(String left, String right) {
        long l = Long.parseLong(left);
        long r = Long.parseLong(right);
        int count = 0;
        for (int i = 0; i <= 100000; i++) {
            StringBuilder sb = new StringBuilder(Integer.toString(i));
            for (int j = sb.length() - 1; j >= 0; j--) {
                sb.append(sb.charAt(j));
            }
            long n = Long.parseLong(sb.toString());
            long squared = n * n;
            if (squared >= l && squared <= r && isPalindrome(squared)) count++;
            if (squared > r) break;
        }

        for (int i = 0; i <= 100000; i++) {
            StringBuilder sb = new StringBuilder(Integer.toString(i));
            for (int j = sb.length() - 2; j >= 0; j--) {
                sb.append(sb.charAt(j));
            }
            long n = Long.parseLong(sb.toString());
            long squared = n * n;
            if (squared >= l && squared <= r && isPalindrome(squared)) count++;
            if (squared > r) break;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(superpalindromesInRange("4", "1000"));
    }


}
