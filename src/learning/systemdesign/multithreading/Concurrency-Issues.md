# Concurrency Issues

In concurrent programming, several issues can arise that affect the correctness and performance of applications. Three common concurrency issues are deadlock, starvation, and livelock. Understanding these issues helps in designing robust multi-threaded applications.

## 1. **Deadlock**

**Deadlock** is a situation where two or more threads are blocked forever, each waiting for the other to release a resource. This occurs when the threads hold resources and wait for additional resources that are held by other threads, creating a circular dependency.

### Example of Deadlock

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {
private final Lock lock1 = new ReentrantLock();
private final Lock lock2 = new ReentrantLock();

    public void method1() {
        lock1.lock();
        try {
            Thread.sleep(100); // Simulate some work
            lock2.lock();
            try {
                System.out.println("In method1");
            } finally {
                lock2.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock1.unlock();
        }
    }

    public void method2() {
        lock2.lock();
        try {
            Thread.sleep(100); // Simulate some work
            lock1.lock();
            try {
                System.out.println("In method2");
            } finally {
                lock1.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock2.unlock();
        }
    }

    public static void main(String[] args) {
        DeadlockExample example = new DeadlockExample();
        Thread t1 = new Thread(example::method1);
        Thread t2 = new Thread(example::method2);

        t1.start();
        t2.start();
    }
}
```

**Explanation:**
- `method1` locks `lock1` and then attempts to lock `lock2`.
- `method2` locks `lock2` and then attempts to lock `lock1`.
- This creates a deadlock if `t1` locks `lock1` and `t2` locks `lock2` at the same time, and neither thread can proceed.

## 2. **Starvation**

**Starvation** occurs when a thread is perpetually denied access to resources because other threads are constantly being given preference. This happens when thread scheduling or resource allocation policies prevent the thread from ever being able to acquire the resources it needs.

### Example of Starvation

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StarvationExample {
private final Lock lock = new ReentrantLock();

    public void accessResource() {
        while (true) {
            if (lock.tryLock()) {
                try {
                    // Simulate some work
                    System.out.println(Thread.currentThread().getName() + " accessing resource");
                    break; // Exit loop when work is done
                } finally {
                    lock.unlock();
                }
            } else {
                // Simulate some waiting
                System.out.println(Thread.currentThread().getName() + " waiting");
            }
        }
    }

    public static void main(String[] args) {
        StarvationExample example = new StarvationExample();

        Runnable task = () -> {
            while (true) {
                example.accessResource();
                try {
                    Thread.sleep(1000); // Simulate time between access attempts
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Start multiple threads
        for (int i = 0; i < 3; i++) {
            new Thread(task, "Thread-" + i).start();
        }
    }
}
```

**Explanation:**
- `tryLock()` is used to attempt to acquire the lock without blocking.
- Threads that fail to acquire the lock immediately keep retrying, potentially leading to one or more threads being starved if other threads keep succeeding in acquiring the lock.

## 3. **Livelock**

**Livelock** occurs when two or more threads keep changing states in response to each other without making progress. Unlike deadlock, threads are actively changing states, but none of them make progress toward completion.

### Example of Livelock

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockExample {
private final Lock lock = new ReentrantLock();
private boolean resourceAvailable = true;

    public void accessResource() {
        while (true) {
            if (lock.tryLock()) {
                try {
                    if (resourceAvailable) {
                        System.out.println(Thread.currentThread().getName() + " accessing resource");
                        resourceAvailable = false;
                        break; // Exit loop when work is done
                    }
                } finally {
                    lock.unlock();
                }
            } else {
                // Simulate some waiting
                System.out.println(Thread.currentThread().getName() + " waiting");
                try {
                    Thread.sleep(100); // Simulate delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        LivelockExample example = new LivelockExample();

        Runnable task = () -> {
            while (true) {
                example.accessResource();
                try {
                    Thread.sleep(1000); // Simulate time between access attempts
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Start multiple threads
        for (int i = 0; i < 3; i++) {
            new Thread(task, "Thread-" + i).start();
        }
    }
}
```

**Explanation:**
- Threads keep trying to acquire the lock and access the resource.
- They keep changing states (attempting to acquire the lock) but fail to make progress because they continuously react to each other’s states.

## Summary

- **Deadlock**: A situation where threads are blocked forever, each waiting for the other to release resources, creating a circular dependency.
- **Starvation**: Occurs when a thread is perpetually denied access to resources because other threads are constantly given preference.
- **Livelock**: Threads are actively changing states in response to each other but are unable to make progress.

Understanding and addressing these issues is crucial for designing effective and robust multi-threaded applications.
