import com.example.lab1_tos.DomainModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DomainModelTest {

    @Test
    void personShouldBeCreatedWithDefaultValues() {
        DomainModel.Person person = new DomainModel.Person("Alice");

        assertEquals("Alice", person.getName());
        assertEquals(DomainModel.Person.DEFAULT_MOOD, person.getMood());
        assertEquals(DomainModel.Person.DEFAULT_INFO, person.getInfo());
    }

    @Test
    void personShouldChangeName() {
        DomainModel.Person person = new DomainModel.Person("Alice");

        person.setName("Bob");

        assertEquals("Bob", person.getName());
    }

    @Test
    void personShouldChangeInfo() {
        DomainModel.Person person = new DomainModel.Person("Alice");

        person.setInfo("Important news");

        assertEquals("Important news", person.getInfo());
    }

    @Test
    void personShouldChangeMood() {
        DomainModel.Person person = new DomainModel.Person("Alice");

        person.setMood(DomainModel.Person.Mood.HAPPY);

        assertEquals(DomainModel.Person.Mood.HAPPY, person.getMood());
    }

    @Test
    void shareInfoShouldCopyInfoToOtherPerson() {
        DomainModel.Person first = new DomainModel.Person("Alice");
        DomainModel.Person second = new DomainModel.Person("Bob");
        first.setInfo("Secret");

        first.shareInfo(second);

        assertEquals("Secret", second.getInfo());
    }

    @Test
    void reactToInspirationalSpeechShouldMakePersonHappy() {
        DomainModel.Person person = new DomainModel.Person("Alice");

        person.reactToSpeech(DomainModel.Event.SpeechType.INSPIRATIONAL);

        assertEquals(DomainModel.Person.Mood.HAPPY, person.getMood());
    }

    @Test
    void reactToAngrySpeechShouldMakePersonAngry() {
        DomainModel.Person person = new DomainModel.Person("Alice");

        person.reactToSpeech(DomainModel.Event.SpeechType.ANGRY);

        assertEquals(DomainModel.Person.Mood.ANGRY, person.getMood());
    }

    @Test
    void reactToNeutralSpeechShouldMakePersonNeutral() {
        DomainModel.Person person = new DomainModel.Person("Alice");
        person.setMood(DomainModel.Person.Mood.HAPPY);

        person.reactToSpeech(DomainModel.Event.SpeechType.NEUTRAL);

        assertEquals(DomainModel.Person.Mood.NEUTRAL, person.getMood());
    }

    @Test
    void exchangeMoodShouldSwapMoods() {
        DomainModel.Person first = new DomainModel.Person("Alice");
        DomainModel.Person second = new DomainModel.Person("Bob");

        first.setMood(DomainModel.Person.Mood.HAPPY);
        second.setMood(DomainModel.Person.Mood.ANGRY);

        first.exchangeMood(second);

        assertEquals(DomainModel.Person.Mood.ANGRY, first.getMood());
        assertEquals(DomainModel.Person.Mood.HAPPY, second.getMood());
    }

    @Test
    void greetShouldReturnCorrectStringAndShareInfo() {
        DomainModel.Person first = new DomainModel.Person("Alice");
        DomainModel.Person second = new DomainModel.Person("Bob");
        first.setInfo("Hello");
        first.setMood(DomainModel.Person.Mood.HAPPY);

        String result = first.greet(second);

        assertEquals("Alice greets Bob with a happy mood.", result);
        assertEquals("Hello", second.getInfo());
    }

    @Test
    void buildingShouldBeCreatedCorrectly() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );

        assertEquals("Hall", building.getName());
        assertEquals(DomainModel.Location.CENTER, building.getLocation());
        assertEquals(150, building.getSize());
    }

    @Test
    void buildingShouldChangeFields() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );

        building.setName("Arena");
        building.setLocation(DomainModel.Location.NORTH);
        building.setSize(350);

        assertEquals("Arena", building.getName());
        assertEquals(DomainModel.Location.NORTH, building.getLocation());
        assertEquals(350, building.getSize());
    }

    @Test
    void determineMaxCrowdCapacityShouldReturnTwentyForLargeBuilding() {
        DomainModel.Building building = new DomainModel.Building(
                "Arena",
                DomainModel.Location.CENTER,
                350
        );

        assertEquals(20, building.determineMaxCrowdCapacity());
    }

    @Test
    void determineMaxCrowdCapacityShouldReturnTenForMediumBuilding() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );

        assertEquals(10, building.determineMaxCrowdCapacity());
    }

    @Test
    void determineMaxCrowdCapacityShouldReturnFiveForSmallBuilding() {
        DomainModel.Building building = new DomainModel.Building(
                "Room",
                DomainModel.Location.CENTER,
                50
        );

        assertEquals(5, building.determineMaxCrowdCapacity());
    }

    @Test
    void determineRequiredVolumeShouldReturnQuietWhenCrowdIsZero() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );

        assertEquals(DomainModel.Building.VolumeLevel.QUIET, building.determineRequiredVolume(0));
    }

    @Test
    void determineRequiredVolumeShouldReturnCorrectValueForLargeBuilding() {
        DomainModel.Building building = new DomainModel.Building(
                "Arena",
                DomainModel.Location.CENTER,
                350
        );

        assertEquals(DomainModel.Building.VolumeLevel.NORMAL, building.determineRequiredVolume(3));
        assertEquals(DomainModel.Building.VolumeLevel.LOUD, building.determineRequiredVolume(10));
        assertEquals(DomainModel.Building.VolumeLevel.VERY_LOUD, building.determineRequiredVolume(18));
    }

    @Test
    void determineRequiredVolumeShouldReturnCorrectValueForMediumBuilding() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );

        assertEquals(DomainModel.Building.VolumeLevel.QUIET, building.determineRequiredVolume(2));
        assertEquals(DomainModel.Building.VolumeLevel.NORMAL, building.determineRequiredVolume(5));
        assertEquals(DomainModel.Building.VolumeLevel.LOUD, building.determineRequiredVolume(9));
    }

    @Test
    void determineRequiredVolumeShouldReturnCorrectValueForSmallBuilding() {
        DomainModel.Building building = new DomainModel.Building(
                "Room",
                DomainModel.Location.CENTER,
                50
        );

        assertEquals(DomainModel.Building.VolumeLevel.QUIET, building.determineRequiredVolume(1));
        assertEquals(DomainModel.Building.VolumeLevel.NORMAL, building.determineRequiredVolume(3));
        assertEquals(DomainModel.Building.VolumeLevel.LOUD, building.determineRequiredVolume(5));
    }

    @Test
    void crowdShouldBeCreatedEmptyFromBuilding() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        assertEquals(0, crowd.getSize());
        assertEquals(building, crowd.getBuilding());
        assertEquals(DomainModel.Location.CENTER, crowd.getLocation());
    }

    @Test
    void crowdShouldBeCreatedFromLocationWithoutBuilding() {
        DomainModel.Crowd crowd = new DomainModel.Crowd(DomainModel.Location.NORTH);

        assertEquals(0, crowd.getSize());
        assertNull(crowd.getBuilding());
        assertEquals(DomainModel.Location.NORTH, crowd.getLocation());
        assertEquals(0, crowd.getMaxCapacity());
    }

    @Test
    void addPersonShouldIncreaseCrowdSize() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);
        DomainModel.Person person = new DomainModel.Person("Alice");

        crowd.addPerson(person);

        assertEquals(1, crowd.getSize());
        assertTrue(crowd.contains(person));
    }

    @Test
    void addPersonShouldNotAddWhenCrowdHasNoBuilding() {
        DomainModel.Crowd crowd = new DomainModel.Crowd(DomainModel.Location.CENTER);
        DomainModel.Person person = new DomainModel.Person("Alice");

        crowd.addPerson(person);

        assertEquals(0, crowd.getSize());
        assertFalse(crowd.contains(person));
    }

    @Test
    void removePersonShouldDecreaseCrowdSize() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);
        DomainModel.Person person = new DomainModel.Person("Alice");
        crowd.addPerson(person);

        crowd.removePerson(person);

        assertEquals(0, crowd.getSize());
        assertFalse(crowd.contains(person));
    }

    @Test
    void displayShouldReturnAllNamesSeparatedByComma() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);
        crowd.addPerson(new DomainModel.Person("Alice"));
        crowd.addPerson(new DomainModel.Person("Bob"));

        assertEquals("Alice, Bob", crowd.display());
    }

    @Test
    void getMaxCapacityShouldReturnBuildingCapacity() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        assertEquals(10, crowd.getMaxCapacity());
    }

    @Test
    void setBuildingShouldUpdateCrowdLocation() {
        DomainModel.Crowd crowd = new DomainModel.Crowd(DomainModel.Location.WEST);
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.EAST,
                150
        );

        crowd.setBuilding(building);

        assertEquals(building, crowd.getBuilding());
        assertEquals(DomainModel.Location.EAST, crowd.getLocation());
    }

    @Test
    void setBuildingShouldRemoveExtraPeopleIfNewBuildingIsSmaller() {
        DomainModel.Building largeBuilding = new DomainModel.Building(
                "Arena",
                DomainModel.Location.CENTER,
                350
        );
        DomainModel.Building smallBuilding = new DomainModel.Building(
                "Room",
                DomainModel.Location.WEST,
                50
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(largeBuilding);

        for (int i = 1; i <= 7; i++) {
            crowd.addPerson(new DomainModel.Person("Person" + i));
        }

        crowd.setBuilding(smallBuilding);

        assertEquals(smallBuilding, crowd.getBuilding());
        assertEquals(DomainModel.Location.WEST, crowd.getLocation());
        assertEquals(5, crowd.getSize());
    }

    @Test
    void setLocationShouldDropBuildingIfLocationsDoNotMatch() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        crowd.setLocation(DomainModel.Location.SOUTH);

        assertEquals(DomainModel.Location.SOUTH, crowd.getLocation());
        assertNull(crowd.getBuilding());
    }

    @Test
    void setLocationShouldKeepBuildingIfLocationsMatch() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        crowd.setLocation(DomainModel.Location.CENTER);

        assertEquals(DomainModel.Location.CENTER, crowd.getLocation());
        assertEquals(building, crowd.getBuilding());
    }

    @Test
    void isInBuildingShouldReturnTrueWhenLocationsMatch() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(DomainModel.Location.CENTER);

        assertTrue(crowd.isInBuilding(building));
    }

    @Test
    void isInBuildingShouldReturnFalseWhenLocationsDoNotMatch() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(DomainModel.Location.SOUTH);

        assertFalse(crowd.isInBuilding(building));
    }

    @Test
    void chooseBuildingShouldAssignMatchingBuilding() {
        DomainModel.Building first = new DomainModel.Building(
                "First",
                DomainModel.Location.NORTH,
                100
        );
        DomainModel.Building second = new DomainModel.Building(
                "Second",
                DomainModel.Location.EAST,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(DomainModel.Location.EAST);

        DomainModel.Building chosen = crowd.chooseBuilding(List.of(first, second));

        assertEquals(second, chosen);
        assertEquals(second, crowd.getBuilding());
        assertEquals(DomainModel.Location.EAST, crowd.getLocation());
    }

    @Test
    void chooseBuildingShouldReturnNullWhenNoMatchingBuildingExists() {
        DomainModel.Building first = new DomainModel.Building(
                "First",
                DomainModel.Location.NORTH,
                100
        );
        DomainModel.Building second = new DomainModel.Building(
                "Second",
                DomainModel.Location.WEST,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(DomainModel.Location.SOUTH);

        DomainModel.Building chosen = crowd.chooseBuilding(List.of(first, second));

        assertNull(chosen);
        assertNull(crowd.getBuilding());
        assertEquals(DomainModel.Location.SOUTH, crowd.getLocation());
    }

    @Test
    void interactShouldThrowExceptionIfLessThanTwoPeople() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);
        crowd.addPerson(new DomainModel.Person("Alice"));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                crowd::interact
        );

        assertEquals("At least two people are needed for interaction", exception.getMessage());
    }

    @Test
    void interactShouldKeepSameCrowdSize() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);
        crowd.addPerson(new DomainModel.Person("Alice"));
        crowd.addPerson(new DomainModel.Person("Bob"));

        crowd.interact();

        assertEquals(2, crowd.getSize());
    }

    @Test
    void changeMoodBasedOnSpeechShouldChangeMoodForAllPeople() {
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);
        DomainModel.Person first = new DomainModel.Person("Alice");
        DomainModel.Person second = new DomainModel.Person("Bob");

        crowd.addPerson(first);
        crowd.addPerson(second);

        crowd.changeMoodBasedOnSpeech(DomainModel.Event.SpeechType.INSPIRATIONAL);

        assertEquals(DomainModel.Person.Mood.HAPPY, first.getMood());
        assertEquals(DomainModel.Person.Mood.HAPPY, second.getMood());
    }

    @Test
    void eventShouldSetSameBuildingForCrowd() {
        DomainModel.Person speaker = new DomainModel.Person("Speaker");
        DomainModel.Building firstBuilding = new DomainModel.Building(
                "Old Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Building secondBuilding = new DomainModel.Building(
                "New Hall",
                DomainModel.Location.SOUTH,
                350
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(firstBuilding);

        DomainModel.Event event = new DomainModel.Event(speaker, crowd, null, secondBuilding);

        assertEquals(secondBuilding, event.getBuilding());
        assertEquals(secondBuilding, crowd.getBuilding());
        assertEquals(DomainModel.Location.SOUTH, crowd.getLocation());
    }

    @Test
    void eventConstructorShouldMoveCrowdToBuildingLocationIfNeeded() {
        DomainModel.Person speaker = new DomainModel.Person("Speaker");
        DomainModel.Crowd crowd = new DomainModel.Crowd(DomainModel.Location.NORTH);
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );

        DomainModel.Event event = new DomainModel.Event(speaker, crowd, null, building);

        assertEquals(building, event.getBuilding());
        assertEquals(building, crowd.getBuilding());
        assertEquals(DomainModel.Location.CENTER, crowd.getLocation());
    }

    @Test
    void setBuildingInEventShouldAlsoChangeCrowdBuilding() {
        DomainModel.Person speaker = new DomainModel.Person("Speaker");
        DomainModel.Building firstBuilding = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Building secondBuilding = new DomainModel.Building(
                "Arena",
                DomainModel.Location.NORTH,
                350
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(firstBuilding);
        DomainModel.Event event = new DomainModel.Event(speaker, crowd, null, firstBuilding);

        event.setBuilding(secondBuilding);

        assertEquals(secondBuilding, event.getBuilding());
        assertEquals(secondBuilding, crowd.getBuilding());
        assertEquals(DomainModel.Location.NORTH, crowd.getLocation());
    }

    @Test
    void startSpeechShouldChangeCrowdMoodAndShareSpeakerInfo() {
        DomainModel.Person speaker = new DomainModel.Person("Speaker");
        speaker.setInfo("Important message");

        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd crowd = new DomainModel.Crowd(building);

        DomainModel.Person first = new DomainModel.Person("Alice");
        DomainModel.Person second = new DomainModel.Person("Bob");

        crowd.addPerson(first);
        crowd.addPerson(second);

        DomainModel.Event event = new DomainModel.Event(speaker, crowd, null, building);
        event.startSpeech(DomainModel.Event.SpeechType.INSPIRATIONAL);

        assertEquals(DomainModel.Person.Mood.HAPPY, first.getMood());
        assertEquals(DomainModel.Person.Mood.HAPPY, second.getMood());
        assertEquals("Important message", first.getInfo());
        assertEquals("Important message", second.getInfo());
    }

    @Test
    void setCrowdShouldReplaceCrowdInEvent() {
        DomainModel.Person speaker = new DomainModel.Person("Speaker");
        DomainModel.Building building = new DomainModel.Building(
                "Hall",
                DomainModel.Location.CENTER,
                150
        );
        DomainModel.Crowd firstCrowd = new DomainModel.Crowd(building);
        DomainModel.Crowd secondCrowd = new DomainModel.Crowd(building);

        DomainModel.Event event = new DomainModel.Event(speaker, firstCrowd, null, building);
        event.setCrowd(secondCrowd);

        assertEquals(secondCrowd, event.getCrowd());
    }
}