package leetcode.problems.google.hard.binarySearch;

public class NthMagicalNumber {
/*

    A positive integer is magical if it is divisible by either a or b.

    Given the three integers n, a, and b, return the nth magical number. Since the answer may be very large, return it modulo 109 + 7.

*/

    class Solution {
        public int nthMagicalNumber(int N, int A, int B) {
            int MOD = 1_000_000_007;
            // This is important,
            int HCF = (A*B) / gcd(A, B);

            long left = 0;
            long right = (long) N * Math.min(A, B);
            while (left < right) {
                long mid = left + (right - left) / 2;
                // If there are not enough magic numbers below mi...
                if (mid / A + mid / B - mid / HCF < N)
                    left = mid + 1;
                else
                    right = mid;
            }

            return (int) (left % MOD);
        }

        public int gcd(int x, int y) {
            if (x == 0) return y;
            return gcd(y % x, x);
        }
    }

}
