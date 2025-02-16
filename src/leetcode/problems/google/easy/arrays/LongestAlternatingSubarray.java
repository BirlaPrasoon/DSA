package leetcode.problems.google.easy.arrays;

public class LongestAlternatingSubarray {
    public int alternatingSubarray(int[] nums) {
        int min = -1;
        int prev = 0;
        int curLen = 1;
        for(int i = 1; i<nums.length; i++) {
            int cur = nums[i-1] - nums[i];
            if(prev == -1 && cur == 1) {
                curLen++;
                prev = 1;
            } else if(prev == 1 && cur == -1) {
                curLen++;
                prev = -1;
            } else {
                if(curLen > min && curLen >= 2) {
                    min = curLen;
                }
                curLen = 1;
                prev = 0;
                if(cur == -1) {
                    curLen = 2;
                    prev = -1;
                }
            }
        }
        if(curLen > min && curLen >= 2) {
            min = curLen;
        }
        return min;
    }
    public static void main(String[] args) {
        LongestAlternatingSubarray l = new LongestAlternatingSubarray();
        System.out.println(l.alternatingSubarray(new int[]{2,3,2}));
    }
}
