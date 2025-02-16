package multithreading_practicals;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Experiments {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        Thread t = new Thread(() ->{
            try {
                System.out.println("Sleeping for 1 sec");
                Thread.sleep(new Random().nextInt(1000, 5000));
                System.out.println("Wokeup, releasing the semaphore");
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();
        System.out.println("Acquiring the thread");
        semaphore.acquire();
        System.out.println("This should not be printed!!!");
        System.out.println("Now I'm releasing!!");
        semaphore.release();
    }
}
