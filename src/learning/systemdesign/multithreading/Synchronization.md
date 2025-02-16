# Thread Synchronization in Java

Thread synchronization is used to control the access of multiple threads to shared resources, ensuring that data remains consistent and preventing race conditions. Below are some common methods and examples of thread synchronization in Java.

## 1. **Synchronized Methods**

Using the `synchronized` keyword, you can make a method synchronized so that only one thread can execute it at a time for a particular object.

**Example:**

```java
public class Counter {
private int count = 0;

    // Synchronized method to increment the counter
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class Main {
public static void main(String[] args) {
Counter counter = new Counter();

        // Creating two threads that increment the counter
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

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + counter.getCount());
    }
}
```

**Explanation:**
- The `increment` method is synchronized, meaning only one thread can execute it at a time for a given `Counter` instance.
- This prevents race conditions where multiple threads might try to increment the `count` simultaneously, leading to incorrect results.

## 2. **Synchronized Blocks**

If you need more granular control over synchronization, you can use synchronized blocks within methods. This allows you to synchronize only a part of the method rather than the entire method.

**Example:**

```java
public class SharedResource {
private int resource = 0;

    public void modifyResource() {
        // Synchronized block to protect only the critical section
        synchronized (this) {
            resource++;
            // Critical section code
        }
    }

    public int getResource() {
        return resource;
    }
}

public class Main {
public static void main(String[] args) {
SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedResource.modifyResource();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedResource.modifyResource();
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

        System.out.println("Final resource value: " + sharedResource.getResource());
    }
}
```

**Explanation:**
- The `synchronized` block ensures that only one thread can execute the critical section of `modifyResource` at a time.
- This can be more efficient than synchronizing the entire method, especially if only part of the method requires synchronization.

## 3. **Using Locks**

Java provides explicit lock mechanisms through the `java.util.concurrent.locks` package. The `ReentrantLock` class allows for more sophisticated locking mechanisms than the synchronized keyword.

**Example:**

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeCounter {
private int count = 0;
private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // Acquiring the lock
        try {
            count++;
        } finally {
            lock.unlock(); // Releasing the lock
        }
    }

    public int getCount() {
        return count;
    }
}

public class Main {
public static void main(String[] args) {
SafeCounter safeCounter = new SafeCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                safeCounter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                safeCounter.increment();
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

        System.out.println("Final count: " + safeCounter.getCount());
    }
}
```

**Explanation:**
- `ReentrantLock` provides more flexibility than the `synchronized` keyword. For example, it allows for try-locks, timed locks, and interruptible locks.
- The `lock.lock()` method acquires the lock, and `lock.unlock()` releases it in a `finally` block to ensure the lock is always released even if an exception occurs.

## 4. **Using `volatile` Keyword**

The `volatile` keyword is used to indicate that a variable's value will be modified by different threads. It ensures that changes to the variable are visible to all threads immediately.

**Example:**

```java
public class VolatileExample {
private volatile boolean running = true;

    public void run() {
        while (running) {
            // Do some work
        }
    }

    public void stop() {
        running = false; // This change is immediately visible to the run method
    }
}

public class Main {
public static void main(String[] args) {
VolatileExample example = new VolatileExample();

        Thread t1 = new Thread(example::run);
        t1.start();

        // Simulate some work
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        example.stop(); // Stop the thread

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread stopped.");
    }
}
```

**Explanation:**
- The `volatile` keyword ensures that changes to the `running` variable are visible to all threads immediately, preventing caching issues and ensuring proper thread communication.

## Summary

Thread synchronization is essential for coordinating access to shared resources and preventing issues such as race conditions and data corruption. Common synchronization techniques include:

- **Synchronized Methods**: Use the `synchronized` keyword to restrict method access to one thread at a time.
- **Synchronized Blocks**: Fine-tune synchronization to only specific code sections.
- **Locks**: Use `ReentrantLock` for more advanced locking capabilities.
- **Volatile Variables**: Ensure visibility of changes to shared variables across threads.

Understanding and implementing these synchronization techniques helps in building robust and thread-safe applications.
