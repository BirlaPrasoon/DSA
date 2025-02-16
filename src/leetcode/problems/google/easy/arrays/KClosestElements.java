package leetcode.problems.google.easy.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int pos =
                Arrays.binarySearch(arr, x);
        if (pos < 0) pos = ~pos-1;
        int i = pos-1;
        int j = pos+1;
        List<Integer> list = new ArrayList<>();
        while(k-->0) {
            if(i<0){
                list.add(arr[j++]);
            } else if(j>=arr.length-1) list.add(arr[i-0]);
            else {
                if(arr[i] > arr[j]) list.add(arr[j++]);
                else list.add(arr[i--]);
            }
        }
        return list;
    }
}
