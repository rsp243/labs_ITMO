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
import src.enums.SatietyType;
import src.enums.VehicleType;
import src.enums.mealType;

public class App{
    public static void main(String[] args) {
        var nezn = new Person("Незнайка");
        var poncho = new Person("Пончик");
        var kozl = new Person("Козлик");        
        kozl.setCurrentCoordinates(new Coordinate(-500, 303, 0));
        var kluk = new Person("Клюква");
        var chizh = new Person("Чижик");
        var someone = new Person("Кто-то");

        poncho.setProfession(Profession.TRADING_SALT);

        // !Refink field timeStart at Scenary Class!
        // Add some logic to Story: we need to sum up strings with same actions.

        Location table = new Location("Стол", new Coordinate(0, 0, 0));
        Scenary st1Scenary1 = new Scenary(
                        poncho, "Сесть за " + table.getName().toLowerCase(), 
                        poncho.goTo(table), 
                        "", 
                        null, 
                        5);
        Meal mealForPoncho1 = new Meal("Тарелка борща", mealType.BORSCH);
        Scenary st1Scenary2 = new Scenary(
                        someone, "Поставить на " + table.getName().toLowerCase(), 
                        Controller.SUCCESSFULLY, 
                        mealForPoncho1.getName(), 
                        null, 
                        20);
        Meal mealForPoncho2 = new Meal("Тарелка каши", mealType.PORRIDGE);
        Scenary st1Scenary3 = new Scenary(
                        someone, "Поставить на " + table.getName().toLowerCase(), 
                        Controller.SUCCESSFULLY, 
                        mealForPoncho2.getName(), 
                        null, 
                        20);
        Scenary st1Scenary4 = new Scenary(
                        poncho, "Кушать", 
                        poncho.eat(mealForPoncho1), 
                        mealForPoncho1.getName(), 
                        null, 
                        30);
        Scenary st1Scenary5 = new Scenary(
                        poncho, "Кушать", 
                        poncho.eat(mealForPoncho2), 
                        mealForPoncho2.getName(), 
                        null, 
                        30);
        Message storyAboutPonchoMsg = new Message("История, которая произошла с ним", MessageType.TRULY);
        Scenary st1Scenary6 = new Scenary(
                        poncho, "Начать рассказывать", 
                        poncho.speakTo(storyAboutPonchoMsg), 
                        storyAboutPonchoMsg.getContent(), 
                        null, 
                        0);
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
                // @GroupNeznAndPoncho
                PersonGroup neznAndPonchoGroup = new PersonGroup("Незнайка с Пончиком", neznAndPonchoArray, 2);
                // @Rocket
                Vehicle rocket = new Vehicle("Ракета", VehicleType.ROCKET, 3, new Person[3], 
                                new Coordinate(15, 15, 2), 200);        
                // @SocietyOfFreeWheelers
                PersonGroup societyOfFreeWheelers = new PersonGroup("Общество свободных крутильщиков", new Person[50], 50);
                // @Moon
                Location moon = new Location("Луна",
                                new Coordinate(15, 15, 500000));
                // @Cave
                Location cave = new Location("Пещера", 
                                                new Coordinate(15, 15, 500000));
                // @UnderMoonSpace                       
                Location underMoonSpace = new Location("Подлунное пространство",
                                                moon.getCoordinates());
                
                // @getInTheVehicle
                Scenary ponchoScenary1 = new Scenary(neznAndPonchoGroup,
                                "Залезть в " + rocket.getName().toLowerCase() + "(-у)",
                                neznAndPonchoGroup.getInTheVehicle(rocket),
                                null, null, 300);
                // @goTo
                Scenary ponchoScenary2 = new Scenary(neznAndPonchoGroup,
                                "Отправиться на " + moon.getName().toLowerCase() + "(-у)", rocket.goTo(moon),
                                null, null, rocket.getTimeGoing(moon));
                // @goTo
                Scenary ponchoScenary3 = new Scenary(neznAndPonchoGroup, 
                                "Путешествовать по " + moon.getName().toLowerCase() + "(-e)",
                                neznAndPonchoGroup.goTo(moon),
                                null, null, 300);

                Scenary ponchoScenary4 = new Scenary(neznAndPonchoGroup, 
                                "Попасть в " + cave.getName().toLowerCase() + "(-y)",
                                neznAndPonchoGroup.goTo(moon),
                                null, null, 90);
                Scenary ponchoScenary5 = new Scenary(nezn,
                                "Провалиться в " + underMoonSpace.getName().toLowerCase(),
                                nezn.goTo(underMoonSpace),
                                null, null, 0);
                Scenary ponchoScenary6 = new Scenary(poncho,
                                "Провалиться в " + underMoonSpace.getName().toLowerCase(),
                                poncho.goTo(underMoonSpace),
                                null, null, 0);
                int timeOfWorking = 600;
                Scenary ponchoScenary7effect = new Scenary(poncho,
                                "Заработать " + poncho.getEarnedMoney(timeOfWorking) + " медных монет", 
                                poncho.work(timeOfWorking), null, null, 
                                timeOfWorking);
                Scenary ponchoScenary7 = new Scenary(poncho,
                                "Работать по профессии '" + poncho.getProfession().getName() + "'",
                                poncho.work(timeOfWorking),
                                null, ponchoScenary7effect, timeOfWorking);
                Scenary ponchoScenary8 = new Scenary(poncho,
                                "Разориться", 
                                poncho.setMoney(0), null, null, 
                                0);
                
                Controller newPonchosProfessionController = poncho.setProfession(Profession.WORK_AT_WHEEL);
                Scenary ponchoScenary9effect = new Scenary(poncho,
                                "Стать членом '" + societyOfFreeWheelers.getName() + "'", 
                                poncho.getInTheGroup(societyOfFreeWheelers), null, null, 
                                0);
                Scenary ponchoScenary9 = new Scenary(poncho,
                                "Начать работать по профессии '" + poncho.getProfession().getName() + "'", 
                                newPonchosProfessionController, null, ponchoScenary9effect, 
                                0);
        }
        var ponchosStory1 = new PonchosStory1();
        ArrayList<Scenary> arrayOfScenaryPonchoStory = new ArrayList<Scenary>();
        arrayOfScenaryPonchoStory.add(ponchosStory1.ponchoScenary1);
        arrayOfScenaryPonchoStory.add(ponchosStory1.ponchoScenary2);
        arrayOfScenaryPonchoStory.add(ponchosStory1.ponchoScenary3);
        arrayOfScenaryPonchoStory.add(ponchosStory1.ponchoScenary4);
        arrayOfScenaryPonchoStory.add(ponchosStory1.ponchoScenary5);
        arrayOfScenaryPonchoStory.add(ponchosStory1.ponchoScenary6);
        arrayOfScenaryPonchoStory.add(ponchosStory1.ponchoScenary7);
        arrayOfScenaryPonchoStory.add(ponchosStory1.ponchoScenary8);
        arrayOfScenaryPonchoStory.add(ponchosStory1.ponchoScenary9);
        Story storyAboutPoncho = new Story(arrayOfScenaryPonchoStory);

