package leetcode.problems.google.hard.dp;

public class TrappingRainWater {

    class Solution {
        public int trap(int[] height) {
            int left[] = new int[height.length];
            int right[] = new int[height.length];
            left[0] = height[0];
            right[right.length-1] = height[height.length-1];
            int n = right.length;
            int max = height[0];
            for(int i = 1; i<height.length; i++) {
                if(max < height[i]) {
                    max = height[i];
                }
                left[i] = max;
            }
            max = height[n-1];
            for(int i = n-2; i>=0; i--) {
                if(max < height[i]) {
                    max = height[i];
                }
                right[i] = max;
            }
            int sum = 0;
            for(int i = 1; i<n-1; i++) {
                int min = Math.min(left[i-1], right[i+1]);
                if(height[i] < min) {
                    sum += min - height[i];
                }
            }
            return sum;
        }
    }

}
