package src.commands.classes;

import src.commands.interfaces.Executable;

public abstract class Command implements Executable {
    private String name;
    private String description;

    public Command(String aName, String aDescription) {
        name = aName;
        description = aDescription;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return name + " - " + description;
    }
}