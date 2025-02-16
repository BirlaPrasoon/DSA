# Command Pattern

The Command Pattern is a behavioral design pattern that encapsulates a request as an object, thereby allowing users to parameterize clients with queues, requests, and operations. It also provides support for undoable operations.

## Definition

The Command Pattern decouples the sender of a request from the receiver by encapsulating the request as an object. This object contains all the information needed to perform the action, including the method call, method arguments, and the object on which the method is invoked.

## Components

1. **Command**: Defines an interface for executing operations.
2. **ConcreteCommand**: Implements the Command interface and defines the binding between a Receiver object and an action.
3. **Client**: Creates a ConcreteCommand object and sets its receiver.
4. **Invoker**: Asks the command to execute the request.
5. **Receiver**: Knows how to perform the operations to satisfy a request.

## Example: RazorPay Payment Processing

In a payment system like RazorPay, the Command Pattern can be used to handle various payment operations such as creating payments, processing refunds, and checking payment status. Each operation can be encapsulated as a command object.

### Java Code Example

```java
// Command interface
public interface PaymentCommand {
    void execute();
}

// Receiver class for RazorPay payment operations
public class RazorPayPaymentProcessor {
    public void createPayment() {
        System.out.println("Creating payment via RazorPay.");
    }

    public void processRefund() {
        System.out.println("Processing refund via RazorPay.");
    }

    public void checkPaymentStatus() {
        System.out.println("Checking payment status via RazorPay.");
    }
}

// ConcreteCommand class for creating payment
public class CreatePaymentCommand implements PaymentCommand {
    private RazorPayPaymentProcessor paymentProcessor;

    public CreatePaymentCommand(RazorPayPaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @Override
    public void execute() {
        paymentProcessor.createPayment();
    }
}

// ConcreteCommand class for processing refund
public class ProcessRefundCommand implements PaymentCommand {
    private RazorPayPaymentProcessor paymentProcessor;

    public ProcessRefundCommand(RazorPayPaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @Override
    public void execute() {
        paymentProcessor.processRefund();
    }
}

// ConcreteCommand class for checking payment status
public class CheckPaymentStatusCommand implements PaymentCommand {
    private RazorPayPaymentProcessor paymentProcessor;

    public CheckPaymentStatusCommand(RazorPayPaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @Override
    public void execute() {
        paymentProcessor.checkPaymentStatus();
    }
}

// Invoker class
public class PaymentInvoker {
    private PaymentCommand command;

    public void setCommand(PaymentCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}

// Client code
public class PaymentClient {
    public static void main(String[] args) {
        RazorPayPaymentProcessor paymentProcessor = new RazorPayPaymentProcessor();

        PaymentCommand createPayment = new CreatePaymentCommand(paymentProcessor);
        PaymentCommand processRefund = new ProcessRefundCommand(paymentProcessor);
        PaymentCommand checkPaymentStatus = new CheckPaymentStatusCommand(paymentProcessor);

        PaymentInvoker invoker = new PaymentInvoker();

        // Create payment
        invoker.setCommand(createPayment);
        invoker.executeCommand();

        // Process refund
        invoker.setCommand(processRefund);
        invoker.executeCommand();

        // Check payment status
        invoker.setCommand(checkPaymentStatus);
        invoker.executeCommand();
    }
}

```

## Explanation

1. **Command Interface**: `PaymentCommand` defines the `execute` method.
2. **ConcreteCommand Classes**: `CreatePaymentCommand`, `ProcessRefundCommand`, and `CheckPaymentStatusCommand` implement `PaymentCommand` and encapsulate the requests to be performed by the `RazorPayPaymentProcessor`.
3. **Receiver**: `RazorPayPaymentProcessor` knows how to perform payment operations.
4. **Invoker**: `PaymentInvoker` takes a command and executes it.
5. **Client**: `PaymentClient` sets commands and triggers their execution via the invoker.

## Use Case

In RazorPay, the Command Pattern helps manage various payment-related operations by encapsulating each operation into command objects. This design makes it easier to extend the system by adding new operations without modifying existing code. It also supports undoable operations if needed by implementing additional functionality in the command objects.
