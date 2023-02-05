package src.commands.classes;

import java.util.ArrayList;

public class Help extends Command {
    private ArrayList<Command> listOfCommands;

    public Help(String aName, String aDescription, ArrayList<Command> aListOfCommands) {
        super(aName, aDescription);
        listOfCommands = aListOfCommands;
    }

    @Override
    public String execute() {
        String strListOfCommands = this.toString();
        for (Command command : listOfCommands ) {
            strListOfCommands += "\n" + command.toString();
        }
        return strListOfCommands;
    }
}
