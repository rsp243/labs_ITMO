package move;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class IceBeam extends StatusMove {
    public IceBeam() {
        super(Type.ICE, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.1) {
            Effect.freeze(p);
        }
    }

    @Override
    protected java.lang.String describe() {
        return "использует способность Ice Beam. Противник может быть заморожен с шансом 10%";
    }
}
