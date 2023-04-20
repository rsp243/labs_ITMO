package server.data.Validators;

public abstract class AbstractValidator<E> {
    private String name;
    private String restrictions;

    public AbstractValidator(String aName, String aRestrictions) {
        name = aName;
        restrictions = aRestrictions;
    }
    
    public boolean validate(E variable) {return true;};

    public E caster(String str) {
        return (E) str;
    };

    public String getName() {
        return name;
    }

    public String getRestrictions() {
        return restrictions;
    }    
}
