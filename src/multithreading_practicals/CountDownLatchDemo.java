package multithreading_practicals;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {


    public class CountDownLatchExample {

        public static void main(String[] args) {
            // Number of threads to wait for
            int numberOfThreads = 3;
            CountDownLatch latch = new CountDownLatch(numberOfThreads);

            // Create and start threads
            for (int i = 0; i < numberOfThreads; i++) {
                new Thread(new Worker(latch)).start();
            }

            try {
                // Main thread will wait until the latch counts down to zero
                latch.await();
                System.out.println("All threads have completed their tasks.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Worker implements Runnable {
        private final CountDownLatch latch;

        public Worker(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                // Simulating work with sleep
                Thread.sleep((int) (Math.random() * 1000));
                System.out.println(Thread.currentThread().getName() + " has finished work.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Count down the latch
                latch.countDown();
            }
        }
    }

}
