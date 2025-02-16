package multithreading_practicals;

import java.util.concurrent.Semaphore;

public class H2oWithSemaphores {
    class H2O {

        private final Semaphore hSemaphore;// = new Semaphore();
        private final Semaphore oSemaphore;// = new Semaphore();

        public H2O() {
            this.hSemaphore = new Semaphore(2);
            this.oSemaphore = new Semaphore(0);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hSemaphore.acquire();
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            oSemaphore.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oSemaphore.acquire();
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            hSemaphore.release(2);
        }
    }
}
