package server.commands.classes;

import java.util.ArrayList;

import server.commands.interfaces.Executable;
import server.data.classes.CollectionWorker;

public abstract class Command implements Executable{
    private String name;
    private String description;
    private Integer countOfExtraArgs;
    private CommandType commandType;

    public Command(String aName, String aDescription, Integer aCountOfExtraArgs, CommandType aCommandType) {
        name = aName;
        description = aDescription;
        countOfExtraArgs = aCountOfExtraArgs;
        commandType = aCommandType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCountOfExtraArgs() {
        return countOfExtraArgs;
    }

    public CommandType getCommandType() {
        return commandType;
    }
    
    @Override
    public String toString() {
        return name + " - " + description;
    }
}