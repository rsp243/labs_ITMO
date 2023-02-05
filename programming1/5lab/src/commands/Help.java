package src.commands;

import java.util.ArrayList;

public class Help extends Command{
    private String name;
    private String description;
    private ArrayList<Command> listOfCommands;

    public Help(String aName, String aDescription, ArrayList<Command> aListOfCommands) {
        super(aName, aDescription);
        listOfCommands = aListOfCommands;
    }

    @Override
    public String execute() {
        String strListOfCommands = this.toString() + "\n";
        for (Command command : listOfCommands ) {
            strListOfCommands += command.toString() + "\n";
        }
        return strListOfCommands;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
