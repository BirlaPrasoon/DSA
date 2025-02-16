# Thread Lifecycle in Java

Understanding the thread lifecycle is crucial for managing threads effectively in Java. A thread's lifecycle encompasses various states from its creation to its termination. Below is a detailed overview of the thread lifecycle and the different states a thread can be in.

## Thread States

A thread in Java can be in one of the following states:

1. **New (Born)**
    - **Description:** The thread is in the new state immediately after it is created but before the `start()` method is called.
    - **Example:** `Thread t = new Thread();` - At this point, the thread is in the "New" state.
    - **Transition:** Moves to the Runnable state when `start()` is called.

2. **Runnable**
    - **Description:** The thread is in the runnable state when it is eligible to run but is not necessarily running at the moment. The thread scheduler determines when it actually runs.
    - **Example:** `t.start();` - After starting the thread, it enters the runnable state.
    - **Transition:** Can transition to the Running state if the thread scheduler picks it to run or back to the Runnable state if it is preempted or blocked.

3. **Running**
    - **Description:** The thread is in the running state when it is currently executing its code.
    - **Example:** When the thread is actively executing the `run()` method.
    - **Transition:** Can transition to the Blocked or Waiting state if it is waiting for a resource or synchronization, or back to Runnable if it is preempted.

4. **Blocked**
    - **Description:** The thread is in the blocked state when it is waiting for a monitor lock to enter a synchronized block or method.
    - **Example:** When a thread tries to acquire a lock on an object that is held by another thread.
    - **Transition:** Moves to the Runnable state when the lock is available.

5. **Waiting**
    - **Description:** The thread is in the waiting state when it is waiting indefinitely for another thread to perform a particular action.
    - **Example:** When a thread calls `Object.wait()` without a timeout or `Thread.join()`.
    - **Transition:** Moves to the Runnable state when it is notified (using `notify()` or `notifyAll()`) or when the specified condition occurs.

6. **Timed Waiting**
    - **Description:** The thread is in the timed waiting state when it is waiting for a specified period of time for another thread to perform a particular action.
    - **Example:** When a thread calls `Thread.sleep()` or `Object.wait()` with a timeout.
    - **Transition:** Moves to the Runnable state when the timeout expires or when the condition is met.

7. **Terminated (Dead)**
    - **Description:** The thread is in the terminated state when it has completed its execution or has been terminated due to an exception or a call to `Thread.stop()`.
    - **Example:** After the `run()` method completes, the thread enters the terminated state.
    - **Transition:** The thread cannot transition to any other state once it has reached the terminated state.

## Thread Lifecycle Diagram

# Thread Lifecycle Diagram

```plaintext
      +------------------+
      |       New        |
      +------------------+
              |
              v
      +------------------+
      |     Runnable     |
      +------------------+
       /         |         \
      /          |          \
     v           v           v
+---------+  +--------+   +---------------+
| Running |  | Blocked|   | Waiting/Timed |
+---------+  +--------+   |    Waiting    |
      |          |        +---------------+
      |          |                |
      |          |                v
      |          +-------------->+--------+
      |                           | Terminated |
      +-------------------------->+--------+
```


## Summary

- **New:** The thread has been created but not yet started.
- **Runnable:** The thread is ready to run and waiting for CPU time.
- **Running:** The thread is currently executing.
- **Blocked:** The thread is waiting for a monitor lock.
- **Waiting:** The thread is waiting indefinitely for a condition.
- **Timed Waiting:** The thread is waiting for a specified period of time.
- **Terminated:** The thread has finished execution or has been terminated.

Understanding these states and transitions is essential for managing thread behavior and optimizing performance in concurrent applications.
