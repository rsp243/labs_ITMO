package battleChecker;

import ru.ifmo.se.pokemon.*;

public class newFoe {
    protected static int i = 0;

    public newFoe(Battle b, Pokemon poke) {
        b.addFoe(poke);
        i += 1;
    }

    public static int getAmount() {
        return i;
    }
}