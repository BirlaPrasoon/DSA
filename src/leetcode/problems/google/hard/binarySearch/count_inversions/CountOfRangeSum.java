package leetcode.problems.google.hard.binarySearch.count_inversions;

public class CountOfRangeSum {

    /**
     * Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.</br></br>
     * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.</br></br>
     * Example 1:</br>
     * Input: nums = [-2,5,-1], lower = -2, upper = 2</br>
     * Output: 3</br>
     * Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.</br></br>
     * Example 2:</br>
     * Input: nums = [0], lower = 0, upper = 0</br>
     * Output: 1</br>
     * Constraints:</br></br>
     * 1 <= nums.length <= 105</br>
     * -2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</br>
     * -10<sup>5</sup> <= lower <= upper <= 10<sup>5</sup></br>
     * The answer is guaranteed to fit in a 32-bit integer.</br>
     */

    // Naive...
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        int ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j <= n; ++j) {
                long sumDiff = sums[j] - sums[i];
                if (sumDiff >= lower && sumDiff <= upper)
                    ans++;
            }
        return ans;
    }


    // Solution for countRangeSum
    class SolutionCountingInversions {
        private int lower;
        private int upper;
        private int count = 0;
        private long[] pfxSum;

        public int countRangeSum(int[] nums, int lower, int upper) {
            int n = nums.length;
            this.lower = lower;
            this.upper = upper;

            this.pfxSum = new long[n + 1];
            for (int i = 0; i < n; i++) {
                pfxSum[i + 1] = pfxSum[i] + nums[i];
            }

            mergeSort(0, n);
            return count;
        }

        private void mergeSort(int low, int high) {
            if (low >= high) return;
            int mid = low + (high - low) / 2;

            mergeSort(low, mid);
            mergeSort(mid + 1, high);

            int i = mid + 1, j = mid + 1;
            for (int k = low; k <= mid; k++) {
                while (i <= high && pfxSum[i] - pfxSum[k] < lower) i++;
                while (j <= high && pfxSum[j] - pfxSum[k] <= upper) j++;

                count += j - i;
            }

            merge(low, mid, high);
        }

        private void merge(int low, int mid, int high) {
            long[] helper = new long[high - low + 1];
            for (int i = low; i <= high; i++) {
                helper[i - low] = pfxSum[i];
            }

            int i = low, j = mid + 1;
            int idx = low;

            while (i <= mid && j <= high) {
                if (helper[i - low] < helper[j - low]) {
                    pfxSum[idx++] = helper[i++ - low];
                } else {
                    pfxSum[idx++] = helper[j++ - low];
                }
            }

            while (i <= mid) {
                pfxSum[idx++] = helper[i++ - low];
            }
        }
    }
}
