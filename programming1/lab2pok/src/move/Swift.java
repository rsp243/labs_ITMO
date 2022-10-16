package move;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class Swift extends StatusMove {
    public Swift() {
        super(Type.NORMAL, 60, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().turns(0).stat(Stat.ACCURACY, 100);
        p.addEffect(e);
    }
    
    @Override
    protected java.lang.String describe() {
        return "использует способность Swift. Покемон не промахивается атаками";
    }
}
