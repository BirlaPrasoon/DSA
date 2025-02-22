package leetcode.problems.google.hard.greedy.jumpGame;

public class JumpGame {


//    Greedy O(n)

    boolean canJump(int[] nums) {
        int goal = nums.length - 1;
        for(int i = nums.length-1;  i>=0; i--) {
            if(i+ nums[i] >= goal){
                goal = i;
            }
        }
        return goal == 0 ? true : false;
    }
}
