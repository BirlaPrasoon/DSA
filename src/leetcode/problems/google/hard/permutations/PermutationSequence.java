package leetcode.problems.google.hard.permutations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {

    // [1,2, ..., n], find kth permutation.

    /**
     * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
     * <p>
     * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
     * <p>
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     */
    class Solution {

        /*
        *
        * n = 4, k = 17
        * 1 is fixed at first position, number of permutations-3! => 6 (0-5)
        * 2 is fixed at first position, number of permutations - 3! => 6 (total 12) (6-11)
        * 3 is fixed at first position, number of permutations - 3! => 6 (total 17), but we need 17th so we take 3 as initial position, (12 - 18),
        *
        * remaining numbers - (1,2,4) and I need to find the 4th permutation in this sequence.
        *
        * fix 1 -> 2! (13 considered)
        * fix 2 -> 2! (15 considered)
        * fix 4 -> 2! (17 considered) that means we need to fix 4 in 2nd position.
        *  remaining - 1, 2
        * 1 fixed - 15th
        * 2 fixed 16th permutation -> 3, 4, 2, 1
        *
        * 1,2,3,4 - 0th permutation
        *
        *
        * 4,3,2,1 - 23rd permutation
        *
        * so I'm looking for 16th permutation.
        *
        * */

        public String getPermutation(int n, int k) {
            int fact = 1;
            List<Integer> numbers = new ArrayList<>();
            for (int i = 1; i < n; i++) {
                fact *= i;
                numbers.add(i);
            }
            numbers.add(n);
            StringBuilder ans = new StringBuilder();
            k = k - 1;
            while (true) {
                ans.append(numbers.get(k / fact));
                numbers.remove(k / fact);
                if (numbers.isEmpty()) break;
                k = k % fact;
                fact = fact / numbers.size();
            }
            return ans.toString();
        }

    }

    public static void main(String[] args) {
    }
}
