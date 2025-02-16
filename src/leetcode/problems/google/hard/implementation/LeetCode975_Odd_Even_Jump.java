package leetcode.problems.google.hard.implementation;

import java.util.Map;
import java.util.TreeMap;

public class LeetCode975_Odd_Even_Jump {
    /**
     * You are given an integer array arr. From some starting index, you can make a series of jumps. The (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even-numbered jumps. Note that the jumps are numbered, not the indices.
     * <p>
     * You may jump forward from index i to index j (with i < j) in the following way:
     * <p>
     * During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that arr[i] <= arr[j] and arr[j] is the smallest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
     * During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that arr[i] >= arr[j] and arr[j] is the largest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
     * It may be the case that for some index i, there are no legal jumps.
     * A starting index is good if, starting from that index, you can reach the end of the array (index arr.length - 1) by jumping some number of times (possibly 0 or more than once).
     * <p>
     * Return the number of good starting indices.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: arr = [10,13,12,14,15]
     * Output: 2
     * Explanation:
     * From starting index i = 0, we can make our 1st jump to i = 2 (since arr[2] is the smallest among arr[1], arr[2], arr[3], arr[4] that is greater or equal to arr[0]), then we cannot jump any more.
     * From starting index i = 1 and i = 2, we can make our 1st jump to i = 3, then we cannot jump any more.
     * From starting index i = 3, we can make our 1st jump to i = 4, so we have reached the end.
     * From starting index i = 4, we have reached the end already.
     * In total, there are 2 different starting indices i = 3 and i = 4, where we can reach the end with some number of
     * jumps.
     * Example 2:
     * <p>
     * Input: arr = [2,3,1,1,4]
     * Output: 3
     * Explanation:
     * From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:
     * During our 1st jump (odd-numbered), we first jump to i = 1 because arr[1] is the smallest value in [arr[1], arr[2], arr[3], arr[4]] that is greater than or equal to arr[0].
     * During our 2nd jump (even-numbered), we jump from i = 1 to i = 2 because arr[2] is the largest value in [arr[2], arr[3], arr[4]] that is less than or equal to arr[1]. arr[3] is also the largest value, but 2 is a smaller index, so we can only jump to i = 2 and not i = 3
     * During our 3rd jump (odd-numbered), we jump from i = 2 to i = 3 because arr[3] is the smallest value in [arr[3], arr[4]] that is greater than or equal to arr[2].
     * We can't jump from i = 3 to i = 4, so the starting index i = 0 is not good.
     * In a similar manner, we can deduce that:
     * From starting index i = 1, we jump to i = 4, so we reach the end.
     * From starting index i = 2, we jump to i = 3, and then we can't jump anymore.
     * From starting index i = 3, we jump to i = 4, so we reach the end.
     * From starting index i = 4, we are already at the end.
     * In total, there are 3 different starting indices i = 1, i = 3, and i = 4, where we can reach the end with some
     * number of jumps.
     * Example 3:
     * <p>
     * Input: arr = [5,1,3,4,2]
     * Output: 3
     * Explanation: We can reach the end from starting indices 1, 2, and 4.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= arr.length <= 2 * 104
     * 0 <= arr[i] < 105
     */

    public int oddEvenJumps(int[] arr) {
        // Array size
        int length = arr.length;

        // Count good jumps
        int count = 1;

        // Jump possibilities table. Init false for All
        boolean[] canJumpToLargest = new boolean[length];
        boolean[] canJumpToSmaller = new boolean[length];

        // Define a Map to store already visited elements and its indexes
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // Configure last element
        map.put(arr[length - 1], length - 1);
        canJumpToLargest[length - 1] = true;
        canJumpToSmaller[length - 1] = true;


        // Traverse the array and calculates possibilities backward.
        for (int i = length - 2; i >= 0; --i) {
            // Current value
            Integer element = arr[i];


            /*
            * Approach is, from last to first, if some smaller index element is able to reach me, he's only going to ask me 2 things, can I reach a smaller element, or can I reach a larger element.
            * That's where this DP is going to help me.
            * */

            // Calculate possibility to Jump to the Largest element
            Map.Entry<Integer, Integer> largestElement = map.ceilingEntry(element);
            if (largestElement != null)
                canJumpToLargest[i] = canJumpToSmaller[largestElement.getValue()];

            // Calculate possibility to Jump to a Smaller element
            Map.Entry<Integer, Integer> smallerElement = map.floorEntry(element);
            if (smallerElement != null)
                canJumpToSmaller[i] = canJumpToLargest[smallerElement.getValue()];

            // If I'm the starting index, can I reach the end? that's why only canJumpToLargest is needed here.
            if (canJumpToLargest[i])
                count++;

            map.put(element, i);
        }

        return count;

    }

    public static void main(String[] args) {
        LeetCode975_Odd_Even_Jump obj = new LeetCode975_Odd_Even_Jump();
        System.out.println(obj.oddEvenJumps(new int[]{10, 13, 12, 14, 15}));
    }
}
