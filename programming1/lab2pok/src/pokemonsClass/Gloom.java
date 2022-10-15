package pokemonsClass;

import move.Absorb;

public class Gloom extends Oddish {
    public Gloom(String string, int i) {
        super(string, i);
        
        this.setStats(60, 65, 70, 85, 75, 40);
        this.addMove(new Absorb());
    }
}
