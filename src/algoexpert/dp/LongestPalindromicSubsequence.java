package algoexpert.dp;

public class LongestPalindromicSubsequence {

    public static String longestPalindromicSubstring(String str) {
        boolean[][] dp = new boolean[str.length()+1][str.length()+1];
        int maxLen = 1;
        String ans = str.charAt(0) +"";
        for(int i = 0; i<str.length()-1; i++) {
            dp[i][i] = true;
            if(str.charAt(i) == str.charAt(i+1)) {
                dp[i][i+1] = true;
                maxLen = 2;
                ans = str.substring(i, i+2);
            }
        }

        for(int l = 3; l<=str.length(); l++) {
            for(int i = 0; i+l-1 < str.length(); i++) {
                int j = i+l-1;
                if(str.charAt(i) == str.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    if(l >= maxLen) {
                        maxLen = l;
                        ans = str.substring(i, j+1);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "aa";
        System.out.println(longestPalindromicSubstring(s));
    }
}
