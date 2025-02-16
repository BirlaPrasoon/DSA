package leetcode.problems.google.medium.breadth_first_search.different;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumDetonations {
    public static void main(String[] args) {
        int a[][] = {{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};
        MaximumDetonations m = new MaximumDetonations();
        System.out.println(m.maximumDetonation(a));
    }

    public int maximumDetonation(int[][] bombs) {
        int max = 0;
        List<List<Integer>> gph = generateGraph(bombs);
        boolean[] visited = new boolean[bombs.length];
        for(int i= 0;i<bombs.length; i++) {
            if(!visited[i]) {
                Arrays.fill(visited, false);
                int count = dfs(gph, i, visited);
                if(count > max) {
                    max = count;
                }
            }
        }
        return max;
    }

    private int dfs(List<List<Integer>> gph, int i, boolean[] visited) {
        if(visited[i]) return 0;
        visited[i] = true;
        int count = 0;
        for(Integer v: gph.get(i)){
            if(!visited[v]) {
                count += dfs(gph, v, visited);
            }
        }
        return count+1;
    }

    private static List<List<Integer>> generateGraph(int[][] bombs) {
        List<List<Integer>> gph = new ArrayList<>();
        for(int i = 0; i< bombs.length; i++) gph.add(new ArrayList<>());

        for(int i = 0; i< bombs.length; i++) {
            for(int j = i+1; j< bombs.length; j++) {
                if(i == j) continue;
                int x1 = bombs[i][0];
                int y1 = bombs[i][1];
                int r1 = bombs[i][2];
                int x2 = bombs[j][0];
                int y2 = bombs[j][1];
                int r2 = bombs[j][2];
                double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                if(distance <= r1) {
                    gph.get(i).add(j);
                }
                if(distance <= r2) {
                    gph.get(j).add(i);
                }
            }
        }
        return gph;
    }

}
