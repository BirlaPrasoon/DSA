package leetcode.problems.google.hard.backtracking;

public class MaximumNumberOfAchievableTransfers {

    class Solution {
        /**
         * We have n buildings numbered from 0 to n - 1. Each building has a number of employees. It's transfer season, and some employees want to change the building they reside in.
         * <p>
         * You are given an array requests where requests[i] = [fromi, toi] represents an employee's request to transfer from building fromi to building toi.
         * <p>
         * All buildings are full, so a list of requests is achievable only if for each building, the net change in employee transfers is zero. This means the number of employees leaving is equal to the number of employees moving in. For example if n = 3 and two employees are leaving building 0, one is leaving building 1, and one is leaving building 2, there should be two employees moving to building 0, one employee moving to building 1, and one employee moving to building 2.
         * <p>
         * Return the maximum number of achievable requests.
         * <p>
         * <p>
         * Example 1:
         * <p>
         * <p>
         * Input: n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]
         * Output: 5
         * Explantion: Let's see the requests:
         * From building 0 we have employees x and y and both want to move to building 1.
         * From building 1 we have employees a and b and they want to move to buildings 2 and 0 respectively.
         * From building 2 we have employee z and they want to move to building 0.
         * From building 3 we have employee c and they want to move to building 4.
         * From building 4 we don't have any requests.
         * We can achieve the requests of users x and b by swapping their places.
         * We can achieve the requests of users y, a and z by swapping the places in the 3 buildings.
         * Example 2:
         * <p>
         * <p>
         * Input: n = 3, requests = [[0,0],[1,2],[2,1]]
         * Output: 3
         * Explantion: Let's see the requests:
         * From building 0 we have employee x and they want to stay in the same building 0.
         * From building 1 we have employee y and they want to move to building 2.
         * From building 2 we have employee z and they want to move to building 1.
         * We can achieve all the requests.
         * Example 3:
         * <p>
         * Input: n = 4, requests = [[0,3],[3,1],[1,2],[2,0]]
         * Output: 4
         * <p>
         * <p>
         * Constraints:
         * <p>
         * 1 <= n <= 20
         * 1 <= requests.length <= 16
         * requests[i].length == 2
         * 0 <= fromi, toi < n
         */

        public int maximumRequests(int n, int[][] requests) {
            int answer = 0;
            int N = requests.length;
            int subsetSize = 1 << N;

            for (int mask = 0; mask < subsetSize; mask++) {

                // Number of set bits representing the requests we will consider.
                int bitCount = Integer.bitCount(mask);

                // If the request count we're going to consider is less than the maximum request
                // We have considered without violating the constraints; then we can return it cannot be the answer.
                if (bitCount <= answer) {
                    continue;
                }

                int[] indegree = new int[n];
                int pos = requests.length - 1;
                // For all the 1's in the number, update the array indegree for the building it involves.
                for (int curr = mask; curr > 0; curr >>= 1, pos--) {
                    if ((curr & 1) == 1) {
                        indegree[requests[pos][0]]--;
                        indegree[requests[pos][1]]++;
                    }
                }

                boolean flag = true;
                // Check if it doesn't violate the constraints
                for (int i = 0; i < n; i++) {
                    if (indegree[i] != 0) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    answer = bitCount;
                }
            }

            return answer;
        }
    }
}
