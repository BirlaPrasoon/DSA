package multithreading_practicals;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(15);

        // Creating three threads that count down the latch
//        latch.countDown();
        for (int i = 0; i < 10; i++) {
            final int threadId = i;
            new Thread(() -> {
                System.out.println("Thread " + threadId + " is running");
                latch.countDown();
            }).start();
        }
        for (int i = 0; i < latch.getCount(); i++) {
            final int threadId = i;
            latch.countDown();
        }
        latch.await();
        // Main thread waits for the count to reach zero

        System.out.println("All threads have completed.");
    }
}
