package battleChecker;
import ru.ifmo.se.pokemon.*;

public class newAlly {
    protected static boolean flag = false;

    public newAlly(Battle b, Pokemon poke) {
        b.addAlly(poke);
        flag = true;
    }

    public static boolean getExistance() {
        return flag;
    }
}
