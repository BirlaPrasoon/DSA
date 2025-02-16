package designPatterns.chain_of_responsibility.handlers;

public abstract class Handler {

    private Handler next;

    public Handler setNextHandler(Handler nextHandler) {
        this.next = nextHandler;
        return this.next;
    }

    public abstract boolean handle(String username, String password);

    protected boolean handleNext(String username, String password) {
        if(next == null) return true;
        return this.next.handle(username, password);
    }

}
