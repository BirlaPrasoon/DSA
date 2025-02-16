package multithreading_practicals;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    private static final int NUMBER_OF_THREADS = 3;

    public static void main(String[] args) {
        // Create a CyclicBarrier that will wait for 3 threads
        CyclicBarrier barrier = new CyclicBarrier(NUMBER_OF_THREADS/*, () -> {
            System.out.println("All threads have reached the barrier, proceeding...");
            try {
                System.out.println("Sleeping for 5 secs");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/);

        // Create and start threads
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            new Thread(new Task(barrier, i + 1)).start();
        }
    }

    static class Task implements Runnable {
        private final CyclicBarrier barrier;
        private final int threadNumber;

        public Task(CyclicBarrier barrier, int threadNumber) {
            this.barrier = barrier;
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            try {
                // Simulate some work
                System.out.println("Thread " + threadNumber + " is working...");
                Thread.sleep((long) (Math.random() * 1000));

                // Wait for other threads at the barrier
                System.out.println("Thread " + threadNumber + " has reached the barrier.");
                barrier.await(); // Wait at the barrier

                barrier.reset();
                System.out.println("Reset complete for. "+ threadNumber + " ...");
                // Continue with the rest of the task
                barrier.await();
                System.out.println("Thread " + threadNumber + " is proceeding after the barrier.");
                barrier.reset();
                System.out.println("Barrier 2nd phase complete!!! for thread " + threadNumber);
                barrier.await();
//                System.out.println("Final point complete!!! for " + threadNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
