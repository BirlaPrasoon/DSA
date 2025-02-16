package algoexpert.arrays;

import java.util.Arrays;

public class MaximizeScore {
    public static void main(String[] args) {
        int[][] points = new int[][]{
                {1,2,3},
                {1,5,1},
                {3,1,1}
        };
        long x = (long)Math.pow(10, 18);
        long t = (long)Math.sqrt(x);
        System.out.println("X is square "+ t);
        System.out.println(x);
        generatePalindromes();
    }


    public static void generatePalindromes() {
        for (int length = 1; length <= 3; length++) {
            generatePalindromesOfLength(length);
        }
    }

    public static void generatePalindromesOfLength(int length) {
        int start = (int) Math.pow(10, length / 2 - 1);
        int end = (int) Math.pow(10, length / 2) - 1;

        System.out.println("Start: " + start + " END: " + end);
        if (length % 2 == 0) {
            for (int i = start; i <= end; i++) {
                String num = Integer.toString(i);
                StringBuilder palindrome = new StringBuilder(num + new StringBuilder(num).reverse());
                System.out.println(palindrome);
            }
        } else {
            for (int i = start; i <= end; i++) {
                String num = Integer.toString(i);
                StringBuilder palindrome = new StringBuilder(num + new StringBuilder(num.substring(0, num.length() - 1)).reverse());
                System.out.println(palindrome);
            }
        }
    }

    public  long maxPoints(int[][] points) {
        if(points.length == 0) return 0;
        long dp[][] = new long[points.length][points[0].length];
        for(long[] a: dp) {
            Arrays.fill(a, -1);
        }
        return solve(points, -1, -1, dp);
    }

    public static long maxPoints2(int[][] points) {
        long[][] dp = new long[points.length][points[0].length];
        for(int i = 0; i<points[0].length; i++){
            dp[0][i] = points[0][i];
        }
        for(int i = 1; i<points.length; i++){
            for (int j = 0; j<points[i].length; j++) {
                if(j-1>=0){
                    dp[i][j] = dp[i-1][j-1] + points[i][j];
                }
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + points[i][j]);
                if(j+1 <=points[i].length){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j+1] + points[i][j]);
                }
            }
        }
        return Arrays.stream(dp[dp.length-1]).max().getAsLong();
    }

    private  long solve(int[][] points, int r, int c1, long[][]dp) {
        int r1 = r + 1;
        if(r1 >= points.length) {
            return 0;
        }
        if(c1 >= 0 && dp[r1][c1] != -1) return dp[r1][c1];
        long max = -1;
        for(int c = 0; c<points[r1].length; c++) {
            long diff = 0;
            if(r == -1) {
                diff = 0;
            } else {
                diff = Math.abs(c1 - c);
            }
            max = Math.max(max, solve(points, r1, c, dp) + points[r1][c] - diff);
        }
        if(c1 > 0)
            dp[r1][c1] = max;
        return max;
    }
}
