import com.example.lab1_tos.DomainModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DomainModelTest {

    // создание человека
    @Test
    public void testPersonCreation() {
        DomainModel.Person person = new DomainModel.Person("Артур");
        assertNotNull(person);
        assertEquals("Артур", person.getName());
    }

    // создание человека с пустым именем
    @Test
    public void testPersonCreationWithEmptyName() {
        DomainModel.Person person = new DomainModel.Person("");
        assertNotNull(person);
        assertEquals("", person.getName());
    }

    // создание толпы и добавление человека
    @Test
    public void testCrowdAddPerson() {
        DomainModel.Person person = new DomainModel.Person("Оратор");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person);
        assertEquals(1, crowd.getSize());
        assertTrue(crowd.contains(person));
    }

    // добавление нескольких людей в толпу
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

    // создание объекта "Помост" и проверка его типа
    @Test
    public void testObjectCreation() {
        DomainModel.Object stage = new DomainModel.Object("Помост", "Второй этаж здания", "Stage");
        assertNotNull(stage);
        assertEquals("Помост", stage.getName());
        assertEquals("Stage", stage.getType());
    }

    // проверка на создание пустого объекта "Помост"
    @Test
    public void testObjectCreationEmpty() {
        DomainModel.Object stage = new DomainModel.Object("", "", "");
        assertNotNull(stage);
        assertEquals("", stage.getName());
        assertEquals("", stage.getLocation());
        assertEquals("", stage.getType());
    }

    // создание события с оратором, толпой и сценой
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

    // проверка на добавление одинаковых людей в толпу
    @Test
    public void testAddDuplicatePersonToCrowd() {
        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Оратор");

        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person1);
        crowd.addPerson(person2);

        // Толпа должна содержать только одного человека с именем "Оратор"
        assertEquals(2, crowd.getSize());
        assertTrue(crowd.contains(person1));
        assertTrue(crowd.contains(person2));
    }

    // тестирование обновления имени объекта
    @Test
    public void testUpdateObjectLocation() {
        DomainModel.Object stage = new DomainModel.Object("Помост", "Второй этаж здания", "Stage");
        stage.setLocation("Третий этаж здания");
        assertEquals("Третий этаж здания", stage.getLocation());
    }

    // взаимодействие людей в толпе
    @Test
    public void testCrowdInteraction() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Person arthur = new DomainModel.Person("Артур");

        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(speaker);
        crowd.addPerson(arthur);

        assertDoesNotThrow(() -> crowd.interact());
    }

    // тестирование взаимодействия одного человека с самим собой
    @Test
    public void testCrowdInteractionOnePerson() {
        DomainModel.Person person = new DomainModel.Person("Оратор");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person);

        assertDoesNotThrow(() -> crowd.interact());
    }

    // удаление человека из толпы
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

    // тест на пустую толпу
    @Test
    public void testEmptyCrowdInteraction() {
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        assertThrows(IllegalArgumentException.class, () -> crowd.interact());
    }

    // создание здания
    @Test
    public void testBuildingCreation() {
        DomainModel.Building building = new DomainModel.Building("Здание с помостом", "Второй этаж");
        assertNotNull(building);
        assertEquals("Здание с помостом", building.getName());
        assertEquals("Второй этаж", building.getLocation());
    }

    // взаимодействие событий
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

    // проверка на корректную работу set методов
    @Test
    public void testSetMethods() {
        DomainModel.Person person = new DomainModel.Person("Оратор");
        person.setName("Новый Оратор");
        assertEquals("Новый Оратор", person.getName());

        DomainModel.Building building = new DomainModel.Building("Здание с помостом", "Третий этаж");
        building.setName("Новый помост");
        building.setLocation("Четвертый этаж");
        assertEquals("Новый помост", building.getName());
        assertEquals("Четвертый этаж", building.getLocation());
    }

    // тестирование взаимодействия людей в толпе при большом количестве участников
    @Test
    public void testLargeCrowdInteraction() {
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        for (int i = 0; i < 1000; i++) {
            crowd.addPerson(new DomainModel.Person("Person " + i));
        }
        assertDoesNotThrow(() -> crowd.interact());
    }

    @Test
    public void testEmptyCrowd() {
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        assertEquals(0, crowd.getSize());
    }

    @Test
    public void testAddSinglePersonToCrowd() {
        DomainModel.Person person = new DomainModel.Person("Оратор");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person);
        assertEquals(1, crowd.getSize());
    }

    @Test
    public void testObjectWithNullValues() {
        DomainModel.Object stage = new DomainModel.Object(null, null, null);
        assertNull(stage.getName());
        assertNull(stage.getLocation());
        assertNull(stage.getType());
    }

    @Test
    public void testEventWithEmptyBuilding() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        DomainModel.Object stage = new DomainModel.Object("Помост", "Второй этаж", "Stage");
        DomainModel.Building building = null;

        DomainModel.Event event = new DomainModel.Event(speaker, crowd, stage, building);

        assertNotNull(event);
        assertNull(event.getBuilding());
    }

    @Test
    public void testRemoveNonExistingPersonFromCrowd() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Person arthur = new DomainModel.Person("Артур");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(speaker);

        crowd.removePerson(arthur);
        assertEquals(1, crowd.getSize());
        assertTrue(crowd.contains(speaker));
    }

    @Test
    public void testMultipleBuildings() {
        DomainModel.Building building1 = new DomainModel.Building("Здание с помостом", "Первый этаж");
        DomainModel.Building building2 = new DomainModel.Building("Другое здание", "Второй этаж");

        assertNotNull(building1);
        assertNotNull(building2);
        assertNotEquals(building1.getName(), building2.getName());
    }

    @Test
    public void testCrowdInteractionOutput() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Person arthur = new DomainModel.Person("Артур");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(speaker);
        crowd.addPerson(arthur);

        assertDoesNotThrow(() -> {
            crowd.interact();
        });
    }

    // Проверка корректной работы с большими строками
    @Test
    public void testPersonWithLargeName() {
        String largeName = "А".repeat(1000);
        DomainModel.Person person = new DomainModel.Person(largeName);
        assertEquals(largeName, person.getName());
    }


    // Проверка добавления объекта в толпу без указания имени
    @Test
    public void testAddObjectWithEmptyNameToCrowd() {
        DomainModel.Person person = new DomainModel.Person("");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person);
        assertEquals(1, crowd.getSize());
        assertTrue(crowd.contains(person));
    }

    // Преобразование в строку для толпы
    @Test
    public void testCrowdToString() {
        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person1);
        crowd.addPerson(person2);
        String crowdString = crowd.display();
        assertTrue(crowdString.contains("Оратор"));
        assertTrue(crowdString.contains("Артур"));
    }

    // Тестирование нескольких объектов в толпе
    @Test
    public void testMultipleCrowdInteractions() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Person arthur = new DomainModel.Person("Артур");
        DomainModel.Person alex = new DomainModel.Person("Алекс");

        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(speaker);
        crowd.addPerson(arthur);
        crowd.addPerson(alex);

        assertDoesNotThrow(() -> crowd.interact());
    }

    // Тест на проверку метода getAllPeople() в Crowd
    @Test
    public void testGetAllPeople() {
        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");

        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(person1);
        crowd.addPerson(person2);

        List<DomainModel.Person> people = crowd.getAllPeople();
        assertEquals(2, people.size());
        assertTrue(people.contains(person1));
        assertTrue(people.contains(person2));
    }

    // Тест на проверку метода setLocation в объекте Building
    @Test
    public void testSetBuildingLocation() {
        DomainModel.Building building = new DomainModel.Building("Здание с помостом", "Первый этаж");
        building.setLocation("Третий этаж");
        assertEquals("Третий этаж", building.getLocation());
    }

    // Тест на проверку правильности работы метода greet в классе Person
    @Test
    public void testPersonGreet() {
        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");

        String greeting = person1.greet(person2);
        assertEquals("Оратор greets Артур with a neutral mood.", greeting);
    }

    // тест на изменение настроения после речи
    @Test
    public void testPersonMoodChangeAfterSpeech() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Person person = new DomainModel.Person("Артур");
        assertEquals("neutral", speaker.getMood());
        speaker.reactToSpeech("inspirational");
        assertEquals("happy", speaker.getMood());
        speaker.reactToSpeech("angry");
        assertEquals("angry", speaker.getMood());
    }

    // тест на информацию, которую человек передает
    @Test
    public void testShareInfoBetweenPeople() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        speaker.setInfo("Новая информация");

        DomainModel.Person person = new DomainModel.Person("Артур");
        assertEquals("", person.getInfo());
        speaker.shareInfo(person);

        assertEquals("Новая информация", person.getInfo());
    }

    // тест на максимальную вместимость толпы
    @Test
    public void testCrowdMaxCapacity() {
        DomainModel.Crowd crowd = new DomainModel.Crowd();
        for (int i = 0; i < 10; i++) {
            crowd.addPerson(new DomainModel.Person("Человек " + i));
        }

        assertEquals(10, crowd.getSize());

        DomainModel.Person newPerson = new DomainModel.Person("Новый человек");
        crowd.addPerson(newPerson);

        assertEquals(10, crowd.getSize());
    }

    // тест на добавление информации и взаимодействие в толпе
    @Test
    public void testCrowdInteractionWithInfoSharing() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Person arthur = new DomainModel.Person("Артур");

        speaker.setInfo("Информация от оратора");
        speaker.shareInfo(arthur);

        DomainModel.Crowd crowd = new DomainModel.Crowd();
        crowd.addPerson(speaker);
        crowd.addPerson(arthur);

        assertEquals("Информация от оратора", arthur.getInfo());
    }
}