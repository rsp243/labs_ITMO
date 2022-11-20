package battleChecker;
import ru.ifmo.se.pokemon.*;

public class newAlly {
    private static int i = 0;

    public newAlly(Battle b, Pokemon poke) {
        b.addAlly(poke);
        i += 1;
    }

    public static int getAmount() {
        return i;
    }
}
