package move;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class DrillRun extends PhysicalMove {
    public DrillRun() {
        super(Type.FLYING, 80, 95);
    }

    @Override
    protected java.lang.String describe() {
        return "использует способность Drill Run. Шанс критического удара увеличен";
    }
}
