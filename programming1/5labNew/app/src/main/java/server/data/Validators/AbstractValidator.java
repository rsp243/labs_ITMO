package server.data.Validators;

public abstract class AbstractValidator<E> {
    private String name;

    public AbstractValidator(String aName) {
        name = aName;
    }
    
    public boolean validate(E variable) {return true;};

    public E caster(String str) {
        return (E) str;
    };

    public String getName() {
        return name;
    }    
}
