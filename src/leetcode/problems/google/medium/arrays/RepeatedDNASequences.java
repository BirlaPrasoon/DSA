package leetcode.problems.google.medium.arrays;

import java.util.*;

public class RepeatedDNASequences {
    /**
     * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
     * <p>
     * For example, "ACGAATTCCG" is a DNA sequence.
     * When studying DNA, it is useful to identify repeated sequences within the DNA.
     * <p>
     * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings)
     * that occur more than once in a DNA molecule. You may return the answer in any order.
     * <p>
     * <p>
     * Example 1:
     * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * Output: ["AAAAACCCCC","CCCCCAAAAA"]
     * Example 2:
     * <p>
     * Input: s = "AAAAAAAAAAAAA"
     * Output: ["AAAAAAAAAA"]
     */

    class SolutionLinearScan {
        // Time:  O((N−L)L)
        // Space: O((N−L)L)
        public List<String> findRepeatedDnaSequences(String s) {
            int L = 10, n = s.length();
            HashSet<String> seen = new HashSet(), output = new HashSet();

            // iterate over all sequences of length L
            for (int start = 0; start < n - L + 1; ++start) {
                String tmp = s.substring(start, start + L);
                if (seen.contains(tmp)) output.add(tmp);
                seen.add(tmp);
            }
            return new ArrayList<String>(output);
        }
    }

    class SolutionRollingHash {
        // Time: O(N−L)
        // Space: O(N−L)
        public List<String> findRepeatedDnaSequences(String s) {
            int L = 10, n = s.length();
            if (n <= L) return new ArrayList();

            // rolling hash parameters: base a
            int a = 4, aL = (int) Math.pow(a, L);

            // convert string to array of integers
            Map<Character, Integer> toInt = new HashMap<>() {
                {
                    put('A', 0);
                    put('C', 1);
                    put('G', 2);
                    put('T', 3);
                }
            };
            int[] nums = new int[n];
            for (int i = 0; i < n; ++i)
                nums[i] = toInt.get(s.charAt(i));

            int h = 0;
            Set<Integer> seen = new HashSet<>();
            Set<String> output = new HashSet<>();
            for (int start = 0; start < n - L + 1; ++start) {
                if (start != 0) {
                    h = h * a - nums[start - 1] * aL + nums[start + L - 1];
                } else {
                    for (int i = 0; i < L; ++i) {
                        h = h * a + nums[i];
                    }
                }

                // update output and hashset of seen sequences
                if (seen.contains(h)) {
                    output.add(s.substring(start, start + L));
                }
                seen.add(h);
            }
            return new ArrayList<>(output);
        }
    }
}
