package leetcode.problems.google.hard.implementation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n == 1) {
            return 1;
        }
        int result = 2;
        for (int i = 0; i < n; i++) {
            Map<Double, Integer> cnt = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double slope = ((points[j][1] - points[i][1] )*1.0)/(points[j][0] - points[i][0]);
                    cnt.put(slope, cnt.getOrDefault(slope,0)+1);
                }
            }
            result = Math.max(result, Collections.max(cnt.values()) + 1);
        }
        return result;
    }
}
