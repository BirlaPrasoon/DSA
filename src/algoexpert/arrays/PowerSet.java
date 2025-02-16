package algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSet {
    public static List<List<Integer>> powerset(List<Integer> array) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        helper(array, path, ans, 0);
        return ans;
    }
    private static void helper(List<Integer> list, List<Integer> path, List<List<Integer>> ans,  int index) {
        if(index > list.size()) return;
        if(index == list.size()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        helper(list, path, ans, index+1);
        path.add(list.get(index));
        helper(list, path, ans, index+1);
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(11, 15, 14, 2));
        System.out.println(powerset(list));
    }
}
