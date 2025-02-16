package multithreading_practicals;

import java.util.concurrent.Phaser;

public class PhaserExample {
    private static final int NUMBER_OF_THREADS = 3;

    public static void main(String[] args) {
        // Create a Phaser with initial number of parties
        Phaser phaser = new Phaser(NUMBER_OF_THREADS);

        // Create and start threads
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            new Thread(new Task(phaser, i + 1)).start();
        }
    }

    static class Task implements Runnable {
        private final Phaser phaser;
        private final int threadNumber;

        public Task(Phaser phaser, int threadNumber) {
            this.phaser = phaser;
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            try {
                // Phase 1: Simulate some work
                System.out.println("Thread " + threadNumber + " is working in phase 1...");
                Thread.sleep((long) (Math.random() * 1000));

                // Arrive and wait for others
//                phaser.arrive();
                int number = phaser.arrive();
                System.out.println("Arrived!!! awaiting in advance " + threadNumber);
                phaser.awaitAdvance(number);
                System.out.println("Advance complete + "+ threadNumber);

                // Phase 2: Simulate some work
                System.out.println("Thread " + threadNumber + " is working in phase 2...");
                Thread.sleep((long) (Math.random() * 1000));

                // Arrive and wait for others
                phaser.arriveAndAwaitAdvance();

                // Phase 3: Final work
                System.out.println("Thread " + threadNumber + " is working in phase 3...");
                Thread.sleep((long) (Math.random() * 1000));

                // Arrive and wait for others
                phaser.arriveAndAwaitAdvance();

                // Phase 3: Final work
                System.out.println("Thread " + threadNumber + " is working in phase 4...");
                Thread.sleep((long) (Math.random() * 1000));

                // Final arrival
                phaser.arrive();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

