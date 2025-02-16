package leetcode.problems.google.easy.arrays;

import java.util.HashMap;

class Logger {
    HashMap<String, Integer> map;
    public Logger() {
        map = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer previousTime = map.getOrDefault(message, null);
        // System.out.println(map);
        if(previousTime == null || (previousTime+10 <= timestamp)) {
            map.put(message, timestamp);
            return true;
        }
        return false;
    }
}
