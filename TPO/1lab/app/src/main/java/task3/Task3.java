package task3;

import java.util.ArrayList;
import java.util.List;

import task3.Person.Mood;
import task3.Person.Post;
import task3.Person.Sex;
import task3.SpaceShip.ShipState;

public class Task3 {
    public static void solveTask() {
        // Во-вторых, мы вот-вот совершим прыжок в гиперкосмос для перелета к звезде
        // Барнарда. По прибытии мы встанем в док на семьдесят два часа для
        // профилактического осмотра. Никто в течение этого времени не должен покидать
        // корабль. Повторяю, все отпуска на планету отменяются. Меня недавно бросила
        // женщина, и я не вижу причины, почему, когда мне плохо, кому-то должно быть
        // хорошо. Конец сообщения.

        // initializing situation background
        SpaceObject barnardStar = new SpaceObject("Звезда Барнарда");

        // initializing connection
        Connection connection = new Connection();

        // capitan
        Person misterCap = new Person("Иван", "Васильев", Post.CAPITAN, Mood.BAD, Sex.MALE, false);

        // crew
        Person crewMember1 = new Person("Петр", "Иванов", Post.ORDINARY, Mood.GOOD, Sex.MALE, true);
        Person crewMember2 = new Person("Анна", "Машина", Post.ORDINARY, Mood.GOOD, Sex.FEMALE, true);
        Person crewMember3 = new Person("Николай", "Татьянин", Post.ORDINARY, Mood.GOOD, Sex.MALE, true);

        connection.sendSeveralMessages(crewMember1.getMessageList());
        connection.sendSeveralMessages(crewMember2.getMessageList());
        connection.sendSeveralMessages(crewMember3.getMessageList());

        List<Person> membersList = new ArrayList<>();
        membersList.add(crewMember1);
        membersList.add(crewMember2);
        membersList.add(crewMember3);

        Crew crew = new Crew(misterCap, membersList);

        SpaceShip ship = new SpaceShip(true, crew, barnardStar, ShipState.MOVING);

        connection.startConnection();

        ship.setState(ShipState.JUMPING);
        misterCap.declineCrewHolidays(crew);

        connection.sendSeveralMessages(ship.getMessageList());
        connection.sendSeveralMessages(misterCap.getMessageList());
        connection.sendMessage(ship.setState(ShipState.CHECKING));

        connection.finishConnection();

        for (Message message : connection.getMessageList()) {
            System.out.println(message.getText());
        }
    }
}
