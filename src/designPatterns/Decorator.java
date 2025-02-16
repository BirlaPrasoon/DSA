package designPatterns;

interface INotifier {
    void send(String message);
    String getUsername();
}

class DatabaseService {}

abstract class BaseNotifierDecorator implements INotifier {
    private final INotifier wrapped;
    protected final DatabaseService databaseService;


    BaseNotifierDecorator(INotifier wrapped) {
        this.wrapped = wrapped;
        this.databaseService = new DatabaseService();
    }

    public void send(String message) {
        wrapped.send(message);
    }
    public String getUsername() {
        return wrapped.getUsername();
    }
}

class Notifier implements INotifier{

    private final String username;
    private final DatabaseService databaseService;

    Notifier(String username, DatabaseService databaseService) {
        this.username = username;
        this.databaseService = databaseService;
    }

    @Override
    public void send(String message) {
        System.out.println("Sending email!!");
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}

class WhatsappDecorator extends BaseNotifierDecorator {
    WhatsappDecorator(INotifier wrapped) {
        super(wrapped);
    }

    @Override
    public void send(String message) {
        super.send(message);
        // String phoneNumber = get from database service.
        System.out.println("Sending whatsapp message!!");
    }
}

class FacebookDecorator extends BaseNotifierDecorator {

    FacebookDecorator(INotifier wrapped) {
        super(wrapped);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending message via facebook");
    }
}

public class Decorator {
    public static void main(String[] args) {
        INotifier notifier = new FacebookDecorator(
                new WhatsappDecorator(
                        new Notifier("Prasoon Birla", new DatabaseService())
                )
        );
        notifier.send("Sending base message via facebook decorator");
    }
}
