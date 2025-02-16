package leetcode.problems.google.easy.search;

import java.util.Arrays;
import java.util.PriorityQueue;

public class RotatedSortedArray {
    public int search(int[] a, int target) {
        int pivot = -1;
        for(int i = 1; i<a.length-1; i++) {
            if(a[i-1] > a[i] && a[i+1] > a[i]) pivot = i;
        }
        int n = a.length;
        int left = 0, right = n-1;
        if(pivot != -1) {
            left = pivot;
            right = pivot + n-1;
        }
        while(left <= right) {
            int mid = (left + right )/2;
            System.out.println(left + " : " + right + " mid: " + mid );
            if(a[mid%n] == target) return mid%n;
            if(a[mid%n] < target) left = mid+1;
            else right = mid-1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int a[] = {3,1};
        RotatedSortedArray m = new RotatedSortedArray();

        System.out.println(m.binarySearch(a, 1));
    }

    private int binarySearch(int[] a, int target) {
        int pivot = -1;
        for(int i = 1; i<a.length; i++) {
            if(a[i-1] > a[i]) pivot = i;
        }
        int n = a.length;
        int left = 0, right = n-1;
        if(pivot != -1) {
            left = pivot;
            right = pivot + n-1;
        }
        while(left <= right) {
            int mid = (left + right )/2;
            System.out.println(left + " : " + right + " mid: " + mid );
            if(a[mid%n] == target) return mid%n;
            if(a[mid%n] < target) left = mid+1;
            else right = mid-1;
        }
        return -1;
    }
}
