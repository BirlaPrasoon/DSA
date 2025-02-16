# Concurrency Utilities in Java

Java provides a rich set of concurrency utilities in the `java.util.concurrent` package to simplify thread management and synchronization. These utilities help manage thread pools, handle concurrent collections, and simplify synchronization tasks.

## 1. **Executors**

The `Executors` class provides factory methods for creating various types of thread pools and executor services.

**Example:**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
public static void main(String[] args) {
// Creating a fixed thread pool with 4 threads
ExecutorService executor = Executors.newFixedThreadPool(4);

        // Submitting tasks to the executor
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running on " + Thread.currentThread().getName());
            });
        }

        // Shutting down the executor
        executor.shutdown();
    }
}
```

**Explanation:**
- `Executors.newFixedThreadPool(4)` creates a thread pool with 4 threads.
- `executor.submit()` is used to submit tasks for execution.
- `executor.shutdown()` initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks are accepted.

## 2. **Callable and Future**

The `Callable` interface is similar to `Runnable`, but it can return a result and throw a checked exception. The `Future` interface represents the result of an asynchronous computation.

**Example:**

```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
public static void main(String[] args) {
ExecutorService executor = Executors.newFixedThreadPool(1);

        // Creating a Callable task
        Callable<Integer> task = () -> {
            Thread.sleep(2000); // Simulating long computation
            return 123;
        };

        // Submitting the task and getting a Future
        Future<Integer> future = executor.submit(task);

        try {
            // Blocking call to get the result
            Integer result = future.get();
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
```

**Explanation:**
- `Callable` allows tasks to return results and throw exceptions.
- `Future.get()` blocks until the result is available, providing the result of the computation.

## 3. **Concurrent Collections**

The `java.util.concurrent` package provides thread-safe collections such as `ConcurrentHashMap`, `CopyOnWriteArrayList`, and `BlockingQueue`.

**Example:**

```java
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
public static void main(String[] args) {
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Putting values into the map
        map.put("A", 1);
        map.put("B", 2);

        // Retrieving and printing values
        System.out.println("Value for 'A': " + map.get("A"));
        System.out.println("Value for 'B': " + map.get("B"));
    }
}
```

**Explanation:**
- `ConcurrentHashMap` is a thread-safe map that supports high concurrency.
- It allows multiple threads to read and write concurrently without needing explicit synchronization.

## 4. **Locks and Synchronizers**

Java provides additional synchronization utilities like `ReentrantLock`, `CountDownLatch`, and `CyclicBarrier`.

**Example with `ReentrantLock`:**

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
private final Lock lock = new ReentrantLock();
private int count = 0;

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();

        // Creating two threads that increment the count
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + example.getCount());
    }
}
```

**Explanation:**
- `ReentrantLock` provides more advanced locking capabilities compared to the synchronized keyword, including the ability to interrupt, try-lock, and more.

**Example with `CountDownLatch`:**

```
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
public static void main(String[] args) throws InterruptedException {
CountDownLatch latch = new CountDownLatch(3);

        // Creating three threads that count down the latch
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            new Thread(() -> {
                System.out.println("Thread " + threadId + " is running");
                latch.countDown();
            }).start();
        }

        // Main thread waits for the count to reach zero
        latch.await();
        System.out.println("All threads have completed.");
    }
}
```

**Explanation:**
- `CountDownLatch` allows one or more threads to wait until a set of operations in other threads completes.
- `countDown()` decreases the count, and `await()` blocks until the count reaches zero.

## Summary

Java's concurrency utilities simplify the development of multi-threaded applications. Key utilities include:

- **Executors**: Manage thread pools and task execution.
- **Callable and Future**: Handle tasks that return results and manage asynchronous computations.
- **Concurrent Collections**: Provide thread-safe collections like `ConcurrentHashMap`.
- **Locks and Synchronizers**: Offer advanced synchronization mechanisms such as `ReentrantLock`, `CountDownLatch`, and `CyclicBarrier`.

Understanding and utilizing these utilities help in writing efficient, scalable, and thread-safe applications.


# Java Concurrency Utilities Examples

This section provides examples of various concurrency utilities in Java, including `BlockingQueue`, `Semaphore`, `CountDownLatch`, and `CyclicBarrier`.

## 1. **BlockingQueue**

`BlockingQueue` is a type of queue that supports operations that wait for the queue to become non-empty when retrieving an element and wait for space to become available in the queue when storing an element.

**Example:**

```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {
public static void main(String[] args) throws InterruptedException {
BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(100); // Simulating time delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Integer value = queue.take();
                    System.out.println("Consumed: " + value);
                    Thread.sleep(200); // Simulating time delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
```

**Explanation:**
- `LinkedBlockingQueue` is a thread-safe queue implementation.
- The producer adds elements to the queue, and the consumer takes elements from the queue.
- `put()` blocks if the queue is full, and `take()` blocks if the queue is empty.

## 2. **Semaphore**

`Semaphore` controls access to a shared resource by multiple threads using a counting semaphore.

**Example:**

```java
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
private static final Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " acquired permit");
                Thread.sleep(2000); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName() + " releasing permit");
                semaphore.release();
            }
        };

        // Create and start threads
        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }
    }
}
```

**Explanation:**
- `Semaphore` is initialized with a permit count of 2.
- Threads try to acquire a permit before accessing the resource and release the permit afterward.
- If all permits are acquired, other threads must wait until a permit is released.

## 3. **CountDownLatch**

`CountDownLatch` allows one or more threads to wait until a set of operations in other threads completes.

**Example:**

```java
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
public static void main(String[] args) throws InterruptedException {
CountDownLatch latch = new CountDownLatch(3);

        Runnable task = (id) -> {
            System.out.println("Task " + id + " is running");
            latch.countDown();
        };

        // Start three threads
        for (int i = 1; i <= 3; i++) {
            final int taskId = i;
            new Thread(() -> task.run(taskId)).start();
        }

        // Main thread waits for the count to reach zero
        latch.await();
        System.out.println("All tasks are completed.");
    }
}
```

**Explanation:**
- `CountDownLatch` is initialized with a count of 3.
- Each thread performs some work and calls `countDown()` to decrease the count.
- The main thread calls `await()` to block until the count reaches zero.

## 4. **CyclicBarrier**

`CyclicBarrier` is used to synchronize a group of threads, ensuring they all reach a common barrier point before continuing.

**Example:**

```java
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
public static void main(String[] args) {
final int numberOfThreads = 3;
CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, () -> System.out.println("Barrier action performed"));

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " passed the barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Start three threads
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(task).start();
        }
    }
}
```

**Explanation:**
- `CyclicBarrier` is initialized with a count of 3.
- Threads wait at the barrier and call `await()`.
- Once all threads reach the barrier, the barrier action is performed (in this case, printing a message).
- The barrier is reset and can be reused.

## Summary

Java provides powerful concurrency utilities to manage multi-threaded programming:

- **BlockingQueue**: Thread-safe queues that support blocking operations.
- **Semaphore**: Controls access to shared resources with permits.
- **CountDownLatch**: Allows threads to wait until a set of operations completes.
- **CyclicBarrier**: Synchronizes a group of threads at a common barrier point.

Understanding and using these utilities helps in managing complex multi-threaded tasks and improving application performance and reliability.
