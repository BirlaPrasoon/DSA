package leetcode.problems.google.medium.binarySearch;

import java.util.Arrays;

public class HouseRobber4 {
    public int minCapability(int[] A, int k) {
        int max = Arrays.stream(A).max().getAsInt();
        int left = 1, right = max, n = A.length;
        while (left < right) {
            int mid = (left + right) / 2, take = 0;
            take = howManyPossible(A, n, mid, take);
            if (take >= k)
                right = mid;
            else
                left = mid + 1;
        }
        return left; //left == right
    }

    private static int howManyPossible(int[] A, int n, int mid, int take) {
        for (int i = 0; i < n; ++i)
            if (A[i] <= mid) {
                take += 1;
                i++;
            }
        return take;
    }
}
