package leetcode.problems.google.easy.arrays;

import java.util.HashMap;

public class CountBinaryStrings {
    public int countBinarySubstrings(String str)
    {
        int count = 0;
        int consecutiveCount = 1;
        int lastConsecutiveCount = 0;
        char[] s = str.toCharArray();

        for (int i = 1; i < s.length; i++)
        {
            if (s[i] != s[i - 1])
            {
                lastConsecutiveCount = consecutiveCount;
                consecutiveCount = 1;
                count++;
            }
            else
            {
                consecutiveCount++;
                if (consecutiveCount <= lastConsecutiveCount) count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        CountBinaryStrings b = new CountBinaryStrings();
        System.out.println(b.countBinarySubstrings("0011"));
    }
}
