package leetcode.problems.google.medium.depth_first_search.diff.much_asked;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Solution {
    int count = 0;
    int k;
    HashMap<Long, Integer> h = new HashMap();

    public void preorder(TreeNode node, long currSum) {
        if (node == null)
            return;

        // The current prefix sum
        currSum += node.val;

        // Here is the sum we're looking for
        if (currSum == k)
            count++;

        // The number of times the curr_sum − k has occurred already,
        // determines the number of times a path with sum k
        // has occurred up to the current node
        count += h.getOrDefault(currSum - k, 0);

        //Add the current sum into the hashmap
        // to use it during the child node's processing
        h.put(currSum, h.getOrDefault(currSum, 0) + 1);

        // Process the left subtree
        preorder(node.left, currSum);

        // Process the right subtree
        preorder(node.right, currSum);

        // Remove the current sum from the hashmap
        // in order not to use it during
        // the parallel subtree processing
        h.put(currSum, h.get(currSum) - 1);
    }

    public int pathSum(TreeNode root, int sum) {
        k = sum;
        preorder(root, 0L);
        return count;
    }
}


public class PathSum3 {


     private static void addSumToMap(HashMap<Integer, HashSet<Integer>> map, int targetSum, int index) {
         HashSet<Integer> set = map.getOrDefault(targetSum, new HashSet<>());
         set.add(index);
         map.put(targetSum, set);
     }

     private static void removeSumFromMap(HashMap<Integer, HashSet<Integer>> map, int targetSum, int index) {
         HashSet<Integer> set = map.get(targetSum);
         if(set == null) return;
         if(set.size() == 1) {
             map.remove(targetSum);
             return;
         }
         set.remove(index);
     }

     private static List<List<Integer>> getTargetLists(HashMap<Integer, HashSet<Integer>> map, List<Integer> path, int index, int targetSum) {
         HashSet<Integer> set = map.get(targetSum);
         List<List<Integer>> ans = new ArrayList<>();
         for(int x: set) {
             if(x == -1) {
                 if(targetSum == 0){
                     x = 0;
                 }else x= index;
             }
             List<Integer> list = path.subList(x, index+1);
             ans.add(list);
         }
         return ans;
     }

//    public static void main(String[] args) {
//        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
//        int[] arr = new int[]{2, 1, 22, 3, -3, 1,22, 23, 53};
//        int sum = 0;
//        int targetSum = 23;
//        List<List<Integer>> list = new ArrayList<>();
//        ArrayList<Integer> path = new ArrayList<>();
//        HashSet<Integer> set = new HashSet<>();
//        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
//        int prefixSum = 0;
//        int k = 3;
//        // Initialize prefix sum map with 0 sum at index -1
//        prefixSumMap.put(0, -1);
//
//        for (int i = 0; i < arr.length; i++) {
//            prefixSum += arr[i];
//
//            // If there exists a subarray with sum k, print it
//            if (prefixSumMap.containsKey(prefixSum - k)) {
//                int startIndex = prefixSumMap.get(prefixSum - k) + 1;
//                printArray(arr, startIndex, i);
//            }
//
//            // Store the current prefix sum and its index
//            prefixSumMap.put(prefixSum, i);
//        }
//        set.add(-1);
//        map.put(0, set);
//        for (int i = 0; i < arr.length; i++) {
//            int x = arr[i];
//            int diff = targetSum - x;
//            path.add(x);
//            sum +=x;
//            addSumToMap(map, sum, i);
//            if(map.containsKey(diff)) {
//                List<List<Integer>> data;
//                if(sum == targetSum) {
//                    data = getTargetLists(map, path, i, 0);
//                } else {
//                    data = getTargetLists(map, path, i, diff);
//                }
//                System.out.println(data);
//            }
//        }
//    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 22, 3, -3, -1,-22, 23, 53};
        int k = 23;
        printSubarraysWithSumK(arr, k);
    }

    public static void printSubarraysWithSumK(int[] arr, int k) {
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        int prefixSum = 0;

        // Initialize prefix sum map with 0 sum at index -1
        prefixSumMap.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            // If the current prefix sum equals k, print the subarray
            if (prefixSum == k) {
                printArray(arr, 0, i);
            }

            // If there exists a subarray with sum k, print it
            if (prefixSumMap.containsKey(prefixSum - k)) {
                int startIndex = prefixSumMap.get(prefixSum - k) + 1;
                int endIndex = i;
                printArray(arr, startIndex, endIndex);
            }

            // Store the current prefix sum and its index
            prefixSumMap.putIfAbsent(prefixSum, i);
        }
    }

    // Helper method to print an array from start index to end index
    public static void printArray(int[] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
