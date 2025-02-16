## DFS

```java
import java.util.*;

public class TopologicalSort {

    // Function to perform DFS and topological sorting
    static void topologicalSortUtil(int v, List<List<Integer>> adj,
                        boolean[] visited,
                        Stack<Integer> stack) {
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all adjacent vertices
        for (int i : adj.get(v)) {
            if (!visited[i])
                topologicalSortUtil(i, adj, visited, stack);
        }

        // Push current vertex to stack which stores the
        // result
        stack.push(v);
    }

    // Function to perform Topological Sort
    static void topologicalSort(List<List<Integer>> adj,
                                int V) {
        // Stack to store the result
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        // Call the recursive helper function to store
        // Topological Sort starting from all vertices one
        // by one
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                topologicalSortUtil(i, adj, visited, stack);
        }

        // Print contents of stack
        System.out.print(
                "Topological sorting of the graph: ");
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    // Driver code
    public static void main(String[] args) {
        // Number of nodes
        int V = 4;

        // Edges
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(Arrays.asList(0, 1));
        edges.add(Arrays.asList(1, 2));
        edges.add(Arrays.asList(3, 1));
        edges.add(Arrays.asList(3, 2));

        // Graph represented as an adjacency list
        List<List<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (List<Integer> i : edges) {
            adj.get(i.get(0)).add(i.get(1));
        }

        topologicalSort(adj, V);
    }
}

```
## BFS (Kahn's)
```java
import java.util.*;

public class Main {

    // Function to return array containing vertices in
    // Topological order.
    public static int[] topologicalSort(
        List<List<Integer> > adj, int V)
    {
        // Array to store indegree of each vertex
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        // Queue to store vertices with indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] result = new int[V];
        int index = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            result[index++] = node;

            // Decrease indegree of adjacent vertices as the
            // current node is in topological order
            for (int it : adj.get(node)) {
                indegree[it]--;

                // If indegree becomes 0, push it to the
                // queue
                if (indegree[it] == 0) {
                    q.offer(it);
                }
            }
        }

        // Check for cycle
        if (index != V) {
            System.out.println("Graph contains cycle!");
            return new int[0];
        }

        return result;
    }

    public static void main(String[] args)
    {
        // Number of nodes
        int n = 6;

        // Edges
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 },
                          { 4, 5 }, { 5, 1 }, { 5, 2 } };

        // Graph represented as an adjacency list
        List<List<Integer> > adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Constructing adjacency list
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        // Performing topological sort
        System.out.println(
            "Topological sorting of the graph: ");
        int[] result = topologicalSort(adj, n);

        // Displaying result
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}



```