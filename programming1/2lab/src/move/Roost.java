package move;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class Roost extends StatusMove{
    public Roost() {
        super(Type.FLYING, 0, 100);
    }

    @Override 
    protected void applySelfEffects(Pokemon p) {
        double maxHP = p.getStat(Stat.HP);
        int halfHP = (int) maxHP / 2;
        Effect e = new Effect().turns(0);
        if (halfHP + p.getHP() < maxHP) {
            e.stat(Stat.HP, (int) (halfHP + p.getHP()));
        } else {
            e.stat(Stat.HP, (int) maxHP);
        }
        p.addEffect(e);
    }

    @Override
    protected java.lang.String describe() {
        return "использовал способность Roost. Здоровье покемона пополнено на половину от его MAX здоровья";
    }
}
