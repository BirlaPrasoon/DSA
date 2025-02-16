package leetcode.problems.google.medium.binarySearch;

public class KthMissingPositive {

    class Solution {
        public int findKthPositive(int[] arr, int k) {
            int lowerBound = lowerBound(arr, k);
            if (lowerBound == -1) return k;
            int missingTillNow = arr[lowerBound] - lowerBound - 1;
            return arr[lowerBound] + k - missingTillNow;
        }

        private int lowerBound(int[] a, int k) {
            int low = 0, high = a.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int missingTillNow = a[mid] - (mid + 1);
                if (missingTillNow < k) {
                    if (mid == a.length - 1) return a.length - 1;
                    int nextMissingCount = a[mid + 1] - (mid + 1 + 1);
                    if (nextMissingCount > k) return mid;
                    low = mid + 1;
                } else if (missingTillNow == k) {
                    if (mid == 0) return -1;
                    int prevMissing = a[mid - 1] - mid;
                    if (prevMissing < k) return mid - 1;
                    high = mid - 1;
                } else {
                    if (mid == 0) return -1;
                    high = mid - 1;
                }
            }
            return -2;
        }
    }
}
