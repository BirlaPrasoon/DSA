package multithreading_practicals;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ExploringSemaphores {

    private final Integer NUM_USERS = 99;
    private final Semaphore semaphore;

    ExploringSemaphores() {
        semaphore = new Semaphore(NUM_USERS);
        Thread t = new Thread(this::printStatus);
        t.start();
    }

    private boolean tryLogin() {
        return semaphore.tryAcquire();
    }

    public void logout() {
        semaphore.release();
    }

    private void printStatus() {
        while(true) {
            try{
                Thread.sleep(1000);
                System.out.println("NUM SUERS: " + (NUM_USERS - semaphore.availablePermits()));
            } catch (InterruptedException e){
                throw new RuntimeException();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int NUM_REQUESTS = 1000;
        ExploringSemaphores server = new ExploringSemaphores();
        Random random = new Random();
        var executor = Executors.newFixedThreadPool(NUM_REQUESTS);
        for (int i = 0; i < NUM_REQUESTS; i++) {
            executor.submit(() -> {
                try {
                    while (!server.tryLogin()) Thread.sleep(random.nextInt(1000));
                    Thread.sleep(random.nextInt(1000));
                    server.logout();
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            });
        }
    }
}
