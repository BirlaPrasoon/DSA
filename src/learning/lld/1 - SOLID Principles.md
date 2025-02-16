# SOLID Principles

SOLID is an acronym representing five design principles in object-oriented programming that help create more understandable, flexible, and maintainable software. These principles were introduced by Robert C. Martin and are essential for achieving good software design.

## 1. Single Responsibility Principle (SRP)

**Definition**: A class should have only one reason to change, meaning it should have only one responsibility or job.

**Example**:

- **Bad Example**:
  ```java
  public class Report {
  public void generateReport() {
  // Code to generate report
  }

      public void saveToFile() {
          // Code to save report to file
      }
  }
  ```
  In this example, the `Report` class has two responsibilities: generating a report and saving it to a file.

- **Good Example**:
  ```java
  public class Report {
  public void generateReport() {
  // Code to generate report
  }
  }

  public class ReportSaver {
  public void saveToFile(Report report) {
  // Code to save report to file
  }
  }
  ```
  In this example, `Report` has a single responsibility (generating the report), and `ReportSaver` handles saving the report to a file. This adheres to the SRP.

## 2. Open/Closed Principle (OCP)

**Definition**: Software entities (e.g., classes, modules, functions) should be open for extension but closed for modification. This means that you should be able to add new functionality without changing existing code.

**Example**:

- **Bad Example**:
  ```java
  public class Shape {
  public enum ShapeType { CIRCLE, SQUARE }
  private ShapeType type;

      public double calculateArea() {
          if (type == ShapeType.CIRCLE) {
              // Calculate area of circle
          } else if (type == ShapeType.SQUARE) {
              // Calculate area of square
          }
          return 0;
      }
  }
  ```
  Adding a new shape type requires modifying the `calculateArea` method.

- **Good Example**:
  ```java
  public interface Shape {
  double calculateArea();
  }

  public class Circle implements Shape {
  @Override
  public double calculateArea() {
  // Calculate area of circle
  }
  }

  public class Square implements Shape {
  @Override
  public double calculateArea() {
  // Calculate area of square
  }
  }
  ```
  New shapes can be added by implementing the `Shape` interface without modifying existing code.

## 3. Liskov Substitution Principle (LSP)

**Definition**: Subtypes must be substitutable for their base types without altering the correctness of the program. This means that objects of a derived class should be able to replace objects of the base class without affecting the functionality.

**Example**:

- **Bad Example**:
  ```java
  public class Bird {
  public void fly() {
  // Bird flying
  }
  }

  public class Penguin extends Bird {
  @Override
  public void fly() {
  throw new UnsupportedOperationException("Penguins can```t fly");
  }
  }
  ```
  Substituting a `Penguin` for a `Bird` violates LSP because a `Penguin` cannot fly.

- **Good Example**:
  ```java
  public abstract class Bird {
  // Common bird behaviors
  }

  public class Sparrow extends Bird {
  public void fly() {
  // Sparrow flying
  }
  }

  public class Penguin extends Bird {
  // No fly method in Penguin
  }
  ```
  In this example, `Penguin` does not have a `fly` method, adhering to LSP as it does not introduce an unsupported operation.

## 4. Interface Segregation Principle (ISP)

**Definition**: Clients should not be forced to depend on interfaces they do not use. This means that an interface should have a specific purpose and should not include methods that are irrelevant to some of its implementers.

**Example**:

- **Bad Example**:
  ```java
  public interface Worker {
  void work();
  void eat();
  }

  public class HumanWorker implements Worker {
  @Override
  public void work() {
  // Human working
  }

      @Override
      public void eat() {
          // Human eating
      }
  }

  public class RobotWorker implements Worker {
  @Override
  public void work() {
  // Robot working
  }

      @Override
      public void eat() {
          // Robots don```t eat
          throw new UnsupportedOperationException("Robots don```t eat");
      }
  }
  ```
  The `RobotWorker` class is forced to implement the `eat` method, which is not relevant to it.

- **Good Example**:
  ```java
  public interface Workable {
  void work();
  }

  public interface Eatable {
  void eat();
  }

  public class HumanWorker implements Workable, Eatable {
  @Override
  public void work() {
  // Human working
  }

      @Override
      public void eat() {
          // Human eating
      }
  }

  public class RobotWorker implements Workable {
  @Override
  public void work() {
  // Robot working
  }
  }
  ```
  In this example, `HumanWorker` implements both `Workable` and `Eatable`, while `RobotWorker` only implements `Workable`, adhering to ISP.

## 5. Dependency Inversion Principle (DIP)

**Definition**: High-level modules should not depend on low-level modules. Both should depend on abstractions. Abstractions should not depend on details. Details should depend on abstractions. This principle promotes the decoupling of components.

**Example**:

- **Bad Example**:
  ```java
  public class LightBulb {
  public void turnOn() {
  // Light bulb turned on
  }

      public void turnOff() {
          // Light bulb turned off
      }
  }

  public class Switch {
  private LightBulb bulb;

      public Switch(LightBulb bulb) {
          this.bulb = bulb;
      }

      public void operate() {
          // Operate the bulb
      }
  }
  ```
  The `Switch` class is tightly coupled to the `LightBulb` class.

- **Good Example**:
  ```java
  public interface Switchable {
  void turnOn();
  void turnOff();
  }

  public class LightBulb implements Switchable {
  @Override
  public void turnOn() {
  // Light bulb turned on
  }

      @Override
      public void turnOff() {
          // Light bulb turned off
      }
  }

  public class Switch {
  private Switchable device;

      public Switch(Switchable device) {
          this.device = device;
      }

      public void operate() {
          // Operate the device
      }
  }
  ```
  In this example, the `Switch` class depends on the `Switchable` interface rather than a specific implementation, promoting flexibility and adherence to DIP.

## Conclusion

The SOLID principles are fundamental for designing robust, maintainable, and scalable object-oriented systems. By following these principles, developers can create code that is easier to understand, extend, and modify, leading to higher-quality software.
