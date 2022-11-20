package pokemonsClass;

public class B {
    private static B instance;
    
    private B() { }

    public static B getInctance() {
        if (instance == null) {
            instance = new B();
        } else {
            return instance;
        }
    }
}