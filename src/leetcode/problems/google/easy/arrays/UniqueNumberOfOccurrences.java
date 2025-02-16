package leetcode.problems.google.easy.arrays;

import java.util.Arrays;public class UniqueNumberOfOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        int SIZE=11;
        boolean freq[] = new boolean[SIZE];
        int neg[] = new int[SIZE];
        int pos[] = new int[SIZE];
        for(int a: arr) {
            if(a<0) {
                neg[-a]++;
            } else {
                pos[a]++;
            }
        }
        for(int n: neg) {
            if(n!= 0 && freq[n]) return false;
            freq[n] = true;
        }
        for(int p: pos) {
            if(p != 0 && freq[p]) return false;
            freq[p] = true;
        }

        System.out.println(Arrays.toString(freq));
        return true;
    }

    public static void main(String[] args) {
        int a[] ={1,2,2,1,1,3};
        System.out.println(new UniqueNumberOfOccurrences().uniqueOccurrences(a));
    }
}
