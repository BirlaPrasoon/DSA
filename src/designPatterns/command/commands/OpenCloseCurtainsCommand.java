package designPatterns.command.commands;

import designPatterns.command.components.Curtains;

public record OpenCloseCurtainsCommand(Curtains curtains) implements Command{
    @Override
    public void execute() {

    }
}
