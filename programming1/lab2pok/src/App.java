import ru.ifmo.se.pokemon.*;
import pokemonsClass.Fearow;
import pokemonsClass.Gloom;
import pokemonsClass.Oddish;
import pokemonsClass.Spearow;
import pokemonsClass.Vileplume;

public class App {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon p1 = new Spearow("Птичка лвл.1", 1);
        Pokemon p2 = new Oddish("Голубика лвл.1", 1);
        Pokemon p3 = new Fearow("Птичка лвл.21", 21);
        Pokemon p4 = new Gloom("Голубика лвл.21", 21);
        Pokemon p5 = new Vileplume("Голубика лвл.41", 41);
        b.addAlly(p2);
        b.addAlly(p4);
        b.addAlly(p5);
        b.addFoe(p1);
        b.addFoe(p3);
        b.go();
    }
}
