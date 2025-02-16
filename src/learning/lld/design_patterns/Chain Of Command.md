# Chain of Responsibility Pattern

The Chain of Responsibility pattern allows an object to pass a request along a chain of potential handlers until the request is handled. Each handler in the chain either processes the request or passes it along to the next handler in the chain. This pattern decouples the sender of a request from the receiver, giving multiple objects a chance to handle the request.

## Example: Myntra's Checkout Process

In an e-commerce platform like Myntra, the Chain of Responsibility pattern can be used to handle various stages of the checkout process, such as applying discounts, calculating taxes, validating payment, and processing the order.

### Java Code Example

```java
// Handler interface
public abstract class CheckoutHandler {
   protected CheckoutHandler nextHandler;

   public void setNextHandler(CheckoutHandler nextHandler) {
      this.nextHandler = nextHandler;
   }

   public abstract void handleRequest(Order order);
}

// ConcreteHandler for applying discounts
public class DiscountHandler extends CheckoutHandler {
   @Override
   public void handleRequest(Order order) {
      if (order.hasDiscount()) {
         System.out.println("Applying discount");
         order.applyDiscount();
      }
      if (nextHandler != null) {
         nextHandler.handleRequest(order);
      }
   }
}

// ConcreteHandler for calculating taxes
public class TaxHandler extends CheckoutHandler {
   @Override
   public void handleRequest(Order order) {
      System.out.println("Calculating taxes");
      order.calculateTaxes();
      if (nextHandler != null) {
         nextHandler.handleRequest(order);
      }
   }
}

// ConcreteHandler for validating payment
public class PaymentHandler extends CheckoutHandler {
   @Override
   public void handleRequest(Order order) {
      System.out.println("Validating payment");
      if (order.validatePayment()) {
         System.out.println("Payment validated");
         if (nextHandler != null) {
            nextHandler.handleRequest(order);
         }
      } else {
         System.out.println("Payment validation failed");
      }
   }
}

// ConcreteHandler for processing the order
public class OrderProcessingHandler extends CheckoutHandler {
   @Override
   public void handleRequest(Order order) {
      System.out.println("Processing order");
      order.processOrder();
   }
}

// Order class
public class Order {
   private boolean discountApplied;
   private boolean paymentValidated;

   public boolean hasDiscount() {
      return !discountApplied;
   }

   public void applyDiscount() {
      discountApplied = true;
   }

   public void calculateTaxes() {
      // Tax calculation logic
   }

   public boolean validatePayment() {
      // Payment validation logic
      paymentValidated = true;
      return paymentValidated;
   }

   public void processOrder() {
      // Order processing logic
   }
}

// Client code
public class CheckoutClient {
   public static void main(String[] args) {
      Order order = new Order();

      CheckoutHandler discountHandler = new DiscountHandler();
      CheckoutHandler taxHandler = new TaxHandler();
      CheckoutHandler paymentHandler = new PaymentHandler();
      CheckoutHandler orderProcessingHandler = new OrderProcessingHandler();

      discountHandler.setNextHandler(taxHandler);
      taxHandler.setNextHandler(paymentHandler);
      paymentHandler.setNextHandler(orderProcessingHandler);

      discountHandler.handleRequest(order);
   }
}
```

## Explanation

1. **Handlers**:
    - `DiscountHandler` applies discounts if available and passes the request to the next handler.
    - `TaxHandler` calculates taxes and passes the request to the next handler.
    - `PaymentHandler` validates payment and passes the request if payment is successful.
    - `OrderProcessingHandler` processes the order.

2. **Chain Setup**: Handlers are linked together in a sequence, ensuring that each step in the checkout process is handled appropriately.

3. **Order Processing**: When `discountHandler.handleRequest(order)` is called, the request travels through the chain, with each handler performing its task and passing the request to the next handler.

## Use Case

In Myntra, this pattern helps in managing complex checkout processes by separating concerns into different handlers. This design allows for easier maintenance and extensibility. For example, if Myntra needs to introduce a new step in the checkout process, it can add a new handler without modifying existing handlers.

The Chain of Responsibility pattern is particularly useful in scenarios where multiple handlers might process a request, and the sequence of processing might change based on certain conditions.
