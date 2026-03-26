import com.example.lab1_tos.DomainModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DomainModelTest {

    @Test
    public void testPersonCreation() {
        DomainModel.Person person = new DomainModel.Person("Артур");

        assertNotNull(person);
        assertEquals("Артур", person.getName());
        assertEquals("neutral", person.getMood());
        assertEquals("", person.getInfo());
    }

    @Test
    public void testPersonCreationWithEmptyName() {
        DomainModel.Person person = new DomainModel.Person("");

        assertNotNull(person);
        assertEquals("", person.getName());
    }

    @Test
    public void testPersonWithLargeName() {
        String largeName = "А".repeat(1000);
        DomainModel.Person person = new DomainModel.Person(largeName);

        assertEquals(largeName, person.getName());
    }

    @Test
    public void testSetMethodsForPerson() {
        DomainModel.Person person = new DomainModel.Person("Оратор");

        person.setName("Новый Оратор");
        person.setMood("happy");
        person.setInfo("Новая информация");

        assertEquals("Новый Оратор", person.getName());
        assertEquals("happy", person.getMood());
        assertEquals("Новая информация", person.getInfo());
    }

    @Test
    public void testPersonMoodChangeAfterSpeech() {
        DomainModel.Person person = new DomainModel.Person("Артур");

        assertEquals("neutral", person.getMood());

        person.reactToSpeech("inspirational");
        assertEquals("happy", person.getMood());

        person.reactToSpeech("angry");
        assertEquals("angry", person.getMood());

        person.reactToSpeech("other");
        assertEquals("neutral", person.getMood());
    }

    @Test
    public void testShareInfoBetweenPeople() {
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Person listener = new DomainModel.Person("Артур");

        speaker.setInfo("Новая информация");
        speaker.shareInfo(listener);

        assertEquals("Новая информация", listener.getInfo());
    }

    @Test
    public void testGreetSharesInfo() {
        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");

        person1.setInfo("Секрет");
        String greeting = person1.greet(person2);

        assertEquals("Оратор greets Артур with a neutral mood.", greeting);
        assertEquals("Секрет", person2.getInfo());
    }

    @Test
    public void testExchangeMood() {
        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");

        person1.setMood("happy");
        person2.setMood("angry");

        person1.exchangeMood(person2);

        assertEquals("angry", person1.getMood());
        assertEquals("happy", person2.getMood());
    }

    @Test
    public void testBuildingCreation() {
        DomainModel.Building building = new DomainModel.Building("Здание с помостом", "Второй этаж", 150);

        assertNotNull(building);
        assertEquals("Здание с помостом", building.getName());
        assertEquals("Второй этаж", building.getLocation());
        assertEquals(150, building.getSize());
    }

    @Test
    public void testBuildingSetMethods() {
        DomainModel.Building building = new DomainModel.Building("Старое здание", "Первый этаж", 100);

        building.setName("Новое здание");
        building.setLocation("Третий этаж");
        building.setSize(350);

        assertEquals("Новое здание", building.getName());
        assertEquals("Третий этаж", building.getLocation());
        assertEquals(350, building.getSize());
    }

    @Test
    public void testBuildingMaxCrowdCapacitySmall() {
        DomainModel.Building building = new DomainModel.Building("Маленькое здание", "Центр", 50);

        assertEquals(5, building.determineMaxCrowdCapacity());
    }

    @Test
    public void testBuildingMaxCrowdCapacityMedium() {
        DomainModel.Building building = new DomainModel.Building("Среднее здание", "Центр", 150);

        assertEquals(10, building.determineMaxCrowdCapacity());
    }

    @Test
    public void testBuildingMaxCrowdCapacityLarge() {
        DomainModel.Building building = new DomainModel.Building("Большое здание", "Центр", 400);

        assertEquals(20, building.determineMaxCrowdCapacity());
    }

    @Test
    public void testDetermineRequiredVolumeForSmallBuilding() {
        DomainModel.Building building = new DomainModel.Building("Маленькое здание", "Центр", 50);

        assertEquals("quiet", building.determineRequiredVolume(1));
        assertEquals("normal", building.determineRequiredVolume(3));
        assertEquals("loud", building.determineRequiredVolume(5));
    }

    @Test
    public void testDetermineRequiredVolumeForMediumBuilding() {
        DomainModel.Building building = new DomainModel.Building("Среднее здание", "Центр", 150);

        assertEquals("quiet", building.determineRequiredVolume(2));
        assertEquals("normal", building.determineRequiredVolume(5));
        assertEquals("loud", building.determineRequiredVolume(10));
    }

    @Test
    public void testDetermineRequiredVolumeForLargeBuilding() {
        DomainModel.Building building = new DomainModel.Building("Большое здание", "Центр", 400);

        assertEquals("normal", building.determineRequiredVolume(3));
        assertEquals("loud", building.determineRequiredVolume(10));
        assertEquals("very loud", building.determineRequiredVolume(20));
    }

    @Test
    public void testEmptyCrowd() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        assertEquals(0, crowd.getSize());
        assertEquals(10, crowd.getMaxCapacity());
        assertEquals(building, crowd.getBuilding());
    }

    @Test
    public void testCrowdAddPerson() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);
        DomainModel.Person person = new DomainModel.Person("Оратор");

        crowd.addPerson(person);

        assertEquals(1, crowd.getSize());
        assertTrue(crowd.contains(person));
    }

    @Test
    public void testAddMultiplePeopleToCrowd() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");

        crowd.addPerson(person1);
        crowd.addPerson(person2);

        assertEquals(2, crowd.getSize());
        assertTrue(crowd.contains(person1));
        assertTrue(crowd.contains(person2));
    }

    @Test
    public void testAddDuplicatePersonToCrowd() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Оратор");

        crowd.addPerson(person1);
        crowd.addPerson(person2);

        assertEquals(2, crowd.getSize());
        assertTrue(crowd.contains(person1));
        assertTrue(crowd.contains(person2));
    }

    @Test
    public void testCrowdMaxCapacityDependsOnBuilding() {
        DomainModel.Building smallBuilding = new DomainModel.Building("Маленькое", "Центр", 50);
        DomainModel.Crowd crowd = new DomainModel.Crowd(smallBuilding);

        for (int i = 0; i < 10; i++) {
            crowd.addPerson(new DomainModel.Person("Человек " + i));
        }

        assertEquals(5, crowd.getSize());
        assertEquals(5, crowd.getMaxCapacity());
    }

    @Test
    public void testRemovePersonFromCrowd() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);
        DomainModel.Person person = new DomainModel.Person("Оратор");

        crowd.addPerson(person);
        assertEquals(1, crowd.getSize());

        crowd.removePerson(person);

        assertEquals(0, crowd.getSize());
        assertFalse(crowd.contains(person));
    }

    @Test
    public void testRemoveNonExistingPersonFromCrowd() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        DomainModel.Person arthur = new DomainModel.Person("Артур");

        crowd.addPerson(speaker);
        crowd.removePerson(arthur);

        assertEquals(1, crowd.getSize());
        assertTrue(crowd.contains(speaker));
    }

    @Test
    public void testCrowdDisplay() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");

        crowd.addPerson(person1);
        crowd.addPerson(person2);

        String result = crowd.display();

        assertTrue(result.contains("Оратор"));
        assertTrue(result.contains("Артур"));
    }

    @Test
    public void testGetAllPeople() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");

        crowd.addPerson(person1);
        crowd.addPerson(person2);

        List<DomainModel.Person> people = crowd.getAllPeople();

        assertEquals(2, people.size());
        assertTrue(people.contains(person1));
        assertTrue(people.contains(person2));
    }

    @Test
    public void testEmptyCrowdInteraction() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        assertThrows(IllegalArgumentException.class, crowd::interact);
    }

    @Test
    public void testCrowdInteractionOnePerson() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        crowd.addPerson(new DomainModel.Person("Оратор"));

        assertThrows(IllegalArgumentException.class, crowd::interact);
    }

    @Test
    public void testCrowdInteractionExchangesMoodsForTwoPeople() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");

        person1.setMood("happy");
        person2.setMood("angry");

        crowd.addPerson(person1);
        crowd.addPerson(person2);

        crowd.interact();

        assertEquals("angry", person1.getMood());
        assertEquals("happy", person2.getMood());
    }

    @Test
    public void testChangeMoodBasedOnSpeech() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        DomainModel.Person person1 = new DomainModel.Person("Оратор");
        DomainModel.Person person2 = new DomainModel.Person("Артур");

        crowd.addPerson(person1);
        crowd.addPerson(person2);

        crowd.changeMoodBasedOnSpeech("inspirational");

        assertEquals("happy", person1.getMood());
        assertEquals("happy", person2.getMood());
    }

    @Test
    public void testSetBuildingForCrowdWithSmallerCapacity() {
        DomainModel.Building largeBuilding = new DomainModel.Building("Большое здание", "Центр", 400);
        DomainModel.Building smallBuilding = new DomainModel.Building("Маленькое здание", "Центр", 50);

        DomainModel.Crowd crowd = new DomainModel.Crowd(largeBuilding);

        for (int i = 0; i < 7; i++) {
            crowd.addPerson(new DomainModel.Person("Человек " + i));
        }

        assertEquals(7, crowd.getSize());

        crowd.setBuilding(smallBuilding);

        assertEquals(5, crowd.getSize());
        assertEquals(smallBuilding, crowd.getBuilding());
        assertEquals(5, crowd.getMaxCapacity());
    }

    @Test
    public void testEventCreation() {
        DomainModel.Building building = new DomainModel.Building("Здание с помостом", "Второй этаж", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);
        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        String stage = "Помост";

        DomainModel.Event event = new DomainModel.Event(speaker, crowd, stage, building);

        assertNotNull(event);
        assertEquals(speaker, event.getSpeaker());
        assertEquals(crowd, event.getCrowd());
        assertEquals(stage, event.getStage());
        assertEquals(building, event.getBuilding());
    }

    @Test
    public void testEventChangesCrowdMoodAndSharesInfo() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        DomainModel.Person speaker = new DomainModel.Person("Оратор");
        speaker.setInfo("Важная информация");

        DomainModel.Person person1 = new DomainModel.Person("Артур");
        DomainModel.Person person2 = new DomainModel.Person("Алекс");

        crowd.addPerson(person1);
        crowd.addPerson(person2);

        DomainModel.Event event = new DomainModel.Event(speaker, crowd, "Помост", building);
        event.startSpeech("inspirational");

        assertEquals("happy", person1.getMood());
        assertEquals("happy", person2.getMood());
        assertEquals("Важная информация", person1.getInfo());
        assertEquals("Важная информация", person2.getInfo());
    }

    @Test
    public void testEventSetBuildingAlsoChangesCrowdBuilding() {
        DomainModel.Building building1 = new DomainModel.Building("Здание 1", "Центр", 400);
        DomainModel.Building building2 = new DomainModel.Building("Здание 2", "Центр", 50);

        DomainModel.Crowd crowd = new DomainModel.Crowd(building1);
        crowd.addPerson(new DomainModel.Person("Человек 1"));
        crowd.addPerson(new DomainModel.Person("Человек 2"));
        crowd.addPerson(new DomainModel.Person("Человек 3"));
        crowd.addPerson(new DomainModel.Person("Человек 4"));
        crowd.addPerson(new DomainModel.Person("Человек 5"));
        crowd.addPerson(new DomainModel.Person("Человек 6"));

        DomainModel.Event event = new DomainModel.Event(
                new DomainModel.Person("Оратор"),
                crowd,
                "Помост",
                building1
        );

        event.setBuilding(building2);

        assertEquals(building2, event.getBuilding());
        assertEquals(building2, event.getCrowd().getBuilding());
        assertEquals(5, event.getCrowd().getSize());
    }

    @Test
    public void testDetermineRequiredVolumeForEmptyCrowd() {
        DomainModel.Building building = new DomainModel.Building("Здание", "Центр", 150);

        String volume = building.determineRequiredVolume(0);

        assertEquals("quiet", volume);
    }

    @Test
    public void testEventConstructorSynchronizesCrowdBuilding() {
        DomainModel.Building building1 = new DomainModel.Building("Здание 1", "Центр", 400);
        DomainModel.Building building2 = new DomainModel.Building("Здание 2", "Центр", 50);

        DomainModel.Crowd crowd = new DomainModel.Crowd(building1);
        crowd.addPerson(new DomainModel.Person("Человек 1"));
        crowd.addPerson(new DomainModel.Person("Человек 2"));
        crowd.addPerson(new DomainModel.Person("Человек 3"));
        crowd.addPerson(new DomainModel.Person("Человек 4"));
        crowd.addPerson(new DomainModel.Person("Человек 5"));
        crowd.addPerson(new DomainModel.Person("Человек 6"));

        DomainModel.Event event = new DomainModel.Event(
                new DomainModel.Person("Оратор"),
                crowd,
                "Помост",
                building2
        );

        assertEquals(building2, event.getBuilding());
        assertEquals(building2, crowd.getBuilding());
        assertEquals(5, crowd.getSize());
    }
}