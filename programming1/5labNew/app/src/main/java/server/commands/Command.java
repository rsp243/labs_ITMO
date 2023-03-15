package server.commands;

/**
 * Abstract class Command
 */

public abstract class Command implements Executable{
    /**
     * Name of command
     */
    private String name;
    /**
     * Descriprion of command
     */
    private String description;
    /**
     * Count of extra necessary fields to fill
     */
    private Integer countOfExtraArgs;
    /**
     * CommandType. Enum CommandType
     */
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