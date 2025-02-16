package leetcode.problems.google.medium.binarySearch;

public class KokoEatingBananas {

    public static void main(String[] args) {
        int a[] = new int[]{312884470};
        System.out.println(new KokoEatingBananas().minEatingSpeed(a, 312884469));
    }

    public int minEatingSpeed(int[] piles, int h) {
        int min = 1, max = Integer.MIN_VALUE;
        for(int a: piles) {
            if(a < min) {
                min = a;
            }
            if(a > max) max = a;
        }

        int left = 1, right = max;
        int ans = Integer.MAX_VALUE;
        while(left <= right) {
            int mid = (left + right)/2;
            if(isPossible(piles, mid, h)) {
                if(mid < ans)
                    ans = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        return ans;
    }

    private boolean isPossible(int a[], int k, int h) {
        int t = 0;
        for(int x: a) {
            int z = x/k;
            if(z*k != x) z++;
            t += z;
            if(t > h) return false;
        }

        return true;
    }
}
