package move;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class IceBeam extends StatusMove {
    public IceBeam() {
        super(Type.ICE, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect e = new Effect().turns(0).chance(0.1).condition(Status.FREEZE);
        p.setCondition(e);
    }

    @Override
    protected java.lang.String describe() {
        return "использует способность Ice Beam. Противник может быть заморожен с шансом 10%";
    }
}
