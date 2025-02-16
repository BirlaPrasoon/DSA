package leetcode.problems.google.easy.hashing;

import java.util.HashSet;

public class SubarrayWithSum0 {
    static boolean findsum(int arr[],int n)
    {
        //Your code here
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        int sum = 0;
        for(int a: arr) {
            sum+=a;
            if(set.contains(sum)) return true;
            set.add(sum);
        }
        return false;
    }

    public static void main(String[] args) {
        int a[] = {1,-1};
        System.out.println(findsum(a, a.length));
    }
}
