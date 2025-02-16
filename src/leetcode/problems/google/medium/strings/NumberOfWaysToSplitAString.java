package leetcode.problems.google.medium.strings;

public class NumberOfWaysToSplitAString {

/*
    Given a binary string s, you can split s into 3 non-empty strings s1, s2, and s3 where s1 + s2 + s3 = s.

    Return the number of ways s can be split such that the number of ones is the same in s1, s2, and s3. Since the answer may be too large, return it modulo 109 + 7.

    Example 1:

    Input: s = "10101"
    Output: 4
    Explanation: There are four ways to split s in 3 parts where each part contain the same number of letters '1'.
            "1|010|1"
            "1|01|01"
            "10|10|1"
            "10|1|01"
    Example 2:

    Input: s = "1001"
    Output: 0
    Example 3:

    Input: s = "0000"
    Output: 3
    Explanation: There are three ways to split s in 3 parts.
        "0|0|00"
        "0|00|0"
        "00|0|0"
*/

    private static final int m = 1_000_000_007;

    public int numWays(String s) {

        int ones = 0, n = s.length();
        for (int i = 0; i < n; ++i) {
            ones += s.charAt(i) - '0';
        }
        if (ones == 0) {
            // (n-1)c(2) , we want to make 2 number of cuts from n-1 positions.
            return (int) ((n - 2L) * (n - 1L) / 2 % m);
        }
        if (ones % 3 != 0) {
            return 0;
        }
        int onesInEachSplitedBlock = ones / 3;
        long waysOfFirstCut = 0, waysOfSecondCut = 0;
        for (int i = 0, count = 0; i < n; ++i) {

            if(s.charAt(i) == '1')
                count++;

            if (count == onesInEachSplitedBlock) {
                ++waysOfFirstCut;
            } else if (count == 2 * onesInEachSplitedBlock) {
                ++waysOfSecondCut;
            }
        }
        return (int) (waysOfFirstCut * waysOfSecondCut % m);
    }
}
