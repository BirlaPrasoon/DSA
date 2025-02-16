package leetcode.problems.google.hard.twoPointer;

import java.util.*;

public class MaximumSumSubarrayWithSumLessThanK {


    // O(NLogN)
    class SolutionSortingPrefixSum {

        // Function to maximum required sum < K
        static int maxSubarraySum(int arr[], int N, int K) {

            // Hash to lookup for value (cum_sum - K)
            Set<Integer> cum_set = new HashSet<>();
            cum_set.add(0);

            int max_sum = Integer.MIN_VALUE, cSum = 0;

            for (int i = 0; i < N; i++) {

                // Getting cumulative sum from [0 to i]
                cSum += arr[i];

                // Lookup for upperbound
                // of (cSum-K) in hash
                ArrayList<Integer> al = new ArrayList<>();
                Iterator<Integer> it = cum_set.iterator();
                int end = 0;

                while (it.hasNext()) {
                    end = it.next();
                    al.add(end);
                }

                Collections.sort(al);
                int sit = lower_bound(al, cSum - K);

                // Check if upper_bound
                // of (cSum-K) exists
                // then update max sum
                if (sit != end) max_sum = Math.max(max_sum, cSum - sit);

                // Insert cumulative value in hash
                cum_set.add(cSum);
            }

            // Return maximum sum
            // lesser than K
            return max_sum;
        }

        static int lower_bound(ArrayList<Integer> al, int x) {

            // x is the target value or key
            int l = -1, r = al.size();
            while (l + 1 < r) {
                int m = (l + r) >>> 1;
                if (al.get(m) >= x) r = m;
                else l = m;
            }
            return r;
        }

        // Driver code
        public static void main(String args[]) {

            // Initialise the array
            int arr[] = {5, -2, 6, 3, -5};

            // Initialise the value of K
            int K = 15;

            // Size of array
            int N = arr.length;

            System.out.println(maxSubarraySum(arr, N, K));
        }
    }

    // O(N^2)
    public class SolutionNaive {
        // To find subarray with maximum sum
        // less than or equal to sum
        static int findMaxSubarraySum(int[] arr, int n, int sum) {
            int result = 0;

            for (int i = 0; i < n; i++) {
                int currSum = 0;
                for (int j = i; j < n; j++) {
                    currSum += arr[j];

                    if (currSum < sum) {
                        result = Math.max(result, currSum);
                    }
                }
            }
            return result;
        }

        public static void main(String[] args) {
            int[] arr = {6, 8, 9};
            int n = arr.length;
            int sum = 20;

            System.out.println(findMaxSubarraySum(arr, n, sum));
        }
    }

    // O(N)
    // Java program to find subarray having
// maximum sum less than or equal to sum
    public class SolutionTwoPointers {

        // To find subarray with maximum sum
        // less than or equal to sum
        static int findMaxSubarraySum(int arr[], int n, int sum) {
            // To store current sum and
            // max sum of subarrays
            int curr_sum = arr[0], max_sum = 0, start = 0;

            // To find max_sum less than sum
            for (int i = 1; i < n; i++) {

                // Update max_sum if it becomes
                // greater than curr_sum
                if (curr_sum <= sum) max_sum = Math.max(max_sum, curr_sum);

                // If curr_sum becomes greater than
                // sum subtract starting elements of array
                while (curr_sum + arr[i] > sum && start < i) {
                    curr_sum -= arr[start];
                    start++;
                }

                // Add elements to curr_sum
                curr_sum += arr[i];
            }

            // Adding an extra check for last subarray
            if (curr_sum <= sum) max_sum = Math.max(max_sum, curr_sum);

            return max_sum;
        }

        // Driver program to test above function
        public static void main(String[] args) {
            int arr[] = {6, 8, 9};
            int n = arr.length;
            int sum = 20;

            System.out.println(findMaxSubarraySum(arr, n, sum));
        }
    }


}
