package leetcode.problems.google.easy.hashing;

import java.util.HashMap;

public class LenOfLongestSubarrayWithGivenSum {
    public static int lenOfLongSubarr(int A[], int N, int K) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int curSum = 0;
        int maxLen = 0;
        for(int i = 0; i<N; i++) {
            curSum += A[i];
            int target = curSum - K;
            if(map.containsKey(target)) {
                maxLen = Math.max(maxLen, i - map.get(target));
            }
            if (!map.containsKey(curSum)) {
                map.put(curSum, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int a[] = {-13, 0, 6, 15, 16, 2, 15, -12, 17, -16, 0, -3, 19, -3, 2, -9, -6};
        System.out.println(lenOfLongSubarr(a, a.length, 15));
    }
}
