# Strategy Pattern

The Strategy Pattern is a behavioral design pattern that enables a client to choose an algorithm from a family of algorithms at runtime. It defines a set of algorithms, encapsulates each one, and makes them interchangeable. The client can switch between algorithms without modifying the code that uses them.

## Definition

The Strategy Pattern allows for the selection of a specific algorithm or strategy at runtime from a family of algorithms. It involves creating a strategy interface and implementing various concrete strategies. The client code interacts with the strategy interface and can switch strategies as needed.

## Components

1. **Strategy Interface**: Defines a common interface for all supported algorithms.
2. **Concrete Strategies**: Implement different variations of the algorithm defined in the strategy interface.
3. **Context**: Maintains a reference to the current strategy and can switch between strategies.

## Example: Payment Gateways

In an e-commerce platform that supports multiple payment gateways, the Strategy Pattern can be used to encapsulate different payment processing strategies (e.g., PayPal, Stripe, RazorPay) and allow users to select their preferred payment method at runtime.

### Java Code Example

```java
// Strategy interface
public interface PaymentStrategy {
    void processPayment(double amount);
}

// ConcreteStrategy for PayPal
public class PayPalPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using PayPal.");
    }
}

// ConcreteStrategy for Stripe
public class StripePayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using Stripe.");
    }
}

// ConcreteStrategy for RazorPay
public class RazorPayPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using RazorPay.");
    }
}

// Context class
public class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount) {
        strategy.processPayment(amount);
    }
}

// Client code
public class PaymentClient {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        PaymentStrategy paypal = new PayPalPayment();
        PaymentStrategy stripe = new StripePayment();
        PaymentStrategy razorpay = new RazorPayPayment();

        context.setStrategy(paypal);
        context.executePayment(100.0);

        context.setStrategy(stripe);
        context.executePayment(200.0);

        context.setStrategy(razorpay);
        context.executePayment(300.0);
    }
}
```

## Explanation

1. **Strategy Interface**: `PaymentStrategy` defines the `processPayment` method.
2. **Concrete Strategies**: `PayPalPayment`, `StripePayment`, and `RazorPayPayment` implement the `PaymentStrategy` interface and provide specific implementations for payment processing.
3. **Context**: `PaymentContext` maintains a reference to the current payment strategy and can switch between different strategies.
4. **Client**: `PaymentClient` sets the payment strategy and executes the payment.

## Use Case

The Strategy Pattern allows the e-commerce platform to handle different payment methods interchangeably. The client code (e.g., `PaymentClient`) can switch between various payment strategies at runtime without changing the underlying code that uses these strategies. This design makes it easy to add or change payment gateways without modifying the existing codebase.
