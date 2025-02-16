package leetcode.problems.google.hard.dp;

public class IntegerKnapsack {

    static class GFGSpaceOptimized {
        static int knapSack(int W, int[] wt, int[] val, int n) {
            int[] dp = new int[W + 1];
            for (int i = 1; i < n + 1; i++) {
                for (int w = W; w >= 0; w--) {
                    if (wt[i - 1] <= w)
                        dp[w] = Math.max(
                                dp[w],
                                dp[w - wt[i - 1]] + val[i - 1]
                        );
                }
            }
            return dp[W];
        }

        // Driver code
        public static void main(String[] args) {
            int[] profit = {60, 100, 120};
            int[] weight = {10, 20, 30};
            int W = 50;
            int n = profit.length;
            System.out.print(knapSack(W, weight, profit, n));
        }
    }


    // Java program for the above approach

    class GFG {

        // Returns the value of maximum profit
        static int knapSackRec(int W, int[] wt, int[] val, int index, int[][] dp) {

            // Base condition
            if (index == 0 || W == 0) return 0;

            if (dp[index][W] != -1) return dp[index][W];

            if (wt[index - 1] > W)

                // Store the value of function call
                // stack in table before return
                return dp[index][W] = knapSackRec(W, wt, val, index - 1, dp);

            else

                // Return value of table after storing
                return dp[index][W] = Math.max(
                        (val[index - 1] + knapSackRec(W - wt[index - 1], wt, val, index - 1, dp)),
                        knapSackRec(W, wt, val, index - 1, dp)
                );
        }

        static int knapSack(int W, int[] wt, int[] val, int N) {

            // Declare the table dynamically
            int[][] dp = new int[N + 1][W + 1];

            // Loop to initially filled the
            // table with -1
            for (int i = 0; i < N + 1; i++)
                for (int j = 0; j < W + 1; j++)
                    dp[i][j] = -1;

            return knapSackRec(W, wt, val, N, dp);
        }

        // Driver Code
        public static void main(String[] args) {
            int[] profit = {60, 100, 120};
            int[] weight = {10, 20, 30};

            int W = 50;
            int N = profit.length;

            System.out.println(knapSack(W, weight, profit, N));
        }
    }

// This code is contributed by gauravrajput1

}
