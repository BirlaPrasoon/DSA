package leetcode.problems.google.hard.greedy.jumpGame;

public class JumpGame5 {

    /*
    *
    * Given an array of integers arr and an integer d. In one step you can jump from index i to index:

i + x where: i + x < arr.length and  0 < x <= d.
i - x where: i - x >= 0 and  0 < x <= d.
In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).

You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.

Notice that you can not jump outside of the array at any time.
    * */

    class Solution {
        public int maxJumps(int[] arr, int d) {
            int[] maxJumpsFromHere = new int[arr.length];
            int maxJumps = 0;

            for (int i=0; i<arr.length; i++) {
                maxJumps = Math.max(maxJumps, calMaxJumps(i, maxJumpsFromHere, arr, d));
            }
            return maxJumps;
        }

        private int calMaxJumps(int i, int[] maxJumpsFromHere, int[] arr, int d) {
            if (maxJumpsFromHere[i] != 0)
                return maxJumpsFromHere[i];
            int maxJumpsCount = 0;
            for (int x = i+1; x <= (i+d) && x < arr.length; x++) {
                if (arr[x] >= arr[i])
                    break;
                maxJumpsCount = Math.max(maxJumpsCount, calMaxJumps(x, maxJumpsFromHere, arr, d));
            }
            for (int x = i-1; x >= (i-d) && x >= 0; x--) {
                if (arr[x] >= arr[i])
                    break;
                maxJumpsCount = Math.max(maxJumpsCount, calMaxJumps(x, maxJumpsFromHere, arr, d));
            }
            maxJumpsFromHere[i] = 1 + maxJumpsCount;
            return maxJumpsFromHere[i];
        }
    }

}
