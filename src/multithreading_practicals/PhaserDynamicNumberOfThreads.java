package multithreading_practicals;

import java.util.concurrent.Phaser;

public class PhaserDynamicNumberOfThreads {

    public class DynamicPhaserExample {
        private static final int TOTAL_THREADS = 5;

        public static void main(String[] args) {
            Phaser phaser = new Phaser();

            // Start some initial threads
            for (int i = 0; i < TOTAL_THREADS; i++) {
                new Thread(new Task(phaser, i + 1)).start();
            }
        }

        static class Task implements Runnable {
            private final Phaser phaser;
            private final int threadNumber;

            public Task(Phaser phaser, int threadNumber) {
                this.phaser = phaser;
                this.threadNumber = threadNumber;
                // Register this thread with the phaser
                phaser.register();
            }

            @Override
            public void run() {
                try {
                    // Phase 1
                    System.out.println("Thread " + threadNumber + " is working in phase 1...");
                    Thread.sleep((long) (Math.random() * 1000));
                    phaser.arriveAndAwaitAdvance();

                    // Phase 2
                    System.out.println("Thread " + threadNumber + " is working in phase 2...");
                    Thread.sleep((long) (Math.random() * 1000));
                    phaser.arriveAndAwaitAdvance();

                    // Final Phase
                    System.out.println("Thread " + threadNumber + " is working in the final phase...");
                    Thread.sleep((long) (Math.random() * 1000));
                    // Deregister this thread
                    phaser.arriveAndDeregister();
                    System.out.println("Thread " + threadNumber + " has completed all phases.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
