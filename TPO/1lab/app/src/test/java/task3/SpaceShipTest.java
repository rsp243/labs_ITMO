package task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import task3.Person.Mood;
import task3.Person.Post;
import task3.Person.Sex;
import task3.SpaceShip.ShipState;

class SpaceShipTest {

    private SpaceShip spaceShip;
    private Crew crew;
    private SpaceObject earth;
    private SpaceObject mars;

    @BeforeEach
    void setUp() {
        Person misterCap = new Person("Иван", "Васильев", Post.CAPITAN, Mood.BAD, Sex.MALE, false);
        crew = new Crew(misterCap, new ArrayList<Person>());
        earth = new SpaceObject("Земля");
        mars = new SpaceObject("Марс");

        spaceShip = new SpaceShip(false, crew, earth, ShipState.MOVING);
    }

    @Test
    void testConstructor() {
        // Проверяем, что корабль создан с правильными параметрами
        assertFalse(spaceShip.isHyperShip());
        assertEquals(crew, spaceShip.getCrew());
        assertEquals(earth, spaceShip.getDirection());
        assertEquals(ShipState.MOVING, spaceShip.getState());
    }

    @Test
    void testSetHyperShip() {
        // Проверяем изменение гипер-состояния корабля
        spaceShip.setHyperShip(true);
        assertTrue(spaceShip.isHyperShip());

        spaceShip.setHyperShip(false);
        assertFalse(spaceShip.isHyperShip());
    }

    @Test
    void testSetCrew() {
        Person misterCap = new Person("Петр", "Петров", Post.CAPITAN, Mood.BAD, Sex.MALE, false);
        Crew newCrew = new Crew(misterCap, new ArrayList<>());
        spaceShip.setCrew(newCrew);
        assertEquals(newCrew, spaceShip.getCrew());
    }

    @Test
    void testSetDirection() {
        spaceShip.setDirection(mars);
        assertEquals(mars, spaceShip.getDirection());

        spaceShip.setDirection(mars);
        assertEquals(mars, spaceShip.getDirection());
    }

    @Test
    void testSetState() {
        Message message = spaceShip.setState(SpaceShip.ShipState.JUMPING);
        assertEquals(SpaceShip.ShipState.JUMPING, spaceShip.getState());
        assertNotNull(message);
        assertEquals("Новое состоянии у корабля: Прыжок", message.getText());
    }
}
