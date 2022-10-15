package pokemonsClass;

import move.DrillRun;

public class Fearow extends Spearow {
    
    public Fearow(String string, int i) {
        super(string, i);
        
        this.setStats(65, 90, 65, 61, 61, 100);
        this.addMove(new DrillRun());
    }
}
