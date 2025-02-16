package designPatterns.strategy;

import designPatterns.strategy.strategy.PaymentStrategy;

public class PaymentService {

    int cost;
    private boolean includeDelivery = true;
    private PaymentStrategy strategy;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isIncludeDelivery() {
        return includeDelivery;
    }

    public void setIncludeDelivery(boolean includeDelivery) {
        this.includeDelivery = includeDelivery;
    }

    public PaymentStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processOrder(int cost) {
        this.cost = cost;
        strategy.collectPaymentDetails();
        if (strategy.validatePaymentDetails()) {
            strategy.pay(getTotal());
        }
    }

    private int getTotal() {
        return includeDelivery ? cost + 10 : cost;
    }
}
