package com.example.lab1_tos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DomainModel {

    public static class Person {
        private String name;
        private String mood;
        private String info;

        public Person(String name) {
            this.name = name;
            this.mood = "neutral";
            this.info = "";
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

        public String getMood() {
            return mood;
        }

        public void setMood(String mood) {
            this.mood = mood;
        }

        public void shareInfo(Person other) {
            other.setInfo(this.info);
            System.out.println(this.name + " shares information with " + other.getName() + ": " + this.info);
        }

        public void reactToSpeech(String speechType) {
            if ("inspirational".equals(speechType)) {
                this.mood = "happy";
            } else if ("angry".equals(speechType)) {
                this.mood = "angry";
            } else {
                this.mood = "neutral";
            }
        }

        public void exchangeMood(Person other) {
            String tempMood = this.mood;
            this.mood = other.mood;
            other.mood = tempMood;

            System.out.println(this.name + " and " + other.getName() + " exchanged moods.");
        }

        public String greet(Person other) {
            this.shareInfo(other);
            return this.name + " greets " + other.getName() + " with a " + this.mood + " mood.";
        }
    }

    public static class Building {
        private String name;
        private String location;
        private int size;

        public Building(String name, String location, int size) {
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
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

        public String determineRequiredVolume(int crowdSize) {
            int maxCapacity = determineMaxCrowdCapacity();

            if (crowdSize == 0) {
                return "quiet";
            }

            double crowdDensity = (double) crowdSize / maxCapacity;

            if (size > 300) {
                if (crowdDensity <= 0.3) {
                    return "normal";
                } else if (crowdDensity <= 0.7) {
                    return "loud";
                } else {
                    return "very loud";
                }
            } else if (size >= 100) {
                if (crowdDensity <= 0.3) {
                    return "quiet";
                } else if (crowdDensity <= 0.7) {
                    return "normal";
                } else {
                    return "loud";
                }
            } else {
                if (crowdDensity <= 0.3) {
                    return "quiet";
                } else if (crowdDensity <= 0.7) {
                    return "normal";
                } else {
                    return "loud";
                }
            }
        }
    }

    public static class Crowd {
        private List<Person> people;
        private Building building;
        private Random random;

        public Crowd(Building building) {
            this.people = new ArrayList<>();
            this.building = building;
            this.random = new Random();
        }

        public void addPerson(Person person) {
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
            return building.determineMaxCrowdCapacity();
        }

        public Building getBuilding() {
            return building;
        }

        public void setBuilding(Building building) {
            this.building = building;

            while (people.size() > building.determineMaxCrowdCapacity()) {
                Person removedPerson = people.remove(people.size() - 1);
                System.out.println(
                        removedPerson.getName() +
                                " was removed because the new building capacity is smaller."
                );
            }
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

        public void changeMoodBasedOnSpeech(String speechType) {
            for (Person person : people) {
                person.reactToSpeech(speechType);
            }
        }
    }

    public static class Event {
        private Person speaker;
        private Crowd crowd;
        private Object stage;
        private Building building;

        public Event(Person speaker, Crowd crowd, Object stage, Building building) {
            this.speaker = speaker;
            this.crowd = crowd;
            this.stage = stage;
            this.building = building;

            if (crowd.getBuilding() != building) {
                crowd.setBuilding(building);
            }
        }

        public void startSpeech(String speechType) {
            String volume = calculateSpeechVolume();

            System.out.println(
                    speaker.getName() + " starts an " + speechType +
                            " speech in " + building.getName() +
                            " with a " + volume + " voice."
            );

            crowd.changeMoodBasedOnSpeech(speechType);

            for (Person person : crowd.getAllPeople()) {
                speaker.shareInfo(person);
            }
        }

        private String calculateSpeechVolume() {
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

        public Object getStage() {
            return stage;
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