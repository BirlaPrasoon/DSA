package leetcode.problems.google.hard.binarySearch;

public class UglyNumber3 {

    class Solution {
        long a, b, c, ab, bc, ac, abc;

        public long countMultiples(long X) {
            return (X / a) + (X / b) + (X / c) - (X / ab) - (X / bc) - (X / ac) + (X / abc);
        }

        public int gcd(int a, int b) {
            return (b == 0) ? a : gcd(b, a % b);
        }

        public int nthUglyNumber(int n, int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;

            ab = ((long) a * b) / gcd(a, b);
            bc = ((long) b * c) / gcd(b, c);
            ac = ((long) a * c) / gcd(a, c);
            abc = ((long) a * bc) / gcd(a, (int) bc);

            long left = 0, right = Long.MAX_VALUE;

            while (left <= right) {
                long mid = left + (right - left) / 2;
                if (countMultiples(mid) < n) left = mid + 1;
                else right = mid - 1;
            }

            return (int) left;
        }
    }

}
