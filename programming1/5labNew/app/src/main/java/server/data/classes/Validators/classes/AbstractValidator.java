package server.data.classes.Validators.classes;

public abstract class AbstractValidator {
    private String name;

    public AbstractValidator(String aName) {
        name = aName;
    }

    public boolean validate() {return true;};

    public String getName() {
        return name;
    }
}
