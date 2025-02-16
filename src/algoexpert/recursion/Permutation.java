package algoexpert.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permutation {

    public static void swap(List<Integer>a, int i, int j) {
        int x = a.get(i);
        a.set(i, a.get(j));
        a.set(j, x);
    }
    public static void permutate(List<Integer> a, int l, int r, List<List<Integer>> res) {
        if(l == r){
            res.add(new ArrayList<>(a));
            return;
        }
        for(int i = l; i<r; i++) {
            swap(a, l, i);
            permutate(a, l+1, r, res);
            swap(a, l, i);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<List<Integer>> res = new ArrayList<>();
        permutate(a, 0, a.size(), res);
        System.out.println(res);
    }
}
