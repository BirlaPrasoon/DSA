package leetcode.problems.google.easy.greedy;

import java.util.Arrays;

public class LargestPerimeterTriangle {

    /**
     * Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three of these lengths. If it is impossible to form any triangle of a non-zero area, return 0.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [2,1,2]
     * Output: 5
     * Explanation: You can form a triangle with three side lengths: 1, 2, and 2.
     * Example 2:
     * <p>
     * Input: nums = [1,2,1,10]
     * Output: 0
     * Explanation:
     * You cannot use the side lengths 1, 1, and 2 to form a triangle.
     * You cannot use the side lengths 1, 1, and 10 to form a triangle.
     * You cannot use the side lengths 1, 2, and 10 to form a triangle.
     * As we cannot use any three side lengths to form a triangle of non-zero area, we return 0.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 3 <= nums.length <= 10<sup>4</sup>
     * 1 <= nums[i] <= 10<sup>6</sup>
     */

    class Solution {
        /**
         * "There is no reason not to choose the largest possible A and B from the array. "
         * <p>
         * It's true, but it's not really obvious up front. For those confused why this variant is best:
         * <p>
         * C has to be greater than the sum of A and B. That said, there are two cases and reasons not to check other values (which allows us to avoid an O(N^3) solution):
         * <p>
         * If C >= A + B, then all other A and B pairs are still going to be such that C >= A + B, since all pairs A' and B' (A and B selected from indexes lower in the sorted array), since the input is now sorted, it must hold that A' <= A and B' <= B and hence A'+B' <= A+B <= C. This means that there can be no other selections of A and B less than or equal to C that can create a valid triangle. So we can just check the next A, B and C.
         * <p>
         * C < A + B. This is a valid triangle. We could check values A' and B' where A' <= A and B' <= B, but A'+B' <= A+B, so we would only find smaller triangles that can be formed with C as the longest(? -- could be C = B > A or C = B = A), which would not override the value A + B + C we already found, since we can only find smaller values A' + B' + C. Hence we can just return this value, because C' <= C will just produce smaller (or equal) triangles as well.
         * <p>
         * That's how this algorithm works, such that we do not have to check every single tuple of (A, B, C). We are then just bounded by O(n*log(n)) because of our sort.
         */
        public int largestPerimeter(int[] A) {
            Arrays.sort(A);
            for (int i = A.length - 1; i >= 2; i--) {
                if (A[i] < A[i - 1] + A[i - 2]) return A[i] + A[i - 1] + A[i - 2];
            }
            return 0;
        }
    }
}
