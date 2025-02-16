package multithreading_practicals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final Lock lock = new ReentrantLock();
    private CountDownLatch latch;
    private int count = 0;

    public void increment() throws InterruptedException {
//        lock.lock();
        latch = new CountDownLatch(1);
        try {
            long id = Thread.currentThread().getId();
//            System.out.println("Thread ID : "+ id);
            count++;
            latch.countDown();
        } finally {
//            lock.unlock();
        }
        latch.await();
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();

        // Creating two threads that increment the count
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                try {
                    example.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                try {
                    example.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