        class PonchosStory2{
                Scenary st2Scenary1 = new Scenary(
                                someone, "Поставить на " + table.getName().toLowerCase(), 
                                Controller.SUCCESSFULLY, 
                                mealForPoncho2.getName(), 
                                null, 
                                20);
                Message storyAboutPonchoMsg1 = new Message("История, которая произошла с " + nezn.getName() + "(-ой/ом)", MessageType.TRULY);
                Scenary st2Scenary2 = new Scenary(
                                poncho, "Начать рассказывать", 
                                poncho.speakTo(storyAboutPonchoMsg1), 
                                storyAboutPonchoMsg1.getContent(), 
                                null, 
                                0);
                Location stupidIsland = new Location("Дурацкий остров", new Coordinate(100, 100, 0));
                Scenary st2Scenary3 = new Scenary(poncho,
                                "Попасть на '" + stupidIsland.getName() + "'", 
                                poncho.goTo(stupidIsland), null, null, 
                                poncho.getTimeGoing(stupidIsland));
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
                                300);        
                Scenary st2Scenary4 = new Scenary(allKorotGroup, 
                                "Спать под '" + locationBridge.getName() + "(-ом)'", 
                                allKorotGroup.Sleep(locationBridge), null, st2Scenary4effect, 
                                3600);        
                Scenary st2Scenary5 = new Scenary(policeBrigade, 
                                "Посадить '" + allKorotGroup.getName() + "(-х)' в", 
                                allKorotGroup.getInTheVehicle(policeBobic), policeBobic.getName(), null, 
                                40); 
                Scenary st2Scenary6 = new Scenary(policeBobic, 
                                "Поехать в '" + PoganosCity.getName() + "'", 
                                policeBobic.goTo(PoganosCity), null, null, 
                                policeBobic.getTimeGoing(PoganosCity)); 
                Vehicle ship = new Vehicle("Корабль", VehicleType.SHIP, 150, allKorot, PoganosCity.getCoordinates(), 35);
                Person[] unHappyArray = {};
                PersonGroup unHappyGroup = new PersonGroup("Несчастные", unHappyArray, 300);
                Location holdOfShip = new Location("Трюм корабля", ship.getCurrentCoordinates());
                Scenary st2Scenary7 = new Scenary(someone, 
                                "Посадить '" + unHappyGroup.getName() + "(-x)' в ", 
                                unHappyGroup.goTo(holdOfShip), holdOfShip.getName(), null, 
                                unHappyGroup.getTimeGoing(holdOfShip)); 
                Scenary st2Scenary8 = new Scenary(someone, 
                                "Посадить '" + allKorotGroup.getName() + "(-x)' в ", 
                                allKorotGroup.goTo(holdOfShip), holdOfShip.getName(), null, 
                                allKorotGroup.getTimeGoing(holdOfShip)); 
                Message bye_byeMsg = new Message("Пока-пока", MessageType.TRULY);
                Scenary st2Scenary9 = new Scenary(unHappyGroup, 
                                "Прощаться c Родной Землей", 
                                unHappyGroup.speakTo(bye_byeMsg), null, null, 
                                0); 
                Clothes strawHat = new Clothes("Соломенная шляпа", Material.STRAW);
                Clothes woolScarf = new Clothes("Шерстяной шарф", Material.WOOL);
                Clothes[] golopuzClothes = {strawHat, woolScarf,}; 
                EmotionType[] golopuzEmotionTypes = {EmotionType.MOTIVATED};
                Person golopuz = new Person("Голопузый", "Толстенький",
                                golopuzClothes, golopuzEmotionTypes, Freedom.PRISONED, 
                                holdOfShip.getCoordinates(), 5, 0, 
                                SatietyType.WELL_FED, Profession.UNEMPLOYED);
                Location emptyBarrel = new Location("Пустая Бочка", holdOfShip.getCoordinates());
                Scenary st2Scenary10effect = new Scenary(nezn, 
                                "Плакать", 
                                nezn.addEmotion(EmotionType.SADNESS), null, null, 
                                0); 
                Scenary st2Scenary10 = new Scenary(nezn, 
                                "Смотреть на '" + unHappyGroup.getName() + "(-x)'", 
                                nezn.see(unHappyGroup), null, st2Scenary10effect, 
                                0); 
                Scenary st2Scenary11 = new Scenary(golopuz, 
                                "Встать на", 
                                golopuz.goTo(emptyBarrel), emptyBarrel.getName(), null, 
                                golopuz.getTimeGoing(emptyBarrel)); 
                Scenary st2Scenary12 = new Scenary(golopuz, 
                                "Начать вносить в группу '" + unHappyGroup.getName() + "' " + EmotionType.CALM.getName(), 
                                golopuz.addEmotion(unHappyGroup, EmotionType.CALM), null, null, 
                                300); 
                Scenary st2Scenary13 = new Scenary(kozl, 
                                "Обидеться", 
                                kozl.addEmotion(EmotionType.OFFENCE), null, null, 
                                0); 
                Location nextoPosition = new Location("Рядом", new Coordinate(-500, 305, 0));
                Scenary st2Scenary14 = new Scenary(kozl, 
                                "Отойти, но быть " + nextoPosition.getName(), 
                                kozl.goTo(nextoPosition), null, null, 
                                kozl.getTimeGoing(nextoPosition));
                Message bestFrase1 = new Message("Поживем -- увидим", MessageType.TRULY);
                Message bestFrase2 = new Message("Сыты будем -- как-нибудь проживем", MessageType.TRULY);
                Scenary st2Scenary15 = new Scenary(golopuz, 
                                "Продолжать речь, повторять фразы '" + bestFrase1.getContent() + "'', '" + bestFrase2.getContent() + "'", 
                                Controller.SUCCESSFULLY, null, null, 
                                0);
                Scenary st2Scenary16effect = new Scenary(unHappyGroup,
                                "Обрести настроение '" + EmotionType.HAPPY.getName() + "'",
                                unHappyGroup.addEmotion(EmotionType.HAPPY), null,
                                null, 0);
                Scenary st2Scenary16 = new Scenary(golopuz, 
                                "Успокоить '" + unHappyGroup.getName() + "'", 
                                golopuz.removeEmotion(unHappyGroup, EmotionType.UNHAPPY), null, st2Scenary16effect, 
                                0);
                Scenary st2Scenary17 = new Scenary(unHappyGroup, 
                                "Начать говорить", 
                                unHappyGroup.speakTo(new Message("Speech", MessageType.TRULY)), null, null, 
                                10);
                Scenary st2Scenary18 = new Scenary(kozl, 
                                "Продолжать хмуриться", 
                                (Arrays.asList(kozl.getArrayOfEmotions()).contains(EmotionType.OFFENCE) ? Controller.SUCCESSFULLY : Controller.FAILED),
                                null, null, 
                                0);
                Location ocean = new Location("Океан", new Coordinate(-50000, 555555, 0));
                Scenary st2Scenary19 = new Scenary(ship, 
                                "Отчалить и плыть в " + ocean.getName().toLowerCase(), 
                                ship.goTo(ocean), null, null, 
                                ship.getTimeGoing(ocean));
                Scenary st2Scenary20effect = new Scenary(unHappyGroup, 
                                "Cпать", 
                                Controller.FAILED, null, null, 
                                ship.getTimeGoing(ocean));
                Scenary st2Scenary20 = new Scenary(unHappyGroup, 
                                "Бояться утонуть на '" + ship.getName() +"(-e)'", 
                                unHappyGroup.addEmotion(EmotionType.TERRIFIED), null, st2Scenary20effect, 
                                ship.getTimeGoing(ocean));
                Location calmBay = new Location("Тихая бухта", new Coordinate(-70000, 655555, 0));
                Scenary st2Scenary21 = new Scenary(ship, 
                                "Войти в " + calmBay.getName().toLowerCase(),
                                ship.goTo(calmBay), null, null,
                                ship.getTimeGoing(calmBay));
                Scenary st2Scenary22 = new Scenary(unHappyGroup, 
                                "Сойти с '" + ship.getName().toLowerCase() + "(-я)'",
                                unHappyGroup.getOutTheVehicle(ship), null, null,
                                60);
                                
                
        }
        var ponchosStory2 = new PonchosStory2();
        ArrayList<Scenary> arrayOfScenarySentences2 = new ArrayList<Scenary>();
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary1);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary2);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary3);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary4);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary5);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary6);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary7);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary8);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary9);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary10);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary11);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary12);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary13);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary14);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary15);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary16);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary17);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary18);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary19);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary20);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary21);
        arrayOfScenarySentences2.add(ponchosStory2.st2Scenary22);
        Story story2 = new Story(arrayOfScenarySentences2);

        // System.out,println(...) Output stories
        System.out.println(story1.execute());
        System.out.println(storyAboutPoncho.execute());
        System.out.println(story2.execute());
        }
}
