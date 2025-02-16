package leetcode.problems.google.medium.monotonic_stack.different;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class SumOfSubarrayMinimums {

    /*
    *
    * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.



Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation:
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444

    *
    * */


    class Solution {
        public int sumSubarrayMins(int[] arr) {
            long res = 0;
            Stack<Integer> stack = new Stack<Integer>();
            long M = (long)1e9 + 7;
            stack.push(-1);

            for (int rightEnd = 0; rightEnd < arr.length+1; rightEnd++){
                int currVal = (rightEnd<arr.length)? arr[rightEnd] : 0;

                while(stack.peek() !=-1 && currVal < arr[stack.peek()]){
                    int index = stack.pop();
                    int previousSmallerOrEqualIndex = stack.peek();
                    int numElementsOnLeftToBeIncluded = index - previousSmallerOrEqualIndex;
                    int numElementsOnRightToBeIncluded = rightEnd - index;
                    long add = (numElementsOnLeftToBeIncluded * numElementsOnRightToBeIncluded * (long)arr[index]) % M;
                    res += add ;
                    res %= M;
                }

                stack.push(rightEnd);

            }

            return (int)res;

        }
    }
    class SolutionNSE_PSEE {
        public int sumSubarrayMins(int[] arr) {
            int mod = 1000000007;
            int len = arr.length;

            int[] preSmaller = new int[len];
            int[] nextSmaller = new int[len];

            Stack<Integer> s = new Stack<>();

            for (int i = 0; i < len; i++){
                preSmaller[i] = -1;
                nextSmaller[i] = len;
            }

            for (int i = 0; i < len; i++){
                while(!s.isEmpty() && arr[s.peek()] >= arr[i]){
                    int index= s.pop();
                    nextSmaller[index] = i;
                }
                s.push(i);
            }
            s.clear();

            for (int i = len-1; i >= 0; i--){
                while(!s.isEmpty() && arr[s.peek()] > arr[i]){
                    int index= s.pop();
                    preSmaller[index] = i;
                }
                s.push(i);
            }
            s.clear();

            long res = 0;
            for (int i = 0; i < len; i++){
                int left= i -preSmaller[i];
                int right=nextSmaller[i]-i;
                res=(res+ ((long)arr[i]*left*right));
            }
            return (int)res%mod;
        }
    }

}
