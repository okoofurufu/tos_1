import com.example.lab1_tos.DomainModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class DomainModelTest {

    //создание объекта Person
    @Test
    public void testPerson() {
        DomainModel.Person person = new DomainModel.Person("Arthur");
        assertEquals("Arthur", person.getName());
    }

    //создание нескольких людей
    @Test
    public void testMultiplePersons() {
        DomainModel.Person person1 = new DomainModel.Person("Arthur");
        DomainModel.Person person2 = new DomainModel.Person("Boris");
        assertEquals("Arthur", person1.getName());
        assertEquals("Boris", person2.getName());
    }

    //создание толпы
    @Test
    public void testCrowd() {
        List<DomainModel.Person> people = Arrays.asList(new DomainModel.Person("Arthur"), new DomainModel.Person("Boris"));
        assertEquals(2, people.size());
        assertEquals("Arthur", people.get(0).getName());
        assertEquals("Boris", people.get(1).getName());
    }

    //заимодействие людей в толпе
    @Test
    public void testPersonInteraction() {
        DomainModel.Person person1 = new DomainModel.Person("Arthur");
        DomainModel.Person person2 = new DomainModel.Person("Boris");
        String interactionMessage = person1.greet(person2);
        assertEquals("Arthur greets Boris", interactionMessage);
    }

    // пустая толпа
    @Test
    public void testEmptyCrowd() {
        List<DomainModel.Person> people = Arrays.asList();
        assertTrue(people.isEmpty());
    }

    //null имя
    @Test
    public void testNullName() {
        DomainModel.Person person = new DomainModel.Person(null);
        assertNull(person.getName());
    }

    // пустое имя
    @Test
    public void testEmptyName() {
        DomainModel.Person person = new DomainModel.Person("");
        assertEquals("", person.getName());
    }

    //добавление человека в толпу
    @Test
    public void testAddPersonToCrowd() {
        DomainModel.Person person1 = new DomainModel.Person("Arthur");
        DomainModel.Person person2 = new DomainModel.Person("Boris");
        List<DomainModel.Person> people = Arrays.asList(person1, person2);
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person1);
        crowd.addPerson(person2);
        assertEquals(2, crowd.getSize());
    }

    //удаление человека из толпы
    @Test
    public void testRemovePersonFromCrowd() {
        DomainModel.Person person1 = new DomainModel.Person("Arthur");
        DomainModel.Person person2 = new DomainModel.Person("Boris");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person1);
        crowd.addPerson(person2);
        crowd.removePerson(person1);
        assertEquals(1, crowd.getSize());
        assertFalse(crowd.contains(person1));
    }

    //добавление нескольких людей в толпу
    @Test
    public void testAddMultiplePersons() {
        DomainModel.Person person1 = new DomainModel.Person("Arthur");
        DomainModel.Person person2 = new DomainModel.Person("Boris");
        DomainModel.Person person3 = new DomainModel.Person("Lena");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person1);
        crowd.addPerson(person2);
        crowd.addPerson(person3);
        assertEquals(3, crowd.getSize());
    }

    //работа с пустым именем в методах модели
    @Test
    public void testCrowdWithEmptyName() {
        DomainModel.Person person1 = new DomainModel.Person("");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person1);
        assertEquals(1, crowd.getSize());
    }

    //получение всех людей из толпы
    @Test
    public void testGetAllPeopleFromCrowd() {
        DomainModel.Person person1 = new DomainModel.Person("Arthur");
        DomainModel.Person person2 = new DomainModel.Person("Boris");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person1);
        crowd.addPerson(person2);
        List<DomainModel.Person> people = crowd.getAllPeople();
        assertEquals(2, people.size());
        assertTrue(people.contains(person1));
        assertTrue(people.contains(person2));
    }

    //правильность отображения толпы
    @Test
    public void testCrowdDisplay() {
        DomainModel.Person person1 = new DomainModel.Person("Arthur");
        DomainModel.Person person2 = new DomainModel.Person("Boris");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person1);
        crowd.addPerson(person2);
        assertEquals("Arthur, Boris", crowd.display());
    }

    //размер толпы
    @Test
    public void testCrowdSize() {
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        DomainModel.Person person1 = new DomainModel.Person("Arthur");
        DomainModel.Person person2 = new DomainModel.Person("Boris");
        crowd.addPerson(person1);
        crowd.addPerson(person2);
        assertEquals(2, crowd.getSize());
    }

    //отсутствие взаимодействий с пустой толпой
    @Test
    public void testNoInteractionInEmptyCrowd() {
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        assertEquals(0, crowd.getSize());
        assertThrows(IllegalArgumentException.class, () -> {
            crowd.interact();
        });
    }
}