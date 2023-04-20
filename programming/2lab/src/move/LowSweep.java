package move;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class LowSweep extends StatusMove {
    public LowSweep() {
        super(Type.FIGHTING, 65, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect e = new Effect().turns(0).stat(Stat.SPEED, -1);
        p.addEffect(e);
    }

    @Override
    protected java.lang.String describe() {
        return "использует способность Low Sweep. Скорость противника уменьшена на 1";
    }
}
