package move;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class Absorb extends StatusMove {
    public Absorb() {
        super(Type.GRASS, 20, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        if (p.getHP() + 30 < p.getStat(Stat.HP)) {
            Effect e = new Effect().turns(-1).stat(Stat.HP, (int) (p.getHP() + 30));
            p.addEffect(e);
        } else {
            Effect e = new Effect().turns(-1).stat(Stat.HP, (int) p.getStat(Stat.HP));
            p.addEffect(e); 
        }
    }

    @Override
    protected java.lang.String describe() {
        return "использует способность Absorb. Восстанавливает половину от нанесенного урона";
    }
}
