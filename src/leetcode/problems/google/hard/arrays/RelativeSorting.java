package leetcode.problems.google.hard.arrays;

import java.util.*;

public class RelativeSorting {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> nonExisting = new ArrayList<>();
        HashSet<Integer>set = new HashSet<>();
        for(int a: arr2) {
            set.add(a);
        }
        for(int a: arr1) {
            map.put(a, map.getOrDefault(a, 0)+1);
            if(!set.contains(a)) {
                nonExisting.add(a);
            }
        }
        System.out.println(map);
        for(int a: arr2) {
            int freq = map.getOrDefault(a, 0);
            while(freq>0) {
                list.add(a);
                freq--;
            }
        }
        Collections.sort(nonExisting);
        list.addAll(nonExisting);

        return list.stream().mapToInt(i->i).toArray();
    }

    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        // Find the maximum value in arr1 to limit the size of the frequency array
        int max = 0;
        for (int num : arr1) {
            max = Math.max(max, num);
        }

        // Frequency array to count occurrences of elements in arr1
        int[] count = new int[max + 1];
        for (int num : arr1) {
            count[num]++;
        }

        int[] res = new int[arr1.length];
        int index = 0;

        // Process elements of arr2 first
        for (int num : arr2) {
            while (count[num] > 0) {
                res[index++] = num;
                count[num]--;
            }
        }

        // Process the remaining elements (those not in arr2)
        for (int num = 0; num <= max; num++) {
            while (count[num] > 0) {
                res[index++] = num;
                count[num]--;
            }
        }

        return res;
    }
}
