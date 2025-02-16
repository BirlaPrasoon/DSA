package designPatterns.strategy.strategy;

public interface PaymentStrategy {

    void collectPaymentDetails();
    boolean validatePaymentDetails();
    void pay(int amount);

}
