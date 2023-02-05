package src.commands;

public abstract class Command implements Executable {
    private String name;
    private String description;

    public Command(String aName, String aDescription) {
        name = aName;
        description = aDescription;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
