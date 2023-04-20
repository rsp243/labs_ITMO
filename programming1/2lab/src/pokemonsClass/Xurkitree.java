package pokemonsClass;

import move.DoubleTeam;
import move.IceBeam;
import move.LowSweep;
import move.Swift;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Xurkitree extends Pokemon {
    public Xurkitree(String string, int i) {
        super(string, i);

        setStats(83, 89, 71, 173, 71, 83);
        setType(Type.ELECTRIC);
        setMove(new LowSweep(), new Swift(), new IceBeam(), new DoubleTeam());
    }
}
