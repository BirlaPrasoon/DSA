# Thread Safety and Data Race

Understanding thread safety and data races is crucial for writing reliable and robust concurrent programs. Below is an explanation of these concepts.

## Thread Safety

**Thread safety** refers to the property of a class or method that ensures it functions correctly when accessed by multiple threads concurrently. A thread-safe class or method prevents data corruption and ensures consistent behavior by managing concurrent access to shared resources.

### Characteristics of Thread-Safe Code

1. **Atomic Operations**: Operations on shared variables are atomic, meaning they complete in a single step relative to other threads. For example, incrementing a counter can be atomic if done using `AtomicInteger`.

2. **Synchronization**: Proper use of synchronization mechanisms like `synchronized` blocks or methods, locks (`ReentrantLock`), and concurrent collections ensures that only one thread can access a critical section at a time.

3. **Immutability**: Immutable objects are inherently thread-safe because their state cannot be modified after construction. Examples include `String` and `Integer` in Java.

### Example of Thread-Safe Code
```java
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter {
private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeCounter counter = new ThreadSafeCounter();

        // Creating multiple threads that increment the counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}
```

**Explanation:**
- `AtomicInteger` provides atomic operations for integer values, making the `increment` method thread-safe without explicit synchronization.

## Data Race

A **data race** occurs when two or more threads access shared data concurrently, and at least one of the threads modifies the data without proper synchronization. This can lead to inconsistent or unexpected results due to the lack of proper coordination between threads.

### Characteristics of Data Race

1. **Concurrent Access**: Multiple threads access the same shared variable or resource.
2. **Uncontrolled Modifications**: At least one thread modifies the shared variable without synchronization.
3. **Visibility Issues**: Changes made by one thread may not be visible to others due to caching or optimization.

### Example of a Data Race
```java
import java.util.HashMap;
import java.util.Map;

public class DataRaceExample {
private static final Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, i);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Map size: " + map.size());
    }
}
```

**Explanation:**
- In this example, `HashMap` is accessed and modified by multiple threads without synchronization, leading to unpredictable results and potential corruption of the map.
- The `size` of the map might not be accurate because of the concurrent modifications.

## Avoiding Data Races

To avoid data races, ensure that shared resources are accessed in a thread-safe manner:

1. **Use Synchronized Blocks/Methods**: Protect critical sections where shared data is accessed or modified.
2. **Use Concurrent Collections**: Utilize thread-safe collections like `ConcurrentHashMap` instead of `HashMap`.
3. **Use Atomic Variables**: Leverage classes like `AtomicInteger` for atomic operations on single variables.
4. **Immutable Objects**: Prefer immutable objects to avoid synchronization issues.

## Summary

- **Thread Safety**: Ensures that a class or method can be safely used by multiple threads simultaneously, typically through atomic operations, synchronization, or immutability.
- **Data Race**: Occurs when multiple threads access shared data concurrently without proper synchronization, leading to inconsistent results.

Understanding and implementing thread safety helps in building reliable and robust multi-threaded applications while avoiding data races ensures data consistency and correctness.
