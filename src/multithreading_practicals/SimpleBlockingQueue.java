package multithreading_practicals;

import java.util.LinkedList;

public class SimpleBlockingQueue<T> {
    private final LinkedList<T> queue = new LinkedList<>();
    private final int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void put(T value) throws InterruptedException {
        while (queue.size() == limit) {
            wait(); // Wait until space is available
        }
        queue.add(value);
        notifyAll(); // Notify consumers that an item is available
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait until an item is available
        }
        T value = queue.removeFirst();
        notifyAll(); // Notify producers that space is available
        return value;
    }

    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);

        long start = System.currentTimeMillis();
        // Producer Thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    queue.put(i);
                    System.out.println("Producer Queue Size :  " + queue.size());
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer Thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    Integer value = queue.take();
                    System.out.println("Consumer Queue Size :  " + queue.size());
                    System.out.println("Consumed: " + value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
            System.out.println("Time Taken: " + (System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
