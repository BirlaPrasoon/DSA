package designPatterns.command;

import designPatterns.command.commands.OpenCloseCurtainsCommand;
import designPatterns.command.commands.SwitchLightsCommand;
import designPatterns.command.components.FloorLamps;
import designPatterns.command.components.Room;

public class Main {

    public static void main(String[] args) {
        Room room = new Room();
        room.setCommand(new OpenCloseCurtainsCommand(room.getCurtains()));
        room.executeCommand();
        System.out.println(room.curtainsOpen());

        System.out.println("==========================================");

        FloorLamps lamp = new FloorLamps();
        lamp.setCommand(new SwitchLightsCommand(lamp.getLight()));
        lamp.executeCommand();
        System.out.println(lamp.isLightOn());
    }
}
