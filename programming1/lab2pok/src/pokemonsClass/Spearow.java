package pokemonsClass;

import move.Confide;
import move.Peck;
import move.Roost;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Spearow extends Pokemon{

    public Spearow(String string, int i) {
        super(string, i);
        
        this.setType(Type.NORMAL, Type.FLYING);
        this.setStats(40, 60, 30, 31, 31, 70);
        this.setMove(new Roost(), new Confide(), new Peck());
    }
    
}
