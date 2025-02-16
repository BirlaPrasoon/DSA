package leetcode.problems.google.medium.binarySearch;

import java.util.HashMap;
import java.util.TreeMap;

public class TimeMap {

    HashMap<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if(map.containsKey(key)) {
            var floor = map.get(key).floorKey(timestamp);
            if(floor == null) return "";
            return map.get(key).get(floor);
        }
        return "";
    }
}
