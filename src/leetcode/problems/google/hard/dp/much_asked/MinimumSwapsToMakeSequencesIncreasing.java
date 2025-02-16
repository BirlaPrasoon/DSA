package leetcode.problems.google.hard.dp.much_asked;

import java.util.Arrays;

public class MinimumSwapsToMakeSequencesIncreasing {

    class SolutionGreedy {
        public int minSwap(int[] A, int[] B) {
            int swapRecord = 1, fixRecord = 0;
            for (int i = 1; i < A.length; i++) {
                if (A[i - 1] >= B[i] || B[i - 1] >= A[i]) {
                    swapRecord++;
                } else if (A[i - 1] >= A[i] || B[i - 1] >= B[i]) {
                    int temp = swapRecord;
                    swapRecord = fixRecord + 1;
                    fixRecord = temp;
                } else {
                    int min = Math.min(swapRecord, fixRecord);
                    swapRecord = min + 1;
                    fixRecord = min;
                }
            }
            return Math.min(swapRecord, fixRecord);
        }
    }

    public int minSwap(int[] nums1, int[] nums2) {

        int dp[] = new int[nums1.length];
        Arrays.fill(dp, -1);

        int ans = solve(nums1, nums2, 0, dp);

        return ans;
    }

    public int solve(int nums1[], int nums2[], int ind, int[] dp) {

        if (ind == nums1.length) return 0;
        if(dp[ind] != -1) return dp[ind];

        /*
        Condition 1 - Ok lets suppose we have two arrays as follows :-
        nums1[] = [3,2]
        nums2[] = [1,4]
        and we are at index 1.we can see that for nums1 we have nums1[ind-1]>= nums1[ind],
        So for this condition we have only one option i,e we have to swap the elements so that array can be made strictly increasing.
        So we will swap elements and recursively call next functions.


        *
        * */

        if (ind > 0 && (nums1[ind - 1] >= nums1[ind] || nums2[ind - 1] >= nums2[ind])) {

            //	nums1[] = [3,2]
            //	nums2[] = [1,4]

            int t = nums1[ind];
            nums1[ind] = nums2[ind];
            nums2[ind] = t;

            int val = 1 + solve(nums1, nums2, ind + 1, dp);

            // Since after swapping array becomes
            // nums1[] = [3,4]
            // nums2[] = [1,2]
            // therefore we have to swap it back so that we can have our original array.

            t = nums1[ind];
            nums1[ind] = nums2[ind];
            nums2[ind] = t;

            return val;

        }
        // Condition 2
        /*
        * Condition 2- lets take another example:-
            nums1[] = {1,2}
            nums2[] = {3,4}
            index = 1;

            if we do swapping of the current element then our array will be nums1[] = {1,4} and nums2[] = {3,2}.
            * We can see that the elements in nums2 is not in increasing order.
        * */

        else if (ind > 0 && (nums1[ind - 1] >= nums2[ind] || nums2[ind - 1] >= nums1[ind])) {
            return solve(nums1, nums2, ind + 1, dp);
        }
        // Condition 3
/*
* Condition 3 -
Now lets take another example
nums1[] = {1,5}
nums2[] = {3,4}
index = 1;

Here we have two options either we swap the current element or we do not swap.
* So here we will take the minimum answer of both the option and return the answer.
* */
        else {
            if (dp[ind] != -1) return dp[ind];

            int tempAns1 = solve(nums1, nums2, ind + 1, dp);

            int t = nums1[ind];
            nums1[ind] = nums2[ind];
            nums2[ind] = t;

            int tempAns2 = 1 + solve(nums1, nums2, ind + 1, dp);

            t = nums1[ind];
            nums1[ind] = nums2[ind];
            nums2[ind] = t;

            return dp[ind] = Math.min(tempAns1, tempAns2);
        }

    }


}
