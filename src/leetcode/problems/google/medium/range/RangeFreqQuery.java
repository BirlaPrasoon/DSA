package leetcode.problems.google.medium.range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class RangeFreqQuery {
    // Very good implementation, we could have just used binary search but this is great. Similar to RangeModule.
    List<Integer>[] ids = new ArrayList[10001];
    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < ids.length; ++i)
            ids[i] = new ArrayList<>();
        for (int i = 0; i < arr.length; ++i)
            ids[arr[i]].add(i);
    }
    public int query(int l, int r, int v) {
        return lowerBound(Collections.binarySearch(ids[v], r + 1)) - lowerBound(Collections.binarySearch(ids[v], l));
    }

    private int lowerBound(int pos) {
        return  pos < 0 ? ~pos : pos;
    }
}
