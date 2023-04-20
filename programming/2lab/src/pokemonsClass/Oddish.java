package pokemonsClass;

import move.DoubleTeam;
import move.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Oddish extends Pokemon{

    public Oddish(String string, int i) {
        super(string, i);

        this.setType(Type.GRASS, Type.POISON);
        this.setStats(45, 50, 55, 75, 65, 30);
        this.setMove(new DoubleTeam(), new Swagger());
    }
}
