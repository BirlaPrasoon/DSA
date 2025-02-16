package designPatterns.command.components;

public class Curtains extends Component{
    private boolean open = false;
    public void openClose() {
        open = !open;
    }

    public boolean isOpen() {
        return open;
    }
}
