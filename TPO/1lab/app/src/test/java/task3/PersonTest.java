package task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonTest {

    private Person person;
    private Person capitan;
    private Crew crew;

    @BeforeEach
    void setUp() {
        // Создаем тестовые данные
        person = new Person("Иван", "Иванов", Person.Post.ORDINARY, Person.Mood.GOOD, Person.Sex.MALE, true);
        capitan = new Person("Петр", "Петров", Person.Post.CAPITAN, Person.Mood.GOOD, Person.Sex.MALE, false);

        List<Person> crewMembers = new ArrayList<>();
        crewMembers.add(person);
        crew = new Crew(capitan, crewMembers);
    }

    @Test
    void testConstructor() {
        // Проверяем, что объект создан с правильными параметрами
        assertEquals("Иван", person.getName());
        assertEquals("Иванов", person.getSurname());
        assertEquals(Person.Post.ORDINARY, person.getPost());
        assertEquals(Person.Mood.GOOD, person.getMood());
        assertEquals(Person.Sex.MALE, person.getSex());
        assertTrue(person.isPlanningHoliday());
    }

    @Test
    void testIsCapitan() {
        // Проверяем, что метод isCapitan работает корректно
        assertFalse(person.isCapitan());
        assertTrue(capitan.isCapitan());
    }

    @Test
    void testIsGoodMood() {
        // Проверяем, что метод isGoodMood работает корректно
        assertTrue(person.isGoodMood());
        person.setMood(Person.Mood.BAD);
        assertFalse(person.isGoodMood());
    }

    @Test
    void testSetMood() {
        // Проверяем изменение настроения
        Message message = person.setMood(Person.Mood.BAD);
        assertEquals(Person.Mood.BAD, person.getMood());
        assertNotNull(message);
        assertEquals("У человека Иван Иванов меняется настроение на BAD", message.getText());
    }

    @Test
    void testDeclineCrewHolidays() {
        // Проверяем, что капитан может отменить отпуск экипажу
        assertEquals(0, capitan.declineCrewHolidays(crew));
        assertFalse(person.isPlanningHoliday());
        assertEquals(Person.Mood.BAD, person.getMood());

        // Проверяем, что обычный член экипажа не может отменить отпуск
        assertEquals(1, person.declineCrewHolidays(crew));
    }

    @Test
    void testDeclineCrewHolidaysByNonCapitan() {
        // Проверяем, что обычный член экипажа не может отменить отпуск
        assertEquals(1, person.declineCrewHolidays(crew));
        assertTrue(person.isPlanningHoliday()); // Отпуск не отменен
    }
}