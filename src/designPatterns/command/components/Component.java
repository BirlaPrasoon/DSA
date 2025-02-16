package designPatterns.command.components;

import designPatterns.command.commands.Command;

public abstract class Component {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }

}
