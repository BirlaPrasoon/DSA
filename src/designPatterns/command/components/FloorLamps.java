package designPatterns.command.components;

public class FloorLamps extends Component{

    private final Light light;

    public FloorLamps() {
        this.light = new Light();
    }

    public Light getLight() {
        return light;
    }

    public boolean isLightOn() {
        return light.isSwitchedOn();
    }
}
