package multithreading_practicals.leetcode;

import java.util.concurrent.Semaphore;

public class H2O {

    class SolutionSemaphore {
        private final Semaphore hydrogenSemaphore;
        private final Semaphore oxygenSemaphore;

        public SolutionSemaphore() {
            hydrogenSemaphore = new Semaphore(2);
            oxygenSemaphore = new Semaphore(0);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hydrogenSemaphore.acquire();

            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            if(hydrogenSemaphore.availablePermits()==0)
                oxygenSemaphore.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oxygenSemaphore.acquire();

            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();

            hydrogenSemaphore.release(2);
        }
    }

    class H2OSynchronized {

        private int hCount;

        public H2OSynchronized() {
            this.hCount = 0;
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            synchronized (this) {
                while (hCount == 2) {
                    wait();
                }
                // releaseHydrogen.run() outputs "H". Do not change or remove this line.
                releaseHydrogen.run();
                hCount++;
                notifyAll();
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            synchronized (this) {
                while (hCount < 2) {
                    wait();
                }
                // releaseOxygen.run() outputs "O". Do not change or remove this line.
                releaseOxygen.run();
                hCount = 0;
                notify();
            }
        }
    }
}