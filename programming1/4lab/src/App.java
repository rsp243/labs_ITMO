package src;
import java.util.ArrayList;
import java.util.Arrays;

import src.classes.Clothes;
import src.classes.Coordinate;
import src.classes.Location;
import src.classes.Meal;
import src.classes.Message;
import src.classes.Person;
import src.classes.PersonGroup;
import src.classes.Scenary;
import src.classes.Story;
import src.classes.Vehicle;
import src.enums.Controller;
import src.enums.EmotionType;
import src.enums.Freedom;
import src.enums.Material;
import src.enums.MessageType;
import src.enums.Profession;
import src.enums.VehicleType;
import src.enums.mealType;

public class App{
    public static void main(String[] args) {
        var nezn = new Person("Незнайка");
        var poncho = new Person("Пончик");
        var kozl = new Person("Козлик");        
        var kluk = new Person("Клюква");
        var chizh = new Person("Чижик");
        var someone = new Person("Кто-то");

        poncho.setProfession(Profession.TRADING_SALT);

        // !Refink field timeStart at Scenary Class!
        // Add some logic to Story: we need to sum up strings with same actions.

        var me = new Person("");
        Location table = new Location("Стол", new Coordinate(0, 0, 0));
        Scenary st1Scenary1 = new Scenary(
                        poncho, "Сесть за " + table.getName().toLowerCase(), 
                        poncho.goTo(table), 
                        "", 
                        null, 
                        0, 5);
        Meal mealForPoncho1 = new Meal("Тарелка борща", mealType.BORSCH);
        Scenary st1Scenary2 = new Scenary(
                        someone, "Поставить на " + table.getName().toLowerCase(), 
                        Controller.SUCCESSFULLY, 
                        mealForPoncho1.getName(), 
                        null, 
                        0, 20);
        Meal mealForPoncho2 = new Meal("Тарелка каши", mealType.PORRIDGE);
        Scenary st1Scenary3 = new Scenary(
                        someone, "Поставить на " + table.getName().toLowerCase(), 
                        Controller.SUCCESSFULLY, 
                        mealForPoncho2.getName(), 
                        null, 
                        0, 20);
        Scenary st1Scenary4 = new Scenary(
                        poncho, "Кушать", 
                        poncho.eat(mealForPoncho1), 
                        mealForPoncho1.getName(), 
                        null, 
                        0, 30);
        Scenary st1Scenary5 = new Scenary(
                        poncho, "Кушать", 
                        poncho.eat(mealForPoncho2), 
                        mealForPoncho2.getName(), 
                        null, 
                        0, 30);
        Message storyAboutPonchoMsg = new Message("История, которая произошла с ним", MessageType.TRULY);
        Scenary st1Scenary6 = new Scenary(
                        poncho, "Начать рассказывать", 
                        poncho.speakTo(storyAboutPonchoMsg), 
                        storyAboutPonchoMsg.getContent(), 
                        null, 
                        0, 0);
        ArrayList<Scenary> arrayOfScenarySentences1 = new ArrayList<Scenary>();
        arrayOfScenarySentences1.add(st1Scenary1);
        arrayOfScenarySentences1.add(st1Scenary2);
        arrayOfScenarySentences1.add(st1Scenary3);
        arrayOfScenarySentences1.add(st1Scenary4);
        arrayOfScenarySentences1.add(st1Scenary5);
        arrayOfScenarySentences1.add(st1Scenary6);
        Story story1 = new Story(arrayOfScenarySentences1);

        // Forming Poncho's Story
        class PonchosStory1{
                Person[] neznAndPonchoArray = {nezn, poncho};
                PersonGroup neznAndPonchoGroup = new PersonGroup("Незнайка с Пончиком", neznAndPonchoArray, 2);
                Vehicle rocket = new Vehicle("Ракета", VehicleType.ROCKET, 3, new Person[3], 
                                new Coordinate(15, 15, 2), 200);        
                Scenary ponchoSenary1 = new Scenary(neznAndPonchoGroup,
                                "Залезть в " + rocket.getName().toLowerCase() + "(-у)",
                                neznAndPonchoGroup.getInTheVehicle(rocket),
                                null, null, 0, 300);

                Location moon = new Location("Луна",
                                new Coordinate(15, 15, 500000));
                Scenary ponchoSenary2 = new Scenary(neznAndPonchoGroup,
                                "Отправиться на " + moon.getName().toLowerCase() + "(-у)", rocket.goTo(moon),
                                null, null, 0, rocket.getTimeGoing(moon));

                Scenary ponchoSenary3 = new Scenary(neznAndPonchoGroup, 
                                "Путешествовать по " + moon.getName().toLowerCase() + "(-e)",
                                neznAndPonchoGroup.goTo(moon),
                                null, null, 0, 300);
                Location cave = new Location("Пещера", 
                                new Coordinate(15, 15, 500000));
                Scenary ponchoSenary4 = new Scenary(neznAndPonchoGroup, 
                                "Попасть в " + cave.getName().toLowerCase() + "(-y)",
                                neznAndPonchoGroup.goTo(moon),
                                null, null, 0, 90);
                Location underMoonSpace = new Location("Подлунное пространство",
                                new Coordinate(15, 15, 4700000));
                Scenary ponchoSenary5 = new Scenary(nezn,
                                "Провалиться в " + underMoonSpace.getName().toLowerCase(),
                                nezn.goTo(underMoonSpace),
                                null, null, 0, 0);
                Scenary ponchoSenary6 = new Scenary(poncho,
                                "Провалиться в " + underMoonSpace.getName().toLowerCase(),
                                poncho.goTo(underMoonSpace),
                                null, null, 0, 0);
                int timeOfWorking = 600;
                Scenary ponchoSenary7effect = new Scenary(poncho,
                                "Заработать " + poncho.getEarnedMoney(timeOfWorking) + " медных монет", 
                                poncho.work(timeOfWorking), null, null, 
                                0, timeOfWorking);
                Scenary ponchoSenary7 = new Scenary(poncho,
                                "Работать по профессии '" + poncho.getProfession().getName() + "'",
                                poncho.work(timeOfWorking),
                                null, ponchoSenary7effect, 0, timeOfWorking);
                Scenary ponchoSenary8 = new Scenary(poncho,
                                "Разориться", 
                                poncho.setMoney(0), null, null, 
                                0, 0);
                Controller newPonchosProfessionController = poncho.setProfession(Profession.WORK_AT_WEEL);
                PersonGroup societyOfFreeWheelers = new PersonGroup("Общество свободных крутильщиков", new Person[50], 50);
                Scenary ponchoSenary9effect = new Scenary(poncho,
                                "Стать членом '" + societyOfFreeWheelers.getName() + "'", 
                                poncho.getInTheGroup(societyOfFreeWheelers), null, null, 
                                0, 0);
                Scenary ponchoSenary9 = new Scenary(poncho,
                                "Начать работать по профессии '" + poncho.getProfession().getName() + "'", 
                                newPonchosProfessionController, null, ponchoSenary9effect, 
                                0, 0);
        }
        
        var ponchosStory = new PonchosStory1();
        ArrayList<Scenary> arrayOfScenaryPonchoStory = new ArrayList<Scenary>();
        arrayOfScenaryPonchoStory.add(ponchosStory.ponchoSenary1);
        arrayOfScenaryPonchoStory.add(ponchosStory.ponchoSenary2);
        arrayOfScenaryPonchoStory.add(ponchosStory.ponchoSenary3);
        arrayOfScenaryPonchoStory.add(ponchosStory.ponchoSenary4);
        arrayOfScenaryPonchoStory.add(ponchosStory.ponchoSenary5);
        arrayOfScenaryPonchoStory.add(ponchosStory.ponchoSenary6);
        arrayOfScenaryPonchoStory.add(ponchosStory.ponchoSenary7);
        arrayOfScenaryPonchoStory.add(ponchosStory.ponchoSenary8);
        arrayOfScenaryPonchoStory.add(ponchosStory.ponchoSenary9);
        Story storyAboutPoncho = new Story(arrayOfScenaryPonchoStory);


        Scenary st2Scenary1 = new Scenary(
                        someone, "Поставить на " + table.getName().toLowerCase(), 
                        Controller.SUCCESSFULLY, 
                        mealForPoncho2.getName(), 
                        null, 
                        0, 20);
        Message storyAboutPonchoMsg1 = new Message("История, которая произошла с " + nezn.getName() + "(-ой/ом)", MessageType.TRULY);
        Scenary st2Scenary2 = new Scenary(
                        poncho, "Начать рассказывать", 
                        poncho.speakTo(storyAboutPonchoMsg1), 
                        storyAboutPonchoMsg1.getContent(), 
                        null, 
                        0, 0);
        Location stupidIsland = new Location("Дурацкий остров", new Coordinate(100, 100, 0));
        Scenary st2Scenary3 = new Scenary(poncho,
                        "Попасть на '" + stupidIsland.getName() + "'", 
                        poncho.goTo(stupidIsland), null, null, 
                        0, poncho.getTimeGoing(stupidIsland));
        Person[] allKorot = {nezn, poncho, kozl, kluk, chizh};
        PersonGroup allKorotGroup = new PersonGroup("Все", allKorot, 5);
        Location locationBridge = new Location("Мост", new Coordinate(15, 15, 0));
        Person[] police = {};
        PersonGroup policeBrigade = new PersonGroup("Полицейский патруль", police, 10);
        Vehicle policeBobic = new Vehicle("Полицейский фургон", VehicleType.VAN, 15,
                        new Person[15], locationBridge.getCoordinates(), 10); 
        Location PoganosCity = new Location("город Лос-Поганос", new Coordinate(-500, 303, 0));
        Scenary st2Scenary4effect = new Scenary(policeBrigade,
                        "Задержать '" + allKorotGroup.getName() + "(-х)'", 
                        policeBrigade.ControlFreedom(allKorotGroup, Freedom.PRISONED), null, null, 
                        0, 300);        
        Scenary st2Scenary4 = new Scenary(allKorotGroup, 
                        "Спать под '" + locationBridge.getName() + "(-ом)'", 
                        allKorotGroup.Sleep(locationBridge), null, st2Scenary4effect, 
                        0, 3600);        
        Scenary st2Scenary5 = new Scenary(policeBrigade, 
                        "Посадить '" + allKorotGroup.getName() + "(-х)' в", 
                        allKorotGroup.getInTheVehicle(policeBobic), policeBobic.getName(), null, 
                        0, 40); 
        Scenary st2Scenary6 = new Scenary(policeBobic, 
                        "Поехать в '" + PoganosCity.getName() + "'", 
                        policeBobic.goTo(PoganosCity), null, null, 
                        0, policeBobic.getTimeGoing(PoganosCity)); 
        Vehicle Ship = new Vehicle("Корабль", VehicleType.SHIP, 150, allKorot, PoganosCity.getCoordinates(), 35);
        Person[] unHappyArray = {};
        PersonGroup unHappyGroup = new PersonGroup("Несчастные", unHappyArray, 300);
        Location holdOfShip = new Location("Трюм корабля", Ship.getCurrentCoordinates());

        ArrayList<Scenary> arrayOfScenarySentences2 = new ArrayList<Scenary>();
        arrayOfScenarySentences2.add(st2Scenary1);
        arrayOfScenarySentences2.add(st2Scenary2);
        arrayOfScenarySentences2.add(st2Scenary3);
        arrayOfScenarySentences2.add(st2Scenary4);
        arrayOfScenarySentences2.add(st2Scenary5);
        arrayOfScenarySentences2.add(st2Scenary6);


        Story story2 = new Story(arrayOfScenarySentences2);
        

        // System.out,println(...) Output stories
        System.out.println(story1.execute());
        System.out.println(storyAboutPoncho.execute());
        System.out.println(story2.execute());

        //                 Vehicle Ship = new Vehicle("Корабль", VehicleType.SHIP, 150, allKorot, new Coordinate(-505, 28), 35);
        //                 Person[] unHappyArray = {};
        //                 PersonGroup unHappyGroup = new PersonGroup("Несчастные", unHappyArray, 300);
        //                 Location holdOfShip = new Location("Трюм корабля", Ship.getCurrentCoordinates());
        //                 if (allKorotGroup.goTo(holdOfShip) != 0.0) {
        //                         System.out.println(allKorotGroup.getName() + "х посадили в " + holdOfShip.getName().toLowerCase() + ", где уже были " + unHappyGroup.getCountOfParticipants() + " " + unHappyGroup.getName().toLowerCase() + "(-x)." );
        //                         Message messageByeBye = new Message("Пока-пока", MessageType.TRULY);
        //                         if (unHappyGroup.speakTo(messageByeBye) == true) {
        //                                 System.out.println("Многие из них (" + unHappyGroup.getName().toLowerCase() + "(-x)) плакали, говоря '" + messageByeBye.getContent() + "' родной земле.");
        //                                 Clothes strawHat = new Clothes("Соломенная шляпа", Material.STRAW);
        //                                 Clothes woolScarf = new Clothes("Шерстяной шарф", Material.WOOL);
        //                                 Clothes boots = new Clothes("Сапоги", Material.LEATHER);
        //                                 Clothes shirt = new Clothes("Рубашка", Material.SYNTHETIC);
        //                                 Clothes[] golopuzClothes = {strawHat, woolScarf,}; 
        //                                 EmotionType[] golopuzEmotionTypes = {EmotionType.MOTIVATED};
        //                                 Person golopuz = new Person("Голопузый", "Толстенький", golopuzClothes, golopuzEmotionTypes, Freedom.PRISONED, holdOfShip.getCoordinates() ,5);
        //                                 Location emptyBarrel = new Location("Пустая Бочка", holdOfShip.getCoordinates());
        //                                 if (nezn.See(unHappyGroup) == unHappyGroup && nezn.addEmotion(EmotionType.SADNESS) == Controller.SUCCESSFULLY && golopuz.goTo(emptyBarrel) != -1  && golopuz.addEmotion(unHappyGroup, EmotionType.CALM) == Controller.SUCCESSFULLY) {
        //                                         System.out.println("Увидев " + nezn.See(unHappyGroup).getName().toLowerCase() + "(-x), " + nezn.getName() + " заплакал, а какой-то " +
        //                                         golopuz.getSecondName().toLowerCase() + " " + golopuz.getName() + " взобрался на " + emptyBarrel.getName().toLowerCase() + "(-у), которая стояла в " + holdOfShip.getName().toLowerCase() + "(-e), и пытался успокоить " + unHappyGroup.getName() + "(-x).");
        //                                 }
        //                                 if (Arrays.asList(golopuz.getArrayOfClothes()).contains(shirt) != true && Arrays.asList(golopuz.getArrayOfClothes())
        //                                 .contains(boots) != true && Arrays.asList(golopuz.getArrayOfClothes())
        //                                 .contains(woolScarf) != false && Arrays.asList(golopuz.getArrayOfClothes())
        //                                 .contains(strawHat) != false) {
        //                                         System.out.println(golopuz.getName() + " был без рубашки и босиком, но зато в соломенной шляпе и в шерстяном шарфе.");
        //                                 }
        //                                 Location nextoPosition = new Location("Рядом", new Coordinate(-505, 27));
        //                                 if (kozl.addEmotion(EmotionType.OFFENCE) == Controller.SUCCESSFULLY && kozl.goTo(nextoPosition) != 0.0) {
        //                                         System.out.println(kozl.getName() + " обиделся и ушел от компании, но был " + nextoPosition.getName().toLowerCase());
        //                                 }
        //                                 Message bestFrase1 = new Message("Поживем -- увидим", MessageType.TRULY);
        //                                 Message bestFrase2 = new Message("Сыты будем -- как-нибудь проживем", MessageType.TRULY);
        //                                 if (golopuz.speakTo(bestFrase1, unHappyGroup) == true && golopuz.speakTo(bestFrase2, unHappyGroup) == true) {
        //                                         System.out.println(golopuz.getName() + " продолжал речь, вставляя туда две свои самые любимые фразы: '" + bestFrase1.getContent() + "' и '" + bestFrase2.getContent() + "'.");
        //                                 }
        //                         }
        //                 }
        //         }
        // }
        
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
        //            System.out.println("У коротышки ' " + name  + "' добавилась новая эмоция '" + emotion.getName() + "'.");
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
