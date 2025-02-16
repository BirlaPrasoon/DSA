package leetcode.problems.google.medium.greedy.tricky_implementation;

import java.util.HashMap;
import java.util.Map;

public class DeleteColumnsToMakeSorted {
    /**
     * Problem: Cinema Seat Allocation (Leetcode 1386)
     * <br><br>
     * Given a cinema with rows of seats and some seats reserved, the goal is to determine
     * the maximum number of four-person groups that can sit in the remaining available seats.
     * Each row has 10 seats, and some seats are reserved. We need to maximize the number of four-person groups
     * that can fit in available sections of each row.
     * <br><br>
     * A group can sit in 3 possible configurations:
     * - The first 4 seats (1-4)
     * - The middle 4 seats (3-6)
     * - The last 4 seats (7-10)
     * <br><br>
     * The strategy is to use bit manipulation to track reserved seats and check available sections for each row.
     * <br><br>
     * Constraints:
     * <br><br>
     * - 1 <= n <= 10^5  (Number of rows in the cinema)
     * <br><br>
     * - 1 <= reservedSeats.length <= 10^4  (Number of reserved seats)
     * <br><br>
     * - reservedSeats[i].length == 2  (Row number and seat number in the row)
     * <br><br>
     * - 1 <= reservedSeats[i][0] <= n (Row number)
     * <br><br>
     * - 1 <= reservedSeats[i][1] <= 10 (Seat number in the row)
     * <br><br>
     * - 1 <= k <= 10^9 (k is the maximum number of rows in the cinema)
     * <br><br>
     * Time complexity is O(m) where m is the number of reserved seats.
     * <br><br>
     * Space complexity is O(m) due to the storage of row-to-seats mappings.
     */

    class Solution {
        /**
         * Approach:
         * <br><br>
         * 1. Use bitwise operations to represent reserved seats in each row.
         * <br><br>
         * 2. A reserved seat is marked with a 1 in a 10-bit integer (one bit per seat).
         * <br><br>
         * 3. Check for the availability of the three possible group configurations:
         *    - First 4 seats: 0b0111100000
         *    - Middle 4 seats: 0b0000011110
         *    - Last 4 seats: 0b0001111000
         * <br><br>
         * 4. If a row is fully empty, we can fit two groups.
         * <br><br>
         * 5. For rows with some reserved seats, calculate how many groups fit.
         * <br><br>
         * 6. For rows with no reserved seats, we can add two groups.
         * <br><br>
         * Time complexity is O(m) where m is the number of reserved seats.
         * <br><br>
         * Space complexity is O(m) due to the storage of row-to-seats mappings.
         */
        public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
            int ans = 0;
            // Map to store the reserved seats for each row.
            Map<Integer, Integer> rowToSeats = new HashMap<>();

            // Mark the reserved seats for each row using bitwise OR operation.
            for (int[] reservedSeat : reservedSeats) {
                final int row = reservedSeat[0];  // Row number
                final int seat = reservedSeat[1]; // Reserved seat
                rowToSeats.put(row, rowToSeats.getOrDefault(row, 0) | 1 << (seat - 1));
            }

            // Check each row for the available seats for group fitting
            for (final int seats : rowToSeats.values()) {
                // Check if both 4-person groups can fit in this row (bitmask for first and last four seats)
                if ((seats & 0b0111111110) == 0) {
                    ans += 2; // Can fit 2 four-person groups.
                }
                // Otherwise, check if at least one 4-person group can fit (left, middle, or right)
                else if ((seats & 0b0111100000) == 0 ||
                        (seats & 0b0001111000) == 0 ||
                        (seats & 0b0000011110) == 0) {
                    ans += 1; // Can fit 1 four-person group.
                }
            }

            // For rows that have no reserved seats, we can fit 2 groups in each of them.
            return ans + (n - rowToSeats.size()) * 2; // Empty rows can accommodate 2 groups
        }
    }


}
