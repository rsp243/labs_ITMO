package move;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class Facade extends StatusMove {
    public Facade() {
        super(Type.NORMAL, 70, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        Status statusM = p.getCondition();
        if (statusM == Status.POISON || statusM == Status.BURN || statusM == Status.PARALYZE) {
            Effect e = new Effect().turns(0).stat(Stat.ATTACK, (int) (p.getStat(Stat.ATTACK) * 2));
            p.addEffect(e);
        }
    }

    @Override
    protected java.lang.String describe() {
        return "использовал способность Facade. Если отравлен, поддожен или парализован, сила атаки удвоится";
    }
}
