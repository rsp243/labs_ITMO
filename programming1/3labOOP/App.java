import javax.swing.text.BoxView;

import src.classes.Clothes;
import src.classes.Coordinate;
import src.classes.Location;
import src.classes.Message;
import src.classes.Person;
import src.classes.PersonGroup;
import src.classes.Vehicle;
import src.enums.Controller;
import src.enums.EmotionType;
import src.enums.Freedom;
import src.enums.Material;
import src.enums.MessageType;
import src.enums.VehicleType;

public class App{
    public static void main(String[] args) {
        var nezn = new Person("Незнайка");
        var poncho = new Person("Пончик");
        var kozl = new Person("Козлик");
        var kluk = new Person("Клюква");
        var chizh = new Person("Чижик");
        
        // nezn.Sleep(new Location("Под мостом", new Coordinate(5, 5)));
        var me = new Person("");
        
        Message messageToMe = new Message("История о Незнайке", MessageType.TRULY);
        
        System.out.println(messageToMe.getContent() + ", которую рассказал " + poncho.getName() + ", была " + messageToMe.getMessageTypeDescription().toLowerCase() + ".");
        if (messageToMe.getMessageType() == MessageType.TRULY) {
                Location stupidIsland = new Location("Дурацкий остров", new Coordinate(100, 100));
                if (nezn.goTo(stupidIsland) != 0.0) {
                        System.out.println(messageToMe.getMessageTypeDescription() + " было то, что " + nezn.getName() + " действительно угодил на " + stupidIsland.getName() + ".");        
                }
                Person[] allKorot = {nezn, poncho, kozl, kluk, chizh};
                PersonGroup allKorotGroup = new PersonGroup("Все", allKorot, 5);
                Location locationBridge = new Location("Мост", new Coordinate(15, 15));
                Person[] police = {};
                PersonGroup policeBrigade = new PersonGroup("Полицейский патруль", police, 10);
                Vehicle policeBobic = new Vehicle("Полицейский фургон", VehicleType.VAN, 15,
                        allKorot, locationBridge.getCoordinates(), 30); 
                Location PoganosCity = new Location("город Лос-Поганос", new Coordinate(-500, 33));
                if (allKorotGroup.Sleep(locationBridge) == Controller.SUCCESSFULLY
                 && policeBrigade.ControlFreedom(allKorotGroup, Freedom.PRISONED) == Controller.SUCCESSFULLY
                 && policeBobic.goTo(PoganosCity) != 0.0) {
                        System.out.println("После того, как " + allKorotGroup.getParticipants().toString()
                        .replace("[", "").replace("]", "") + " спали под " + locationBridge.getName()
                        .toLowerCase() + "ом, их задержал " + policeBrigade.getName().toLowerCase() + " " + allKorotGroup.getName().toLowerCase() + " были посажены " + policeBobic.getName() + " и доставлены в " + PoganosCity.getName() + "." );
                        Vehicle Ship = new Vehicle("Корабль", VehicleType.SHIP, 150, allKorot, new Coordinate(-505, 28), 35);
                        Person[] unHappyArray = {};
                        PersonGroup unHappyGroup = new PersonGroup("Несчастные", unHappyArray, 300);
                        Location holdOfShip = new Location("Трюм корабля", Ship.getCurrentCoordinates());
                        if (allKorotGroup.goTo(holdOfShip) != 0.0) {
                                System.out.println(allKorotGroup.getName() + "х посадили в " + holdOfShip.getName().toLowerCase() + ", где уже были " + unHappyGroup.getCountOfParticipants() + " " + unHappyGroup.getName().toLowerCase() + "(-x)." );
                                Message messageByeBye = new Message("Пока-пока", MessageType.TRULY);
                                if (unHappyGroup.speakTo(messageByeBye, null) == true) {
                                        System.out.println("Многие из них (" + unHappyGroup.getName().toLowerCase() + "(-x)) плакали, говоря '" + messageByeBye.getContent() + "' родной земле.");
                                }
                        }
                }
        }
        
        // if (nezn.putOnClothes(cap) == Controller.SUCCESSFULLY) {
        //         System.out.println("Коротышка '" + nezn.getName()  + "' надел '" + cap.getName() + "'.");
        // } else {
        //         System.out.println("У коротышки '" + nezn.getName() + "' не может быть так много элементов одежды, удалите какой-нибудь элемент одежды методом removeEmotion().");
        // }



        //            System.out.println("Коротышка '" + name  + "' снял этот элемент одежды -> '" + clothes.getName() + "'.");
        //             System.out.println("У коротышки '" + name + "' не нашлось такого элемента одежды. Возможно, вы искали другой.");
        //             System.out.println("У коротышки '" + name + "' не нашлось такой эмоции. Возможно, вы искали другую.");
        //             System.out.println("У коротышки '" + name + "' не нашлось такой эмоции. Возможно, вы искали другую.");
        //             System.out.println("У коротышки '" + name + "' не может быть так много эмоций, удалите какую-нибудь методом removeEmotion(), и перезапустите команду");
        //            System.out.println("У коротышки '" + name  + "' добавилась новая эмоция '" + emotion.getName() + "'.");
        //              System.out.println("У коротышки '" + person.name + "' не нашлось такой эмоции. Возможно, вы искали другую");
        //              System.out.println("У коротышки '" + person.name  + "' удалилась эта эмоция -> '" + emotion.getName() + "'.");
        //             System.out.println("У коротышки '" + person.name + "' не может быть так много эмоций, удалите какую-нибудь методом removeEmotion(), и перезапустите комманду");
        //             System.out.println("У коротышки '" + person.name  + "' добавилась новая эмоция '" + emotion.getName() + "'.");
        //              System.out.println(name + " сказал коротышке '" + targetPerson.getName() + "': '" + contentOfMessage + "', - что было абсолютной " + isMessageTruly);
        //              System.out.println(name + " классно поспал 5 часов " + location.getName().toLowerCase());
        //              System.out.println(name + " пришёл в " + location.getName().toLowerCase() + " за " +  + " минут");
        //             System.out.println("Все коротышки из группы " + name + " успешно дошли до " + location.getName().toLowerCase());  

        }
}
