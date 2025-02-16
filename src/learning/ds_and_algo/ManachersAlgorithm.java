package learning.ds_and_algo;

public class ManachersAlgorithm {

    public String longestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        String sPrime = sb.toString();
        int n = sPrime.length();
        int[] palindromeRadii = new int[n];
        int centre = 0;
        int radius = 0;
        for (int i = 0; i < n; i++) {
            int mirror = 2 * centre - i;
            if (radius - i > 0) {
                palindromeRadii[i] = Math.min(radius - i, palindromeRadii[mirror]);
            }
            while (i + 1 + palindromeRadii[i] < n
                    && i - 1 - palindromeRadii[i] >= 0
                    && sPrime.charAt(i + 1 + palindromeRadii[i]) == sPrime.charAt(i - 1 - palindromeRadii[i])) {
                palindromeRadii[i]++;
            }
            if (i + palindromeRadii[i] > radius) {
                centre = i;
                radius = i + palindromeRadii[i];
            }
        }
        int maxLen = 0;
        int centreIndex = 0;
        for (int i = 0; i < n; i++) {
            if (palindromeRadii[i] > maxLen) {
                maxLen = palindromeRadii[i];
                centreIndex = i;
            }
        }
        int startIndex = (centreIndex - maxLen) / 2;
        return s.substring(startIndex, startIndex + maxLen);
    }
}
