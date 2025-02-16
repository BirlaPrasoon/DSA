package designPatterns.command.components;

public class Light extends Component{

    private boolean switchedOn = false;

    public void switchLights() {
        switchedOn = !switchedOn;
    }

    public boolean isSwitchedOn() {
        return switchedOn;
    }

}
