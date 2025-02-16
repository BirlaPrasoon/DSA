package designPatterns.strategy;

import designPatterns.strategy.strategy.PaymentByCreditCard;
import designPatterns.strategy.strategy.PaymentByPaypal;

public class Main {

    /*
     * Video Reference: https://youtu.be/Nrwj3gZiuJU
     */
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        // The strategy can now be easily picked at runtime

        paymentService.setStrategy(new PaymentByCreditCard());
        paymentService.processOrder(100);

        System.out.println("==========================================");

        paymentService.setStrategy(new PaymentByPaypal());
        paymentService.processOrder(100);
    }
}
