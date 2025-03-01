package task3;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Person {
    public enum Post {
        CAPITAN(1),
        ORDINARY(0);

        private int priority;

        Post(int aPriority) {
            priority = aPriority;
        }

        public int getPriority() {
            return priority;
        }
    }

    public enum Mood {
        GOOD,
        BAD;
    }

    public enum Sex {
        MALE,
        FEMALE;
    }

    private String name;
    private String surname;
    private Post post;
    private Mood mood;
    private Sex sex;
    private boolean isPlanningHoliday;

    private List<Message> messageList;

    public Person(String aName, String aSurname, Post aPost, Mood aMood, Sex aSex, boolean isPlanningHoliday) {
        name = aName;
        surname = aSurname;
        post = aPost;
        mood = aMood;
        sex = aSex;
        this.isPlanningHoliday = isPlanningHoliday;

        messageList = new ArrayList<>();

        Message message = new Message(
                "Создан человек. Его зовут " + name + " " + surname + ". Он " + (isCapitan() ? "" : "не")
                        + " капитан, находится в " + (isGoodMood() ? "" : "не") + "хорошем настроении. "
                        + (isPlanningHoliday ? "Планирует" : "Не планирует") + " отпуск.");
        messageList.add(message);
    }

    public boolean isCapitan() {
        return post == Post.CAPITAN;
    }

    public boolean isGoodMood() {
        return mood == Mood.GOOD;
    }

    public int declineCrewHolidays(Crew crew) {
        if (crew.getCapitan().equals(this)) {
            declineCrewHolidays(crew.getCrew());

            return 0;
        }

        Message message = new Message("Только капитан может отменить отпуска экипажу.");
        messageList.add(message);

        return 1;
    }

    private void declineCrewHolidays(List<Person> crew) {
        Message message = new Message("Капитан отменяет всему экипажу отпуска.");
        for (Person person : crew) {
            person.setPlanningHoliday(false);
            messageList.add(person.setMood(Mood.BAD));
        }
        messageList.add(message);
    }

    public Message setMood(Mood aMood) {
        Message message = new Message("У человека " + name + " " + surname + " меняется настроение на " + aMood.toString());
        messageList.add(message);

        mood = aMood;

        return message;
    }
}
