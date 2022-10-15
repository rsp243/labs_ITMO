import ru.ifmo.se.pokemon.*;
import pokemonsClass.Oddish;
import pokemonsClass.Spearow;

public class App {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon p1 = new Spearow("Птичка лвл.1", 1);
        Pokemon p2 = new Oddish("Голубика лвл.1", 1);
        b.addAlly(p1);
        b.addFoe(p2);
        b.go();
    }
}
