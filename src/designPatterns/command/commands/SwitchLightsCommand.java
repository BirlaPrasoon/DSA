package designPatterns.command.commands;

import designPatterns.command.components.Light;

public record SwitchLightsCommand(Light light) implements Command {
    @Override
    public void execute() {
        light.switchLights();
    }
}
