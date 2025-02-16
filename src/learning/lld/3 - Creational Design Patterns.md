# Creational Design Patterns

Creational design patterns deal with object creation mechanisms, trying to create objects in a manner suitable to the situation. These patterns can be categorized into class-creation patterns and object-creation patterns. They help in making the system independent of how its objects are created, composed, and represented.

## 1. Singleton

**Definition**: The Singleton pattern ensures a class has only one instance and provides a global point of access to that instance. It is used to control access to a shared resource, ensuring that only one instance exists.

**Example**:
```java
public class Singleton {
private static Singleton instance;

    private Singleton() {
        // Private constructor to prevent instantiation
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

## 2. Factory Method

**Definition**: The Factory Method pattern defines an interface for creating objects but allows subclasses to alter the type of objects that will be created. It provides a way to delegate the instantiation logic to subclasses.

**Example**:
```java
// Product interface
public interface Product {
    void create();
}

// ConcreteProductA class
public class ConcreteProductA implements Product {
    @Override
    public void create() {
        System.out.println("ConcreteProductA created");
    }
}

// ConcreteProductB class
public class ConcreteProductB implements Product {
    @Override
    public void create() {
        System.out.println("ConcreteProductB created");
    }
}

// Creator abstract class
public abstract class Creator {
    public abstract Product factoryMethod();

    public void someOperation() {
        Product product = factoryMethod();
        product.create();
    }
}

// ConcreteCreatorA class
public class ConcreteCreatorA extends Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProductA();
    }
}

// ConcreteCreatorB class
public class ConcreteCreatorB extends Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProductB();
    }
}

```

## 3. Abstract Factory

**Definition**: The Abstract Factory pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes. It allows a client to create objects of multiple types without specifying their exact classes.

**Example**:
```java
// AbstractFactory interface
public interface AbstractFactory {
    ProductA createProductA();

    ProductB createProductB();
}

// ConcreteFactory1 class
public class ConcreteFactory1 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB1();
    }
}

// ConcreteFactory2 class
public class ConcreteFactory2 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB2();
    }
}

// AbstractProductA interface
public interface ProductA {
    void methodA();
}

// ConcreteProductA1 class
public class ProductA1 implements ProductA {
    @Override
    public void methodA() {
        System.out.println("ProductA1 methodA");
    }
}

// ConcreteProductA2 class
public class ProductA2 implements ProductA {
    @Override
    public void methodA() {
        System.out.println("ProductA2 methodA");
    }
}

// AbstractProductB interface
public interface ProductB {
    void methodB();
}

// ConcreteProductB1 class
public class ProductB1 implements ProductB {
    @Override
    public void methodB() {
        System.out.println("ProductB1 methodB");
    }
}

// ConcreteProductB2 class
public class ProductB2 implements ProductB {
    @Override
    public void methodB() {
        System.out.println("ProductB2 methodB");
    }
}
```

## 4. Builder

**Definition**: The Builder pattern separates the construction of a complex object from its representation, allowing the same construction process to create different representations. It helps in creating complex objects step-by-step.

**Example**:
```java
// Product class
public class Product {
private String part1;
private String part2;

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    @Override
    public String toString() {
        return "Product [part1=" + part1 + ", part2=" + part2 + "]";
    }
}

// Builder interface
public interface Builder {
void buildPart1();
void buildPart2();
Product getResult();
}

// ConcreteBuilder class
public class ConcreteBuilder implements Builder {
private Product product = new Product();

    @Override
    public void buildPart1() {
        product.setPart1("Part1");
    }

    @Override
    public void buildPart2() {
        product.setPart2("Part2");
    }

    @Override
    public Product getResult() {
        return product;
    }
}

// Director class
public class Director {
private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildPart1();
        builder.buildPart2();
    }
}
```

## 5. Prototype

**Definition**: The Prototype pattern creates objects based on a template of an existing object through cloning. It allows for the creation of new objects by copying an existing object, thus reducing the need for creating new instances from scratch.

**Example**:
```java
// Prototype interface
public interface Prototype extends Cloneable {
Prototype clone();
}

// ConcretePrototype class
public class ConcretePrototype implements Prototype {
private String field;

    public ConcretePrototype(String field) {
        this.field = field;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype(field);
    }

    @Override
    public String toString() {
        return "ConcretePrototype [field=" + field + "]";
    }
}
```

## Conclusion

Creational design patterns focus on how objects are created and provide mechanisms for object creation in a way that is both flexible and efficient. These patterns address the challenges of object creation, allowing for control over object instantiation and ensuring that objects are created in a manner suited to the application’s requirements.
