package leetcode.problems.google.medium.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MergeIntervals {
    public static void main(String[] args) {
            // [[1,3],[2,6],[8,10],[15,18]]
        int[][] x = {{1,4}, {0, 1}};
        MergeIntervals mergeIntervals = new MergeIntervals();
        System.out.println(Arrays.deepToString(mergeIntervals.merge2(x)));
    }


    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        System.out.println(Arrays.deepToString(intervals));
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            Map.Entry<Integer, Integer> tl = map.floorEntry(left);
            Map.Entry<Integer, Integer> tr = map.floorEntry(right);
            if(tl != null && tl.getValue() >= left) {
                left = Math.min(left, tl.getKey());
            }
            if(tr != null && tr.getValue() >= right) {
                right = tr.getValue();
            }
            map.subMap(left, true, right, true).clear();
            map.put(left, right);
        }
        int size = map.size();
        int[][] res = new int[size][2];
        int i = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res[i][0] = entry.getKey();
            res[i][1] = entry.getValue();
            i++;
        }
        return res;
    }

    public int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        System.out.println(Arrays.deepToString(intervals));
        ArrayList<int[]> list = new ArrayList<>();
        int i = 0;
        while(i<intervals.length) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            i++;
            while(i<intervals.length && intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
                i++;
            }
            list.add(new int[]{start, end});
        }
        for(int[] interval : list) {
            System.out.println(Arrays.toString(interval));
        }
        int[][] ans = new int[list.size()][2];
        int t = 0;
        for(int[] interval : list) {
            ans[t][0] = interval[0];
            ans[t++][1] = interval[1];
        }
        return ans;
    }

}
