package leetcode.problems.google.medium.binarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ShortestWordDistance2 {
    HashMap<String, ArrayList<Integer>> map;
    int n;

    public ShortestWordDistance2(String[] wordsDict) {
        map = new HashMap<>();
        for(int i = 0; i<wordsDict.length; i++){
            String w= wordsDict[i];
            if(!map.containsKey(w)) map.put(w, new ArrayList<>());
            map.get(w).add(i);
        }
        n = wordsDict.length;
    }

    public int shortest(String word1, String word2) {
        if(!map.containsKey(word1) || !map.containsKey(word2)) return -1;
        ArrayList<Integer> list = map.get(word2);
        ArrayList<Integer> list2 = map.get(word1);
        int ans = Integer.MAX_VALUE;
        for(int i: list) {
            int pos = Collections.binarySearch(list2, i);
            if(pos <0) pos = ~pos;
            int left = (pos - 1);
            int N = list2.size();
            if(left < 0) {
                left += N;
            }
            left = left % N;
            int right = pos%N;
            int min = Math.min(Math.abs(i - list2.get(left)), Math.abs(i - list2.get(right)));
            ans = Math.min(min, ans);
        }
        return ans;
    }
}
