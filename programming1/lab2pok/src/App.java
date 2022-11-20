import ru.ifmo.se.pokemon.*;
import battleChecker.newAlly;
import battleChecker.newFoe;
import pokemonsClass.Fearow;
import pokemonsClass.Gloom;
import pokemonsClass.Oddish;
import pokemonsClass.Spearow;
import pokemonsClass.Vileplume;
import pokemonsClass.Xurkitree;

public class App {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon p1 = new Spearow("Птичка лвл.1", 1);
        Pokemon p3 = new Fearow("Птичка лвл.21", 21);
        Pokemon p6 = new Xurkitree("Электроник лвл.21", 21);
        
        Pokemon p2 = new Oddish("Голубика лвл.1", 1);
        Pokemon p4 = new Gloom("Голубика лвл.21", 21);
        Pokemon p5 = new Vileplume("Голубика лвл.41", 41);
        
        new newFoe(b, p2);
        new newFoe(b, p4);
        new newFoe(b, p5);
        // new newAlly(b, p1);
        // new newAlly(b, p3);
        // new newAlly(b, p6);

        System.out.println("Из 1 команды пришли " + newAlly.getAmount() + " покемона(ов)");
        System.out.println("Из 2 команды пришли " + newFoe.getAmount() + " покенона(ов)");
        try {
            b.go();
        } catch (java.lang.NullPointerException e) {
            if (newFoe.getAmount() == 0 && newFoe.getAmount() == 0) {
                System.out.println("Обе команды испугались и не пришли на бой!");
            } else if (newAlly.getAmount() == 0) {
                System.out.println("Первая команда не пришла на бой!");  
            } else {
                System.out.println("Вторая команда не пришла на бой!"); 
            }
        }
    }
}
