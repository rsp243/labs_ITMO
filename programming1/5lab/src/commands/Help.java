package src.commands;

public class Help extends Command{
    private String name;
    private String description;

    public Help(String aName, String aDescription) {
        super(aName, aDescription);
    }

    @Override
    public String execute() {
        return "";
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
