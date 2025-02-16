package leetcode.problems.google.medium.monotonic_stack;


import java.util.Arrays;
import java.util.Random;

public class MaximumChunksToMakeSorted {

    /*
    *
    * You are given an integer array arr of length n that represents a permutation of the integers in the range [0, n - 1].

We split arr into some number of chunks (i.e., partitions), and individually sort each chunk. After concatenating them, the result should equal the sorted array.

Return the largest number of chunks we can make to sort the array.
    *
    * */

/*
    Example 1:

    Input: arr = [4,3,2,1,0]
    Output: 1
    Explanation:
    Splitting into two or more chunks will not return the required result.
    For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
    Example 2:

    Input: arr = [1,0,2,3,4]
    Output: 4
    Explanation:
    We can split into two chunks, such as [1, 0], [2, 3, 4].
    However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
*/


    class Solution {
        public int maxChunksToSorted(int[] arr) {
            int max = 0; int ans = 0; int n = arr.length;
            for(int i=0; i<n; i++){
                if(max<arr[i]){
                    max=arr[i];
                }
                if(max==i){
                    ans++;
                }
            }
            return ans;
        }
    }

    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        if(n == 0) return 0;
        int[] minFromLast = new int[n+1];
        minFromLast[n] = Integer.MAX_VALUE;
        minFromLast[n-1] = n-1;
        for(int j = n-2; j>=0; j--) {
            if(arr[j] < arr[minFromLast[j+1]]) {
                minFromLast[j] = j;
            } else {
                minFromLast[j] = minFromLast[j+1];
            }
        }
        int[] maxFromBeginnning = new int[n+1];
        maxFromBeginnning[n] = Integer.MIN_VALUE;
        maxFromBeginnning[0] = 0;
        for(int i= 1; i<n; i++) {
            if(arr[i] > arr[maxFromBeginnning[i-1]])
                maxFromBeginnning[i] = i;
            else maxFromBeginnning[i] = maxFromBeginnning[i-1];
        }
        int i= 0;
        int ans = 0;
        while(i<n) {
            int j = i;
            while(minFromLast[j+1]<n && !(arr[maxFromBeginnning[j]] <= arr[minFromLast[j+1]])) j++;
            ans++;
            i = j+1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(10);
        System.out.println(maxChunksToSorted(new int[]{11, 22, 12,12,4,5,55,23,33, 56, 98}));
    }

    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] randomArray = new int[size];

        for (int i = 0; i < size; i++) {
            randomArray[i] = random.nextInt(100); // generates random number between 0 and 99
        }

        return randomArray;
    }
}
