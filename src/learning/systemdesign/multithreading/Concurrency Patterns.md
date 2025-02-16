# Concurrency Patterns

Concurrency patterns are design solutions to common problems in multi-threaded programming. Understanding these patterns helps in building scalable and efficient concurrent applications. This section covers the Producer-Consumer pattern, Thread Pools, and Future and Callable.

## 1. **Producer-Consumer Pattern**

The **Producer-Consumer** pattern involves two types of threads: producers and consumers. Producers generate data and place it into a shared buffer, while consumers take data from the buffer and process it. This pattern helps in managing the flow of data between producers and consumers, ensuring that they do not compete for access to the buffer.

### Example of Producer-Consumer Pattern

```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerExample {
private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Runnable producer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(100); // Simulate delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Integer value = queue.take();
                    System.out.println("Consumed: " + value);
                    Thread.sleep(200); // Simulate delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }
}
```

**Explanation:**
- `BlockingQueue` is used to handle the shared buffer between producers and consumers.
- Producers add items to the queue with `put()`, and consumers retrieve items with `take()`.
- This pattern helps in decoupling production and consumption rates, handling variability in production and consumption speeds.

## 2. **Thread Pools**

**Thread Pools** manage a pool of worker threads that can be reused to execute multiple tasks. This pattern helps in reducing the overhead of thread creation and destruction, managing thread life cycles efficiently, and controlling the maximum number of concurrent threads.

### Example of Thread Pools

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
public static void main(String[] args) {
ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " is executing task");
            try {
                Thread.sleep(2000); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 0; i < 5; i++) {
            executor.execute(task);
        }

        executor.shutdown();
    }
}
```

**Explanation:**
- `Executors.newFixedThreadPool(3)` creates a thread pool with 3 threads.
- Tasks are submitted to the pool using `execute()`.
- The pool manages the execution of tasks and reuses threads efficiently.

## 3. **Future and Callable**

**Future** and **Callable** are used for asynchronous computation. `Callable` is similar to `Runnable`, but it can return a result or throw an exception. `Future` represents the result of an asynchronous computation and provides methods to check the status or retrieve the result.

### Example of Future and Callable

```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCallableExample {
public static void main(String[] args) throws InterruptedException, ExecutionException {
ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> callableTask = () -> {
            Thread.sleep(1000); // Simulate computation
            return 123;
        };

        Future<Integer> future = executor.submit(callableTask);

        // Do other work while the callable is being executed
        System.out.println("Doing other work...");

        // Retrieve the result from the future
        Integer result = future.get(); // Blocks until the result is available
        System.out.println("Result from callable: " + result);

        executor.shutdown();
    }
}
```

**Explanation:**
- `Callable` is used to define a task that returns a result.
- `ExecutorService.submit()` submits the `Callable` task for execution and returns a `Future`.
- `Future.get()` retrieves the result, blocking if necessary until the result is available.

## Differences and Use Cases

### Producer-Consumer vs. Thread Pools

- **Producer-Consumer**: This pattern is used when you need to decouple the production and consumption of tasks or data, often using a shared buffer.
- **Thread Pools**: Useful when you need to manage and reuse a set of threads for executing tasks concurrently, without the overhead of creating and destroying threads frequently.

### Future vs. Callable

- **Callable**: Defines a task that can return a result or throw an exception, used for asynchronous computation.
- **Future**: Represents the result of an asynchronous computation started by a `Callable`. It allows checking the computation status and retrieving the result.

**When to Use:**
- Use **Producer-Consumer** when dealing with tasks that need to be processed by multiple consumers and produced by multiple producers, often involving a shared buffer.
- Use **Thread Pools** when you need to manage a group of threads for executing multiple tasks efficiently.
- Use **Future and Callable** when you need to perform asynchronous computation and retrieve the result at a later time.

## Summary

- **Producer-Consumer**: Manages the flow of data between producers and consumers using a shared buffer.
- **Thread Pools**: Manages and reuses a pool of threads to efficiently execute multiple tasks.
- **Future and Callable**: Used for asynchronous computation, where `Callable` represents the task and `Future` represents the result of the computation.

Understanding these concurrency patterns helps in designing efficient and scalable multi-threaded applications.
