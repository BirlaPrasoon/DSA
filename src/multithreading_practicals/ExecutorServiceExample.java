package multithreading_practicals;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Executors.newScheduledThreadPool(10);
        Set<Callable<String>> callables = new HashSet<Callable<String>>();
        CountDownLatch latch = new CountDownLatch(10);
        IntStream.range(0,10).forEach((i) -> {
            callables.add(() -> {
                latch.countDown();
                System.out.println("I: " + i);
                return "Task " + i;
            });
        });
        List<ScheduledFuture<String>> list = new ArrayList<>();
        List<Future<String>> futures = executorService.invokeAll(callables);
//        for (Callable<String> callable : callables) {
//            list.add(executorService.schedule(callable, 300, TimeUnit.MILLISECONDS));
//        }
        System.out.println("Awaiting latch");
        latch.await();
        System.out.println("Await complete!!");
        for(Future<String> values: futures) {
            System.out.println("Value: " + values.get());
        }

        Thread.sleep(5000);
        System.out.println("Done with getting all values");
        executorService.shutdown();
    }
}