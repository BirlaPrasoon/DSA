package multithreading_practicals;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        // Creating a Callable task
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000); // Simulating long computation
                return 123;
            }
        };


        // Submitting the task and getting a Future
        Future<Integer> future = executor.submit(task);
        Future<Integer> future2 = executor.submit(task);
        Future<Integer> future3 = executor.submit(task);
        Future<Integer> futur4 = executor.submit(task);

        try {
            // Blocking call to get the result
            System.out.println("Waiting: ");
//            Integer result = future.get();
            System.out.println("Thread 1 done");
//            future2.get();
            System.out.println("Thread 2 done");
//            future3.get();
            System.out.println("Thread 3 done");
//            futur4.get();
//            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
