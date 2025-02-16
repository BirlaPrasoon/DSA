package designPatterns;


abstract class Pizza {
    protected String sauce;
    protected String toppings;
    protected String curst;

    public abstract void assemble();
    public abstract void qualityCheck();
}

class PeporannyPizza extends Pizza {

    @Override
    public void assemble() {

    }

    @Override
    public void qualityCheck() {

    }
}

class VeggiePizza extends Pizza {

    @Override
    public void assemble() {

    }

    @Override
    public void qualityCheck() {

    }
}

abstract class Restaurant {
    protected Pizza pizza;
    protected Restaurant(Pizza pizza) {
        this.pizza = pizza;
    }

    abstract void addSauce();
    abstract void addToppings();
    abstract void makeCrust();

    public void deliver() {
        /// ...
    }
}

class AmericanRestaurant extends Restaurant {

    protected AmericanRestaurant(Pizza pizza) {
        super(pizza);
    }

    @Override
    void addSauce() {

    }

    @Override
    void addToppings() {

    }

    @Override
    void makeCrust() {

    }
}

class ItalianRestaurant extends Restaurant {

    protected ItalianRestaurant(Pizza pizza) {
        super(pizza);
    }

    @Override
    void addSauce() {

    }

    @Override
    void addToppings() {

    }

    @Override
    void makeCrust() {

    }
}

public class Bridge {

    public static void main(String[] args) {
        AmericanRestaurant americanRestaurant = new AmericanRestaurant(new PeporannyPizza());
        americanRestaurant.deliver();
        ItalianRestaurant italianRestaurant = new ItalianRestaurant(new VeggiePizza());
        italianRestaurant.deliver();

    }
}
