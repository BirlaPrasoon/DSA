package leetcode.problems.google.medium.monotonic_stack.different;

import java.util.Stack;

public class StepsToMakeArrayNonDecreasing {

/*
    You are given a 0-indexed integer array nums. In one step, remove all elements nums[i] where nums[i - 1] > nums[i] for all 0 < i < nums.length.

    Return the number of steps performed until nums becomes a non-decreasing array.



    Example 1:

    Input: nums = [5,3,4,4,7,3,6,11,8,5,11]
    Output: 3
    Explanation: The following are the steps performed:
            - Step 1: [5,3,4,4,7,3,6,11,8,5,11] becomes [5,4,4,7,6,11,11]
            - Step 2: [5,4,4,7,6,11,11] becomes [5,4,7,11,11]
            - Step 3: [5,4,7,11,11] becomes [5,7,11,11]
            [5,7,11,11] is a non-decreasing array. Therefore, we return 3.
    Example 2:

    Input: nums = [4,5,7,7,13]
    Output: 0
    Explanation: nums is already a non-decreasing array. Therefore, we return 0.
*/

    class Solution {
        /*
        1. The stack helps us to figure out for each element, which is the first strictly greater element present to the left of it.

        2. It is because of this element that the current element will be removed.

        3. Once that is figured out we need to figure how many steps have passed when both these elements become adjacent to each other.
            In order to figure that out, whenever we perform the popping operation on stack we maintain a variable to record
            the max. steps that have passed in order for all intermedate elements to get removed,so that the current element and
            the strictly greater element have become adjacent.

        4. The number of steps reqd. for this element to get removed would be one greater than the max. of all the intermediate elements' steps.

        5. Maintain all the steps in an array called `step_arr`. Max of all the steps would be the answer.

        6. Do comment or upvote if you stumble upon this discussion_thread.

*/

        public int totalSteps(int[] nums) {
            Stack<Integer> st = new Stack<>();
            st.push(0);
            int steps=0;
            int[] step_arr = new int[nums.length];
            for(int i=1;i<nums.length;i++){
                int max_steps = 0;
                while(!st.empty() && nums[i]>=nums[st.peek()]){
                    max_steps=Math.max(max_steps,step_arr[st.peek()]);
                    st.pop();
                }
                if(!st.empty() && nums[i]<nums[st.peek()]){
                    step_arr[i]=1+max_steps;
                }
                steps = Math.max(steps,step_arr[i]);
                st.push(i);
            }
            return steps;
        }
    }

}
