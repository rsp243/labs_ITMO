package pokemonsClass;

import move.Facade;

public class Vileplume extends Gloom {
    public Vileplume(String string, int i) {
        super(string, i);

        this.setStats(75, 80, 85, 110, 90, 50);
        this.addMove(new Facade());
    }
}
