package algoexpert.graphs;

import java.util.*;

public class TopologicalSort {

    //Function to return list containing vertices in Topological order.
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj)
    {
        int indegrees[] = new int[V];
        for(int n = 0; n<V; n++) {
            for(int e: adj.get(n)) {
                indegrees[e]++;
            }
        }
        boolean visited [] = new boolean[V];
        int ans[] = new int[V];
        int i = 0;
        for(int j = 0; j<V; j++) {
            int x = getIndexWithMinValue(indegrees, visited);
            visited[x] = true;
            for(int k: adj.get(x)) {
                indegrees[k]--;
            }
            ans[i++] = x;
        }
        return ans;
    }

    private static int getIndexWithMinValue(int[] indegree, boolean[] vis) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int i = 0; i< indegree.length; i++) {
            if(!vis[i] && indegree[i] < min) {
                minIndex = i;
                min = indegree[i];
            }
        }
        return minIndex;
    }

    public static List<Integer> topologicalSort(
            List<Integer> jobs, List<Integer[]> deps
    ) {
        HashMap<Integer, ArrayList<Integer>> gph = new HashMap<>();
        for(int j: jobs) {
            gph.put(j, new ArrayList<>());
        }
        for(Integer[] d: deps) {
            gph.get(d[0]).add(d[1]);
        }
        HashSet<Integer> visited = new HashSet<>();
        for(int j: jobs) {
            if(!visited.contains(j)) {
                boolean ans = dfs(j, gph, visited);
                if(ans) return new ArrayList<>();
            }
        }
        visited.clear();
        Stack<Integer> stack = new Stack<>();
        for(int j: jobs) {
            if(!visited.contains(j)) {
                dfs(j, gph, visited, stack);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            ans.add(stack.pop());
        }
        return ans;
    }

    private static void dfs(int i, HashMap<Integer, ArrayList<Integer>> gph, HashSet<Integer> set, Stack<Integer> stack) {
        if(set.contains(i)) return;

        set.add(i);

        for(Integer n: gph.get(i)) {
            dfs(n, gph, set, stack);
        }

        stack.add(i);
    }

    private static boolean dfs(int i, HashMap<Integer, ArrayList<Integer>> gph, HashSet<Integer> visited) {
        if(visited.contains(i)) return true;
        visited.add(i);
        for(Integer n: gph.get(i)) {
            boolean ans = dfs(n, gph, visited);
            if(ans) return true;
        }
        visited.remove(i);
        return false;
    }
}
