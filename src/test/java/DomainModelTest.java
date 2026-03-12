
import com.example.lab1_tos.DomainModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DomainModelTest {

    //создание человека (Артур)
    @Test
    public void testPersonCreation() {
        DomainModel.Person person = new DomainModel.Person("Артур");
        assertNotNull(person);
        assertEquals("Артур", person.getName());
    }

    //создание толпы и добавление человека
    @Test
    public void testCrowdAddPerson() {
        DomainModel.Person person = new DomainModel.Person("Оратор");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person);
        assertEquals(1, crowd.getSize());
        assertTrue(crowd.contains(person));
    }

    //создание объекта "Помост" и проверку его типа
    @Test
    public void testObjectCreation() {
        DomainModel.Object stage = new DomainModel.Object("Помост", "Второй этаж здания", "Stage");
        assertNotNull(stage);
        assertEquals("Помост", stage.getName());
        assertEquals("Stage", stage.getType());
    }

    //создание события с оратором, толпой и сценой
    @Test
    public void testEventCreation() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        DomainModel.Object stage = new DomainModel.Object("Помост", "Второй этаж здания", "Stage");
        DomainModel.Building building = new DomainModel.Building("Здание с помостом", "Второй этаж");

        DomainModel.Event event = new DomainModel.Event(speaker, crowd, stage, building);

        assertNotNull(event);
        assertEquals("Оратор", event.getSpeaker().getName());
        assertEquals(0, event.getCrowd().getSize());  // толпа пуста
        assertEquals("Помост", event.getStage().getName());
        assertEquals("Здание с помостом", event.getBuilding().getName());
    }

    //взаимодействие людей в толпе
    @Test
    public void testCrowdInteraction() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Person arthur = new DomainModel.Person("Артур");

        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(speaker);
        crowd.addPerson(arthur);

        assertDoesNotThrow(() -> crowd.interact());
    }

    //удаление человека из толпы
    @Test
    public void testRemovePersonFromCrowd() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(speaker);

        assertEquals(1, crowd.getSize());

        crowd.removePerson(speaker);
        assertEquals(0, crowd.getSize());
        assertFalse(crowd.contains(speaker));
    }

    //тест на пустую толпу
    @Test
    public void testEmptyCrowdInteraction() {
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        assertThrows(IllegalArgumentException.class, () -> crowd.interact());
    }

    //создание здания
    @Test
    public void testBuildingCreation() {
        DomainModel.Building building = new DomainModel.Building("Здание с помостом", "Второй этаж");
        assertNotNull(building);
        assertEquals("Здание с помостом", building.getName());
        assertEquals("Второй этаж", building.getLocation());
    }

    //взаимодействие событий
    @Test
    public void testEventInteraction() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        DomainModel.Object stage = new DomainModel.Object("Помост", "Второй этаж здания", "Stage");
        DomainModel.Building building = new DomainModel.Building("Здание с помостом", "Второй этаж");

        DomainModel.Event event = new DomainModel.Event(speaker, crowd, stage, building);

        assertEquals("Оратор", event.getSpeaker().getName());
        assertEquals("Помост", event.getStage().getName());
        assertEquals("Здание с помостом", event.getBuilding().getName());
    }

    //добавление нескольких людей в толпу
    @Test
    public void testAddMultiplePeopleToCrowd() {
        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");

        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person1);
        crowd.addPerson(person2);

        assertEquals(2, crowd.getSize());
        assertTrue(crowd.contains(person1));
        assertTrue(crowd.contains(person2));
    }

    //отображение всех людей в толпе
    @Test
    public void testCrowdDisplay() {
        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");

        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person1);
        crowd.addPerson(person2);

        String display = crowd.display();
        assertTrue(display.contains("Оратор"));
        assertTrue(display.contains("Артур"));
    }
}