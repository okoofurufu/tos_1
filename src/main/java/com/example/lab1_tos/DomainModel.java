package com.example.lab1_tos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DomainModel {

    public enum Location {
        NORTH,
        SOUTH,
        WEST,
        EAST,
        CENTER,
        NORTH_WEST,
        NORTH_EAST,
        SOUTH_WEST,
        SOUTH_EAST
    }

    public static class Person {
        public static final Mood DEFAULT_MOOD = Mood.NEUTRAL;
        public static final String DEFAULT_INFO = "";

        public enum Mood {
            NEUTRAL,
            HAPPY,
            ANGRY
        }

        private String name;
        private Mood mood;
        private String info;

        public Person(String name) {
            this.name = name;
            this.mood = DEFAULT_MOOD;
            this.info = DEFAULT_INFO;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Mood getMood() {
            return mood;
        }

        public void setMood(Mood mood) {
            this.mood = mood;
        }

        public void shareInfo(Person other) {
            other.setInfo(this.info);
            System.out.println(this.name + " shares information with " + other.getName() + ": " + this.info);
        }

        public void reactToSpeech(Event.SpeechType speechType) {
            switch (speechType) {
                case INSPIRATIONAL:
                    this.mood = Mood.HAPPY;
                    break;
                case ANGRY:
                    this.mood = Mood.ANGRY;
                    break;
                default:
                    this.mood = Mood.NEUTRAL;
                    break;
            }
        }

        public void exchangeMood(Person other) {
            Mood tempMood = this.mood;
            this.mood = other.mood;
            other.mood = tempMood;

            System.out.println(this.name + " and " + other.getName() + " exchanged moods.");
        }

        public String greet(Person other) {
            this.shareInfo(other);
            return this.name + " greets " + other.getName() + " with a " + this.mood.name().toLowerCase() + " mood.";
        }
    }

    public static class Building {
        public enum VolumeLevel {
            QUIET,
            NORMAL,
            LOUD,
            VERY_LOUD
        }

        private String name;
        private Location location;
        private int size;

        public Building(String name, Location location, int size) {
            this.name = name;
            this.location = location;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int determineMaxCrowdCapacity() {
            if (size > 300) {
                return 20;
            } else if (size >= 100) {
                return 10;
            } else {
                return 5;
            }
        }

        public VolumeLevel determineRequiredVolume(int crowdSize) {
            int maxCapacity = determineMaxCrowdCapacity();

            if (crowdSize == 0) {
                return VolumeLevel.QUIET;
            }

            double crowdDensity = (double) crowdSize / maxCapacity;

            if (size > 300) {
                if (crowdDensity <= 0.3) {
                    return VolumeLevel.NORMAL;
                } else if (crowdDensity <= 0.7) {
                    return VolumeLevel.LOUD;
                } else {
                    return VolumeLevel.VERY_LOUD;
                }
            } else if (size >= 100) {
                if (crowdDensity <= 0.3) {
                    return VolumeLevel.QUIET;
                } else if (crowdDensity <= 0.7) {
                    return VolumeLevel.NORMAL;
                } else {
                    return VolumeLevel.LOUD;
                }
            } else {
                if (crowdDensity <= 0.3) {
                    return VolumeLevel.QUIET;
                } else if (crowdDensity <= 0.7) {
                    return VolumeLevel.NORMAL;
                } else {
                    return VolumeLevel.LOUD;
                }
            }
        }
    }

    public static class Crowd {
        private List<Person> people;
        private Building building;
        private Location location;
        private Random random;

        public Crowd(Building building) {
            this.people = new ArrayList<>();
            this.building = building;
            this.location = building.getLocation();
            this.random = new Random();
        }

        public Crowd(Location location) {
            this.people = new ArrayList<>();
            this.building = null;
            this.location = location;
            this.random = new Random();
        }

        public void addPerson(Person person) {
            if (building == null) {
                System.out.println("Crowd is not assigned to any building.");
                return;
            }

            if (people.size() < building.determineMaxCrowdCapacity()) {
                people.add(person);
            } else {
                System.out.println(
                        "The crowd has reached the maximum capacity for building " +
                                building.getName() + ". Cannot add more people."
                );
            }
        }

        public void removePerson(Person person) {
            people.remove(person);
        }

        public List<Person> getAllPeople() {
            return people;
        }

        public String display() {
            StringBuilder display = new StringBuilder();
            for (Person person : people) {
                if (display.length() > 0) {
                    display.append(", ");
                }
                display.append(person.getName());
            }
            return display.toString();
        }

        public int getSize() {
            return people.size();
        }

        public boolean contains(Person person) {
            return people.contains(person);
        }

        public int getMaxCapacity() {
            if (building == null) {
                return 0;
            }
            return building.determineMaxCrowdCapacity();
        }

        public Building getBuilding() {
            return building;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;

            if (building != null && building.getLocation() != location) {
                building = null;
            }
        }

        public void setBuilding(Building building) {
            this.building = building;
            this.location = building.getLocation();

            while (people.size() > building.determineMaxCrowdCapacity()) {
                Person removedPerson = people.remove(people.size() - 1);
                System.out.println(
                        removedPerson.getName() +
                                " was removed because the new building capacity is smaller."
                );
            }
        }

        public boolean isInBuilding(Building building) {
            return this.location == building.getLocation();
        }

        public Building chooseBuilding(List<Building> availableBuildings) {
            for (Building candidate : availableBuildings) {
                if (candidate.getLocation() == this.location) {
                    this.setBuilding(candidate);
                    return candidate;
                }
            }

            this.building = null;
            return null;
        }

        public void interact() {
            if (people.size() < 2) {
                throw new IllegalArgumentException("At least two people are needed for interaction");
            }

            int firstIndex = random.nextInt(people.size());
            int secondIndex;

            do {
                secondIndex = random.nextInt(people.size());
            } while (secondIndex == firstIndex);

            Person firstPerson = people.get(firstIndex);
            Person secondPerson = people.get(secondIndex);

            System.out.println(firstPerson.getName() + " interacts with " + secondPerson.getName());
            firstPerson.exchangeMood(secondPerson);
        }

        public void changeMoodBasedOnSpeech(Event.SpeechType speechType) {
            for (Person person : people) {
                person.reactToSpeech(speechType);
            }
        }
    }

    public static class Event {
        public enum SpeechType {
            INSPIRATIONAL,
            ANGRY,
            NEUTRAL
        }

        private Person speaker;
        private Crowd crowd;
        private Building building;

        public Event(Person speaker, Crowd crowd, Object stage, Building building) {
            this.speaker = speaker;
            this.crowd = crowd;
            this.building = building;

            if (!crowd.isInBuilding(building)) {
                crowd.setLocation(building.getLocation());
            }

            crowd.setBuilding(building);
        }

        public void startSpeech(SpeechType speechType) {
            Building.VolumeLevel volume = calculateSpeechVolume();

            System.out.println(
                    speaker.getName() + " starts an " + speechType.name().toLowerCase() +
                            " speech in " + building.getName() +
                            " with a " + volume.name().toLowerCase().replace('_', ' ') + " voice."
            );

            crowd.changeMoodBasedOnSpeech(speechType);

            for (Person person : crowd.getAllPeople()) {
                speaker.shareInfo(person);
            }
        }

        private Building.VolumeLevel calculateSpeechVolume() {
            return building.determineRequiredVolume(crowd.getSize());
        }

        public Person getSpeaker() {
            return speaker;
        }

        public Crowd getCrowd() {
            return crowd;
        }

        public void setCrowd(Crowd crowd) {
            this.crowd = crowd;
        }

        public Building getBuilding() {
            return building;
        }

        public void setBuilding(Building building) {
            this.building = building;
            this.crowd.setBuilding(building);
        }
    }
}