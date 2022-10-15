package move;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class DoubleTeam extends StatusMove {

    public DoubleTeam() {
        super(Type.NORMAL, 0, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().turns(0).stat(Stat.EVASION, 1);
        p.addEffect(e);
    }

    @Override
    protected java.lang.String describe() {
        return "использует способность DoubleTeam. Характеристика 'Уклонение' увеличена на 1";
    }
}
