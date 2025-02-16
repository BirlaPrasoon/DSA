# Behavioral Design Patterns

Behavioral design patterns focus on how objects interact and communicate with one another. They are concerned with algorithms and the assignment of responsibilities between objects. These patterns help define the way objects collaborate and communicate, making it easier to manage complex behaviors.

## 1. Chain of Responsibility

**Definition**: The Chain of Responsibility pattern allows multiple objects to handle a request without the sender needing to know which object will handle it. Each handler in the chain either processes the request or passes it along to the next handler.

**Example**:
```java
public abstract class Handler {
protected Handler nextHandler;

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    public abstract void handleRequest(int request);
}

public class ConcreteHandlerA extends Handler {
@Override
public void handleRequest(int request) {
if (request < 10) {
System.out.println("Handler A processed request: " + request);
} else if (nextHandler != null) {
nextHandler.handleRequest(request);
}
}
}

public class ConcreteHandlerB extends Handler {
@Override
public void handleRequest(int request) {
if (request >= 10 && request < 20) {
System.out.println("Handler B processed request: " + request);
} else if (nextHandler != null) {
nextHandler.handleRequest(request);
}
}
}
```

## 2. Command

**Definition**: The Command pattern encapsulates a request as an object, thereby allowing parameterization of clients with queues, requests, and operations. It decouples the sender of a request from the receiver, allowing requests to be executed at different times.

**Example**:
```java
public interface Command {
void execute();
}

public class LightOnCommand implements Command {
private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

public class Light {
public void turnOn() {
System.out.println("Light is ON");
}

    public void turnOff() {
        System.out.println("Light is OFF");
    }
}
```

## 3. Interpreter

**Definition**: The Interpreter pattern defines a grammatical representation for a language and provides an interpreter to interpret sentences of the language. It is useful for implementing languages or expression interpreters.

**Example**:
```java
public interface Expression {
boolean interpret(String context);
}

public class TerminalExpression implements Expression {
private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }
}
```

## 4. Iterator

**Definition**: The Iterator pattern provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation. It allows for traversing a collection without exposing its internal structure.

**Example**:
```java
public interface Iterator {
boolean hasNext();
Object next();
}

public interface Collection {
Iterator createIterator();
}

public class ConcreteIterator implements Iterator {
private List<Object> items;
private int position;

    public ConcreteIterator(List<Object> items) {
        this.items = items;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < items.size();
    }

    @Override
    public Object next() {
        return items.get(position++);
    }
}

public class ConcreteCollection implements Collection {
private List<Object> items = new ArrayList<>();

    public void addItem(Object item) {
        items.add(item);
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(items);
    }
}
```

## 5. Mediator

**Definition**: The Mediator pattern defines an object that encapsulates how a set of objects interact, promoting loose coupling by keeping objects from referring to each other explicitly. It facilitates communication between objects through a central mediator.

**Example**:
```java
public interface Mediator {
void notify(Object sender, String event);
}

public class ConcreteMediator implements Mediator {
private Colleague1 colleague1;
private Colleague2 colleague2;

    public void setColleague1(Colleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(Colleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    @Override
    public void notify(Object sender, String event) {
        if (sender == colleague1) {
            colleague2.doSomething();
        } else if (sender == colleague2) {
            colleague1.doSomething();
        }
    }
}

public class Colleague1 {
private Mediator mediator;

    public Colleague1(Mediator mediator) {
        this.mediator = mediator;
    }

    public void doSomething() {
        System.out.println("Colleague1 does something");
        mediator.notify(this, "event1");
    }
}

public class Colleague2 {
private Mediator mediator;

    public Colleague2(Mediator mediator) {
        this.mediator = mediator;
    }

    public void doSomething() {
        System.out.println("Colleague2 does something");
        mediator.notify(this, "event2");
    }
}
```

## 6. Memento

**Definition**: The Memento pattern captures and externalizes an object```s internal state without violating encapsulation, allowing the object to be restored to that state later. It provides a way to restore an object to its previous state.

**Example**:
```java
public class Memento {
private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

public class Originator {
private String state;

    public void setState(String state) {
        this.state = state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}

public class Caretaker {
private Memento memento;

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

    public Memento getMemento() {
        return memento;
    }
}
```

## 7. Observer

**Definition**: The Observer pattern defines a dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. It allows an object (the subject) to notify other objects (observers) about changes.

**Example**:
```java
public interface Observer {
void update(String message);
}

public class ConcreteObserver implements Observer {
private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}

public class Subject {
private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
```

## 8. State

**Definition**: The State pattern allows an object to alter its behavior when its internal state changes, making the object appear to change its class. It is used to implement state machines.

**Example**:
```java
public interface State {
void handleRequest();
}

public class ConcreteStateA implements State {
@Override
public void handleRequest() {
System.out.println("Handling request in State A");
}
}

public class ConcreteStateB implements State {
@Override
public void handleRequest() {
System.out.println("Handling request in State B");
}
}

public class Context {
private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void request() {
        state.handleRequest();
    }
}
```

## 9. Strategy

**Definition**: The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable, allowing the algorithm to vary independently from clients that use it.

**Example**:
```java
public interface Strategy {
void execute();
}

public class ConcreteStrategyA implements Strategy {
@Override
public void execute() {
System.out.println("Strategy A executed");
}
}

public class ConcreteStrategyB implements Strategy {
@Override
public void execute() {
System.out.println("Strategy B executed");
}
}

public class Context {
private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.execute();
    }
}
```

## 10. Template Method

**Definition**: The Template Method pattern defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.

**Example**:
```java
public abstract class AbstractClass {
public void templateMethod() {
stepOne();
stepTwo();
stepThree();
}

    protected abstract void stepOne();
    protected abstract void stepTwo();
    protected void stepThree() {
        System.out.println("Default implementation of step three");
    }
}

public class ConcreteClass extends AbstractClass {
@Override
protected void stepOne() {
System.out.println("ConcreteClass implementation of step one");
}

    @Override
    protected void stepTwo() {
        System.out.println("ConcreteClass implementation of step two");
    }
}
```

## 11. Visitor

**Definition**: The Visitor pattern defines a new operation to a set of objects without changing the classes of the elements on which it operates. It allows adding new operations to objects without modifying their classes.

**Example**:
```java
public interface Visitor {
void visit(Element element);
}

public class ConcreteVisitor implements Visitor {
@Override
public void visit(Element element) {
System.out.println("Visiting element");
}
}

public interface Element {
void accept(Visitor visitor);
}

public class ConcreteElement implements Element {
@Override
public void accept(Visitor visitor) {
visitor.visit(this);
}
}
```

## Conclusion

Behavioral design patterns offer ways to manage algorithms, relationships, and responsibilities between objects. By understanding and applying these patterns, developers can design more flexible and effective communication strategies between components of their systems.
