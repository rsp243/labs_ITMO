package battleChecker;

import ru.ifmo.se.pokemon.*;

public class newFoe {
    protected static boolean flag = false;

    public newFoe(Battle b, Pokemon poke) {
        b.addFoe(poke);
        flag = true;
    }

    public static boolean getExistance() {
        return flag;
    }
}