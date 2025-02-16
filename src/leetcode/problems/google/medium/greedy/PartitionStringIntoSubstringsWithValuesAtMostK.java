package leetcode.problems.google.medium.greedy;

public class PartitionStringIntoSubstringsWithValuesAtMostK {
    /**
     * Solution for Leetcode 2522: "Partition String Into Substrings With Values at Most K".<br>
     * <br>
     * Given a string s consisting of digits from 1 to 9 and an integer k, the objective is to partition
     * the string s into the minimum number of substrings such that:<br>
     * <br>
     * - Each digit of s is part of exactly one substring.<br>
     * - The integer value of each substring is less than or equal to k.<br>
     * <br>
     * A substring is a contiguous sequence of characters within s. The integer value of a substring
     * is its interpretation as a number (e.g., the value of "123" is 123).<br>
     * <br>
     * If no valid partitioning exists (where each substring's value <= k), return -1.<br>
     * <br>
     * Examples:<br>
     * <br>
     * 1. Input: s = "165462", k = 60<br>
     *    Output: 4<br>
     *    Explanation: Possible partitions are ["16", "54", "6", "2"], where each substring has a value <= 60.<br>
     *    It is not possible to partition the string into fewer than 4 substrings with values <= k.<br>
     * <br>
     * 2. Input: s = "238182", k = 5<br>
     *    Output: -1<br>
     *    Explanation: No valid partition exists since no substring can be <= k (5).<br>
     * <br>
     * Constraints:<br>
     * <br>
     * - 1 <= s.length <= 10^5<br>
     * - s[i] is a digit from '1' to '9'.<br>
     * - 1 <= k <= 10^9<br>
     */
    public class Solution {

        /**
         * Finds the minimum number of substrings in a good partition of s, where each substring's
         * integer value is <= k. Returns -1 if no valid partition exists.
         *
         * @param s The input string of digits from '1' to '9'.
         * @param k The integer threshold for each substring's value.
         * @return The minimum number of substrings in a good partition of s, or -1 if impossible.
         */
        public int minimumPartition(String s, int k) {
            int partitions = 0;         // Count of partitions (substrings)
            int n = s.length();         // Length of the string s
            long current = 0;           // Accumulator for the current substring's integer value

            // Traverse each character in the string s
            for (int i = 0; i < n; i++) {
                int digit = s.charAt(i) - '0';  // Convert character to its integer value

                // If any individual digit is greater than k, no valid partition is possible
                if (digit > k) {
                    return -1;
                }

                // Attempt to add the digit to the current partition
                current = current * 10 + digit;

                // If the accumulated value exceeds k, finalize this partition and start a new one
                if (current > k) {
                    partitions++;      // Increment partition count
                    current = digit;   // Start a new partition with the current digit
                }
            }

            // Account for the last partition, if any characters remain
            if (current > 0) {
                partitions++;
            }

            return partitions;
        }
    }


}
