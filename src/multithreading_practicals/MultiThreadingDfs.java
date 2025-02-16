package multithreading_practicals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadingDfs {

    private final HashMap<String, List<String>> graph;

    /*
    *
    * Services -> Adapters, Core, Util
    * Adapters -> Interfaces
    * Util -> Types
    * Core -> Adapters, Types, Adapters
    *
    * */

    MultiThreadingDfs(){
        this.graph = new HashMap<>();
        graph.putIfAbsent("Services", new ArrayList<>());
        graph.get("Services").add("Adapters");
        graph.get("Services").add("Core");
        graph.get("Services").add("Util");
        graph.putIfAbsent("Core", new ArrayList<>());
//        graph.get("Core").add("Adapters");
        graph.get("Core").add("Types");
        graph.putIfAbsent("Adapters", new ArrayList<>());
        graph.get("Adapters").add("Interfaces");

        graph.putIfAbsent("Interfaces", new ArrayList<>());

        graph.putIfAbsent("Types", new ArrayList<>());


        graph.putIfAbsent("Util", new ArrayList<>());
        graph.get("Util").add("Types");
    }

    public List<String> getDependencyExecutionOrder(String src, int i) {
        List<String> dependencyOrder = new ArrayList<>();
        System.out.println("Executing ith time: " + i);
        if(src.isBlank() || !graph.containsKey(src)) {
            dependencyOrder.add(src);
            return dependencyOrder;
        }
        HashSet<String> visited = new HashSet<>();
        getDependencyExecutionOrderHelper(src, graph, visited, dependencyOrder);
        System.out.println("dependencyOrder: " + dependencyOrder);
        System.out.println("Executing ith time complete: " + i);
        return dependencyOrder;
    }

    private void getDependencyExecutionOrderHelper(String src, HashMap<String, List<String>> graph, HashSet<String> visited, List<String> dependencyOrder) {
        if(visited.contains(src)) return;
        visited.add(src);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for(String dependency: graph.get(src)) {
            if(!visited.contains(dependency)) {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    getDependencyExecutionOrderHelper(dependency, graph, visited, dependencyOrder);
                }, executor);
                futures.add(future);
            }
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allOf.get(); // Wait for all API calls to complete
            System.out.println("All API calls have completed. Current processed nodes ->" + dependencyOrder);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }


        dependencyOrder.add(src);
    }

    public static void main(String[] args) {
        MultiThreadingDfs m = new MultiThreadingDfs();
        ExecutorService service = Executors.newFixedThreadPool(1000);
        List<Future<List<String>>> futures = new ArrayList<>();
        int times = 1;
        for(int i= 0; i<times; i++) {
            int finalI = i;
            Callable<List<String>> task = () -> m.getDependencyExecutionOrder("Services", finalI);
            Future<List<String>> future = service.submit(task);
            futures.add(future);
        }

        // Shutdown the executor service to prevent new tasks from being submitted
        service.shutdown();

        // Wait for all futures to complete and collect results
        try {
            for (Future<List<String>> future : futures) {
                // This will block until the result is available
                List<String> result = future.get();
//                System.out.println(result); // Print each result
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(); // Handle exceptions
        }

//        System.out.println(m.getDependencyExecutionOrder("Services"));
    }
}
