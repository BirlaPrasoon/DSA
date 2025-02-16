package leetcode.problems.google.medium.binarySearch;

public class FirstAndLastIndex {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1, -1};
        if(nums.length == 1 && nums[0] != target) return  new int[]{-1, -1};
        if(nums.length == 1) return new int[]{0,0};
        int first = binarySearch(nums, target, true);
        int second = binarySearch(nums, target, false);
        return new int[]{first, second};
    }

    private int binarySearch(int nums[], int target, boolean first) {
        int left = 0, right = nums.length-1;
        while(left<= right) {
            int mid = (left + right)/2;
            if(nums[mid] == target) {
                if(!first){
                    if(mid == 0 && nums[mid+1] > target) return mid;
                    if(mid == nums.length-1) return mid;
                    if(nums[mid] < nums[mid+1]) return mid;
                    left = mid+1;
                } else {
                    if(mid == 0) return 0;
                    if(mid == nums.length-1 && nums[mid-1] < target) return mid;
                    if(nums[mid-1] < nums[mid]) return mid;
                    right = mid-1;
                }
            }else if(nums[mid] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return -1;
    }
}
