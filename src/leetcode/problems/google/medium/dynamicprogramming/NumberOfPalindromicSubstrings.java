package leetcode.problems.google.medium.dynamicprogramming;

public class NumberOfPalindromicSubstrings {




    // Binary Search with a fast rolling hash algorithm (like Rabin-Karp). This approach tries to optimize
    // Approach #3 by speeding up the time to figure out the largest palindrome for each of the 2N−1 centers
    // in logarithmic time. This approach counts all palindromic substrings in O(NlogN) time. Here's a Quora answer
    // by T.V. Raziman which explains this approach well.

    // Palindromic trees (also known as EERTREE). It is a data structure invented by Mikhail Rubinchik which
    // links progressively larger palindromic substrings within a string. The tree construction takes linear time,
    // and the number of palindromic substrings can be counted while constructing the tree in O(N) time. Additionally,
    // the tree can be used to compute how many distinct palindromic substrings are in a string (it's just the number
    // of nodes in the tree) and how frequently each such palindrome occurs. This blog post does a good job of explaining
    // the construction of a palindromic tree.

    // Suffix Arrays with quick Lowest common Ancestor (LCA) lookups. This approach utilizes Ukonnen's algorithm to
    // build suffix trees for the input string and its reverse in linear time. Subsequently, quick LCA lookups can
    // be used to find maximum palindromes, which are themselves composed of smaller palindromes. This approach can produce
    // a count of all palindromic substrings in O(N) time. The original paper describes the algorithm, and this Quora answer
    // demonstrates an example.

    // Manacher's algorithm. It's basically Approach #3, on steroids.TM The algorithm reuses computations done for previous
    // palindromic centers to process new centers in sub-linear time (which reduces progressively for each new center). This
    // algorithm counts all palindromic substrings in O(N) time. This e-maxx post provides a fairly simple implementation of
    // this algorithm.
    public static void main(String[] args) {

    }

    class Solution {
        public int countSubstrings(String s) {
            int n = s.length(), ans = 0;

            if (n == 0)
                return 0;

            boolean[][] dp = new boolean[n][n];

            // Base case: single letter substrings
            for (int i = 0; i < n; ++i, ++ans)
                dp[i][i] = true;

            // Base case: double letter substrings
            for (int i = 0; i < n - 1; ++i) {
                dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
                ans += (dp[i][i + 1] ? 1 : 0);
            }

            // All other cases: substrings of length 3 to n
            for (int len = 3; len <= n; ++len)
                for (int i = 0, j = i + len - 1; j < n; ++i, ++j) {
                    dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                    ans += (dp[i][j] ? 1 : 0);
                }

            return ans;
        }
    }
}
