package com.example.lab1_tos;

import java.util.ArrayList;
import java.util.List;

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

        public String greet(Person other) {
            this.shareInfo(other);
            return this.name + " greets " + other.getName() + " with a " + this.mood + " mood.";
        }
    }


    public static class Crowd {
        private List<Person> people;
        private static final int MAX_CAPACITY = 10;

        public Crowd() {
            this.people = new ArrayList<>();
        }

        public void addPerson(Person person) {
            if (people.size() < MAX_CAPACITY) {
                people.add(person);
            } else {
                System.out.println("Толпа достигла максимальной вместимости, не может добавить больше людей.");
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

        public void interact() {
            if (people.isEmpty()) {
                throw new IllegalArgumentException("No one to interact with");
            }
            Person firstPerson = people.get(0);
            Person lastPerson = people.get(people.size() - 1);
            System.out.println(firstPerson.getName() + " interacts with " + lastPerson.getName());
        }

        public void changeMoodBasedOnSpeech(String speechType) {
            for (Person person : people) {
                person.reactToSpeech(speechType);
            }
        }
    }

    public static class Building {
        private String name;
        private String location;

        public Building(String name, String location) {
            this.name = name;
            this.location = location;
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
    }

    public static class Object {
        private String name;
        private String location;
        private String type;

        public Object(String name, String location, String type) {
            this.name = name;
            this.location = location;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
        }

        public void startSpeech(String speechType) {
            System.out.println(speaker.getName() + " starts an " + speechType + " speech.");
            crowd.changeMoodBasedOnSpeech(speechType); // меняем настроение толпы в зависимости от речи
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

        public void setStage(Object stage) {
            this.stage = stage;
        }

        public Building getBuilding() {
            return building;
        }

        public void setBuilding(Building building) {
            this.building = building;
        }
    }
}