package move;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class Peck extends PhysicalMove {
    public Peck() {
        super(Type.FLYING, 35, 100);
    }
    
    @Override
    protected java.lang.String describe() {
        return "использует способность Peck. Наносит обычную атаку с силой 35";
    }
}
