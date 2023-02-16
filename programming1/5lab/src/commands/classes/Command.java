package src.commands.classes;

import src.commands.interfaces.Executable;

public abstract class Command implements Executable {
    private String name;
    private String description;
    private Integer countOfExtraArgs;

    public Command(String aName, String aDescription, Integer aCountOfExtraArgs) {
        name = aName;
        description = aDescription;
        countOfExtraArgs = aCountOfExtraArgs;
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
    
    @Override
    public String toString() {
        return name + " - " + description;
    }
}