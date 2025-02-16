package leetcode.problems.google.medium.arrays;

import java.util.Arrays;

public class ValidTriangleNumber {

    // Linear Scan
    public int triangleNumberLinearScan(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            // Zero case is very, very important, we need to skip every 0 from our answer.
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k]) k++;
                count += k - j - 1;
            }
        }
        return count;
    }


    public int triangleNumberBinarySearch(int[] nums) {
        Arrays.sort(nums);
        if(nums.length <=2) return 0;
        int n = nums.length;
        int cnt = 0;
        for(int i = 0; i<n; i++) {
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                int k = binarySearch(nums, j+1, nums.length - 1, nums[i] + nums[j]);
                cnt += k - j - 1;
            }
        }
        return cnt;
    }

    int binarySearch(int nums[], int l, int r, int x) {
        while (r >= l && r < nums.length) {
            int mid = (l + r) / 2;
            if (nums[mid] >= x)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        ValidTriangleNumber validTriangleNumber = new ValidTriangleNumber();
        System.out.println(validTriangleNumber.triangleNumberBinarySearch(new int[]{4,2,3,4}));
    }
}
