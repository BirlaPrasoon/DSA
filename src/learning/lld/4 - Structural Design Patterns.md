# Structural Design Patterns

Structural design patterns deal with object composition and the way objects are composed to form larger structures. These patterns help ensure that if one part of a system changes, the entire system doesn’t need to do the same. They simplify the design by identifying simple ways to realize relationships between entities.

## 1. Adapter

**Definition**: The Adapter pattern allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces, enabling them to interact seamlessly.

**Example**:
```java
// Target interface
public interface Target {
void request();
}

// Adaptee class
public class Adaptee {
public void specificRequest() {
System.out.println("Specific request");
}
}

// Adapter class
public class Adapter implements Target {
private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
```

## 2. Bridge

**Definition**: The Bridge pattern separates an abstraction from its implementation, allowing the two to vary independently. It decouples abstraction from implementation so that they can evolve separately.

**Example**:
```java
// Implementor interface
public interface Implementor {
void operationImpl();
}

// ConcreteImplementor class
public class ConcreteImplementorA implements Implementor {
@Override
public void operationImpl() {
System.out.println("ConcreteImplementorA operation");
}
}

// Abstraction class
public abstract class Abstraction {
protected Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();
}

// RefinedAbstraction class
public class RefinedAbstraction extends Abstraction {
public RefinedAbstraction(Implementor implementor) {
super(implementor);
}

    @Override
    public void operation() {
        implementor.operationImpl();
    }
}
```

## 3. Composite

**Definition**: The Composite pattern allows you to compose objects into tree structures to represent part-whole hierarchies. It enables clients to treat individual objects and compositions of objects uniformly.

**Example**:
```java
// Component interface
public interface Component {
void operation();
}

// Leaf class
public class Leaf implements Component {
@Override
public void operation() {
System.out.println("Leaf operation");
}
}

// Composite class
public class Composite implements Component {
private List<Component> children = new ArrayList<>();

    public void add(Component component) {
        children.add(component);
    }

    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public void operation() {
        for (Component component : children) {
            component.operation();
        }
    }
}
```

## 4. Decorator

**Definition**: The Decorator pattern allows you to dynamically add behavior to an object. It provides a flexible alternative to subclassing for extending functionality.

**Example**:
```java
// Component interface
public interface Component {
void operation();
}

// ConcreteComponent class
public class ConcreteComponent implements Component {
@Override
public void operation() {
System.out.println("ConcreteComponent operation");
}
}

// Decorator class
public abstract class Decorator implements Component {
protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

// ConcreteDecorator class
public class ConcreteDecorator extends Decorator {
public ConcreteDecorator(Component component) {
super(component);
}

    @Override
    public void operation() {
        super.operation();
        addedBehavior();
    }

    private void addedBehavior() {
        System.out.println("ConcreteDecorator added behavior");
    }
}
```

## 5. Facade

**Definition**: The Facade pattern provides a simplified interface to a complex subsystem, making it easier to use. It hides the complexities of the subsystem and provides an easy-to-use interface.

**Example**:
```java
// Subsystem classes
public class SubsystemA {
public void operationA() {
System.out.println("SubsystemA operation");
}
}

public class SubsystemB {
public void operationB() {
System.out.println("SubsystemB operation");
}
}

// Facade class
public class Facade {
private SubsystemA subsystemA;
private SubsystemB subsystemB;

    public Facade() {
        this.subsystemA = new SubsystemA();
        this.subsystemB = new SubsystemB();
    }

    public void complexOperation() {
        subsystemA.operationA();
        subsystemB.operationB();
    }
}
```

## 6. Flyweight

**Definition**: The Flyweight pattern reduces the number of objects created by sharing objects. It is used when a large number of objects are needed but they share common state.

**Example**:
```java
// Flyweight interface
public interface Flyweight {
void operation();
}

// ConcreteFlyweight class
public class ConcreteFlyweight implements Flyweight {
private String intrinsicState;

    public ConcreteFlyweight(String state) {
        this.intrinsicState = state;
    }

    @Override
    public void operation() {
        System.out.println("Flyweight with state: " + intrinsicState);
    }
}

// FlyweightFactory class
public class FlyweightFactory {
private Map<String, Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(String state) {
        Flyweight flyweight = flyweights.get(state);
        if (flyweight == null) {
            flyweight = new ConcreteFlyweight(state);
            flyweights.put(state, flyweight);
        }
        return flyweight;
    }
}
```

## 7. Proxy

**Definition**: The Proxy pattern provides a surrogate or placeholder for another object. It controls access to the original object and can perform additional operations before or after forwarding requests to it.

**Example**:
```java
// Subject interface
public interface Subject {
void request();
}

// RealSubject class
public class RealSubject implements Subject {
@Override
public void request() {
System.out.println("RealSubject request");
}
}

// Proxy class
public class Proxy implements Subject {
private RealSubject realSubject;

    @Override
    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        System.out.println("Proxy operation");
        realSubject.request();
    }
}
```

## Conclusion

Structural design patterns help in creating a system that is easy to understand and maintain by defining simple ways to realize relationships between entities. They ensure that if one part of the system changes, the entire system doesn’t need to change. Understanding and applying these patterns can greatly enhance the flexibility and robustness of your designs.
