package server.data.classes.Validators.classes;

public abstract class AbstractValidator<E> {
    private String name;

    public AbstractValidator(String aName) {
        name = aName;
    }
    
    public boolean validate(E variable) {return true;};

    public String getName() {
        return name;
    }
}
