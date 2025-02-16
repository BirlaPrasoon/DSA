package leetcode.problems.google.easy.hashing;

import java.util.Arrays;
import java.util.HashMap;

public class WinnerOfAnElection {

    public static String[] winner(String arr[], int n)
    {
        HashMap<String, Integer> map = new HashMap<>();
        int maxFreq = 0;
        String ans = "";
        for(String s: arr) {
            map.put(s, map.getOrDefault(s, 0)+1);
            int freq = map.get(s);
            if(freq == maxFreq) {
                if(ans.isEmpty()) {
                    ans = s;
                } else {
                    if(s.compareTo(ans) < 0) {
                        ans =s;
                    }
                }
            }
            if(freq > maxFreq) {
                maxFreq = freq;
                ans = s;
            }
        }
        return new String[]{ans, Integer.toString(maxFreq)};
    }

    public static void main(String[] args) {
        String arr[] = {};
        System.out.println(Arrays.toString(winner(arr, arr.length)));
    }
}
