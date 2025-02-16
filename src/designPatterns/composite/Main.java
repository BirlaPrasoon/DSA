package designPatterns.composite;

import designPatterns.composite.products.Book;
import designPatterns.composite.products.VideoGame;

public class Main {
    public static void main(String[] args) {
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setupOrder(
                new CompositeBox(
                        new VideoGame("Some Video Game", 231)
                ),
                new CompositeBox(
                        new CompositeBox(
                                new Book("Book 1", 123),
                                new Book("Book 2", 12312),
                                new VideoGame("VG 1", 131)
                        ),
                        new CompositeBox(
                                new Book("Book 2", 123123),
                                new Book("alksdjf", 123)
                        )
                )
        );
        System.out.println("Total price: " + deliveryService.calculateOrderPrice());
    }
}
