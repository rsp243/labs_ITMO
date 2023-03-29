package server.commands;

/**
 * Abstract class Command
 */

public abstract class Command implements Executable{
    /**
     * Name of command
     */
    private String name;

    private String fullName;    

    /**
     * Descriprion of command
     */
    private String description;
    /**
     * Count of extra necessary fields to fill
     */
    private Integer countOfExtraInlineArgs;
    /**
     * CommandType. Enum CommandType
     */
    private CommandType commandType;

    public Command(String aName, String aFullName, String aDescription, Integer aCountOfExtraInlineArgs, CommandType aCommandType) {
        name = aName;
        fullName = aFullName;
        description = aDescription;
        countOfExtraInlineArgs = aCountOfExtraInlineArgs;
        commandType = aCommandType;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCountOfExtraInlineArgs() {
        return countOfExtraInlineArgs;
    }

    public CommandType getCommandType() {
        return commandType;
    }
    
    @Override
    public String toString() {
        return fullName + " - " + description;
    }
}