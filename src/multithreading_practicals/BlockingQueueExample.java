package multithreading_practicals;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> queue = new ArrayBlockingQueue<>(5);

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    queue.offer(i);
                    System.out.println("Queue Size from Producer" + queue.size());
                    System.out.println("Produced: " + i);
                    Thread.sleep(100); // Simulating time delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    Integer value = queue.poll();
                    System.out.println("Queue Size from Consumer" + queue.size());
                    System.out.println("Consumed: " + value);
                    Thread.sleep(200); // Simulating time delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
