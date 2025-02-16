package algoexpert.strings;

import java.util.*;

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(List<String> words) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String w: words) {
            String t = getCharacterString(w);
            ArrayList<String> list = map.getOrDefault(t, new ArrayList<>());
            list.add(w);
            map.put(t, list);
        }
        Collection<ArrayList<String>> ans = map.values();
        return new ArrayList<>(ans);
    }

    public static String getCharacterString(String word) {
        HashSet<Character> set = new HashSet<>();
        for(char c: word.toCharArray()) {
            set.add(c);
        }
        ArrayList<Character> list = new ArrayList<>(set);
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for(char c: list) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
