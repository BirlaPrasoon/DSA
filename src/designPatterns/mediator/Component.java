package designPatterns.mediator;

public interface Component {
    void setMediator(Mediator mediator);
    String getName();
}
