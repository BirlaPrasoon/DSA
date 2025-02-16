package leetcode.problems.google.medium.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstCompletelyPaintedRowOrColumn {
    public static int firstCompleteIndex(int[] arr, int[][] mat) {
        int cols[] = new int[mat[0].length];
        int rows[] = new int[mat.length];
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for(int i = 0; i<mat.length; i++) {
            for(int j = 0; j<mat[i].length; j++){
                int val = mat[i][j];
                map.putIfAbsent(val, new ArrayList<>());
                map.get(val).add(new int[]{i,j});
            }
        }
        for(int i= 0; i<arr.length; i++) {
            int a = arr[i];
            List<int[]> indexes = map.getOrDefault(a, null);
            if(indexes != null) {
                for(int []is: indexes) {
                    int row = is[0];
                    int col = is[1];
                    if(++rows[row] == mat[0].length) return i;
                    if(++cols[col] == mat.length) return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int a[] = {1,4,5,2,6,3};
        int [][]mat = {{4,3,5}, {1,2,6}};
        System.out.println(firstCompleteIndex(a, mat));
    }
}
