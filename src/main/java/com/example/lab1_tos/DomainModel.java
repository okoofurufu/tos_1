package com.example.lab1_tos;

import java.util.ArrayList;
import java.util.List;

public class DomainModel {

    public static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String greet(Person other) {
            return this.name + " greets " + other.getName();
        }
    }

    public static class Crowd {
        private List<Person> people;

        public Crowd() {
            this.people = new ArrayList<>();
        }

        public void addPerson(Person person) {
            people.add(person);
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
    }

    public static class Object {
        private String name;
        private String location;

        public Object(String name, String location) {
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

    public static class Event {
        private Person speaker;
        private Crowd crowd;
        private Object stage;

        public Event(Person speaker, Crowd crowd, Object stage) {
            this.speaker = speaker;
            this.crowd = crowd;
            this.stage = stage;
        }

        public Person getSpeaker() {
            return speaker;
        }

        public void setSpeaker(Person speaker) {
            this.speaker = speaker;
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
    }
}