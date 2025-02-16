# Atomic Operations and Variables

In concurrent programming, atomic operations and variables are fundamental to ensuring data consistency and thread safety without the overhead of locks. Here's an overview of these concepts.

## Atomic Operations

**Atomic operations** are low-level operations that are performed as a single, indivisible step. They are crucial for building thread-safe applications because they prevent race conditions and ensure that operations on shared variables are completed without interference from other threads.

### Characteristics of Atomic Operations

1. **Indivisibility**: Atomic operations are completed in a single step relative to other threads. No other thread can observe the operation in an intermediate state.
2. **Efficiency**: Atomic operations are generally more efficient than locking mechanisms because they avoid the overhead associated with acquiring and releasing locks.

## Atomic Variables

Java provides atomic variables in the `java.util.concurrent.atomic` package. These variables support atomic operations on single variables, making it easier to perform thread-safe operations without explicit synchronization.

### Examples of Atomic Variables

**1. AtomicInteger**

`AtomicInteger` provides atomic operations on an integer value.

**Example:**

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet(); // Atomically increments by 1
    }

    public int getCounter() {
        return counter.get(); // Atomically gets the current value
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerExample example = new AtomicIntegerExample();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        };

        // Start multiple threads
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final counter value: " + example.getCounter());
    }
}
```

**Explanation:**
- `incrementAndGet()` performs an atomic increment operation.
- `get()` retrieves the current value of the counter atomically.

**2. AtomicBoolean**

`AtomicBoolean` provides atomic operations on a boolean value.

**Example:**

```java
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {
private final AtomicBoolean flag = new AtomicBoolean(false);

    public void toggle() {
        boolean current = flag.get();
        flag.set(!current); // Atomically toggles the boolean value
    }

    public boolean getFlag() {
        return flag.get(); // Atomically gets the current value
    }

    public static void main(String[] args) {
        AtomicBooleanExample example = new AtomicBooleanExample();

        // Toggle the boolean value in multiple threads
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                example.toggle();
                System.out.println(Thread.currentThread().getName() + " toggled flag to: " + example.getFlag());
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }
}
```

**Explanation:**
- `set()` and `get()` methods provide atomic operations to set and retrieve the boolean value.

## Compare and Swap (CAS)

**Compare and Swap (CAS)** is a low-level atomic operation used to achieve thread safety. CAS involves three steps:
1. **Compare**: Check if the current value of a variable matches an expected value.
2. **Swap**: If the current value matches the expected value, replace it with a new value.
3. **Return**: Return whether the swap was successful.

CAS is widely used to implement atomic variables and other concurrency utilities.

### Example of CAS Operation

**Custom CAS Implementation:**

```java
import java.util.concurrent.atomic.AtomicInteger;

public class CASExample {
private final AtomicInteger value = new AtomicInteger(0);

    public boolean compareAndSet(int expectedValue, int newValue) {
        return value.compareAndSet(expectedValue, newValue); // Atomic CAS operation
    }

    public static void main(String[] args) {
        CASExample example = new CASExample();

        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                boolean result = example.compareAndSet(0, i);
                System.out.println(Thread.currentThread().getName() + " CAS result: " + result);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }
}
```

**Explanation:**
- `compareAndSet(expectedValue, newValue)` attempts to set the value to `newValue` if it matches `expectedValue`.
- It returns `true` if the value was updated successfully; otherwise, it returns `false`.

## Summary

- **Atomic Operations**: Ensure that operations on shared variables are completed as indivisible steps to prevent interference from other threads.
- **Atomic Variables**: Java provides atomic classes like `AtomicInteger` and `AtomicBoolean` that support atomic operations on single variables.
- **Compare and Swap (CAS)**: A fundamental atomic operation used to achieve thread safety by comparing and updating values in a single step.

Understanding and using atomic operations and variables help in building efficient and thread-safe concurrent applications.
