# Circuit Breakers

## What is a Circuit Breaker?

A **Circuit Breaker** is a design pattern used in software development to handle failures and prevent cascading failures in distributed systems. It is commonly used to manage interactions between services and to prevent one service failure from affecting the entire system. The circuit breaker pattern helps to improve system resilience and stability by managing how failures are handled and allowing for graceful recovery.

## Key Concepts

1. **States of a Circuit Breaker**:
    - **Closed**: The circuit breaker is in a normal state, and requests are allowed to pass through. If a certain threshold of failures is reached, the circuit breaker transitions to the Open state.
    - **Open**: The circuit breaker is open, and all requests are automatically rejected or redirected to a fallback. The circuit breaker remains in this state for a predefined time to allow the system to recover.
    - **Half-Open**: After a timeout period, the circuit breaker transitions to the Half-Open state to test if the system has recovered. A limited number of requests are allowed to pass through to check if the failures have been resolved.

2. **Thresholds**:
    - **Failure Threshold**: The percentage or number of failures that triggers the circuit breaker to open.
    - **Timeout**: The duration the circuit breaker remains open before transitioning to the Half-Open state.

3. **Fallback**:
    - A mechanism to handle requests when the circuit breaker is open. This can involve returning a default response or redirecting requests to an alternative service.

## Benefits

- **Prevents Cascading Failures**: Stops failure from spreading to other parts of the system.
- **Improves System Resilience**: Allows services to fail gracefully and recover without impacting the entire system.
- **Enhances System Stability**: Helps in managing service dependencies and improving overall system reliability.

## Example Implementation

### Java Implementation using Resilience4j

Resilience4j is a popular library for implementing circuit breakers in Java applications.

**Dependencies** (Maven):
```xml
<dependency>
<groupId>io.github.resilience4j</groupId>
<artifactId>resilience4j-circuitbreaker</artifactId>
<version>1.7.0</version>
</dependency>
```

**Example Code** (Java using Resilience4j):

```java
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;

import java.time.Duration;
import java.util.function.Supplier;

public class CircuitBreakerExample {
public static void main(String[] args) {
// Create a CircuitBreakerConfig with custom settings
CircuitBreakerConfig config = CircuitBreakerConfig.custom()
.failureRateThreshold(50) // 50% failure rate to open the circuit
.waitDurationInOpenState(Duration.ofSeconds(10)) // Time to wait before transitioning to Half-Open
.slidingWindowSize(10) // Number of calls to consider for calculating failure rate
.build();

        // Create a CircuitBreakerRegistry and a CircuitBreaker with the custom config
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("myCircuitBreaker");

        // Define a supplier with circuit breaker logic
        Supplier<String> supplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> {
            // Simulate a service call that may fail
            if (Math.random() < 0.5) {
                throw new RuntimeException("Service call failed");
            }
            return "Service call succeeded";
        });

        // Execute the supplier with circuit breaker protection
        try {
            String result = supplier.get();
            System.out.println(result);
        } catch (CallNotPermittedException e) {
            System.out.println("Circuit breaker is open. Request rejected.");
        } catch (Exception e) {
            System.out.println("Service call failed: " + e.getMessage());
        }
    }
}
```

## Conclusion

The Circuit Breaker pattern is crucial for maintaining the stability and resilience of distributed systems. By managing failures and preventing cascading issues, circuit breakers help ensure that services can fail gracefully and recover effectively. The provided Java code example demonstrates how to implement a circuit breaker using Resilience4j, allowing you to integrate this pattern into your applications for improved reliability.
